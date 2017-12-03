package com.zzz.enums;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
public enum RoomStatus {
    CAN_BOOK("CAN_BOOK", "可预订"),
    CAN_NOT_BOOK("CAN_NOT_BOOK", "不可预定"),
    CAN_NOT_USE("CAN_NOT_USE", "不可使用");

    private String code;
    private String name;

    RoomStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
