package com.tekzee.racp.ui.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("role_id")
    @Expose
    private Integer roleId;
    @SerializedName("role_name")
    @Expose
    private String roleName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public Integer getUserId() {
    return userId;
    }

    public void setUserId(Integer userId) {
    this.userId = userId;
    }

    public String getUserName() {
    return userName;
    }

    public void setUserName(String userName) {
    this.userName = userName;
    }

    public Integer getRoleId() {
    return roleId;
    }

    public void setRoleId(Integer roleId) {
    this.roleId = roleId;
    }

    public String getRoleName() {
    return roleName;
    }

    public void setRoleName(String roleName) {
    this.roleName = roleName;
    }

    public String getEmail() {
    return email;
    }

    public void setEmail(String email) {
    this.email = email;
    }

    public String getMobile() {
    return mobile;
    }

    public void setMobile(String mobile) {
    this.mobile = mobile;
    }

}