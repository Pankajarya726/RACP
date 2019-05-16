package com.tekzee.racp.ui.Form.mtg_prasikshan.model;

import com.orm.SugarRecord;

public class DataMtgTraingin extends SugarRecord {

    String name;
    int mtg_id;
    String date;
    String place;
    String count;
    String note;


    public DataMtgTraingin() {
    }

    public DataMtgTraingin(String name, int mtg_id, String date, String place, String count, String note) {
        this.name = name;
        this.mtg_id = mtg_id;
        this.date = date;
        this.place = place;
        this.count = count;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public int getMtg_id() {
        return mtg_id;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getCount() {
        return count;
    }

    public String getNote() {
        return note;
    }
}
