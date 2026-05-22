package com.uth.fms.common.enums;

public enum PriceStatus {
    FIXED,      // Giá chốt cứng (Hàng có sẵn/BOM chuẩn)
    ESTIMATED   // Giá tạm tính (Hàng thiết kế riêng, phải chờ chốt BOM mới ra final_amount)
}
