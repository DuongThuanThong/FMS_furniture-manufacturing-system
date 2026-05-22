package com.uth.fms.common.enums;

public enum ApprovalStatus {
    PENDING,    // Bản vẽ mới upload, chờ TP SX duyệt
    APPROVED,   // TP SX duyệt, có thể bắt đầu sản xuất
    REJECTED    // TP SX từ chối, yêu cầu Designer sửa lại (phiên bản mới)
}
