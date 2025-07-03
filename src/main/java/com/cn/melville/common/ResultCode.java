package com.cn.melville.common;

import lombok.Getter;

@Getter
public enum  ResultCode {
    SUCCESS(200, "操作成功"),
    ERROR(500, "服务器内部错误"),
    NOT_FOUND(404, "资源未找到"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    BAD_REQUEST(400, "错误的请求参数"),
    VALIDATION_FAILED(422, "参数验证失败"),
    INTERNAL_SERVER_ERROR(500, "内部服务器错误");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
