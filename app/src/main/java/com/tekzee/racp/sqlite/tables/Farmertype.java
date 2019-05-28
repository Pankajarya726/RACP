package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class Farmertype extends SugarRecord {


    private Integer farmertypeId;

    private String farmertypeName;

    public Farmertype() {
    }

    public Farmertype(Integer farmertypeId, String farmertypeName) {
        super();
        this.farmertypeId = farmertypeId;
        this.farmertypeName = farmertypeName;
    }

    public Integer getFarmertypeId() {
        return farmertypeId;
    }

    public void setFarmertypeId(Integer farmertypeId) {
        this.farmertypeId = farmertypeId;
    }

    public String getFarmertypeName() {
        return farmertypeName;
    }

    public void setFarmertypeName(String farmertypeName) {
        this.farmertypeName = farmertypeName;
    }

}