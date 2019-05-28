package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class Vidhanasabha extends SugarRecord {


    private Integer vidhanasabhaId;
    private String vidhanasabhaName;
    private Integer districtId;

    /**
     * No args constructor for use in serialization
     */
    public Vidhanasabha() {
    }

    /**
     * @param districtId
     * @param vidhanasabhaName
     * @param vidhanasabhaId
     */
    public Vidhanasabha(Integer vidhanasabhaId, String vidhanasabhaName, Integer districtId) {
        super();
        this.vidhanasabhaId = vidhanasabhaId;
        this.vidhanasabhaName = vidhanasabhaName;
        this.districtId = districtId;
    }

    public Integer getVidhanasabhaId() {
        return vidhanasabhaId;
    }

    public void setVidhanasabhaId(Integer vidhanasabhaId) {
        this.vidhanasabhaId = vidhanasabhaId;
    }

    public String getVidhanasabhaName() {
        return vidhanasabhaName;
    }

    public void setVidhanasabhaName(String vidhanasabhaName) {
        this.vidhanasabhaName = vidhanasabhaName;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

}