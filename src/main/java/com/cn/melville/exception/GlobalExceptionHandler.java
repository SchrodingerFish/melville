package com.cn.melville.exception;

import com.cn.melville.common.Result;
import com.cn.melville.common.ResultCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 处理所有未捕获的 Exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handleException(Exception e, HttpServletRequest request) {
        log.error("Unhandled exception: {} - {}", request.getRequestURI(), e.getMessage(), e);
        return Result.error(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "Internal Server Error: " + e.getMessage());
    }
    // 处理自定义异常
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.warn("Business exception: {} - {}", request.getRequestURI(), e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }
    // 处理参数验证失败异常 (例如 @Valid 注解)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("Validation exception: {} - {}", request.getRequestURI(), e.getMessage());
        String errorMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage(); // 获取第一个错误信息
        return Result.error(ResultCode.BAD_REQUEST.getCode(), "Validation failed: " + errorMessage);
    }
}
