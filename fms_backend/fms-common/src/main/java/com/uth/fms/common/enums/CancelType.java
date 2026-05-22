package com.uth.fms.common.enums;

public enum CancelType {
    FACTORY_ERROR,      // Lỗi do xưởng (không sản xuất kịp, sai thiết kế) → cần bồi thường
    CUSTOMER_REQUEST    // Khách hàng chủ động hủy (thay đổi nhu cầu, không muốn mua nữa)
}
