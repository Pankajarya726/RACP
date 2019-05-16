package com.tekzee.racp.ui.formselection.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tekzee.racp.R;

public class GetAllFormResponse {

@SerializedName("success")
@Expose
private Boolean success;
@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private List<FormData> data = null;

/**
* No args constructor for use in serialization
*
*/
public GetAllFormResponse() {
}

/**
*
* @param message
* @param data
* @param success
*/
public GetAllFormResponse(Boolean success, String message, List<FormData> data) {
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

public List<FormData> getData() {
return data;
}

public void setData(List<FormData> data) {
this.data = data;
}

}