package com.tekzee.racp.ui.Form.bakara_rotation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrivedBakraRotationResponse {

@SerializedName("success")
@Expose
private Boolean success;
@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private Data data;

/**
* No args constructor for use in serialization
*
*/
public RetrivedBakraRotationResponse() {
}

/**
*
* @param message
* @param data
* @param success
*/
public RetrivedBakraRotationResponse(Boolean success, String message, Data data) {
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

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

}