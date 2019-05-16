package com.tekzee.racp.ui.Form.feed_suppliment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("table_id")
@Expose
private Integer tableId;
@SerializedName("animaltype_id")
@Expose
private Integer animaltypeId;
@SerializedName("tag_no")
@Expose
private Integer tagNo;
@SerializedName("age")
@Expose
private Integer age;
@SerializedName("weight_start")
@Expose
private Integer weightStart;
@SerializedName("milk_production_start")
@Expose
private Integer milkProductionStart;
@SerializedName("feed_supliment_amount")
@Expose
private Integer feedSuplimentAmount;
@SerializedName("feed_suppliment_start_date")
@Expose
private String feedSupplimentStartDate;
@SerializedName("date_after_90_days")
@Expose
private String dateAfter90Days;
@SerializedName("suppliment_effect_month")
@Expose
private String supplimentEffectMonth;
@SerializedName("effeted_weight")
@Expose
private Integer effetedWeight;
@SerializedName("coat_shining")
@Expose
private String coatShining;
@SerializedName("milk_production")
@Expose
private String milkProduction;
@SerializedName("heat_frquency")
@Expose
private String heatFrquency;
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
* @param feedSuplimentAmount
* @param dateReceipt
* @param tagNo
* @param supplimentEffectMonth
* @param milkProduction
* @param effetedWeight
* @param milkProductionStart
* @param heatFrquency
* @param feedSupplimentStartDate
* @param tableId
* @param formId
* @param age
* @param coatShining
* @param animaltypeId
* @param dateAfter90Days
* @param weightStart
*/
public Data(Integer tableId, Integer animaltypeId, Integer tagNo, Integer age, Integer weightStart, Integer milkProductionStart, Integer feedSuplimentAmount, String feedSupplimentStartDate, String dateAfter90Days, String supplimentEffectMonth, Integer effetedWeight, String coatShining, String milkProduction, String heatFrquency, String dateReceipt, Integer formId) {
super();
this.tableId = tableId;
this.animaltypeId = animaltypeId;
this.tagNo = tagNo;
this.age = age;
this.weightStart = weightStart;
this.milkProductionStart = milkProductionStart;
this.feedSuplimentAmount = feedSuplimentAmount;
this.feedSupplimentStartDate = feedSupplimentStartDate;
this.dateAfter90Days = dateAfter90Days;
this.supplimentEffectMonth = supplimentEffectMonth;
this.effetedWeight = effetedWeight;
this.coatShining = coatShining;
this.milkProduction = milkProduction;
this.heatFrquency = heatFrquency;
this.dateReceipt = dateReceipt;
this.formId = formId;
}

public Integer getTableId() {
return tableId;
}

public void setTableId(Integer tableId) {
this.tableId = tableId;
}

public Integer getAnimaltypeId() {
return animaltypeId;
}

public void setAnimaltypeId(Integer animaltypeId) {
this.animaltypeId = animaltypeId;
}

public Integer getTagNo() {
return tagNo;
}

public void setTagNo(Integer tagNo) {
this.tagNo = tagNo;
}

public Integer getAge() {
return age;
}

public void setAge(Integer age) {
this.age = age;
}

public Integer getWeightStart() {
return weightStart;
}

public void setWeightStart(Integer weightStart) {
this.weightStart = weightStart;
}

public Integer getMilkProductionStart() {
return milkProductionStart;
}

public void setMilkProductionStart(Integer milkProductionStart) {
this.milkProductionStart = milkProductionStart;
}

public Integer getFeedSuplimentAmount() {
return feedSuplimentAmount;
}

public void setFeedSuplimentAmount(Integer feedSuplimentAmount) {
this.feedSuplimentAmount = feedSuplimentAmount;
}

public String getFeedSupplimentStartDate() {
return feedSupplimentStartDate;
}

public void setFeedSupplimentStartDate(String feedSupplimentStartDate) {
this.feedSupplimentStartDate = feedSupplimentStartDate;
}

public String getDateAfter90Days() {
return dateAfter90Days;
}

public void setDateAfter90Days(String dateAfter90Days) {
this.dateAfter90Days = dateAfter90Days;
}

public String getSupplimentEffectMonth() {
return supplimentEffectMonth;
}

public void setSupplimentEffectMonth(String supplimentEffectMonth) {
this.supplimentEffectMonth = supplimentEffectMonth;
}

public Integer getEffetedWeight() {
return effetedWeight;
}

public void setEffetedWeight(Integer effetedWeight) {
this.effetedWeight = effetedWeight;
}

public String getCoatShining() {
return coatShining;
}

public void setCoatShining(String coatShining) {
this.coatShining = coatShining;
}

public String getMilkProduction() {
return milkProduction;
}

public void setMilkProduction(String milkProduction) {
this.milkProduction = milkProduction;
}

public String getHeatFrquency() {
return heatFrquency;
}

public void setHeatFrquency(String heatFrquency) {
this.heatFrquency = heatFrquency;
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