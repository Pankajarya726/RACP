package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhysicalProofData {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("form_table_id")
@Expose
private Integer formTableId;
@SerializedName("form_id")
@Expose
private Integer formId;
@SerializedName("date_physical_proof")
@Expose
private String datePhysicalProof;
@SerializedName("image_upload")
@Expose
private String imageUpload;
@SerializedName("img_lat")
@Expose
private String imgLat;
@SerializedName("img_long")
@Expose
private String imgLong;
@SerializedName("mtggroup_id")
@Expose
private Integer mtggroupId;
@SerializedName("pashupalak_id")
@Expose
private Integer pashupalakId;
@SerializedName("tag_no")
@Expose
private Object tagNo;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;

/**
* No args constructor for use in serialization
*
*/
public PhysicalProofData() {
}

/**
*
* @param updatedAt
* @param id
* @param imageUpload
* @param pashupalakId
* @param datePhysicalProof
* @param formId
* @param createdAt
* @param imgLong
* @param formTableId
* @param tagNo
* @param mtggroupId
* @param imgLat
*/
public PhysicalProofData(Integer id, Integer formTableId, Integer formId, String datePhysicalProof, String imageUpload, String imgLat, String imgLong, Integer mtggroupId, Integer pashupalakId, Object tagNo, String createdAt, String updatedAt) {
super();
this.id = id;
this.formTableId = formTableId;
this.formId = formId;
this.datePhysicalProof = datePhysicalProof;
this.imageUpload = imageUpload;
this.imgLat = imgLat;
this.imgLong = imgLong;
this.mtggroupId = mtggroupId;
this.pashupalakId = pashupalakId;
this.tagNo = tagNo;
this.createdAt = createdAt;
this.updatedAt = updatedAt;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getFormTableId() {
return formTableId;
}

public void setFormTableId(Integer formTableId) {
this.formTableId = formTableId;
}

public Integer getFormId() {
return formId;
}

public void setFormId(Integer formId) {
this.formId = formId;
}

public String getDatePhysicalProof() {
return datePhysicalProof;
}

public void setDatePhysicalProof(String datePhysicalProof) {
this.datePhysicalProof = datePhysicalProof;
}

public String getImageUpload() {
return imageUpload;
}

public void setImageUpload(String imageUpload) {
this.imageUpload = imageUpload;
}

public String getImgLat() {
return imgLat;
}

public void setImgLat(String imgLat) {
this.imgLat = imgLat;
}

public String getImgLong() {
return imgLong;
}

public void setImgLong(String imgLong) {
this.imgLong = imgLong;
}

public Integer getMtggroupId() {
return mtggroupId;
}

public void setMtggroupId(Integer mtggroupId) {
this.mtggroupId = mtggroupId;
}

public Integer getPashupalakId() {
return pashupalakId;
}

public void setPashupalakId(Integer pashupalakId) {
this.pashupalakId = pashupalakId;
}

public Object getTagNo() {
return tagNo;
}

public void setTagNo(Object tagNo) {
this.tagNo = tagNo;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

}