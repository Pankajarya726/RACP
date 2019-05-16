
package com.tekzee.racp.ui.info.model;


import com.google.gson.annotations.SerializedName;

public class Test {

    @SerializedName("image")
    private String mImage;
    @SerializedName("info")
    private String mInfo;

    public String getImage() {
        return mImage;
    }

    public String getInfo() {
        return mInfo;
    }


}
