package com.tekzee.racp.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.constant.GlideModuleConstant;
import com.tekzee.racp.sqlite.SqliteDB;
import com.tekzee.racp.ui.addMGTgroup.AddMtgActivity;
import com.tekzee.racp.ui.add_animal_owner.AddOwnerActivity;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.dashboard.DashboardFragment;
import com.tekzee.racp.ui.home.model.SideMenuResponse;
import com.tekzee.racp.ui.home.model.Sidemenu;
import com.tekzee.racp.ui.info.InfoActivity;
import com.tekzee.racp.ui.login.activity.Login;
import com.tekzee.racp.ui.selectMtgGroup.SelectMtgActivity;
import com.tekzee.racp.utils.PhotoFullPopupWindow;
import com.tekzee.racp.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends MvpActivity <homePresenter>

        implements HomeView, NavigationView.OnNavigationItemSelectedListener, NavigationDrawerCallBack {

    private static final String TAG = HomeActivity.class.getSimpleName();
    public RecyclerView recyclerView;
    public NavigationAdapter adapter;
    public ProgressDialog progressDialog;
    Fragment fragment = null;
    DrawerLayout drawer;
    //List <NavItem> navItemList = new ArrayList <>();
    List <Sidemenu> sidemenu;
    SqliteDB sqliteDB = new SqliteDB(getContext());
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.dashboard);
        Log.e(TAG, String.valueOf(1));

        getNAvigationData();
        inti();

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ImageView image_profile = navigationView.findViewById(R.id.header_img);
        TextView tv_name = navigationView.findViewById(R.id.header_name);
        TextView tv_mobile = navigationView.findViewById(R.id.header_mobile);
        image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PhotoFullPopupWindow(getContext(), R.layout.popup_photo_full, image_profile, Utility.getSharedPreferences(getContext(),Constant.image), null);

            }
        });

        //;

        setHeaderData(image_profile, tv_name, tv_mobile);
        recyclerView = navigationView.findViewById(R.id.recycler_nav);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.menu);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected homePresenter createPresenter() {
        return new homePresenter(this);
    }


    @Override
    public void onBackPressed() {

        this.finish();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void replaseFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.disallowAddToBackStack();
        transaction.replace(R.id.container, fragment).commit();
    }

    private void inti() {

        Fragment fragment = new DashboardFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.disallowAddToBackStack();
        transaction.add(R.id.container, fragment).commit();


    }

    @Override
    public void onNavigationDrawerItemSelected(int position, List <Sidemenu> items, NavigationAdapter adapter) {

        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        this.sidemenu = items;
        this.adapter = adapter;
        selectTab(position, items, adapter);

    }


    private void selectTab(int position, List <Sidemenu> items, NavigationAdapter adapter) {

        fragment = null;
        Log.e(TAG, "" + position);

        switch (position) {
            case 0:
                //fragment = new DashboardFragment();
                drawer.closeDrawers();
                break;
            case 1:
                Log.e(TAG, "" + position);
                startActivity(new Intent(HomeActivity.this, AddOwnerActivity.class));
                drawer.closeDrawers();
                break;

            case 2:
                startActivity(new Intent(HomeActivity.this, SelectMtgActivity.class));
                drawer.closeDrawers();
                break;
            case 3:
                Intent intent = new Intent(HomeActivity.this, SelectMtgActivity.class);
                intent.putExtra("filled_form", 1);
                startActivity(intent);
                drawer.closeDrawers();
                break;
            case 4:
                startActivity(new Intent(HomeActivity.this, AddMtgActivity.class));
                drawer.closeDrawers();
                break;

            case 5:
                startActivity(new Intent(HomeActivity.this, Login.class));
                Utility.removepreference(getContext());
                finish();
                drawer.closeDrawers();
                break;

            case 6:
                startActivity(new Intent(HomeActivity.this, InfoActivity.class));
                drawer.closeDrawers();
                break;

        }
        if (fragment != null) {
            Log.e(TAG, "" + position);
            replaseFragment(fragment);
        }

    }

    private void setHeaderData(ImageView image_profile, TextView tv_name, TextView tv_mobile) {

        com.tekzee.racp.utils.Log.view(TAG,Utility.getSharedPreferences(getContext(),Constant.image));
        GlideModuleConstant.setCircleImage(image_profile, this, Utility.getSharedPreferences(getContext(),Constant.image));
        tv_mobile.setText(Utility.getSharedPreferences(this, Constant.Mobile));
        tv_name.setText(Utility.getSharedPreferences(this, Constant.UserName));

    }

    private void getNAvigationData() {
        JsonObject input = new JsonObject();
        input.addProperty("userId", Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
        input.addProperty("mobile", Utility.getSharedPreferences(getContext(), Constant.Mobile));
        mvpPresenter.getSideMenuData(input);
        /*progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
*/
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onNoInternetConnectivity(CommonResult result) {

        Cursor cursor = sqliteDB.getSideMenu();
        if (cursor.getCount() == 0) {
            Log.e("dadfasdf ", "" + cursor.getCount());
        } else {
            sidemenu = new ArrayList <>();
            while (cursor.moveToNext()) {
                Log.e("data", cursor.getString(0) + "" + cursor.getString(1));
                sidemenu.add(new Sidemenu(cursor.getString(0), cursor.getString(1)));
            }
        }

        RecyclerView recyclerView1 = findViewById(R.id.recycler_nav);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(linearLayoutManager);
        NavigationAdapter navigationAdapter = new NavigationAdapter(sidemenu, getContext());
        recyclerView1.setAdapter(navigationAdapter);
        navigationAdapter.setNavigationDrawerCallbacks(this);
    }

    @Override
    public void onSuccess(SideMenuResponse successResult) {
        Utility.setSharedPreference(getContext(),Constant.image,successResult.getData().getProfile().getProfileImage());

        sqliteDB.clearSideMenu();
        sidemenu = new ArrayList <>();
        sidemenu.removeAll(successResult.getData().getSidemenu());
        sidemenu.addAll(successResult.getData().getSidemenu());

        for (int i = 0; i < sidemenu.size(); i++) {
            Sidemenu side = successResult.getData().getSidemenu().get(i);
            sqliteDB.addSideMenu(side.getMenuName(), side.getMenuImage());
        }
        progressDialog.dismiss();
        setUpRecyclerView(sidemenu);
    }

    private void setUpRecyclerView(List <Sidemenu> sidemenu) {

        List <Sidemenu> sidemenus = new ArrayList <>();

        sidemenus = sidemenu;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NavigationAdapter(sidemenus, this);
        recyclerView.setAdapter(adapter);
        adapter.setNavigationDrawerCallbacks(this);
    }


}
