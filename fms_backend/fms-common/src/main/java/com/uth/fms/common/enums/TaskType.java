package com.uth.fms.common.enums;

public enum TaskType {
    DESIGN,         // Công đoạn thiết kế bản vẽ (giao cho DESIGNER)
    CUT,            // Công đoạn cắt gỗ (giao cho TEAM_LEADER)
    ASSEMBLE,       // Lắp ráp (giao cho TEAM_LEADER)
    PAINT,          // Sơn / phủ bề mặt (giao cho TEAM_LEADER)
    FINISH          // Hoàn thiện, đóng gói (giao cho TEAM_LEADER)
}
