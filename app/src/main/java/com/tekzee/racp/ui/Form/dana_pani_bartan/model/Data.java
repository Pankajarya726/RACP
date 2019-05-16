package com.tekzee.racp.ui.Form.dana_pani_bartan.model;

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
* @param usability
* @param tableId
* @param formId
* @param dateReceipt
* @param physicalProof
* @param note
*/
public Data(Integer tableId, String physicalProof, String usability, String note, String dateReceipt, Integer formId) {
super();
this.tableId = tableId;
this.physicalProof = physicalProof;
this.usability = usability;
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
