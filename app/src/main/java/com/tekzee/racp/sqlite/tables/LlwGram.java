package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class LlwGram extends SugarRecord {

    private Integer gramId;
    private String gramName;

    private Integer grampanchayatId;

    public LlwGram() {
    }

    public LlwGram(Integer gramId, String gramName, Integer grampanchayatId) {
        super();
        this.gramId = gramId;
        this.gramName = gramName;
        this.grampanchayatId = grampanchayatId;
    }

    public Integer getGramId() {
        return gramId;
    }

    public void setGramId(Integer gramId) {
        this.gramId = gramId;
    }

    public String getGramName() {
        return gramName;
    }

    public void setGramName(String gramName) {
        this.gramName = gramName;
    }

    public Integer getGrampanchayatId() {
        return grampanchayatId;
    }

    public void setGrampanchayatId(Integer grampanchayatId) {
        this.grampanchayatId = grampanchayatId;
    }

}