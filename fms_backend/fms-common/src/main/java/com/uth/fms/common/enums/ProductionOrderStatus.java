package com.uth.fms.common.enums;

public enum ProductionOrderStatus {
    PENDING,        // Lệnh SX mới tạo, chưa bắt đầu (chờ duyệt BOM hoặc bản vẽ)
    IN_PROGRESS,    // Đang sản xuất (ít nhất 1 task đã bắt đầu, chưa hoàn thành tất cả)
    COMPLETED,      // Tất cả các task đã hoàn thành và QC PASS
    CANCELLED       // Lệnh SX bị hủy giữa chừng (do KH hủy đơn hoặc revision)
}
