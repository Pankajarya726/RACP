package com.tekzee.racp.ui.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sidemenu {

@SerializedName("menu_name")
@Expose
private String menuName;
@SerializedName("menu_image")
@Expose
private String menuImage;

    public Sidemenu(String menuName, String menuImage) {
        this.menuName = menuName;
        this.menuImage = menuImage;
    }

    public String getMenuName() {
return menuName;
}

public void setMenuName(String menuName) {
this.menuName = menuName;
}

public String getMenuImage() {
return menuImage;
}

public void setMenuImage(String menuImage) {
this.menuImage = menuImage;
}

}