package com.fuwu.smartplm.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApiResponse<T> implements Serializable {
    private String status;
    private String code;
    private List messages;
    private T result;

    public static ApiResponse success(Object result, List messages){
        ApiResponse response = new ApiResponse();
        response.status = APIStatus.SUCCESS.name;
        response.code = ApiCode.SUCCESS.code;
        response.result = result;
        response.messages = messages;
        return response;
    }
    public static ApiResponse failed(List messages){
        ApiResponse response = new ApiResponse();
        response.status = APIStatus.FAILED.name;
        response.code = ApiCode.FAILED.code;
        response.messages = messages;
        return response;
    }
    public static ApiResponse failed(List messages, String code){
        ApiResponse response = new ApiResponse();
        response.status = APIStatus.FAILED.name;
        response.code = code;
        response.messages = messages;
        return response;
    }

}
