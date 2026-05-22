package com.uth.fms.common.enums;

public enum ResolveType {
    SELF_FIX,   // TP SX tự điều chỉnh (thay đổi kế hoạch, phân công lại) mà không cần lên cấp
    ESCALATE,   // Leo thang lên GĐ hoặc Sale để thương lượng với KH về thiết kế
    CANCEL      // Hủy đơn hàng hoặc lệnh SX do không thể giải quyết
}
