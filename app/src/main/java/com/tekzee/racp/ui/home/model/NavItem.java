package com.tekzee.racp.ui.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NavItem {
    @SerializedName("txt")
    @Expose
    private String txt;

    @SerializedName("img")
    @Expose
    private int img;

    public NavItem(String txt, int img) {
        this.txt = txt;
        this.img = img;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
