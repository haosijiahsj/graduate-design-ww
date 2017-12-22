package com.zzz.support;

/**
 * controller返回实体的状态枚举
 */
public enum ResponseStatus {

    SUCCESS(200, "success"),
    SUCCESS_EMPTY_RESULT(300, "empty result"),
    FAILED(400, "failed"),
    ILLEGAL_PARAM(401, "illegal param"),
    FAILED_LOGIN(405, "no login");

    private int code;
    private String codeMsg;

    ResponseStatus(int code, String codeMsg) {
        this.code = code;
        this.codeMsg = codeMsg;
    }

    public int getCode() {
        return code;
    }

    public String getCodeMsg() {
        return codeMsg;
    }
}
