package com.uth.fms.common.enums;

public enum PurchaseRequestStatus {
    PENDING,    // Yêu cầu mua hàng mới tạo, chờ duyệt
    APPROVED,   // Đã được duyệt, có thể tiến hành đặt hàng nhà cung cấp
    ORDERED,    // Đã đặt hàng với nhà cung cấp (có thể có actual_price)
    RECEIVED,   // Nhà cung cấp đã giao hàng (đang chờ nhập kho)
    COMPLETED   // Đã nhập kho xong, kết thúc yêu cầu
}
