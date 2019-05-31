package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model;

import com.orm.SugarRecord;

public class BakariDataModel extends SugarRecord {

    String tag_no;
    String age;
    String weight;
    String milk_procution;
    String nasl;
    int naslid;


    public BakariDataModel() {
    }

    public BakariDataModel(String tag_no, String age, String weight,String milk_procution,String nasl, int naslid) {
        this.tag_no = tag_no;
        this.age = age;
        this.weight = weight;
        this.nasl = nasl;
        this.naslid = naslid;
        this.milk_procution = milk_procution;
    }

    public String getTag_no() {
        return tag_no;
    }

    public void setTag_no(String tag_no) {
        this.tag_no = tag_no;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getNasl() {
        return nasl;
    }

    public void setNasl(String nasl) {
        this.nasl = nasl;
    }

    public int getNaslid() {
        return naslid;
    }

    public void setNaslid(int naslid) {
        this.naslid = naslid;
    }

    public String getMilk_procution() {
        return milk_procution;
    }

    public void setMilk_procution(String milk_procution) {
        this.milk_procution = milk_procution;
    }
}
