package com.fuwu.smartplm.dto;

public enum APIStatus {
    SUCCESS("success"),
    FAILED("failed");
    String name;

    APIStatus(String name){
        this.name = name;
    }
}
