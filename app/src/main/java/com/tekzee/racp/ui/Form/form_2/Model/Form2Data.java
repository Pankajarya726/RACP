package com.tekzee.racp.ui.Form.form_2.Model;

import com.orm.SugarRecord;

public class Form2Data extends SugarRecord {
    String data;

    public Form2Data(String data) {

        this.data = data;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
