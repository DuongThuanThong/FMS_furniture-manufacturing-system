package com.uth.fms.common.enums;

public enum PaymentType {
    DEPOSIT,        // Tiền đặt cọc giữ đơn hàng (thường 30-50%)
    DESIGN_FEE,     // Phí thiết kế riêng cho sản phẩm custom (1-2 triệu)
    FINAL_PAYMENT,  // Thanh toán phần còn lại khi giao hàng
    REFUND          // Hoàn tiền cho khách (khi hủy đơn hoặc trả hàng)
}
