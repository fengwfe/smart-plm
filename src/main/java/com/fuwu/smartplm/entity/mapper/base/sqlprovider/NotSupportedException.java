package com.fuwu.smartplm.entity.mapper.base.sqlprovider;

public class NotSupportedException extends RuntimeException{
    public NotSupportedException(String msg){
        super(msg);
    }
}
