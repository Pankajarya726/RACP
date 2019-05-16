package com.tekzee.racp.ui.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Homemenu {

    @SerializedName("menu_id")
    @Expose
    private Integer menuId;
    @SerializedName("menu_name")
    @Expose
    private String menuName;
    @SerializedName("menu_image")
    @Expose
    private String menuImage;

    /**
     * No args constructor for use in serialization
     *
     */
    public Homemenu() {
    }

    /**
     *
     * @param menuImage
     * @param menuId
     * @param menuName
     */
    public Homemenu(Integer menuId, String menuName, String menuImage) {
        super();
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuImage = menuImage;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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