package com.tekzee.racp.ui.Form.clean_milkkit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("table_id")
@Expose
private Integer tableId;
@SerializedName("physical_proof")
@Expose
private String physicalProof;
@SerializedName("usability")
@Expose
private String usability;
@SerializedName("note")
@Expose
private Object note;
@SerializedName("reagent_availability")
@Expose
private String reagentAvailability;
@SerializedName("test_date")
@Expose
private String testDate;
@SerializedName("llw_visiting_date")
@Expose
private String llwVisitingDate;
@SerializedName("thanela_rog_result")
@Expose
private String thanelaRogResult;
@SerializedName("before_use_clean_milk_keet")
@Expose
private String beforeUseCleanMilkKeet;
@SerializedName("after_use_clean_milk_keet")
@Expose
private String afterUseCleanMilkKeet;
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
* @param llwVisitingDate
* @param testDate
* @param usability
* @param beforeUseCleanMilkKeet
* @param thanelaRogResult
* @param tableId
* @param formId
* @param dateReceipt
* @param reagentAvailability
* @param physicalProof
* @param afterUseCleanMilkKeet
* @param note
*/
public Data(Integer tableId, String physicalProof, String usability, Object note, String reagentAvailability, String testDate, String llwVisitingDate, String thanelaRogResult, String beforeUseCleanMilkKeet, String afterUseCleanMilkKeet, String dateReceipt, Integer formId) {
super();
this.tableId = tableId;
this.physicalProof = physicalProof;
this.usability = usability;
this.note = note;
this.reagentAvailability = reagentAvailability;
this.testDate = testDate;
this.llwVisitingDate = llwVisitingDate;
this.thanelaRogResult = thanelaRogResult;
this.beforeUseCleanMilkKeet = beforeUseCleanMilkKeet;
this.afterUseCleanMilkKeet = afterUseCleanMilkKeet;
this.dateReceipt = dateReceipt;
this.formId = formId;
}

public Integer getTableId() {
return tableId;
}

public void setTableId(Integer tableId) {
this.tableId = tableId;
}

public String getPhysicalProof() {
return physicalProof;
}

public void setPhysicalProof(String physicalProof) {
this.physicalProof = physicalProof;
}

public String getUsability() {
return usability;
}

public void setUsability(String usability) {
this.usability = usability;
}

public Object getNote() {
return note;
}

public void setNote(Object note) {
this.note = note;
}

public String getReagentAvailability() {
return reagentAvailability;
}

public void setReagentAvailability(String reagentAvailability) {
this.reagentAvailability = reagentAvailability;
}

public String getTestDate() {
return testDate;
}

public void setTestDate(String testDate) {
this.testDate = testDate;
}

public String getLlwVisitingDate() {
return llwVisitingDate;
}

public void setLlwVisitingDate(String llwVisitingDate) {
this.llwVisitingDate = llwVisitingDate;
}

public String getThanelaRogResult() {
return thanelaRogResult;
}

public void setThanelaRogResult(String thanelaRogResult) {
this.thanelaRogResult = thanelaRogResult;
}

public String getBeforeUseCleanMilkKeet() {
return beforeUseCleanMilkKeet;
}

public void setBeforeUseCleanMilkKeet(String beforeUseCleanMilkKeet) {
this.beforeUseCleanMilkKeet = beforeUseCleanMilkKeet;
}

public String getAfterUseCleanMilkKeet() {
return afterUseCleanMilkKeet;
}

public void setAfterUseCleanMilkKeet(String afterUseCleanMilkKeet) {
this.afterUseCleanMilkKeet = afterUseCleanMilkKeet;
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