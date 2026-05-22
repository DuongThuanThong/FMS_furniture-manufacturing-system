package com.uth.fms.product.service.impl;

import com.uth.fms.common.dto.PageResponse;
import com.uth.fms.common.exception.BusinessException;
import com.uth.fms.product.dto.request.ProductTemplateRequest;
import com.uth.fms.product.dto.response.ProductTemplateResponse;
import com.uth.fms.product.entity.ProductBom;
import com.uth.fms.product.entity.ProductTemplate;
import com.uth.fms.product.mapper.ProductTemplateMapper;
import com.uth.fms.product.repository.ProductTemplateRepository;
import com.uth.fms.product.service.ProductTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductTemplateServiceImpl implements ProductTemplateService {

    private final ProductTemplateRepository productTemplateRepository;
    private final ProductTemplateMapper productTemplateMapper;

    @Override
    @Transactional
    public ProductTemplateResponse create(ProductTemplateRequest request) {
        // Kiểm tra mã code trùng
        if (productTemplateRepository.existsByCodeAndIsDeletedFalse(request.getCode())) {
            throw new BusinessException(
                    HttpStatus.BAD_REQUEST.value(),
                    "Mã sản phẩm đã tồn tại",
                    "PRODUCT_CODE_EXISTS"
            );
        }

        ProductTemplate template = productTemplateMapper.toEntity(request);
        
        // Xử lý BOM
        if (request.getBoms() != null) {
            List<ProductBom> boms = request.getBoms().stream()
                    .map(bomReq -> {
                        ProductBom bom = productTemplateMapper.toBomEntity(bomReq);
                        bom.setProductTemplate(template);
                        return bom;
                    })
                    .collect(Collectors.toList());
            template.setBoms(boms);
        }

        template.setDeleteFlag(false); // Khởi tạo giá trị cho soft delete
        return productTemplateMapper.toResponse(productTemplateRepository.save(template));
    }

    @Override
    public PageResponse<ProductTemplateResponse> findAll(String keyword, Pageable pageable) {
        Page<ProductTemplate> page = productTemplateRepository.search(keyword, pageable);
        return PageResponse.from(page, productTemplateMapper::toResponse);
    }

    @Override
    public ProductTemplateResponse findById(Long id) {
        return productTemplateRepository.findByIdAndIsDeletedFalse(id)
                .map(productTemplateMapper::toResponse)
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.NOT_FOUND.value(),
                        "Không tìm thấy mẫu sản phẩm",
                        "PRODUCT_TEMPLATE_NOT_FOUND"
                ));
    }

    @Override
    @Transactional
    public ProductTemplateResponse update(Long id, ProductTemplateRequest request) {
        ProductTemplate template = productTemplateRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.NOT_FOUND.value(),
                        "Không tìm thấy mẫu sản phẩm để cập nhật",
                        "PRODUCT_TEMPLATE_NOT_FOUND"
                ));

        // Kiểm tra code nếu có thay đổi
        if (request.getCode() != null && !request.getCode().equals(template.getCode())) {
            if (productTemplateRepository.existsByCodeAndIsDeletedFalse(request.getCode())) {
                throw new BusinessException(
                        HttpStatus.BAD_REQUEST.value(),
                        "Mã sản phẩm mới đã tồn tại",
                        "PRODUCT_CODE_EXISTS"
                );
            }
            template.setCode(request.getCode());
        }

        productTemplateMapper.updateEntity(request, template);

        // Xử lý thay thế toàn bộ BOM (Replace all)
        if (request.getBoms() != null) {
            List<ProductBom> newBoms = request.getBoms().stream()
                    .map(bomReq -> {
                        ProductBom bom = productTemplateMapper.toBomEntity(bomReq);
                        bom.setProductTemplate(template);
                        return bom;
                    })
                    .collect(Collectors.toList());
            template.setBoms(newBoms);
        }

        return productTemplateMapper.toResponse(productTemplateRepository.save(template));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ProductTemplate template = productTemplateRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.NOT_FOUND.value(),
                        "Không tìm thấy mẫu sản phẩm để xóa",
                        "PRODUCT_TEMPLATE_NOT_FOUND"
                ));

        // Business Logic: Có thể kiểm tra xem template có đang được sử dụng trong Order không
        // Tạm thời chỉ soft delete
        template.setDeleteFlag(true);
        productTemplateRepository.save(template);
    }
}