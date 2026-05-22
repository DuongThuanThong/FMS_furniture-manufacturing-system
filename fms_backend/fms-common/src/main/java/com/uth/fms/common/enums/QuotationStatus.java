package com.uth.fms.common.enums;

public enum QuotationStatus {
    DRAFT,              // Đang nháp
    PENDING_APPROVAL,   // Chờ GĐ duyệt (Đơn > ngưỡng)
    APPROVED,           // GĐ đã duyệt
    REJECTED,           // GĐ từ chối
    SENT,               // Đã gửi cho khách hàng
    ACCEPTED,           // Khách chốt/Đồng ý giá
    CONVERTED           // Đã chuyển thành Đơn hàng (Kết thúc vòng đời báo giá)
}
