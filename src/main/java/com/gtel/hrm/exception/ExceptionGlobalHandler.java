package com.gtel.hrm.exception;


import com.gtel.hrm.dto.request.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//
@ControllerAdvice
public class ExceptionGlobalHandler {
//    @ExceptionHandler(value = Exception.class)
//    ResponseEntity<ApiResponse> handlerRuntimeException(RuntimeException exception) {
//        ApiResponse apiResponse = new ApiResponse();
//
//        apiResponse.setMessage(ErrorCode.UNCATEGORIEZ_EXCEPTION.getMessage());
//        apiResponse.setCode(ErrorCode.UNCATEGORIEZ_EXCEPTION.getCode());
//
//        return ResponseEntity.badRequest().body(apiResponse);
//    }
//
//
//    @ExceptionHandler(value = RuntimeException.class)
//    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
//        ApiResponse apiResponse = new ApiResponse();
//
//        apiResponse.setCode(1001);
//        apiResponse.setMessage(exception.getMessage());
//
//        return ResponseEntity.badRequest().body(apiResponse);
//    }
//
//    @ExceptionHandler(value = AppException.class)
//    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
//        ErrorCode errorCode = exception.getErrorCode();
//        ApiResponse apiResponse = new ApiResponse();
//
//        apiResponse.setCode(errorCode.getCode());
//        apiResponse.setMessage(errorCode.getMessage());
//
//        return ResponseEntity.badRequest().body(apiResponse);
//    }
//
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    ResponseEntity<ApiResponse> handlervalidation(MethodArgumentNotValidException exception){
//        String enumkey = exception.getFieldError().getDefaultMessage();
//        ErrorCode errorCode = ErrorCode.valueOf(enumkey);
//
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setCode(errorCode.getCode());
//        apiResponse.setMessage(errorCode.getMessage());
//        return ResponseEntity.badRequest().body(apiResponse);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handlerException(Exception exception) {
        ApiResponse<String> apiResponse = new ApiResponse<>(false, ErrorCode.UNCATEGORIEZ_EXCEPTION.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> handlingRuntimeException(RuntimeException exception) {
        ApiResponse<String> apiResponse = new ApiResponse<>(false, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<String>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse<String> apiResponse = new ApiResponse<>(false, errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getStatusCode()).body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidation(MethodArgumentNotValidException exception) {
        if (exception.getFieldError() != null) {
            String enumKey = exception.getFieldError().getDefaultMessage();
            ErrorCode errorCode;
            try {
                errorCode = ErrorCode.valueOf(enumKey);
            } catch (IllegalArgumentException e) {
                errorCode = ErrorCode.UNCATEGORIEZ_EXCEPTION; // Giá trị mặc định nếu không tìm thấy key
            }

            ApiResponse<String> apiResponse = new ApiResponse<>(false, errorCode.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, "Validation error"));
    }
}
