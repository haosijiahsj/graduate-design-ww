package com.zzz.enums;

/**
 * Created by  on 12/2 0002.
 */
public enum RoomType {
    SINGLE_ROOM("SINGLE_ROOM", "单间"),
    STANDARD_ROOM("STANDARD_ROOM", "双人间"),
    BIG_BED_ROOM("BIG_BED_ROOM", "大床房");

    private String code;
    private String name;

    RoomType(String code, String name) {
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
