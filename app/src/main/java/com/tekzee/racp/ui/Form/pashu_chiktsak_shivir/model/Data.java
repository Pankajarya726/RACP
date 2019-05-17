package com.tekzee.racp.ui.Form.pashu_chiktsak_shivir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("table_id")
@Expose
private Integer tableId;
@SerializedName("date_receipt")
@Expose
private String dateReceipt;
@SerializedName("form_id")
@Expose
private Integer formId;
@SerializedName("shivir_sthal")
@Expose
private String shivirSthal;
@SerializedName("pashu_shivir_aayojan_date")
@Expose
private String pashuShivirAayojanDate;
@SerializedName("present_mtgmember_number")
@Expose
private Integer presentMtgmemberNumber;
@SerializedName("bade_pashu")
@Expose
private Integer badePashu;
@SerializedName("chote_pashu")
@Expose
private Integer chotePashu;
@SerializedName("biomedical_waste_disposal")
@Expose
private String biomedicalWasteDisposal;
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
* @param biomedicalWasteDisposal
* @param tableId
* @param badePashu
* @param formId
* @param dateReceipt
* @param presentMtgmemberNumber
* @param pashuShivirAayojanDate
* @param shivirSthal
* @param note
* @param chotePashu
*/
public Data(Integer tableId, String  dateReceipt, Integer formId, String shivirSthal, String pashuShivirAayojanDate, Integer presentMtgmemberNumber, Integer badePashu, Integer chotePashu, String biomedicalWasteDisposal, String note) {
super();
this.tableId = tableId;
this.dateReceipt = dateReceipt;
this.formId = formId;
this.shivirSthal = shivirSthal;
this.pashuShivirAayojanDate = pashuShivirAayojanDate;
this.presentMtgmemberNumber = presentMtgmemberNumber;
this.badePashu = badePashu;
this.chotePashu = chotePashu;
this.biomedicalWasteDisposal = biomedicalWasteDisposal;
this.note = note;
}

public Integer getTableId() {
return tableId;
}

public void setTableId(Integer tableId) {
this.tableId = tableId;
}

public String getDateReceipt() {
return dateReceipt;
}

public void setDateReceipt(String  dateReceipt) {
this.dateReceipt = dateReceipt;
}

public Integer getFormId() {
return formId;
}

public void setFormId(Integer formId) {
this.formId = formId;
}

public String getShivirSthal() {
return shivirSthal;
}

public void setShivirSthal(String shivirSthal) {
this.shivirSthal = shivirSthal;
}

public String getPashuShivirAayojanDate() {
return pashuShivirAayojanDate;
}

public void setPashuShivirAayojanDate(String pashuShivirAayojanDate) {
this.pashuShivirAayojanDate = pashuShivirAayojanDate;
}

public Integer getPresentMtgmemberNumber() {
return presentMtgmemberNumber;
}

public void setPresentMtgmemberNumber(Integer presentMtgmemberNumber) {
this.presentMtgmemberNumber = presentMtgmemberNumber;
}

public Integer getBadePashu() {
return badePashu;
}

public void setBadePashu(Integer badePashu) {
this.badePashu = badePashu;
}

public Integer getChotePashu() {
return chotePashu;
}

public void setChotePashu(Integer chotePashu) {
this.chotePashu = chotePashu;
}

public String getBiomedicalWasteDisposal() {
return biomedicalWasteDisposal;
}

public void setBiomedicalWasteDisposal(String biomedicalWasteDisposal) {
this.biomedicalWasteDisposal = biomedicalWasteDisposal;
}

public String getNote() {
return note;
}

public void setNote(String note) {
this.note = note;
}

}