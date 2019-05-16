package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormSubmitResponse {

@SerializedName("success")
@Expose
private Boolean success;
@SerializedName("message")
@Expose
private String message;

/**
* No args constructor for use in serialization
* 
*/
public FormSubmitResponse() {
}

/**
* 
* @param message
* @param success
*/
public FormSubmitResponse(Boolean success, String message) {
super();
this.success = success;
this.message = message;
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

}