package com.fuwu.smartplm.dto;

public enum ApiCode {
    SUCCESS("200", "操作成功"),
    FAILED("500", "操作失败"),
    INVALID_PARAM("10001", "参数错误"),
    OPTIMISTIC_LOCK_ERROR("10002", "该记录已被修改");
    String code;
    String msg;
    ApiCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }



}
