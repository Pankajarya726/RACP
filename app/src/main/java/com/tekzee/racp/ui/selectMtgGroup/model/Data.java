package com.tekzee.racp.ui.selectMtgGroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("mtggroup_id")
    @Expose
    private Integer mtggroupId;
    @SerializedName("mtggroup_name")
    @Expose
    private String mtggroupName;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }

    /**
     *
     * @param mtggroupName
     * @param mtggroupId
     */
    public Data(Integer mtggroupId, String mtggroupName) {
        super();
        this.mtggroupId = mtggroupId;
        this.mtggroupName = mtggroupName;
    }

    public Integer getMtggroupId() {
        return mtggroupId;
    }

    public void setMtggroupId(Integer mtggroupId) {
        this.mtggroupId = mtggroupId;
    }

    public String getMtggroupName() {
        return mtggroupName;
    }

    public void setMtggroupName(String mtggroupName) {
        this.mtggroupName = mtggroupName;
    }

}