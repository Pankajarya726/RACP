package com.tekzee.racp.ui.Form.milk_info_bakari.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("table_id")
@Expose
private Integer tableId;
@SerializedName("is_mtg_member")
@Expose
private Integer isMtgMember;
@SerializedName("total_milk_production_per_day")
@Expose
private Integer totalMilkProductionPerDay;
@SerializedName("consumption_quantity_per_day")
@Expose
private Float consumptionQuantityPerDay;
@SerializedName("pashupalak_name")
@Expose
private String pashupalakName;
@SerializedName("pashupalak_mobile")
@Expose
private String pashupalakMobile;
@SerializedName("pashupalak_address")
@Expose
private String pashupalakAddress;
@SerializedName("form_id")
@Expose
private Integer formId;
@SerializedName("quantity_sale_per_day")
@Expose
private Float quantitySalePerDay;
@SerializedName("mtgmember_name")
@Expose
private String mtgmemberName;
@SerializedName("mtggroup_name")
@Expose
private String mtggroupName;
@SerializedName("grampanchayat_name")
@Expose
private String grampanchayatName;
@SerializedName("gram_name")
@Expose
private String gramName;

/**
* No args constructor for use in serialization
*
*/
public Data() {
}

/**
*
* @param totalMilkProductionPerDay
* @param pashupalakAddress
* @param mtggroupName
* @param isMtgMember
* @param grampanchayatName
* @param tableId
* @param quantitySalePerDay
* @param formId
* @param consumptionQuantityPerDay
* @param pashupalakName
* @param gramName
* @param pashupalakMobile
* @param mtgmemberName
*/
public Data(Integer tableId, Integer isMtgMember, Integer totalMilkProductionPerDay, Float consumptionQuantityPerDay, String pashupalakName, String pashupalakMobile, String pashupalakAddress, Integer formId, Float quantitySalePerDay, String mtgmemberName, String mtggroupName, String grampanchayatName, String gramName) {
super();
this.tableId = tableId;
this.isMtgMember = isMtgMember;
this.totalMilkProductionPerDay = totalMilkProductionPerDay;
this.consumptionQuantityPerDay = consumptionQuantityPerDay;
this.pashupalakName = pashupalakName;
this.pashupalakMobile = pashupalakMobile;
this.pashupalakAddress = pashupalakAddress;
this.formId = formId;
this.quantitySalePerDay = quantitySalePerDay;
this.mtgmemberName = mtgmemberName;
this.mtggroupName = mtggroupName;
this.grampanchayatName = grampanchayatName;
this.gramName = gramName;
}

public Integer getTableId() {
return tableId;
}

public void setTableId(Integer tableId) {
this.tableId = tableId;
}

public Integer getIsMtgMember() {
return isMtgMember;
}

public void setIsMtgMember(Integer isMtgMember) {
this.isMtgMember = isMtgMember;
}

public Integer getTotalMilkProductionPerDay() {
return totalMilkProductionPerDay;
}

public void setTotalMilkProductionPerDay(Integer totalMilkProductionPerDay) {
this.totalMilkProductionPerDay = totalMilkProductionPerDay;
}

public Float getConsumptionQuantityPerDay() {
return consumptionQuantityPerDay;
}

public void setConsumptionQuantityPerDay(Float consumptionQuantityPerDay) {
this.consumptionQuantityPerDay = consumptionQuantityPerDay;
}

public String getPashupalakName() {
return pashupalakName;
}

public void setPashupalakName(String pashupalakName) {
this.pashupalakName = pashupalakName;
}

public String getPashupalakMobile() {
return pashupalakMobile;
}

public void setPashupalakMobile(String pashupalakMobile) {
this.pashupalakMobile = pashupalakMobile;
}

public String getPashupalakAddress() {
return pashupalakAddress;
}

public void setPashupalakAddress(String pashupalakAddress) {
this.pashupalakAddress = pashupalakAddress;
}

public Integer getFormId() {
return formId;
}

public void setFormId(Integer formId) {
this.formId = formId;
}

public Float getQuantitySalePerDay() {
return quantitySalePerDay;
}

public void setQuantitySalePerDay(Float quantitySalePerDay) {
this.quantitySalePerDay = quantitySalePerDay;
}

public String getMtgmemberName() {
return mtgmemberName;
}

public void setMtgmemberName(String mtgmemberName) {
this.mtgmemberName = mtgmemberName;
}

public String getMtggroupName() {
return mtggroupName;
}

public void setMtggroupName(String mtggroupName) {
this.mtggroupName = mtggroupName;
}

public String getGrampanchayatName() {
return grampanchayatName;
}

public void setGrampanchayatName(String grampanchayatName) {
this.grampanchayatName = grampanchayatName;
}

public String getGramName() {
return gramName;
}

public void setGramName(String gramName) {
this.gramName = gramName;
}

}