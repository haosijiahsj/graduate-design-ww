package com.zzz.support;

/**
 * Created by on 2017/11/2.
 */
public enum ResponseStatus {

    SUCCESS(200, "success"),
    SUCCESS_EMPTY_RESULT(300, "empty result"),
    FAILED(400, "failed"),
    ILLEGAL_PARAM(401, "illegal param");

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
