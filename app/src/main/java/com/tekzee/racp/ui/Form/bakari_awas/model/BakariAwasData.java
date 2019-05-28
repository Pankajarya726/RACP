package com.tekzee.racp.ui.Form.bakari_awas.model;

import com.orm.SugarRecord;

import java.sql.Blob;

public class BakariAwasData extends SugarRecord {

    String member_id;
    String group_id;
    Blob image;
    String lattitude;
    String longitude;
    String physical_proof;
    String usability;
    String build_date;
    String note;
    String dd;
    String mm;
    String yy;

    public BakariAwasData() {
    }



    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }



    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhysical_proof() {
        return physical_proof;
    }

    public void setPhysical_proof(String physical_proof) {
        this.physical_proof = physical_proof;
    }

    public String getUsability() {
        return usability;
    }

    public void setUsability(String usability) {
        this.usability = usability;
    }
}
