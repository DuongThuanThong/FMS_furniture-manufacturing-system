package com.uth.fms.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Filter;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusinessException extends RuntimeException {
    final String errorCode; //
    final int status;

    /**
     * Khởi tạo một ngoại lệ nghiệp vụ mới.
     *
     * @param status Mã lỗi HTTP tương ứng (vd: 400 cho lỗi yêu cầu sai).
     * @param errorCode Chuỗi ký tự định danh lỗi (vd: "PRODUCT_NOT_FOUND").
     * @param message Thông báo chi tiết về nguyên nhân lỗi để hiển thị cho người dùng.
     */
    public BusinessException(int status,String errorCode, String message) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }
}
