package com.uth.fms.common.enums;

public enum NotificationType {
    TASK_ASSIGNED,      // Phân công task mới cho Tổ trưởng/Thợ vẽ
    BLOCKED,            // Báo cáo tắc nghẽn (thiếu gỗ, sai kích thước)
    QC_FAILED,          // QC không đạt, yêu cầu làm lại
    ORDER_APPROVED,     // Báo giá/Đơn hàng đã được duyệt
    STOCK_LOW,          // Cảnh báo tồn kho dưới mức tối thiểu
    DEADLINE_WARNING    // Cảnh báo đơn hàng sắp trễ hạn
}