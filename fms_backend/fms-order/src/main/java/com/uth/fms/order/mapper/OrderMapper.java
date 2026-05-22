package com.uth.fms.order.mapper;

import com.uth.fms.common.mapper.MapperConfig;
import com.uth.fms.order.dto.request.OrderItemRequest;
import com.uth.fms.order.dto.request.OrderRequest;
import com.uth.fms.order.dto.response.OrderItemResponse;
import com.uth.fms.order.dto.response.OrderResponse;
import com.uth.fms.order.entity.Order;
import com.uth.fms.order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    @Mapping(target = "orderCode", source = "code")
    @Mapping(target = "deliveryDate", source = "expectedDeliveryDate")
    OrderResponse toResponse(Order order);

    @Mapping(target = "note", source = "customNote")
    OrderItemResponse toItemResponse(OrderItem item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "code", source = "orderCode")
    Order toEntity(OrderRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "amount", ignore = true)
    @Mapping(target = "bomType", constant = "NORMAL")
    OrderItem toItemEntity(OrderItemRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    void updateEntity(OrderRequest request, @MappingTarget Order order);
}