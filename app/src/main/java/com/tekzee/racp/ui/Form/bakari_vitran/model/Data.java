package com.tekzee.racp.ui.Form.bakari_vitran.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("table_id")
    @Expose
    private Integer tableId;
    @SerializedName("tag_no")
    @Expose
    private Integer tagNo;
    @SerializedName("date_physical_proof")
    @Expose
    private String datePhysicalProof;
    @SerializedName("beema_detail")
    @Expose
    private String beemaDetail;
    @SerializedName("policy_no")
    @Expose
    private String policyNo;
    @SerializedName("average_milk_production")
    @Expose
    private Integer averageMilkProduction;
    @SerializedName("date_ppr")
    @Expose
    private String datePpr;
    @SerializedName("date_et")
    @Expose
    private String dateEt;
    @SerializedName("date_fmd")
    @Expose
    private String dateFmd;
    @SerializedName("date_hs")
    @Expose
    private String dateHs;
    @SerializedName("date_receipt")
    @Expose
    private String dateReceipt;
    @SerializedName("form_id")
    @Expose
    private Integer formId;
    @SerializedName("note")
    @Expose
    private String note;

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
     * @param dateEt
     * @param datePhysicalProof
     * @param datePpr
     * @param beemaDetail
     * @param dateReceipt
     * @param dateHs
     * @param tagNo
     * @param averageMilkProduction
     * @param policyNo
     * @param dateFmd
     * @param note
     */
    public Data(Integer tableId, Integer tagNo, String datePhysicalProof, String beemaDetail, String policyNo, Integer averageMilkProduction, String datePpr, String dateEt, String dateFmd, String dateHs, String dateReceipt, Integer formId, String note) {
        super();
        this.tableId = tableId;
        this.tagNo = tagNo;
        this.datePhysicalProof = datePhysicalProof;
        this.beemaDetail = beemaDetail;
        this.policyNo = policyNo;
        this.averageMilkProduction = averageMilkProduction;
        this.datePpr = datePpr;
        this.dateEt = dateEt;
        this.dateFmd = dateFmd;
        this.dateHs = dateHs;
        this.dateReceipt = dateReceipt;
        this.formId = formId;
        this.note = note;
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

    public String getDatePhysicalProof() {
        return datePhysicalProof;
    }

    public void setDatePhysicalProof(String datePhysicalProof) {
        this.datePhysicalProof = datePhysicalProof;
    }

    public String getBeemaDetail() {
        return beemaDetail;
    }

    public void setBeemaDetail(String beemaDetail) {
        this.beemaDetail = beemaDetail;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public Integer getAverageMilkProduction() {
        return averageMilkProduction;
    }

    public void setAverageMilkProduction(Integer averageMilkProduction) {
        this.averageMilkProduction = averageMilkProduction;
    }

    public String getDatePpr() {
        return datePpr;
    }

    public void setDatePpr(String datePpr) {
        this.datePpr = datePpr;
    }

    public String getDateEt() {
        return dateEt;
    }

    public void setDateEt(String dateEt) {
        this.dateEt = dateEt;
    }

    public String getDateFmd() {
        return dateFmd;
    }

    public void setDateFmd(String dateFmd) {
        this.dateFmd = dateFmd;
    }

    public String getDateHs() {
        return dateHs;
    }

    public void setDateHs(String dateHs) {
        this.dateHs = dateHs;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}