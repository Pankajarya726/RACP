package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model;

import com.orm.SugarRecord;

public class Record extends SugarRecord {

    int recordNo;
    String data;

    public Record(int recordNo, String data) {
        this.recordNo = recordNo;
        this.data = data;
    }

    public Record() {
    }

    public int getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(int recordNo) {
        this.recordNo = recordNo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
