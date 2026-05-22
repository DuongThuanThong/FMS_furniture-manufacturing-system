package com.uth.fms.common.mapper;

import org.mapstruct.Builder;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.MapperConfig(
        componentModel = MappingConstants.ComponentModel.SPRING, // Tự động biến Mapper thành Spring Bean (@Component)
        unmappedTargetPolicy = ReportingPolicy.IGNORE,          // Nếu thiếu trường thì bỏ qua, không báo lỗi Build
        builder = @Builder(disableBuilder = true)               // Tắt builder của MapStruct để dùng Constructor/Setter của mình
)
public interface MapperConfig {
}