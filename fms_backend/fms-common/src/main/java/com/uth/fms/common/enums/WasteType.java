package com.uth.fms.common.enums;

public enum WasteType {
    QC_FAIL,            // Hao hụt do làm lại sản phẩm (lỗi thợ)
    PRODUCTION_ERROR,   // Hao hụt do cắt sai, gia công sai (không do QC)
    REVISION,           // Hao hụt do khách hàng đổi thiết kế, phần đã làm phải bỏ
    CANCEL              // Hao hụt do hủy đơn hàng giữa chừng
}
