package com.tekzee.racp.ui.selectMtgGroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMtgResponse {

@SerializedName("success")
@Expose
private Boolean success;
@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private List <Data> data = null;

/**
* No args constructor for use in serialization
* 
*/
public GetMtgResponse() {
}

/**
* 
* @param message
* @param data
* @param success
*/
public GetMtgResponse(Boolean success, String message, List<Data> data) {
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

public List<Data> getData() {
return data;
}

public void setData(List<Data> data) {
this.data = data;
}

}