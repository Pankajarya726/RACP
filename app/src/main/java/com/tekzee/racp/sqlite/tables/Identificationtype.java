package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class Identificationtype extends SugarRecord {


    private Integer identificationtypeId;

    private String identificationtypeName;

    public Identificationtype() {
    }

    public Identificationtype(Integer identificationtypeId, String identificationtypeName) {
        super();
        this.identificationtypeId = identificationtypeId;
        this.identificationtypeName = identificationtypeName;
    }

    public Integer getIdentificationtypeId() {
        return identificationtypeId;
    }

    public void setIdentificationtypeId(Integer identificationtypeId) {
        this.identificationtypeId = identificationtypeId;
    }

    public String getIdentificationtypeName() {
        return identificationtypeName;
    }

    public void setIdentificationtypeName(String identificationtypeName) {
        this.identificationtypeName = identificationtypeName;
    }

}