package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class LlwGrampanchayat extends SugarRecord {

    private Integer grampanchayatId;
    private String grampanchayatName;

    /**
     * No args constructor for use in serialization
     */
    public LlwGrampanchayat() {
    }

    /**
     * @param grampanchayatName
     * @param grampanchayatId
     */
    public LlwGrampanchayat(Integer grampanchayatId, String grampanchayatName) {
        super();
        this.grampanchayatId = grampanchayatId;
        this.grampanchayatName = grampanchayatName;
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

}