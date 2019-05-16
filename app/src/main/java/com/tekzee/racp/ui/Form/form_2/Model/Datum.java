package com.tekzee.racp.ui.Form.form_2.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("nasla_id")
    @Expose
    private Integer naslaId;
    @SerializedName("nasla_Name")
    @Expose
    private String naslaName;

    /**
     * No args constructor for use in serialization
     */
    public Datum() {
    }

    /**
     * @param naslaId
     * @param naslaName
     */
    public Datum(Integer naslaId, String naslaName) {
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