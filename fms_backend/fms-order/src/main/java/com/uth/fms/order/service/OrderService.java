package com.uth.fms.order.service;

import com.uth.fms.common.dto.PageResponse;
import com.uth.fms.order.dto.request.OrderRequest;
import com.uth.fms.order.dto.response.OrderResponse;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse create(OrderRequest request);
    PageResponse<OrderResponse> findAll(String keyword, Pageable pageable);
    OrderResponse findById(Long id);
    OrderResponse update(Long id, OrderRequest request);
    void delete(Long id);
}