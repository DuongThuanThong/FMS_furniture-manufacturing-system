package com.uth.fms.product.service;

import com.uth.fms.common.dto.PageResponse;
import com.uth.fms.product.dto.request.ProductTemplateRequest;
import com.uth.fms.product.dto.response.ProductTemplateResponse;
import org.springframework.data.domain.Pageable;

public interface ProductTemplateService {
    ProductTemplateResponse create(ProductTemplateRequest request);
    PageResponse<ProductTemplateResponse> findAll(String keyword, Pageable pageable);
    ProductTemplateResponse findById(Long id);
    ProductTemplateResponse update(Long id, ProductTemplateRequest request);
    void delete(Long id);
}