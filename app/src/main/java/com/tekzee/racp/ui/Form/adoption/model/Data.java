package com.tekzee.racp.ui.Form.adoption.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("table_id")
    @Expose
    private Integer tableId;
    @SerializedName("date_receipt")
    @Expose
    private String dateReceipt;
    @SerializedName("form_id")
    @Expose
    private Integer formId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("activity")
    @Expose
    private String activity;
    @SerializedName("pashupalak_name")
    @Expose
    private String pashupalakName;
    @SerializedName("pashupalak_father_husband_name")
    @Expose
    private String pashupalakFatherHusbandName;
    @SerializedName("pashupalak_address")
    @Expose
    private String pashupalakAddress;
    @SerializedName("pashupalak_mobile")
    @Expose
    private String pashupalakMobile;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("cluster_name")
    @Expose
    private String clusterName;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("vidhanasabha_name")
    @Expose
    private String vidhanasabhaName;
    @SerializedName("tahsil_name")
    @Expose
    private String tahsilName;
    @SerializedName("grampanchayat_name")
    @Expose
    private String grampanchayatName;
    @SerializedName("gram_name")
    @Expose
    private String gramName;

    /**
     * No args constructor for use in serialization
     */
    public Data() {
    }

    /**
     * @param clusterName
     * @param pashupalakAddress
     * @param vidhanasabhaName
     * @param dateReceipt
     * @param grampanchayatName
     * @param pashupalakFatherHusbandName
     * @param districtName
     * @param tableId
     * @param tahsilName
     * @param formId
     * @param createdAt
     * @param pashupalakName
     * @param gramName
     * @param pashupalakMobile
     * @param activity
     * @param note
     */
    public Data(Integer tableId, String dateReceipt, Integer formId, String createdAt, String activity, String pashupalakName, String pashupalakFatherHusbandName, String pashupalakAddress, String pashupalakMobile, String note, String clusterName, String districtName, String vidhanasabhaName, String tahsilName, String grampanchayatName, String gramName) {
        super();
        this.tableId = tableId;
        this.dateReceipt = dateReceipt;
        this.formId = formId;
        this.createdAt = createdAt;
        this.activity = activity;
        this.pashupalakName = pashupalakName;
        this.pashupalakFatherHusbandName = pashupalakFatherHusbandName;
        this.pashupalakAddress = pashupalakAddress;
        this.pashupalakMobile = pashupalakMobile;
        this.note = note;
        this.clusterName = clusterName;
        this.districtName = districtName;
        this.vidhanasabhaName = vidhanasabhaName;
        this.tahsilName = tahsilName;
        this.grampanchayatName = grampanchayatName;
        this.gramName = gramName;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getDateReceipt() {
        return dateReceipt;
    }

    public void setDateReceipt(String dateReceipt) {
        this.dateReceipt = dateReceipt;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getPashupalakName() {
        return pashupalakName;
    }

    public void setPashupalakName(String pashupalakName) {
        this.pashupalakName = pashupalakName;
    }

    public String getPashupalakFatherHusbandName() {
        return pashupalakFatherHusbandName;
    }

    public void setPashupalakFatherHusbandName(String pashupalakFatherHusbandName) {
        this.pashupalakFatherHusbandName = pashupalakFatherHusbandName;
    }

    public String getPashupalakAddress() {
        return pashupalakAddress;
    }

    public void setPashupalakAddress(String pashupalakAddress) {
        this.pashupalakAddress = pashupalakAddress;
    }

    public String getPashupalakMobile() {
        return pashupalakMobile;
    }

    public void setPashupalakMobile(String pashupalakMobile) {
        this.pashupalakMobile = pashupalakMobile;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getVidhanasabhaName() {
        return vidhanasabhaName;
    }

    public void setVidhanasabhaName(String vidhanasabhaName) {
        this.vidhanasabhaName = vidhanasabhaName;
    }

    public String getTahsilName() {
        return tahsilName;
    }

    public void setTahsilName(String tahsilName) {
        this.tahsilName = tahsilName;
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