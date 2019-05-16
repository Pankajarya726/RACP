package com.tekzee.racp.ui.Form.adoption.model;

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
@SerializedName("activity")
@Expose
private String activity;
@SerializedName("pashupalak_name")
@Expose
private String pashupalakName;
@SerializedName("pashupalak_address")
@Expose
private String pashupalakAddress;
@SerializedName("pashupalak_mobile")
@Expose
private String pashupalakMobile;
@SerializedName("note")
@Expose
private String note;
@SerializedName("cluster_id")
@Expose
private Integer clusterId;
@SerializedName("district_id")
@Expose
private Integer districtId;
@SerializedName("vidhanasabha_id")
@Expose
private Integer vidhanasabhaId;
@SerializedName("tahsil_id")
@Expose
private Integer tahsilId;
@SerializedName("grampanchayat_id")
@Expose
private Integer grampanchayatId;
@SerializedName("gram_id")
@Expose
private Integer gramId;

/**
* No args constructor for use in serialization
*
*/
public Data() {
}

/**
*
* @param pashupalakAddress
* @param dateReceipt
* @param grampanchayatId
* @param tahsilId
* @param districtId
* @param tableId
* @param formId
* @param createdAt
* @param gramId
* @param pashupalakName
* @param pashupalakMobile
* @param clusterId
* @param activity
* @param note
* @param vidhanasabhaId
*/
public Data(Integer tableId, Object dateReceipt, Integer formId, String createdAt, String activity, String pashupalakName, String pashupalakAddress, String pashupalakMobile, String note, Integer clusterId, Integer districtId, Integer vidhanasabhaId, Integer tahsilId, Integer grampanchayatId, Integer gramId) {
super();
this.tableId = tableId;
this.dateReceipt = dateReceipt;
this.formId = formId;
this.createdAt = createdAt;
this.activity = activity;
this.pashupalakName = pashupalakName;
this.pashupalakAddress = pashupalakAddress;
this.pashupalakMobile = pashupalakMobile;
this.note = note;
this.clusterId = clusterId;
this.districtId = districtId;
this.vidhanasabhaId = vidhanasabhaId;
this.tahsilId = tahsilId;
this.grampanchayatId = grampanchayatId;
this.gramId = gramId;
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

public Integer getClusterId() {
return clusterId;
}

public void setClusterId(Integer clusterId) {
this.clusterId = clusterId;
}

public Integer getDistrictId() {
return districtId;
}

public void setDistrictId(Integer districtId) {
this.districtId = districtId;
}

public Integer getVidhanasabhaId() {
return vidhanasabhaId;
}

public void setVidhanasabhaId(Integer vidhanasabhaId) {
this.vidhanasabhaId = vidhanasabhaId;
}

public Integer getTahsilId() {
return tahsilId;
}

public void setTahsilId(Integer tahsilId) {
this.tahsilId = tahsilId;
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

}