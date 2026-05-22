package com.uth.fms.common.enums;

public enum TransactionType {
    RECEIVE,    // Nhập kho từ nhà cung cấp
    ISSUE,      // Xuất kho cho sản xuất
    RETURN,     // Trả lại kho từ sản xuất
    WASTE,      // Hao hụt, mất mát
    RESERVE     // Đặt giữ hàng cho lệnh SX
}
