package com.tekzee.racp.ui.Form.vipran_talika;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.tekzee.racp.R;
import com.tekzee.racp.databinding.FormVipranTableBinding;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.utils.mDatePickerDialog;

import java.util.ArrayList;
import java.util.List;

public class VipranTableActivity extends MvpActivity<VipranPresenter> implements VipranView, View.OnClickListener {
    private static String TAG = VipranTableActivity.class.getSimpleName();

    private FormVipranTableBinding  binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.form_vipran_table);

        getSupportActionBar().setTitle(R.string.form_5);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


      /*  binding.edtDateet.setOnClickListener(this);
        binding.edtDatefmd.setOnClickListener(this);
        binding.edtDatehs.setOnClickListener(this);
        binding.edtDateppr.setOnClickListener(this);
*/
        //setDataInSpinner();

    }

    @Override
    protected VipranPresenter createPresenter() {
        return null;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            //startActivity(new Intent(ForSale.this, HomeActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){


        /*    case R.id.edt_dateet:
                mDatePickerDialog.getdate(this,binding.edtDateet);
                break;
            case R.id.edt_dateppr:
                mDatePickerDialog.getdate(this,binding.edtDateppr);
                break;
            case R.id.edt_datefmd:
                mDatePickerDialog.getdate(this,binding.edtDatefmd);
                break;
            case R.id.edt_datehs:
                mDatePickerDialog.getdate(this,binding.edtDatehs);
*/
        }

    }


  /*  private void setDataInSpinner() {



        List<String> animal = new ArrayList <>();
        animal.add(getString(R.string.bakra));
        animal.add(getString(R.string.bakari));
        ArrayAdapter <String> adapter_animal = new ArrayAdapter<String>(this,
                R.layout.spinner_item, animal);
        adapter_animal.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spAnimalType.setAdapter(adapter_animal);



    }
*/
    @Override
    public Context getContext() {
        return this;
    }
}
