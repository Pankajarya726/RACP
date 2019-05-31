package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BakriDatum {

@SerializedName("bakri_tag_no")
@Expose
private Integer bakriTagNo;
@SerializedName("bakri_weight")
@Expose
private Integer bakriWeight;
@SerializedName("bakri_age")
@Expose
private Integer bakriAge;
@SerializedName("average_milk_production")
@Expose
private Integer averageMilkProduction;
@SerializedName("bakri_nasl")
@Expose
private Integer bakriNasl;

/**
* No args constructor for use in serialization
*
*/
public BakriDatum() {
}

/**
*
* @param bakriNasl
* @param bakriAge
* @param bakriTagNo
* @param averageMilkProduction
* @param bakriWeight
*/
public BakriDatum(Integer bakriTagNo, Integer bakriWeight, Integer bakriAge, Integer averageMilkProduction, Integer bakriNasl) {
super();
this.bakriTagNo = bakriTagNo;
this.bakriWeight = bakriWeight;
this.bakriAge = bakriAge;
this.averageMilkProduction = averageMilkProduction;
this.bakriNasl = bakriNasl;
}

public Integer getBakriTagNo() {
return bakriTagNo;
}

public void setBakriTagNo(Integer bakriTagNo) {
this.bakriTagNo = bakriTagNo;
}

public Integer getBakriWeight() {
return bakriWeight;
}

public void setBakriWeight(Integer bakriWeight) {
this.bakriWeight = bakriWeight;
}

public Integer getBakriAge() {
return bakriAge;
}

public void setBakriAge(Integer bakriAge) {
this.bakriAge = bakriAge;
}

public Integer getAverageMilkProduction() {
return averageMilkProduction;
}

public void setAverageMilkProduction(Integer averageMilkProduction) {
this.averageMilkProduction = averageMilkProduction;
}

public Integer getBakriNasl() {
return bakriNasl;
}

public void setBakriNasl(Integer bakriNasl) {
this.bakriNasl = bakriNasl;
}

}