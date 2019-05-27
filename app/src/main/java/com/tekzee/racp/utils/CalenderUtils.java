package com.tekzee.racp.utils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tekzee.racp.R;

import java.util.ArrayList;
import java.util.List;

public class CalenderUtils {

    public static void loadMonths(Context context, Spinner month, Spinner years) {
        List <String> months = new ArrayList <>();
        months.add("जनवरी (01)");
        months.add("फरवरी (02)");
        months.add("मार्च (03)");
        months.add("अप्रैल (04)");
        months.add("मई (05)");
        months.add("जून (06)");
        months.add("जुलाई (07)");
        months.add("अगस्त (08)");
        months.add("सितम्बर (09)");
        months.add("अक्टूबर (10)");
        months.add("नवम्बर (11)");
        months.add("दिसंबर (12)");

        ArrayAdapter <String> MonthAdapter = new ArrayAdapter <String>(context, R.layout.spinner_item, months);
        MonthAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        month.setAdapter(MonthAdapter);


        List <Integer> year = new ArrayList <>();
        for (int i = 2015; i <= mDatePickerDialog.getYear(); i++) {
            year.add(i);
        }
        ArrayAdapter <Integer> adapter2 = new ArrayAdapter <Integer>(context,
                R.layout.spinner_item, year);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        years.setAdapter(adapter2);


    }
}
