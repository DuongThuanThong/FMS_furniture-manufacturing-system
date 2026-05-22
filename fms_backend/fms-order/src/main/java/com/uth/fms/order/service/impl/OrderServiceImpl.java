package com.uth.fms.order.service.impl;

import com.uth.fms.common.dto.PageResponse;
import com.uth.fms.common.enums.OrderStatus;
import com.uth.fms.common.exception.BusinessException;
import com.uth.fms.order.dto.request.OrderRequest;
import com.uth.fms.order.dto.response.OrderResponse;
import com.uth.fms.order.entity.Order;
import com.uth.fms.order.entity.OrderItem;
import com.uth.fms.order.mapper.OrderMapper;
import com.uth.fms.order.repository.OrderRepository;
import com.uth.fms.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderProductValidatorImpl orderProductValidator;

    @Override
    @Transactional
    public OrderResponse create(OrderRequest request) {
        Order order = orderMapper.toEntity(request);
        order.setStatus(OrderStatus.PENDING);
        order.setDeleteFlag(false);

        if (request.getItems() != null) {
            List<OrderItem> items = request.getItems().stream()
                    .map(itemReq -> {
                        // Kiểm tra sản phẩm hợp lệ từ module product
                        orderProductValidator.validateProductForOrder(itemReq.getProductTemplateId());
                        
                        OrderItem item = orderMapper.toItemEntity(itemReq);
                        item.setOrder(order);
                        // Tính toán amount cho item
                        item.setAmount(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                        return item;
                    })
                    .collect(Collectors.toList());
            order.setItems(items);
            
            // Tính tổng tiền đơn hàng
            BigDecimal total = items.stream()
                    .map(OrderItem::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setTotalAmount(total);
            order.setFinalAmount(total);
        }

        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public PageResponse<OrderResponse> findAll(String keyword, Pageable pageable) {
        Page<Order> page = orderRepository.search(keyword, pageable);
        return PageResponse.from(page, orderMapper::toResponse);
    }

    @Override
    public OrderResponse findById(Long id) {
        return orderRepository.findByIdAndDeleteFlagFalse(id)
                .map(orderMapper::toResponse)
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.NOT_FOUND.value(),
                        "Không tìm thấy đơn hàng",
                        "ORDER_NOT_FOUND"
                ));
    }

    @Override
    @Transactional
    public OrderResponse update(Long id, OrderRequest request) {
        Order order = orderRepository.findByIdAndDeleteFlagFalse(id)
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.NOT_FOUND.value(),
                        "Không tìm thấy đơn hàng để cập nhật",
                        "ORDER_NOT_FOUND"
                ));

        // Chỉ cho phép cập nhật nếu đơn hàng ở trạng thái PENDING
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new BusinessException(
                    HttpStatus.BAD_REQUEST.value(),
                    "Chỉ có thể cập nhật đơn hàng ở trạng thái CHỜ XỬ LÝ",
                    "ORDER_UPDATE_NOT_ALLOWED"
            );
        }

        orderMapper.updateEntity(request, order);
        
        if (request.getItems() != null) {
            // Xóa items cũ và thay bằng items mới (Replace all pattern)
            order.getItems().clear();
            List<OrderItem> items = request.getItems().stream()
                    .map(itemReq -> {
                        orderProductValidator.validateProductForOrder(itemReq.getProductTemplateId());
                        OrderItem item = orderMapper.toItemEntity(itemReq);
                        item.setOrder(order);
                        item.setAmount(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                        return item;
                    })
                    .collect(Collectors.toList());
            order.getItems().addAll(items);

            BigDecimal total = items.stream()
                    .map(OrderItem::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setTotalAmount(total);
            order.setFinalAmount(total);
        }

        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Order order = orderRepository.findByIdAndDeleteFlagFalse(id)
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.NOT_FOUND.value(),
                        "Không tìm thấy đơn hàng để xóa",
                        "ORDER_NOT_FOUND"
                ));

        // Soft delete
        order.setDeleteFlag(true);
        orderRepository.save(order);
    }
}