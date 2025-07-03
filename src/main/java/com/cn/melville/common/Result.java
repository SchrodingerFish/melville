package com.cn.melville.common;

import lombok.Data;

import java.io.Serializable;



@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String message;
    private T data;
    private boolean success;

    public Result() {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
        this.success = true;
    }
    public Result(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
        this.data = data;
        this.success = true;
    }
    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.success = false;
    }
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = false;
    }
    public static <T> Result<T> success() {
        return new Result<>();
    }
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }
    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message);
    }
    public static <T> Result<T> error(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

}
