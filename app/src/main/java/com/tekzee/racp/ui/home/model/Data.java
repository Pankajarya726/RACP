package com.tekzee.racp.ui.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

@SerializedName("profile")
@Expose
private Profile profile;
@SerializedName("sidemenu")
@Expose
private List <Sidemenu> sidemenu = null;

public Profile getProfile() {
return profile;
}

public void setProfile(Profile profile) {
this.profile = profile;
}

public List<Sidemenu> getSidemenu() {
return sidemenu;
}

public void setSidemenu(List<Sidemenu> sidemenu) {
this.sidemenu = sidemenu;
}

}