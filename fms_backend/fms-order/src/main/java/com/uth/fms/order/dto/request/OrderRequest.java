package com.uth.fms.order.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    @NotBlank(message = "Mã đơn hàng không được để trống")
    String orderCode;

    @NotNull(message = "ID khách hàng không được để trống")
    Long customerId;

    java.time.LocalDate orderDate;
    java.time.LocalDate deliveryDate;
    String note;

    @NotEmpty(message = "Đơn hàng phải có ít nhất một sản phẩm")
    @Valid
    List<OrderItemRequest> items;
}