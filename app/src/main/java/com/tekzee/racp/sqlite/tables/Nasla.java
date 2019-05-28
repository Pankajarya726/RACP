package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class Nasla extends SugarRecord {

    private Integer naslaId;
    private String naslaName;

    /**
     * No args constructor for use in serialization
     */
    public Nasla() {
    }

    /**
     * @param naslaId
     * @param naslaName
     */
    public Nasla(Integer naslaId, String naslaName) {
        super();
        this.naslaId = naslaId;
        this.naslaName = naslaName;
    }

    public Integer getNaslaId() {
        return naslaId;
    }

    public void setNaslaId(Integer naslaId) {
        this.naslaId = naslaId;
    }

    public String getNaslaName() {
        return naslaName;
    }

    public void setNaslaName(String naslaName) {
        this.naslaName = naslaName;
    }

}