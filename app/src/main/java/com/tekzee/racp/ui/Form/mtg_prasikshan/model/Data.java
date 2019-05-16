package com.tekzee.racp.ui.Form.mtg_prasikshan.model;

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
@SerializedName("training_date")
@Expose
private String trainingDate;
@SerializedName("trainee_number")
@Expose
private Integer traineeNumber;
@SerializedName("training_place")
@Expose
private String trainingPlace;
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
* @param tableId
* @param formId
* @param traineeNumber
* @param createdAt
* @param trainingPlace
* @param dateReceipt
* @param trainingDate
* @param note
*/
public Data(Integer tableId, Object dateReceipt, Integer formId, String createdAt, String trainingDate, Integer traineeNumber, String trainingPlace, String note) {
super();
this.tableId = tableId;
this.dateReceipt = dateReceipt;
this.formId = formId;
this.createdAt = createdAt;
this.trainingDate = trainingDate;
this.traineeNumber = traineeNumber;
this.trainingPlace = trainingPlace;
this.note = note;
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

public String getTrainingDate() {
return trainingDate;
}

public void setTrainingDate(String trainingDate) {
this.trainingDate = trainingDate;
}

public Integer getTraineeNumber() {
return traineeNumber;
}

public void setTraineeNumber(Integer traineeNumber) {
this.traineeNumber = traineeNumber;
}

public String getTrainingPlace() {
return trainingPlace;
}

public void setTrainingPlace(String trainingPlace) {
this.trainingPlace = trainingPlace;
}

public String getNote() {
return note;
}

public void setNote(String note) {
this.note = note;
}

}