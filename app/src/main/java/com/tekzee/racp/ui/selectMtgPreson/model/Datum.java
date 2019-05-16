package com.tekzee.racp.ui.selectMtgPreson.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

@SerializedName("pashupalak_id")
@Expose
private Integer pashupalakId;
@SerializedName("pashupalak_name")
@Expose
private String pashupalakName;

/**
* No args constructor for use in serialization
* 
*/
public Datum() {
}

/**
* 
* @param pashupalakId
* @param pashupalakName
*/
public Datum(Integer pashupalakId, String pashupalakName) {
super();
this.pashupalakId = pashupalakId;
this.pashupalakName = pashupalakName;
}

public Integer getPashupalakId() {
return pashupalakId;
}

public void setPashupalakId(Integer pashupalakId) {
this.pashupalakId = pashupalakId;
}

public String getPashupalakName() {
return pashupalakName;
}

public void setPashupalakName(String pashupalakName) {
this.pashupalakName = pashupalakName;
}

}