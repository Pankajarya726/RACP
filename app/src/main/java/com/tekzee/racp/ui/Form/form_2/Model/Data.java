package com.tekzee.racp.ui.Form.form_2.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("table_id")
    @Expose
    private Integer tableId;
    @SerializedName("tag_no")
    @Expose
    private Integer tagNo;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("average_milk_production")
    @Expose
    private Integer averageMilkProduction;
    @SerializedName("nasl")
    @Expose
    private Integer nasl;
    @SerializedName("nasla_name")
    @Expose
    private String naslaName;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("date_receipt")
    @Expose
    private String dateReceipt;
    @SerializedName("form_id")
    @Expose
    private Integer formId;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }

    /**
     *
     * @param tableId
     * @param formId
     * @param age
     * @param dateReceipt
     * @param tagNo
     * @param averageMilkProduction
     * @param note
     * @param naslaName
     * @param nasl
     */
    public Data(Integer tableId, Integer tagNo, Integer age, Integer averageMilkProduction, Integer nasl, String naslaName, String note, String dateReceipt, Integer formId) {
        super();
        this.tableId = tableId;
        this.tagNo = tagNo;
        this.age = age;
        this.averageMilkProduction = averageMilkProduction;
        this.nasl = nasl;
        this.naslaName = naslaName;
        this.note = note;
        this.dateReceipt = dateReceipt;
        this.formId = formId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getTagNo() {
        return tagNo;
    }

    public void setTagNo(Integer tagNo) {
        this.tagNo = tagNo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAverageMilkProduction() {
        return averageMilkProduction;
    }

    public void setAverageMilkProduction(Integer averageMilkProduction) {
        this.averageMilkProduction = averageMilkProduction;
    }

    public Integer getNasl() {
        return nasl;
    }

    public void setNasl(Integer nasl) {
        this.nasl = nasl;
    }

    public String getNaslaName() {
        return naslaName;
    }

    public void setNaslaName(String naslaName) {
        this.naslaName = naslaName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

}