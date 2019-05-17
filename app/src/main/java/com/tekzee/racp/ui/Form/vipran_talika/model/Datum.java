package com.tekzee.racp.ui.Form.vipran_talika.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("pashupalak_id")
    @Expose
    private Integer pashupalakId;
    @SerializedName("pashupalak_name")
    @Expose
    private String pashupalakName;
    @SerializedName("pashupalak_mobile")
    @Expose
    private String pashupalakMobile;
    @SerializedName("grampanchayat_id")
    @Expose
    private Integer grampanchayatId;
    @SerializedName("gram_id")
    @Expose
    private Integer gramId;
    @SerializedName("grampanchayat_name")
    @Expose
    private String grampanchayatName;
    @SerializedName("gram_name")
    @Expose
    private String gramName;

    /**
     * No args constructor for use in serialization
     *
     */
    public Datum() {
    }

    /**
     *
     * @param pashupalakId
     * @param grampanchayatName
     * @param grampanchayatId
     * @param gramId
     * @param pashupalakName
     * @param gramName
     * @param pashupalakMobile
     */
    public Datum(Integer pashupalakId, String pashupalakName, String pashupalakMobile, Integer grampanchayatId, Integer gramId, String grampanchayatName, String gramName) {
        super();
        this.pashupalakId = pashupalakId;
        this.pashupalakName = pashupalakName;
        this.pashupalakMobile = pashupalakMobile;
        this.grampanchayatId = grampanchayatId;
        this.gramId = gramId;
        this.grampanchayatName = grampanchayatName;
        this.gramName = gramName;
    }

    public Integer getPashupalakId() {
        return pashupalakId;
    }

    public void setPashupalakId(Integer pashupalakId) {
        this.pashupalakId = pashupalakId;
    }

    public String getPashupalakName() {
        return pashupalakName;
    }

    public void setPashupalakName(String pashupalakName) {
        this.pashupalakName = pashupalakName;
    }

    public String getPashupalakMobile() {
        return pashupalakMobile;
    }

    public void setPashupalakMobile(String pashupalakMobile) {
        this.pashupalakMobile = pashupalakMobile;
    }

    public Integer getGrampanchayatId() {
        return grampanchayatId;
    }

    public void setGrampanchayatId(Integer grampanchayatId) {
        this.grampanchayatId = grampanchayatId;
    }

    public Integer getGramId() {
        return gramId;
    }

    public void setGramId(Integer gramId) {
        this.gramId = gramId;
    }

    public String getGrampanchayatName() {
        return grampanchayatName;
    }

    public void setGrampanchayatName(String grampanchayatName) {
        this.grampanchayatName = grampanchayatName;
    }

    public String getGramName() {
        return gramName;
    }

    public void setGramName(String gramName) {
        this.gramName = gramName;
    }

}