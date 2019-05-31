package com.tekzee.racp.ui.Form.beema_detail.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("tag_no")
@Expose
private Object tagNo;
@SerializedName("date_receipt")
@Expose
private Object dateReceipt;

/**
* No args constructor for use in serialization
*
*/
public Datum() {
}

/**
*
* @param id
* @param dateReceipt
* @param tagNo
*/
public Datum(Integer id, Object tagNo, Object dateReceipt) {
super();
this.id = id;
this.tagNo = tagNo;
this.dateReceipt = dateReceipt;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Object getTagNo() {
return tagNo;
}

public void setTagNo(Object tagNo) {
this.tagNo = tagNo;
}

public Object getDateReceipt() {
return dateReceipt;
}

public void setDateReceipt(Object dateReceipt) {
this.dateReceipt = dateReceipt;
}

}