package com.tekzee.racp.ui.addMGTgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gram {

@SerializedName("gram_id")
@Expose
private Integer gramId;
@SerializedName("gram_name")
@Expose
private String gramName;

public Integer getGramId() {
return gramId;
}

public void setGramId(Integer gramId) {
this.gramId = gramId;
}

public String getGramName() {
return gramName;
}

    public Gram(Integer gramId, String gramName) {
        this.gramId = gramId;
        this.gramName = gramName;
    }

    public void setGramName(String gramName) {
this.gramName = gramName;
}

}