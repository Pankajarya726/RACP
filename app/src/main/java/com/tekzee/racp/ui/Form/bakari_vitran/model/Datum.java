package com.tekzee.racp.ui.Form.bakari_vitran.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

@SerializedName("table_id")
@Expose
private Integer tableId;
@SerializedName("tag_no")
@Expose
private String tagNo;
@SerializedName("date_physical_proof")
@Expose
private String datePhysicalProof;
@SerializedName("weight")
@Expose
private Integer weight;
@SerializedName("age")
@Expose
private Integer age;
@SerializedName("nasl")
@Expose
private Integer nasl;
@SerializedName("nasl_name")
@Expose
private String naslName;
@SerializedName("ppcategory_name")
@Expose
private String ppcategoryName;
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
@SerializedName("image_upload")
@Expose
private String imageUpload;
@SerializedName("img_lat")
@Expose
private String imgLat;
@SerializedName("img_long")
@Expose
private String imgLong;

/**
* No args constructor for use in serialization
*
*/
public Datum() {
}

/**
*
* @param imageUpload
* @param weight
* @param datePhysicalProof
* @param imgLong
* @param dateReceipt
* @param dateHs
* @param tagNo
* @param nasl
* @param tableId
* @param formId
* @param dateEt
* @param datePpr
* @param age
* @param naslName
* @param ppcategoryName
* @param averageMilkProduction
* @param imgLat
* @param dateFmd
* @param note
*/
public Datum(Integer tableId, String tagNo, String datePhysicalProof, Integer weight, Integer age, Integer nasl, String naslName, String ppcategoryName, Integer averageMilkProduction, String datePpr, String dateEt, String dateFmd, String dateHs, String dateReceipt, Integer formId, String note, String imageUpload, String imgLat, String imgLong) {
super();
this.tableId = tableId;
this.tagNo = tagNo;
this.datePhysicalProof = datePhysicalProof;
this.weight = weight;
this.age = age;
this.nasl = nasl;
this.naslName = naslName;
this.ppcategoryName = ppcategoryName;
this.averageMilkProduction = averageMilkProduction;
this.datePpr = datePpr;
this.dateEt = dateEt;
this.dateFmd = dateFmd;
this.dateHs = dateHs;
this.dateReceipt = dateReceipt;
this.formId = formId;
this.note = note;
this.imageUpload = imageUpload;
this.imgLat = imgLat;
this.imgLong = imgLong;
}

public Integer getTableId() {
return tableId;
}

public void setTableId(Integer tableId) {
this.tableId = tableId;
}

public String getTagNo() {
return tagNo;
}

public void setTagNo(String tagNo) {
this.tagNo = tagNo;
}

public String getDatePhysicalProof() {
return datePhysicalProof;
}

public void setDatePhysicalProof(String datePhysicalProof) {
this.datePhysicalProof = datePhysicalProof;
}

public Integer getWeight() {
return weight;
}

public void setWeight(Integer weight) {
this.weight = weight;
}

public Integer getAge() {
return age;
}

public void setAge(Integer age) {
this.age = age;
}

public Integer getNasl() {
return nasl;
}

public void setNasl(Integer nasl) {
this.nasl = nasl;
}

public String getNaslName() {
return naslName;
}

public void setNaslName(String naslName) {
this.naslName = naslName;
}

public String getPpcategoryName() {
return ppcategoryName;
}

public void setPpcategoryName(String ppcategoryName) {
this.ppcategoryName = ppcategoryName;
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

public String getImageUpload() {
return imageUpload;
}

public void setImageUpload(String imageUpload) {
this.imageUpload = imageUpload;
}

public String getImgLat() {
return imgLat;
}

public void setImgLat(String imgLat) {
this.imgLat = imgLat;
}

public String getImgLong() {
return imgLong;
}

public void setImgLong(String imgLong) {
this.imgLong = imgLong;
}

}