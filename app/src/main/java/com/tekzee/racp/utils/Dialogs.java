package com.tekzee.racp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tekzee.racp.R;
import com.tekzee.racp.RacpApplication;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.ui.base.BaseView;

import org.json.JSONException;
import org.json.JSONObject;

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
        dialog.setColor("#FF6500");
        dialog.setContentText(msg);
        dialog.setPositiveListener("ok", new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {

                dialog.dismiss();
            }
        }).show();
    }



    public static void showColorDialog(Context context,String msg){
        ColorDialog dialog = new ColorDialog(context);
        dialog.setCancelable(false);
        dialog.setTitle(R.string.app_name);
        dialog.setColor("#FF6500");
        dialog.setContentText(msg);
        dialog.setPositiveListener(R.string.ok, new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                dialog.dismiss();
            }
        }).show();

    }

    public static void showErrorDialog(Context context,String msg){
        ColorDialog dialog = new ColorDialog(context);
        dialog.setCancelable(false);
        dialog.setTitle(R.string.app_name );
        dialog.setContentText(msg);
        dialog.setPositiveListener(R.string.ok, new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                dialog.dismiss();
            }
        }).show();

    }

    private static void showDialog(Context context,String Msg) {


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
   /* public static void showPopUp(Context context) {
        final Dialog dialog = new Dialog(context);
        try {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.popup_select_grampanchayat);
            dialog.getWindow().setLayout(-1, -2);
            dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);

            RecyclerView rv_country = dialog.findViewById(R.id.rv_grampanchayat);

            rv_country.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
            final CountryAdapter adapter = new CountryAdapter(arrayList, new CountryAdapter.RowSelect() {
                @Override
                public void onSelect(GramPanchayat model) {
                    mvpView.hideSoftKeyboard();
                    dialog.dismiss();
                    mvpView.onGramPanchayatSelected(model, type);


                }
            });
            mvpView.hideSoftKeyboard();
            rv_country.setAdapter(adapter);
            EditText et_country_name = dialog.findViewById(R.id.et_gram_panchayat);

            et_country_name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            dialog.findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mvpView.hideSoftKeyboard();
                    dialog.dismiss();
                }
            });
            dialog.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mvpView.hideSoftKeyboard();
                    dialog.dismiss();
                }
            });

            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/


}