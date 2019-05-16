package com.tekzee.racp.ui.Form.mtg_meeting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("table_id")
@Expose
private Integer tableId;
@SerializedName("date_receipt")
@Expose
private Object dateReceipt;
@SerializedName("form_id")
@Expose
private Integer formId;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("meeting_date")
@Expose
private String meetingDate;
@SerializedName("note")
@Expose
private String note;
@SerializedName("mtggroup_id")
@Expose
private Integer mtggroupId;
@SerializedName("pashupalak_id")
@Expose
private String pashupalakId;

/**
* No args constructor for use in serialization
*
*/
public Data() {
}

/**
*
* @param pashupalakId
* @param tableId
* @param formId
* @param createdAt
* @param dateReceipt
* @param mtggroupId
* @param meetingDate
* @param note
*/
public Data(Integer tableId, Object dateReceipt, Integer formId, String createdAt, String meetingDate, String note, Integer mtggroupId, String pashupalakId) {
super();
this.tableId = tableId;
this.dateReceipt = dateReceipt;
this.formId = formId;
this.createdAt = createdAt;
this.meetingDate = meetingDate;
this.note = note;
this.mtggroupId = mtggroupId;
this.pashupalakId = pashupalakId;
}

public Integer getTableId() {
return tableId;
}

public void setTableId(Integer tableId) {
this.tableId = tableId;
}

public Object getDateReceipt() {
return dateReceipt;
}

public void setDateReceipt(Object dateReceipt) {
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

public String getMeetingDate() {
return meetingDate;
}

public void setMeetingDate(String meetingDate) {
this.meetingDate = meetingDate;
}

public String getNote() {
return note;
}

public void setNote(String note) {
this.note = note;
}

public Integer getMtggroupId() {
return mtggroupId;
}

public void setMtggroupId(Integer mtggroupId) {
this.mtggroupId = mtggroupId;
}

public String getPashupalakId() {
return pashupalakId;
}

public void setPashupalakId(String pashupalakId) {
this.pashupalakId = pashupalakId;
}

}