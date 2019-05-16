package com.tekzee.racp.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class mDatePickerDialog extends DialogFragment {
    static String date;


    public static void getdate(Context context, TextView textView) {
        KeyboardUtils.hideSoftInput((Activity) context);

        final TextView tv = textView;
        final android.app.DatePickerDialog fromDatePickerDialog;
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        final Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new android.app.DatePickerDialog(context, new android.app.DatePickerDialog.OnDateSetListener() {


            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tv.setText(dateFormatter.format(newDate.getTime()));
                date = dateFormatter.format(newDate.getTime());
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        fromDatePickerDialog.show();


    }


    public static String showDate(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-YYYY");
        String strDate =mdformat.format(calendar.getTime());
        return strDate;

    }

    public static String mshowDate(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-YYYY");
        String strDate =mdformat.format(calendar.getTime());

        return strDate;

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }





}
