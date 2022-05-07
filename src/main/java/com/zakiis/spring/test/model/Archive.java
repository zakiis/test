package com.zakiis.spring.test.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Table name: archive
 * 2022-04-13 11:05:16
 */
public class Archive {
    private Long id;

    private BigDecimal field2;

    private Date addTime;

    private Date addTime2;

    private Date addTime3;

    private String field1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getField2() {
        return field2;
    }

    public void setField2(BigDecimal field2) {
        this.field2 = field2;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getAddTime2() {
        return addTime2;
    }

    public void setAddTime2(Date addTime2) {
        this.addTime2 = addTime2;
    }

    public Date getAddTime3() {
        return addTime3;
    }

    public void setAddTime3(Date addTime3) {
        this.addTime3 = addTime3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }
}