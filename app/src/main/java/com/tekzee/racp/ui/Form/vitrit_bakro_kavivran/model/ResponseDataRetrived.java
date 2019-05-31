package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDataRetrived {

@SerializedName("success")
@Expose
private Boolean success;
@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private Data1 data;

/**
* No args constructor for use in serialization
*
*/
public ResponseDataRetrived() {
}

/**
*
* @param message
* @param data
* @param success
*/
public ResponseDataRetrived(Boolean success, String message, Data1 data) {
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

public Data1 getData() {
return data;
}

public void setData(Data1 data) {
this.data = data;
}

}