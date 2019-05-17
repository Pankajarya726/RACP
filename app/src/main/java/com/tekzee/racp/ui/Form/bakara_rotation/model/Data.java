package com.tekzee.racp.ui.Form.bakara_rotation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("table_id")
@Expose
private Integer tableId;
@SerializedName("form_id")
@Expose
private Integer formId;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("tag_no")
@Expose
private String tagNo;
@SerializedName("before_mtg_group_name")
@Expose
private String beforeMtgGroupName;
@SerializedName("before_pashupalak_name")
@Expose
private String beforePashupalakName;
@SerializedName("before_pashupalak_pata")
@Expose
private String beforePashupalakPata;
@SerializedName("before_mobile_no")
@Expose
private String beforeMobileNo;
@SerializedName("before_vidhansabha")
@Expose
private String beforeVidhansabha;
@SerializedName("before_gram_panchayat")
@Expose
private String beforeGramPanchayat;
@SerializedName("before_gram")
@Expose
private String beforeGram;
@SerializedName("before_tehsil")
@Expose
private String beforeTehsil;
@SerializedName("after_mtg_group_name")
@Expose
private String afterMtgGroupName;
@SerializedName("after_pashupalak_name")
@Expose
private String afterPashupalakName;
@SerializedName("after_pashupalak_pata")
@Expose
private String afterPashupalakPata;
@SerializedName("after_mobile_no")
@Expose
private String afterMobileNo;
@SerializedName("after_vidhansabha")
@Expose
private String afterVidhansabha;
@SerializedName("after_gram_panchayat")
@Expose
private String afterGramPanchayat;
@SerializedName("after_gram")
@Expose
private String afterGram;
@SerializedName("after_tehsil")
@Expose
private String afterTehsil;

/**
* No args constructor for use in serialization
*
*/
public Data() {
}

/**
*
* @param beforeGramPanchayat
* @param afterGramPanchayat
* @param afterMtgGroupName
* @param tagNo
* @param afterPashupalakName
* @param afterGram
* @param afterPashupalakPata
* @param beforePashupalakName
* @param beforeMobileNo
* @param tableId
* @param beforePashupalakPata
* @param afterMobileNo
* @param formId
* @param createdAt
* @param beforeMtgGroupName
* @param beforeGram
* @param beforeVidhansabha
* @param beforeTehsil
* @param afterVidhansabha
* @param afterTehsil
*/
public Data(Integer tableId, Integer formId, String createdAt, String tagNo, String beforeMtgGroupName, String beforePashupalakName, String beforePashupalakPata, String beforeMobileNo, String beforeVidhansabha, String beforeGramPanchayat, String beforeGram, String beforeTehsil, String afterMtgGroupName, String afterPashupalakName, String afterPashupalakPata, String afterMobileNo, String afterVidhansabha, String afterGramPanchayat, String afterGram, String afterTehsil) {
super();
this.tableId = tableId;
this.formId = formId;
this.createdAt = createdAt;
this.tagNo = tagNo;
this.beforeMtgGroupName = beforeMtgGroupName;
this.beforePashupalakName = beforePashupalakName;
this.beforePashupalakPata = beforePashupalakPata;
this.beforeMobileNo = beforeMobileNo;
this.beforeVidhansabha = beforeVidhansabha;
this.beforeGramPanchayat = beforeGramPanchayat;
this.beforeGram = beforeGram;
this.beforeTehsil = beforeTehsil;
this.afterMtgGroupName = afterMtgGroupName;
this.afterPashupalakName = afterPashupalakName;
this.afterPashupalakPata = afterPashupalakPata;
this.afterMobileNo = afterMobileNo;
this.afterVidhansabha = afterVidhansabha;
this.afterGramPanchayat = afterGramPanchayat;
this.afterGram = afterGram;
this.afterTehsil = afterTehsil;
}

public Integer getTableId() {
return tableId;
}

public void setTableId(Integer tableId) {
this.tableId = tableId;
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

public String getTagNo() {
return tagNo;
}

public void setTagNo(String tagNo) {
this.tagNo = tagNo;
}

public String getBeforeMtgGroupName() {
return beforeMtgGroupName;
}

public void setBeforeMtgGroupName(String beforeMtgGroupName) {
this.beforeMtgGroupName = beforeMtgGroupName;
}

public String getBeforePashupalakName() {
return beforePashupalakName;
}

public void setBeforePashupalakName(String beforePashupalakName) {
this.beforePashupalakName = beforePashupalakName;
}

public String getBeforePashupalakPata() {
return beforePashupalakPata;
}

public void setBeforePashupalakPata(String beforePashupalakPata) {
this.beforePashupalakPata = beforePashupalakPata;
}

public String getBeforeMobileNo() {
return beforeMobileNo;
}

public void setBeforeMobileNo(String beforeMobileNo) {
this.beforeMobileNo = beforeMobileNo;
}

public String getBeforeVidhansabha() {
return beforeVidhansabha;
}

public void setBeforeVidhansabha(String beforeVidhansabha) {
this.beforeVidhansabha = beforeVidhansabha;
}

public String getBeforeGramPanchayat() {
return beforeGramPanchayat;
}

public void setBeforeGramPanchayat(String beforeGramPanchayat) {
this.beforeGramPanchayat = beforeGramPanchayat;
}

public String getBeforeGram() {
return beforeGram;
}

public void setBeforeGram(String beforeGram) {
this.beforeGram = beforeGram;
}

public String getBeforeTehsil() {
return beforeTehsil;
}

public void setBeforeTehsil(String beforeTehsil) {
this.beforeTehsil = beforeTehsil;
}

public String getAfterMtgGroupName() {
return afterMtgGroupName;
}

public void setAfterMtgGroupName(String afterMtgGroupName) {
this.afterMtgGroupName = afterMtgGroupName;
}

public String getAfterPashupalakName() {
return afterPashupalakName;
}

public void setAfterPashupalakName(String afterPashupalakName) {
this.afterPashupalakName = afterPashupalakName;
}

public String getAfterPashupalakPata() {
return afterPashupalakPata;
}

public void setAfterPashupalakPata(String afterPashupalakPata) {
this.afterPashupalakPata = afterPashupalakPata;
}

public String getAfterMobileNo() {
return afterMobileNo;
}

public void setAfterMobileNo(String afterMobileNo) {
this.afterMobileNo = afterMobileNo;
}

public String getAfterVidhansabha() {
return afterVidhansabha;
}

public void setAfterVidhansabha(String afterVidhansabha) {
this.afterVidhansabha = afterVidhansabha;
}

public String getAfterGramPanchayat() {
return afterGramPanchayat;
}

public void setAfterGramPanchayat(String afterGramPanchayat) {
this.afterGramPanchayat = afterGramPanchayat;
}

public String getAfterGram() {
return afterGram;
}

public void setAfterGram(String afterGram) {
this.afterGram = afterGram;
}

public String getAfterTehsil() {
return afterTehsil;
}

public void setAfterTehsil(String afterTehsil) {
this.afterTehsil = afterTehsil;
}

}