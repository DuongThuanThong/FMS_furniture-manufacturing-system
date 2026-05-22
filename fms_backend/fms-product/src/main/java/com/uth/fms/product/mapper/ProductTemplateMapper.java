package com.uth.fms.product.mapper;

import com.uth.fms.common.mapper.MapperConfig;
import com.uth.fms.product.dto.request.ProductBomRequest;
import com.uth.fms.product.dto.request.ProductTemplateRequest;
import com.uth.fms.product.dto.response.ProductBomResponse;
import com.uth.fms.product.dto.response.ProductTemplateResponse;
import com.uth.fms.product.entity.ProductBom;
import com.uth.fms.product.entity.ProductTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface ProductTemplateMapper {

    @Mapping(target = "status", expression = "java(template.getIsActive() ? \"ACTIVE\" : \"INACTIVE\")")
    ProductTemplateResponse toResponse(ProductTemplate template);

    ProductBomResponse toBomResponse(ProductBom bom);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "boms", ignore = true) // Sẽ xử lý thủ công trong service để set parent
    ProductTemplate toEntity(ProductTemplateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productTemplate", ignore = true)
    ProductBom toBomEntity(ProductBomRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true) // Code thường không được đổi hoặc check unique riêng
    @Mapping(target = "boms", ignore = true)
    void updateEntity(ProductTemplateRequest request, @MappingTarget ProductTemplate template);
}