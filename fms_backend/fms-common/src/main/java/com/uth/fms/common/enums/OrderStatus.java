package com.uth.fms.common.enums;

public enum OrderStatus {
    CONFIRMED,          // Đã chốt đơn (Bắt đầu vòng đời đơn hàng)
    WAITING_DESIGN,     // Chờ thợ vẽ (Nếu có custom)
    IN_PRODUCTION,      // Đang kẹt dưới xưởng sản xuất
    WAITING_DELIVERY,   // SX xong, nhập kho chờ giao
    DELIVERING,         // Đang trên xe giao hàng
    COMPLETED,          // Hoàn thành, đã thu đủ tiền
    CANCELLED           // Đã hủy (Kèm lý do)
}
