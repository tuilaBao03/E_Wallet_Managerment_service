package com.backend.smartwalletapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.backend.smartwalletapp.dto.response.ApiResponse;


@ControllerAdvice
public class GlobalHandlerException {

    @SuppressWarnings("rawtypes")
    private ResponseEntity<ApiResponse> buildResponse(int code, String message) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        return ResponseEntity.badRequest().body(apiResponse);
    }
    
    @SuppressWarnings({"rawtypes"})
    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
        if (e instanceof AppException appException) {
            System.err.println("appException");
            return handleAppException(appException); // Delegate
        }
        System.err.println("appException____");
        return buildResponse(ErrorCode.ERROR_NOT_FOUND.getCode(), e.getMessage());
    }
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return buildResponse(ErrorCode.ERROR_NOT_FOUND.getCode(), e.getMessage());
    }
    
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException e) {
        System.err.println("appException :"+e.getMessage());
        return buildResponse(e.getErrorCode().getCode(), e.getMessage());
    }
    
}
