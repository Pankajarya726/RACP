package com.tekzee.racp.ui.Form.ajola.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("table_id")
@Expose
private Integer tableId;
@SerializedName("animaltype_id")
@Expose
private Integer animaltypeId;
@SerializedName("physical_proof")
@Expose
private String physicalProof;
@SerializedName("average_milk_production")
@Expose
private Float averageMilkProduction;
@SerializedName("fat_check_status")
@Expose
private String fatCheckStatus;
@SerializedName("fat_before_ajola")
@Expose
private Integer fatBeforeAjola;
@SerializedName("fat_after_ajola")
@Expose
private Integer fatAfterAjola;
@SerializedName("milk_quantity_before_ajola")
@Expose
private Float milkQuantityBeforeAjola;
@SerializedName("milk_quantity_after_ajola")
@Expose
private Float milkQuantityAfterAjola;
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
* @param fatBeforeAjola
* @param tableId
* @param formId
* @param fatCheckStatus
* @param milkQuantityBeforeAjola
* @param dateReceipt
* @param animaltypeId
* @param averageMilkProduction
* @param milkQuantityAfterAjola
* @param fatAfterAjola
* @param physicalProof
*/
public Data(Integer tableId, Integer animaltypeId, String physicalProof, Float averageMilkProduction, String fatCheckStatus, Integer fatBeforeAjola, Integer fatAfterAjola, Float milkQuantityBeforeAjola, Float milkQuantityAfterAjola, String dateReceipt, Integer formId) {
super();
this.tableId = tableId;
this.animaltypeId = animaltypeId;
this.physicalProof = physicalProof;
this.averageMilkProduction = averageMilkProduction;
this.fatCheckStatus = fatCheckStatus;
this.fatBeforeAjola = fatBeforeAjola;
this.fatAfterAjola = fatAfterAjola;
this.milkQuantityBeforeAjola = milkQuantityBeforeAjola;
this.milkQuantityAfterAjola = milkQuantityAfterAjola;
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

public String getPhysicalProof() {
return physicalProof;
}

public void setPhysicalProof(String physicalProof) {
this.physicalProof = physicalProof;
}

public Float getAverageMilkProduction() {
return averageMilkProduction;
}

public void setAverageMilkProduction(Float averageMilkProduction) {
this.averageMilkProduction = averageMilkProduction;
}

public String getFatCheckStatus() {
return fatCheckStatus;
}

public void setFatCheckStatus(String fatCheckStatus) {
this.fatCheckStatus = fatCheckStatus;
}

public Integer getFatBeforeAjola() {
return fatBeforeAjola;
}

public void setFatBeforeAjola(Integer fatBeforeAjola) {
this.fatBeforeAjola = fatBeforeAjola;
}

public Integer getFatAfterAjola() {
return fatAfterAjola;
}

public void setFatAfterAjola(Integer fatAfterAjola) {
this.fatAfterAjola = fatAfterAjola;
}

public Float getMilkQuantityBeforeAjola() {
return milkQuantityBeforeAjola;
}

public void setMilkQuantityBeforeAjola(Float milkQuantityBeforeAjola) {
this.milkQuantityBeforeAjola = milkQuantityBeforeAjola;
}

public Float getMilkQuantityAfterAjola() {
return milkQuantityAfterAjola;
}

public void setMilkQuantityAfterAjola(Float milkQuantityAfterAjola) {
this.milkQuantityAfterAjola = milkQuantityAfterAjola;
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