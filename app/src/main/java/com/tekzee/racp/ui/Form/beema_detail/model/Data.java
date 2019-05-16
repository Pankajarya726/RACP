package com.tekzee.racp.ui.Form.beema_detail.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("table_id")
@Expose
private Integer tableId;
@SerializedName("tag_no")
@Expose
private Integer tagNo;
@SerializedName("date_from_beema")
@Expose
private String dateFromBeema;
@SerializedName("date_to_beema")
@Expose
private String dateToBeema;
@SerializedName("death_stage")
@Expose
private String deathStage;
@SerializedName("claim_receipt_stage")
@Expose
private String claimReceiptStage;
@SerializedName("date_death")
@Expose
private String dateDeath;
@SerializedName("policy_no")
@Expose
private String policyNo;
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
* @param dateDeath
* @param claimReceiptStage
* @param dateFromBeema
* @param tableId
* @param formId
* @param dateReceipt
* @param deathStage
* @param tagNo
* @param policyNo
* @param dateToBeema
*/
public Data(Integer tableId, Integer tagNo, String dateFromBeema, String dateToBeema, String deathStage, String claimReceiptStage, String dateDeath, String policyNo, String dateReceipt, Integer formId) {
super();
this.tableId = tableId;
this.tagNo = tagNo;
this.dateFromBeema = dateFromBeema;
this.dateToBeema = dateToBeema;
this.deathStage = deathStage;
this.claimReceiptStage = claimReceiptStage;
this.dateDeath = dateDeath;
this.policyNo = policyNo;
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

public String getDateFromBeema() {
return dateFromBeema;
}

public void setDateFromBeema(String dateFromBeema) {
this.dateFromBeema = dateFromBeema;
}

public String getDateToBeema() {
return dateToBeema;
}

public void setDateToBeema(String dateToBeema) {
this.dateToBeema = dateToBeema;
}

public String getDeathStage() {
return deathStage;
}

public void setDeathStage(String deathStage) {
this.deathStage = deathStage;
}

public String getClaimReceiptStage() {
return claimReceiptStage;
}

public void setClaimReceiptStage(String claimReceiptStage) {
this.claimReceiptStage = claimReceiptStage;
}

public String getDateDeath() {
return dateDeath;
}

public void setDateDeath(String dateDeath) {
this.dateDeath = dateDeath;
}

public String getPolicyNo() {
return policyNo;
}

public void setPolicyNo(String policyNo) {
this.policyNo = policyNo;
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