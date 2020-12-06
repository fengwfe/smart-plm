package com.fuwu.smartplm.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;
@Data
public class UpdateDTO implements Serializable {
    private Long id;
    private Long version = 99l;
    private String tableName;
    private Map<String, Object> valuesMap;
}
