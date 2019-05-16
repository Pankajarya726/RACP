
package com.tekzee.racp.ui.Form.weighting_machine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("table_id")
@Expose
private Integer tableId;
@SerializedName("animaltype_id")
@Expose
private Integer animaltypeId;
@SerializedName("physical_proof")
@Expose
private String physicalProof;
@SerializedName("usability")
@Expose
private String usability;
@SerializedName("bakra_tag_no")
@Expose
private Integer bakraTagNo;
@SerializedName("bakra_bhar")
@Expose
private Integer bakraBhar;
@SerializedName("bakra_age")
@Expose
private Integer bakraAge;
@SerializedName("bakri_tag_no")
@Expose
private Object bakriTagNo;
@SerializedName("bakri_bhar")
@Expose
private Object bakriBhar;
@SerializedName("bakri_age")
@Expose
private Object bakriAge;
@SerializedName("nar_tag_no")
@Expose
private Object narTagNo;
@SerializedName("nar_bhar")
@Expose
private Object narBhar;
@SerializedName("nar_age")
@Expose
private Object narAge;
@SerializedName("mada_tag_no")
@Expose
private Object madaTagNo;
@SerializedName("mada_bhar")
@Expose
private Object madaBhar;
@SerializedName("mada_age")
@Expose
private Object madaAge;
@SerializedName("parent_bakre_ka_tag_no")
@Expose
private Object parentBakreKaTagNo;
@SerializedName("parent_bakre_ka_bhar")
@Expose
private Object parentBakreKaBhar;
@SerializedName("parent_bakre_ki_age")
@Expose
private Object parentBakreKiAge;
@SerializedName("parent_bakri_ka_tag_no")
@Expose
private Object parentBakriKaTagNo;
@SerializedName("parent_bakri_ka_bhar")
@Expose
private Object parentBakriKaBhar;
@SerializedName("parent_bakri_ki_age")
@Expose
private Object parentBakriKiAge;
@SerializedName("date_receipt")
@Expose
private String dateReceipt;
@SerializedName("form_id")
@Expose
private Integer formId;

/**
* No args constructor for use in serialization
*
*/
public Data() {
}

/**
*
* @param parentBakreKaBhar
* @param madaAge
* @param narAge
* @param parentBakreKaTagNo
* @param bakriBhar
* @param parentBakreKiAge
* @param dateReceipt
* @param narBhar
* @param parentBakriKaTagNo
* @param parentBakriKiAge
* @param madaTagNo
* @param usability
* @param parentBakriKaBhar
* @param tableId
* @param formId
* @param bakraBhar
* @param bakriTagNo
* @param bakriAge
* @param bakraTagNo
* @param bakraAge
* @param narTagNo
* @param animaltypeId
* @param madaBhar
* @param physicalProof
*/
public Data(Integer tableId, Integer animaltypeId, String physicalProof, String usability, Integer bakraTagNo, Integer bakraBhar, Integer bakraAge, Object bakriTagNo, Object bakriBhar, Object bakriAge, Object narTagNo, Object narBhar, Object narAge, Object madaTagNo, Object madaBhar, Object madaAge, Object parentBakreKaTagNo, Object parentBakreKaBhar, Object parentBakreKiAge, Object parentBakriKaTagNo, Object parentBakriKaBhar, Object parentBakriKiAge, String dateReceipt, Integer formId) {
super();
this.tableId = tableId;
this.animaltypeId = animaltypeId;
this.physicalProof = physicalProof;
this.usability = usability;
this.bakraTagNo = bakraTagNo;
this.bakraBhar = bakraBhar;
this.bakraAge = bakraAge;
this.bakriTagNo = bakriTagNo;
this.bakriBhar = bakriBhar;
this.bakriAge = bakriAge;
this.narTagNo = narTagNo;
this.narBhar = narBhar;
this.narAge = narAge;
this.madaTagNo = madaTagNo;
this.madaBhar = madaBhar;
this.madaAge = madaAge;
this.parentBakreKaTagNo = parentBakreKaTagNo;
this.parentBakreKaBhar = parentBakreKaBhar;
this.parentBakreKiAge = parentBakreKiAge;
this.parentBakriKaTagNo = parentBakriKaTagNo;
this.parentBakriKaBhar = parentBakriKaBhar;
this.parentBakriKiAge = parentBakriKiAge;
this.dateReceipt = dateReceipt;
this.formId = formId;
}

