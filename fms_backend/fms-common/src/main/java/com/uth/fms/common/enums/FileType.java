package com.uth.fms.common.enums;

public enum FileType {
    DESIGN_DRAWING,     // Bản vẽ kỹ thuật (PDF) do Designer upload
    QC_PHOTO,           // Ảnh sản phẩm sau khi hoàn thành task do Tổ trưởng upload
    REVISION_DRAWING    // Bản vẽ sửa đổi sau khi QC REJECTED hoặc KH yêu cầu thay đổi
}
