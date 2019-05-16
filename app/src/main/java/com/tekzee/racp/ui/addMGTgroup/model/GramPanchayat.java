package com.tekzee.racp.ui.addMGTgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GramPanchayat {

    @SerializedName("grampanchayat_id")
    @Expose
    private Integer grampanchayatId;

    @SerializedName("grampanchayat_name")
    @Expose
    private String grampanchayatName;

    public GramPanchayat(Integer grampanchayatId, String grampanchayatName) {
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