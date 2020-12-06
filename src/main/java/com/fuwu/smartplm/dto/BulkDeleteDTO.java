package com.fuwu.smartplm.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class BulkDeleteDTO implements Serializable {
    private String tableName;
    List<Long> ids;
}
