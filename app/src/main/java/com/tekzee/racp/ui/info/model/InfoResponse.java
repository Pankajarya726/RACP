package com.tekzee.racp.ui.info.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoResponse {

@SerializedName("image")
@Expose
private String image;
@SerializedName("info")
@Expose
private String info;

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public String getInfo() {
return info;
}

public void setInfo(String info) {
this.info = info;
}

}