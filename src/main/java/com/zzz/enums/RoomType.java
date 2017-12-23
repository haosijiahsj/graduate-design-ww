package com.zzz.enums;

/**
 * Created by on 12/2 0002.
 */
public enum RoomType {
    SINGLE_ROOM("SINGLE_ROOM", "商务单间"),
    STANDARD_ROOM("STANDARD_ROOM", "商务标间"),
    DELUXE_SINGLE_ROOM("DELUXE_SINGLE_ROOM", "豪华单间"),
    DELUXE_STANDARD_ROOM("DELUXE_STANDARD_ROOM", "豪华标间"),
    MEDITERRANEAN_SEA_ROOM("MEDITERRANEAN_SEA_ROOM", "地中海风情"),
    LOVE_SEA_ROOM("LOVE_SEA_ROOM", "爱情海风情"),
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
