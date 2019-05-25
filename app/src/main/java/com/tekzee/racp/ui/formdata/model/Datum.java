package com.tekzee.racp.ui.formdata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("table_id")
    @Expose
    private Integer tableId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tag_no")
    @Expose
    private Integer tagNo;
    @SerializedName("date_receipt")
    @Expose
    private String dateReceipt;
    @SerializedName("form_id")
    @Expose
    private Integer formId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public Datum() {
    }

    /**
     *
     * @param tableId
     * @param formId
     * @param createdAt
     * @param name
     * @param dateReceipt
     * @param tagNo
     */
    public Datum(Integer tableId, String name, Integer tagNo, String dateReceipt, Integer formId, String createdAt) {
        super();
        this.tableId = tableId;
        this.name = name;
        this.tagNo = tagNo;
        this.dateReceipt = dateReceipt;
        this.formId = formId;
        this.createdAt = createdAt;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTagNo() {
        return tagNo;
    }

    public void setTagNo(Integer tagNo) {
        this.tagNo = tagNo;
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

}