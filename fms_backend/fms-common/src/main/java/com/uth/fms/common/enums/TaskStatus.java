package com.uth.fms.common.enums;

public enum TaskStatus {
    PENDING,        // Chờ tới lượt (task trước chưa hoàn thành và PASS QC)
    IN_PROGRESS,    // Đang thực hiện (người được giao đã bấm bắt đầu)
    COMPLETED,      // Đã hoàn thành công việc, chờ QC
    REJECTED,       // QC FAIL, phải làm lại (task sẽ quay về IN_PROGRESS sau khi sửa)
    BLOCKED         // Bị tắc nghẽn (thiếu bản vẽ, thiếu NVL, thiết kế không khả thi)
}
