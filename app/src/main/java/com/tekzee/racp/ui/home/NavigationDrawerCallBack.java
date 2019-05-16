package com.tekzee.racp.ui.home;

import com.tekzee.racp.ui.home.model.NavItem;
import com.tekzee.racp.ui.home.model.Sidemenu;

import java.util.List;

public interface NavigationDrawerCallBack {

    void onNavigationDrawerItemSelected(int position, List <Sidemenu> items, NavigationAdapter adapter);
}
