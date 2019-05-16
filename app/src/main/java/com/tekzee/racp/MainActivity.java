package com.tekzee.racp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.tekzee.racp.ui.dashboard.DashboardFragment;

public class MainActivity extends AppCompatActivity {

    Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = DashboardFragment.createInstance();
        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager  fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, fragment).commit();
    }
}
