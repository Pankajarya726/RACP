package com.tekzee.racp.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class mDatePickerDialog extends DialogFragment {
    static String date;


    public static void getdate(Context context, TextView textView) {
        KeyboardUtils.hideSoftInput((Activity) context);

        final TextView tv = textView;
        final android.app.DatePickerDialog fromDatePickerDialog;
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

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

    public static Integer getYear(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-YYYY");
        String strDate =mdformat.format(calendar.getTime());
        int year  = calendar.get(Calendar.YEAR);

        return year;

    }

    public static String mshowDate(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-YYYY");
        String strDate =mdformat.format(calendar.getTime());

        return strDate;

    }

    public static Boolean validateDate(Integer day, Integer month,Integer year) {


        if ((month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) && day>31){
            Log.view("month"+month , "day"+day);
            return false;
        }
        if ((month==4 || month==6 || month==9 || month==11) && day>30){
            Log.view("month"+month , "day"+day);
            return false;
        }
        if (month==2 && year%4==0 && day>29){
            Log.view("month"+month , "day"+day);
            return false;
        }
        if (month==2 && year%4!=0 && day>28){
            Log.view("month"+month , "day"+day);
            return false;
        }

        return true;

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

    public static String changeFormate(String Date){
        String newDate = "";
        String[] date = Date.split("-");
        newDate = date[2]+"-"+date[1]+"-"+date[0];
        return newDate;
    }

}
