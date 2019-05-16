package com.tekzee.racp.ui.Form.bakara_rotation;

import com.orm.SugarRecord;

public class DataBakaraRotation extends SugarRecord {

    String tagno;
    String name_before;
    String mtg_name_before;
    String address_before;
    String mobile_before;
    String vidhansabha_before;
    String grampanchayat_before;
    String gram_before;
    String tehsil_before;
    String name_after;
    String mtg_name_after;
    String address_after;
    String mobile_after;
    String vidhansabha_after;
    String grampanchayat_after;
    String gram_after;
    String tehsil_after;


    public DataBakaraRotation() {
    }

    public DataBakaraRotation(String tagno, String name_before, String mtg_name_before, String address_before, String mobile_before, String vidhansabha_before, String grampanchayat_before, String gram_before, String tehsil_before, String name_after, String mtg_name_after, String address_after, String mobile_after, String vidhansabha_after, String grampanchayat_after, String gram_after, String tehsil_after) {
        this.tagno = tagno;
        this.name_before = name_before;
        this.mtg_name_before = mtg_name_before;
        this.address_before = address_before;
        this.mobile_before = mobile_before;
        this.vidhansabha_before = vidhansabha_before;
        this.grampanchayat_before = grampanchayat_before;
        this.gram_before = gram_before;
        this.tehsil_before = tehsil_before;
        this.name_after = name_after;
        this.mtg_name_after = mtg_name_after;
        this.address_after = address_after;
        this.mobile_after = mobile_after;
        this.vidhansabha_after = vidhansabha_after;
        this.grampanchayat_after = grampanchayat_after;
        this.gram_after = gram_after;
        this.tehsil_after = tehsil_after;
    }

    public String getTagno() {
        return tagno;
    }

    public String getName_before() {
        return name_before;
    }

    public String getMtg_name_before() {
        return mtg_name_before;
    }

    public String getAddress_before() {
        return address_before;
    }

    public String getMobile_before() {
        return mobile_before;
    }

    public String getVidhansabha_before() {
        return vidhansabha_before;
    }

    public String getGrampanchayat_before() {
        return grampanchayat_before;
    }

    public String getGram_before() {
        return gram_before;
    }

    public String getTehsil_before() {
        return tehsil_before;
    }

    public String getName_after() {
        return name_after;
    }

    public String getMtg_name_after() {
        return mtg_name_after;
    }

    public String getAddress_after() {
        return address_after;
    }

    public String getMobile_after() {
        return mobile_after;
    }

    public String getVidhansabha_after() {
        return vidhansabha_after;
    }

    public String getGrampanchayat_after() {
        return grampanchayat_after;
    }

    public String getGram_after() {
        return gram_after;
    }

    public String getTehsil_after() {
        return tehsil_after;
    }
}
