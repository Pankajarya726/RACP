package com.tekzee.racp.ui.Form.bachne_yogya_bakara_bakari;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.tekzee.racp.R;
import com.tekzee.racp.databinding.FormForsaleBinding;
import com.tekzee.racp.ui.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class ForSale extends AppCompatActivity {
    private static String tag = ForSale.class.getSimpleName();

    private FormForsaleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.form_forsale);

        getSupportActionBar().setTitle(R.string.form_13);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setDataInSpinner();

    }



    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
           startActivity(new Intent(ForSale.this, HomeActivity.class));
           finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void setDataInSpinner() {

        List <Integer> year = new ArrayList <>();
        for (int i=0;i<21;i++){
            year.add(i);
        }
        ArrayAdapter <Integer> adapter = new ArrayAdapter<Integer>(this,
                R.layout.spinner_item, year);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spYear.setAdapter(adapter);

        List<Integer> month = new ArrayList <>();
        for (int i=0;i<12;i++){
            month.add(i);
        }
        ArrayAdapter <Integer> adaptermonth = new ArrayAdapter<Integer>(this,
                R.layout.spinner_item, month);
        adaptermonth.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spMonth.setAdapter(adaptermonth);

        List<String> animal = new ArrayList <>();
        animal.add(getString(R.string.bakra));
        animal.add(getString(R.string.bakari));
        ArrayAdapter <String> adapter_animal = new ArrayAdapter<String>(this,
                R.layout.spinner_item, animal);
        adapter_animal.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spAnimalType.setAdapter(adapter_animal);



    }

}