public Integer getTableId() {
return tableId;
}

public void setTableId(Integer tableId) {
this.tableId = tableId;
}

public Integer getAnimaltypeId() {
return animaltypeId;
}

public void setAnimaltypeId(Integer animaltypeId) {
this.animaltypeId = animaltypeId;
}

public String getPhysicalProof() {
return physicalProof;
}

public void setPhysicalProof(String physicalProof) {
this.physicalProof = physicalProof;
}

public String getUsability() {
return usability;
}

public void setUsability(String usability) {
this.usability = usability;
}

public Integer getBakraTagNo() {
return bakraTagNo;
}

public void setBakraTagNo(Integer bakraTagNo) {
this.bakraTagNo = bakraTagNo;
}

public Integer getBakraBhar() {
return bakraBhar;
}

public void setBakraBhar(Integer bakraBhar) {
this.bakraBhar = bakraBhar;
}

public Integer getBakraAge() {
return bakraAge;
}

public void setBakraAge(Integer bakraAge) {
this.bakraAge = bakraAge;
}

public Object getBakriTagNo() {
return bakriTagNo;
}

public void setBakriTagNo(Object bakriTagNo) {
this.bakriTagNo = bakriTagNo;
}

public Object getBakriBhar() {
return bakriBhar;
}

public void setBakriBhar(Object bakriBhar) {
this.bakriBhar = bakriBhar;
}

public Object getBakriAge() {
return bakriAge;
}

public void setBakriAge(Object bakriAge) {
this.bakriAge = bakriAge;
}

public Object getNarTagNo() {
return narTagNo;
}

public void setNarTagNo(Object narTagNo) {
this.narTagNo = narTagNo;
}

public Object getNarBhar() {
return narBhar;
}

public void setNarBhar(Object narBhar) {
this.narBhar = narBhar;
}

public Object getNarAge() {
return narAge;
}

public void setNarAge(Object narAge) {
this.narAge = narAge;
}

public Object getMadaTagNo() {
return madaTagNo;
}

public void setMadaTagNo(Object madaTagNo) {
this.madaTagNo = madaTagNo;
}

public Object getMadaBhar() {
return madaBhar;
}

public void setMadaBhar(Object madaBhar) {
this.madaBhar = madaBhar;
}

public Object getMadaAge() {
return madaAge;
}

public void setMadaAge(Object madaAge) {
this.madaAge = madaAge;
}

public Object getParentBakreKaTagNo() {
return parentBakreKaTagNo;
}

public void setParentBakreKaTagNo(Object parentBakreKaTagNo) {
this.parentBakreKaTagNo = parentBakreKaTagNo;
}

public Object getParentBakreKaBhar() {
return parentBakreKaBhar;
}

public void setParentBakreKaBhar(Object parentBakreKaBhar) {
this.parentBakreKaBhar = parentBakreKaBhar;
}

public Object getParentBakreKiAge() {
return parentBakreKiAge;
}

public void setParentBakreKiAge(Object parentBakreKiAge) {
this.parentBakreKiAge = parentBakreKiAge;
}

public Object getParentBakriKaTagNo() {
return parentBakriKaTagNo;
}

public void setParentBakriKaTagNo(Object parentBakriKaTagNo) {
this.parentBakriKaTagNo = parentBakriKaTagNo;
}

public Object getParentBakriKaBhar() {
return parentBakriKaBhar;
}

public void setParentBakriKaBhar(Object parentBakriKaBhar) {
this.parentBakriKaBhar = parentBakriKaBhar;
}

public Object getParentBakriKiAge() {
return parentBakriKiAge;
}

public void setParentBakriKiAge(Object parentBakriKiAge) {
this.parentBakriKiAge = parentBakriKiAge;
}

public String getDateReceipt() {
return dateReceipt;
}

public void setDateReceipt(String dateReceipt) {
this.dateReceipt = dateReceipt;
}

public Integer getFormId() {
return formId;
}

public void setFormId(Integer formId) {
this.formId = formId;
}

}