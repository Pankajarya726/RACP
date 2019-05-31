package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BakraData {

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
private Object beemaDetail;
@SerializedName("policy_no")
@Expose
private Object policyNo;
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

/**
* No args constructor for use in serialization
*
*/
public BakraData() {
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
* @param policyNo
* @param dateFmd
*/
public BakraData(Integer tableId, Integer tagNo, String datePhysicalProof, Object beemaDetail, Object policyNo, String datePpr, String dateEt, String dateFmd, String dateHs, String dateReceipt, Integer formId) {
super();
this.tableId = tableId;
this.tagNo = tagNo;
this.datePhysicalProof = datePhysicalProof;
this.beemaDetail = beemaDetail;
this.policyNo = policyNo;
this.datePpr = datePpr;
this.dateEt = dateEt;
this.dateFmd = dateFmd;
this.dateHs = dateHs;
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

public String getDatePhysicalProof() {
return datePhysicalProof;
}

public void setDatePhysicalProof(String datePhysicalProof) {
this.datePhysicalProof = datePhysicalProof;
}

public Object getBeemaDetail() {
return beemaDetail;
}

public void setBeemaDetail(Object beemaDetail) {
this.beemaDetail = beemaDetail;
}

public Object getPolicyNo() {
return policyNo;
}

public void setPolicyNo(Object policyNo) {
this.policyNo = policyNo;
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

}