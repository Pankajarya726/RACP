package com.tekzee.racp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tekzee.racp.R;

import cn.refactor.lib.colordialog.ColorDialog;

public class Dialogs {
    private Context ctx;

    public static void ShowDialog(Context context, String message) {
        KeyboardUtils.hideSoftInput((Activity) context);
        Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);

        dialog.setTitle(message);
        dialog.show();

    }

    public static void showcolorDialog(Context context, String msg) {
        ColorDialog dialog = new ColorDialog(context);
        dialog.setCancelable(false);
        dialog.setColor("#5eabbb");
        dialog.setContentText(msg);
        dialog.setPositiveListener(R.string.ok, new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {

                dialog.dismiss();
            }
        }).show();
    }





    public static void showColorDialog(Context context, String msg) {
        final Dialog dialog = new Dialog(context);
        try {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.custom_dialog1);
            dialog.getWindow().setLayout(-1, -2);
            dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);

            TextView textView = dialog.findViewById(R.id.dialog1_msg);

            textView.setText(msg);

            dialog.findViewById(R.id.dialog1_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void showDialog(Context context, String Msg) {


        final Dialog dialog = new Dialog(context);
        try {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.custom_dialog);
            dialog.getWindow().setLayout(-1, -2);
            dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);

            TextView textView = dialog.findViewById(R.id.yes);
            TextView textView1 = dialog.findViewById(R.id.no);

            TextView msg = dialog.findViewById(R.id.dialog_msg);


            msg.setText(Msg);


            View view = dialog.findViewById(R.id.viewad);
            view.setVisibility(View.GONE);

            dialog.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();

                }
            });

            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void ShowSelectionDialog(Context context, String Title, DialogClickListner listner) {
        final Dialog dialog = new Dialog(context);
        try {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.custom_dialog);
            dialog.getWindow().setLayout(-1, -2);
            dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);

            TextView textView = dialog.findViewById(R.id.yes);
            TextView textView1 = dialog.findViewById(R.id.no);

            textView.setText(context.getResources().getString(R.string.yes));
            textView1.setText(context.getResources().getString(R.string.no));

            TextView msg = dialog.findViewById(R.id.dialog_msg);
            msg.setText(Title);

            dialog.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listner.onNoClick();
                    dialog.dismiss();

                }
            });

            dialog.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onOkClick();
                   dialog.dismiss();

                }
            });


            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void ShowCustomDialog(Context context, String msg, okClickListner listner,String... title) {
        final Dialog dialog = new Dialog(context);
        try {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.custom_dialog1);
            dialog.getWindow().setLayout(-1, -2);
            dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);


            TextView tv_title = dialog.findViewById(R.id.dialog1_title);
            if(title.length > 0){

                if (title[0].equalsIgnoreCase("")){
                    tv_title.setText(context.getResources().getString(R.string.alert));
                }else
                {
                    tv_title.setText(title[0]);
                }


            }

            TextView textView = dialog.findViewById(R.id.dialog1_msg);

            textView.setText(msg);

            dialog.findViewById(R.id.dialog1_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onOkClickListner();
                    dialog.dismiss();
                }
            });
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public interface okClickListner {
        void onOkClickListner();
       // void onNoClick();
    }


 public interface DialogClickListner {
        void onOkClick();
        void onNoClick();
    }





}
