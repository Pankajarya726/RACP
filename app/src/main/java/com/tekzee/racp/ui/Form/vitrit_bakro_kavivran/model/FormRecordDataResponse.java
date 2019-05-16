package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormRecordDataResponse {

@SerializedName("success")
@Expose
private Boolean success;
@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private Datum data;

/**
* No args constructor for use in serialization
*
*/
public FormRecordDataResponse() {
}

/**
*
* @param message
* @param data
* @param success
*/
public FormRecordDataResponse(Boolean success, String message, Datum data) {
super();
this.success = success;
this.message = message;
this.data = data;
}

public Boolean getSuccess() {
return success;
}

public void setSuccess(Boolean success) {
this.success = success;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public Datum getData() {
return data;
}

public void setData(Datum data) {
this.data = data;
}

}