package com.tekzee.racp.ui.Form.ajola.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

@SerializedName("animaltype_id")
@Expose
private Integer animaltypeId;
@SerializedName("animaltype_Name")
@Expose
private String animaltypeName;

/**
* No args constructor for use in serialization
*
*/
public Datum() {
}

/**
*
* @param animaltypeId
* @param animaltypeName
*/
public Datum(Integer animaltypeId, String animaltypeName) {
super();
this.animaltypeId = animaltypeId;
this.animaltypeName = animaltypeName;
}

public Integer getAnimaltypeId() {
return animaltypeId;
}

public void setAnimaltypeId(Integer animaltypeId) {
this.animaltypeId = animaltypeId;
}

public String getAnimaltypeName() {
return animaltypeName;
}

public void setAnimaltypeName(String animaltypeName) {
this.animaltypeName = animaltypeName;
}

}