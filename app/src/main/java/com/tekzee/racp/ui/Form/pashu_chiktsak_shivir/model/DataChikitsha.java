package com.tekzee.racp.ui.Form.pashu_chiktsak_shivir.model;

import com.orm.SugarRecord;

public class DataChikitsha extends SugarRecord {

    String place;
    String date;
    String count;
    String big;
    String small;
    String biomedical_west;
    boolean use;
    String note;

    public DataChikitsha() {
    }

    public DataChikitsha(String place, String date, String count, String big, String small, String biomedical_west, boolean use, String note) {
        this.place = place;
        this.date = date;
        this.count = count;
        this.big = big;
        this.small = small;
        this.biomedical_west = biomedical_west;
        this.use = use;
        this.note = note;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getCount() {
        return count;
    }

    public String getBig() {
        return big;
    }

    public String getSmall() {
        return small;
    }

    public String getBiomedical_west() {
        return biomedical_west;
    }

    public boolean isUse() {
        return use;
    }

    public String getNote() {
        return note;
    }
}
