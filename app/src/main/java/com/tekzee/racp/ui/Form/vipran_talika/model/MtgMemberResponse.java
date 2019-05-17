package com.tekzee.racp.ui.Form.vipran_talika.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MtgMemberResponse {

@SerializedName("success")
@Expose
private Boolean success;
@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private List <Datum> data = new ArrayList <>();

/**
* No args constructor for use in serialization
*
*/
public MtgMemberResponse() {
}

/**
*
* @param message
* @param data
* @param success
*/
public MtgMemberResponse(Boolean success, String message, List<Datum> data) {
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

public List<Datum> getData() {
return data;
}

public void setData(List<Datum> data) {
this.data = data;
}

}