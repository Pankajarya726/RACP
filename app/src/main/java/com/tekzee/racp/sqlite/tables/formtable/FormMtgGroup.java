package com.tekzee.racp.sqlite.tables.formtable;

import com.orm.SugarRecord;

public class FormMtgGroup extends SugarRecord {

    String Name;
    String GramPanchayat;
    String GramPanchayatId;
    String Gram;
    String GramId;

    public FormMtgGroup() {
    }


    public FormMtgGroup(String name, String gramPanchayat, String gramPanchayatId, String gram, String gramId) {
        Name = name;
        GramPanchayat = gramPanchayat;
        GramPanchayatId = gramPanchayatId;
        Gram = gram;
        GramId = gramId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGramPanchayat() {
        return GramPanchayat;
    }

    public void setGramPanchayat(String gramPanchayat) {
        GramPanchayat = gramPanchayat;
    }

    public String getGramPanchayatId() {
        return GramPanchayatId;
    }

    public void setGramPanchayatId(String gramPanchayatId) {
        GramPanchayatId = gramPanchayatId;
    }

    public String getGram() {
        return Gram;
    }

    public void setGram(String gram) {
        Gram = gram;
    }

    public String getGramId() {
        return GramId;
    }

    public void setGramId(String gramId) {
        GramId = gramId;
    }
}
