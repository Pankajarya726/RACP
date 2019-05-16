package com.tekzee.racp.ui.add_animal_owner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPashuPalakResponse {

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
public AddPashuPalakResponse() {
}

/**
* 
* @param message
* @param success
*/
public AddPashuPalakResponse(Boolean success, String message) {
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