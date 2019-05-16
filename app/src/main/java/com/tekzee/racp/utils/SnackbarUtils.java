package com.tekzee.racp.utils;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


public final class SnackbarUtils {

    public static void snackBarBottom(View view, String message){

        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();

    }

    public static void snackBarTop(View view, String message){
        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_LONG);
        View view1 = snackbar.getView();

        try {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
            params.gravity = Gravity.TOP;
            view1.setLayoutParams(params);
        }catch (Exception e){

            try {
                DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) view1.getLayoutParams();
                params.gravity = Gravity.TOP;
                view1.setLayoutParams(params);
            }catch (Exception e1){
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view1.getLayoutParams();
                params.gravity = Gravity.TOP;
                view1.setLayoutParams(params);

            }

        }


//        View sbView = snackbar.getView();
        TextView textView = (TextView) view1.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();

    }

}
