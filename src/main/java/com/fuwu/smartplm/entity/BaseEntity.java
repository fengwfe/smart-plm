package com.fuwu.smartplm.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseEntity implements Serializable {
    protected Long id;
    protected Long version;
    protected String deleted;
    protected Date created;
    protected Long createdBy = 0l;
    protected Date updated;
    protected Long updatedBy = 0l;

}
