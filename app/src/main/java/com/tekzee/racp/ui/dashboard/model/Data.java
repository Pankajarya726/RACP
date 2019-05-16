package com.tekzee.racp.ui.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

@SerializedName("profile")
@Expose
private Profile profile;
@SerializedName("homemenu")
@Expose
private List <Homemenu> homemenu = null;

public Profile getProfile() {
return profile;
}

public void setProfile(Profile profile) {
this.profile = profile;
}

public List<Homemenu> getHomemenu() {
return homemenu;
}

public void setHomemenu(List<Homemenu> homemenu) {
this.homemenu = homemenu;
}

}