package com.uth.fms.order.service.impl;

import com.uth.fms.common.exception.BusinessException;
import com.uth.fms.order.repository.ProductTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProductValidatorImpl {

    private final ProductTemplateRepository productTemplateRepository;

    /**
     * Kiểm tra mẫu sản phẩm có hợp lệ để đưa vào đơn hàng hay không
     */
    public void validateProductForOrder(Long productTemplateId) {
        productTemplateRepository.findByIdAndDeleteFlagFalse(productTemplateId)
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.NOT_FOUND.value(),
                        "Mẫu sản phẩm không tồn tại hoặc đã bị ngừng kinh doanh",
                        "PRODUCT_TEMPLATE_INVALID"
                ));
    }
}