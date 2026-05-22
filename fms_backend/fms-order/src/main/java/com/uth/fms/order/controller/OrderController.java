package com.uth.fms.order.controller;

import com.uth.fms.common.dto.ApiResponse;
import com.uth.fms.common.dto.PageResponse;
import com.uth.fms.order.dto.request.OrderRequest;
import com.uth.fms.order.dto.response.OrderResponse;
import com.uth.fms.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> create(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(orderService.create(request), "Tạo đơn hàng thành công"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<OrderResponse>>> findAll(
            @RequestParam(required = false) String keyword,
            @PageableDefault(sort = "createTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(orderService.findAll(keyword, pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(orderService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequest request) {
        return ResponseEntity.ok(ApiResponse.success(orderService.update(id, request), "Cập nhật đơn hàng thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa đơn hàng thành công"));
    }
}