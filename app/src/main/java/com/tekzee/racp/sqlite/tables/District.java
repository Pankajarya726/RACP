package com.tekzee.racp.sqlite.tables;


import com.orm.SugarRecord;

public class District extends SugarRecord {

private Integer districtId;

private String districtName;


public District() {
}

public District(Integer districtId, String districtName) {
super();
this.districtId = districtId;
this.districtName = districtName;
}

public Integer getDistrictId() {
return districtId;
}

public void setDistrictId(Integer districtId) {
this.districtId = districtId;
}

public String getDistrictName() {
return districtName;
}

public void setDistrictName(String districtName) {
this.districtName = districtName;
}

}