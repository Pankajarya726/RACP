package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model;

import com.orm.SugarRecord;

public class Data extends SugarRecord {

    String data;

    public Data( String data) {

        this.data = data;
    }

    public Data() {
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}