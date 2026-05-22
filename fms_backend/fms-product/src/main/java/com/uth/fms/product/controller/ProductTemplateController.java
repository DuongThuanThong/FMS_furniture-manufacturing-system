package com.uth.fms.product.controller;

import com.uth.fms.common.dto.ApiResponse;
import com.uth.fms.common.dto.PageResponse;
import com.uth.fms.product.dto.request.ProductTemplateRequest;
import com.uth.fms.product.dto.response.ProductTemplateResponse;
import com.uth.fms.product.service.ProductTemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-templates")
@RequiredArgsConstructor
public class ProductTemplateController {

    private final ProductTemplateService productTemplateService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductTemplateResponse>> create(
            @Valid @RequestBody ProductTemplateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(productTemplateService.create(request), "Tạo mẫu sản phẩm thành công"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ProductTemplateResponse>>> findAll(
            @RequestParam(required = false) String keyword,
            @PageableDefault(sort = "createTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(productTemplateService.findAll(keyword, pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductTemplateResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(productTemplateService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductTemplateResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductTemplateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(productTemplateService.update(id, request), "Cập nhật thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productTemplateService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thành công"));
    }
}