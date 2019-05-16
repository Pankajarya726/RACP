package com.tekzee.racp.ui.Form.form_2.Model;

import com.orm.SugarRecord;

public class Record2 extends SugarRecord {

    String data;

    public Record2(String data) {
        this.data = data;
    }

    public Record2() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
