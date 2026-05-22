package com.uth.fms.common.exception;

import com.uth.fms.common.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /* Xử lý các ngoại lệ nghiệp vụ
     *Ví dụ: Khi kiểm tra kho thấy thiếu gỗ hoặc đơn hàng đã hủy không thể cập nhật
     * @param status Mã lỗi HTTP tương ứng (vd: 400 cho lỗi yêu cầu sai).
     * @param errorCode Chuỗi ký tự định danh lỗi (vd: "PRODUCT_NOT_FOUND").
     * @param message Thông báo chi tiết về nguyên nhân lỗi để hiển thị cho người dùng.
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleException(BusinessException exception) {
        ErrorResponse error = ErrorResponse.builder()
                                           .status(exception.getStatus())
                                           .message(exception.getMessage())
                                           .details(exception.getErrorCode())
                                           .build();
        return ResponseEntity.status(exception.getStatus())
                             .body(error);
    }

    /*
     * Xữ lý lỗi không có quyền truy cập 403
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleException(AccessDeniedException exception) {
        ErrorResponse error = ErrorResponse.builder()
                                           .status(HttpStatus.FORBIDDEN.value())
                                           .message("Không có quyền truy cập")
                                           .details("ACCESS_DENIED")
                                           .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                             .body(error);
    }

    /*
     * Xử lý lỗi sai phương thức HTTP
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        ErrorResponse error = ErrorResponse.builder()
                                           .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                                           .message("Phương thức HTTP không được hỗ trợ: " + ex.getMethod())
                                           .details("METHOD_NOT_ALLOWED")
                                           .build();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                             .body(error);
    }

    /*
     * Xử lý các lỗi xác thực dữ liệu đầu vào
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult()
                 .getFieldErrors()
                 .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponse error = ErrorResponse.builder()
                                           .status(HttpStatus.BAD_REQUEST.value())
                                           .message("Dữ liệu đầu vào không hợp lệ")
                                           .details(errors) // Quăng cục Map vào đây
                                           .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(error);
    }

    /*
     * Xử lý tất cả các ngoại lệ không xác định hoặc các lỗi Runtime 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        log.error("Internal Server Error: ", ex);

        ErrorResponse error = ErrorResponse.builder()
                                           .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                           .message("Lỗi hệ thống")
                                           .details(ex.getMessage())
                                           .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(error);
    }
}
