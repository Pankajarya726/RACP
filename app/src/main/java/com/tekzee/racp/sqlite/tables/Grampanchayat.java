package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class Grampanchayat extends SugarRecord {


    private Integer grampanchayatId;

    private String grampanchayatName;

    private Integer tahsilId;


    public Grampanchayat() {
    }

    public Grampanchayat(Integer grampanchayatId, String grampanchayatName, Integer tahsilId) {
        super();
        this.grampanchayatId = grampanchayatId;
        this.grampanchayatName = grampanchayatName;
        this.tahsilId = tahsilId;
    }

    public Integer getGrampanchayatId() {
        return grampanchayatId;
    }

    public void setGrampanchayatId(Integer grampanchayatId) {
        this.grampanchayatId = grampanchayatId;
    }

    public String getGrampanchayatName() {
        return grampanchayatName;
    }

    public void setGrampanchayatName(String grampanchayatName) {
        this.grampanchayatName = grampanchayatName;
    }

    public Integer getTahsilId() {
        return tahsilId;
    }

    public void setTahsilId(Integer tahsilId) {
        this.tahsilId = tahsilId;
    }

}