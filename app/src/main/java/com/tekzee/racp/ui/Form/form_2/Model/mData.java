package com.tekzee.racp.ui.Form.form_2.Model;

import com.orm.SugarRecord;

public class mData extends SugarRecord {

    String tag_no;
    String dd;
    String mm;
    String yy;
    String age;
    String average;
    int nasl_Selection;
    String nasl;
    String note;

    public mData() {
    }

    public mData(String tag_no, String dd, String mm, String yy, String age, String average, int nasl_Selection, String nasl, String note) {
        this.tag_no = tag_no;
        this.dd = dd;
        this.mm = mm;
        this.yy = yy;
        this.age = age;
        this.average = average;
        this.nasl_Selection = nasl_Selection;
        this.nasl = nasl;
        this.note = note;
    }

    public String getTag_no() {
        return tag_no;
    }

    public String getDd() {
        return dd;
    }

    public String getMm() {
        return mm;
    }

    public String getYy() {
        return yy;
    }

    public String getAge() {
        return age;
    }

    public String getAverage() {
        return average;
    }

    public int getNasl_Selection() {
        return nasl_Selection;
    }

    public String getNasl() {
        return nasl;
    }

    public String getNote() {
        return note;
    }
}
