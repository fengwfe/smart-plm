package com.fuwu.smartplm.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class InsertDTO implements Serializable {
    private Long id;
    private String tableName;
    private Map<String, Object> valuesMap;
}
