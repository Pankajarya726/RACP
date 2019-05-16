package com.tekzee.racp.ui.Form.bakari_vitran.model;

import com.orm.SugarRecord;

public class TeekaData extends SugarRecord {
    String data;

    public TeekaData(String data) {
        this.data = data;
    }

    public TeekaData() {

    }

    public String getData() {

        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
