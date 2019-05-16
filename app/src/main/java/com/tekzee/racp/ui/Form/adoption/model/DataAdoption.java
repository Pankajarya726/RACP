package com.tekzee.racp.ui.Form.adoption.model;

import com.orm.SugarRecord;

public class DataAdoption extends SugarRecord {

    String activity;
    String name;
    String fahter_name;
    String address;
    String district;
    int district_id;
    String vidhansabha;
    int vidhansabha_id;
    String tehsil;
    int tehsil_id;
    String grampanchayat;
    int gramPanchayat_id;
    String gram;
    int gram_id;
    String mobile;
    String note;

    public DataAdoption() {
    }

    public DataAdoption(String activity, String name, String fahter_name, String address, String district, int district_id, String vidhansabha, int vidhansabha_id, String tehsil, int tehsil_id, String grampanchayat, int gramPanchayat_id, String gram, int gram_id, String mobile, String note) {
        this.activity = activity;
        this.name = name;
        this.fahter_name = fahter_name;
        this.address = address;
        this.district = district;
        this.district_id = district_id;
        this.vidhansabha = vidhansabha;
        this.vidhansabha_id = vidhansabha_id;
        this.tehsil = tehsil;
        this.tehsil_id = tehsil_id;
        this.grampanchayat = grampanchayat;
        this.gramPanchayat_id = gramPanchayat_id;
        this.gram = gram;
        this.gram_id = gram_id;
        this.mobile = mobile;
        this.note = note;
    }

    public String getActivity() {
        return activity;
    }

    public String getName() {
        return name;
    }

    public String getFahter_name() {
        return fahter_name;
    }

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public String getMobile() {
        return mobile;
    }

    public String getVidhansabha() {
        return vidhansabha;
    }

    public int getVidhansabha_id() {
        return vidhansabha_id;
    }

    public String getTehsil() {
        return tehsil;
    }

    public int getTehsil_id() {
        return tehsil_id;
    }

    public String getGrampanchayat() {
        return grampanchayat;
    }

    public int getGramPanchayat_id() {
        return gramPanchayat_id;
    }

    public String getGram() {
        return gram;
    }

    public int getGram_id() {
        return gram_id;
    }

    public String getNote() {
        return note;
    }
}
