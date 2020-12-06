package com.fuwu.smartplm.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MetaNode extends BaseEntity{
    private String label;
    private String apiCode;
    private Long parentId;
    private Long seq;
    private String description;
}
