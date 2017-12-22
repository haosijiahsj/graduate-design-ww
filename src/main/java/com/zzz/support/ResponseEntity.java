package com.zzz.support;

import lombok.*;
import org.springframework.http.HttpMessage;

import java.io.Serializable;

/**
 * controller层统一返回的实体
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ResponseEntity implements Serializable {

    private static final long serialVersionUID = 9064531997679501400L;

    private int msgCode;
    private String msgContent;
    private Object result;

    public ResponseEntity(ResponseStatus status) {
        this.msgCode = status.getCode();
        this.msgContent = status.getCodeMsg();
    }

    public ResponseEntity(ResponseStatus status, Object result) {
        this.msgCode = status.getCode();
        this.msgContent = status.getCodeMsg();
        this.result = result;
    }

    /**
     * 请求成功，并返回查询结果
     * @param result
     * @return
     */
    public static ResponseEntity successRequest(Object result) {
        return new ResponseEntity(ResponseStatus.SUCCESS, result);
    }

    /**
     * 请求成功，无结果返回（保存，更新，或者调用没有返回值的方法）
     * @return
     */
    public static ResponseEntity successRequest() {
        return new ResponseEntity(ResponseStatus.SUCCESS);
    }

    /**
     * 请求成功，（查询无结果）
     * @return
     */
    public static ResponseEntity successRequestButEmptyResult() {
        return new ResponseEntity(ResponseStatus.SUCCESS_EMPTY_RESULT);
    }

    /**
     * 请求失败，接口报异常
     * @param msgContent
     * @return
     */
    public static ResponseEntity failedRequest(String msgContent) {
        return ResponseEntity.builder()
                .msgCode(ResponseStatus.FAILED.getCode())
                .msgContent(msgContent)
                .build();
    }

    /**
     * 请求失败，不合法参数
     * @return
     */
    public static ResponseEntity failedRequestIllegalParam() {
        return new ResponseEntity(ResponseStatus.ILLEGAL_PARAM);
    }

    /**
     * 请求失败，不合法参数
     * @return
     */
    public static ResponseEntity failedRequestIllegalParam(Object param) {
        return ResponseEntity.builder()
                .msgCode(ResponseStatus.ILLEGAL_PARAM.getCode())
                .msgContent(String.format("illegal param for: %s", param))
                .build();
    }

}
