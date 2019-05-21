package com.tekzee.racp.ui.Form.mtg_meeting;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.FormMtgMeetingBinding;
import com.tekzee.racp.ui.Form.mtg_meeting.model.DataMtgMeeting;
import com.tekzee.racp.ui.Form.mtg_meeting.model.MtgMember;
import com.tekzee.racp.ui.Form.mtg_meeting.model.MtgMemberResponse;
import com.tekzee.racp.ui.Form.mtg_meeting.model.RetrivedMtgMeetingResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formdata.FormDataActivity;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class MtgMeeting extends MvpActivity <MtgMeetingPreseter> implements MtgMeetingView, View.OnClickListener,Dialogs.okClickListner {

    private static String tag = MtgMeeting.class.getSimpleName();
    int form_id;
    private FormMtgMeetingBinding binding;
    private int recordNo = 1;
    private List <DataMtgMeeting> meetingList = new ArrayList <>();
    private List <MtgMember> mtgMembers = new ArrayList <>();
    private int record_count = 1;
    private int mtg_id;
    int table_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_mtg_meeting);

        getSupportActionBar().setTitle(R.string.form_16);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        form_id = getIntent().getIntExtra("form_id", 0);
        table_id = getIntent().getIntExtra("table_id",0);
        if (table_id != 0) {
            getFormRecordData();
        } else {
            ShowSelectionDialog();
        }


        record_count = (int) DataMtgMeeting.count(DataMtgMeeting.class);
        binding.tvDate.setText(mDatePickerDialog.showDate());

        binding.txtno.setText(String.valueOf(recordNo));
        binding.edtMeetingdate.setOnClickListener(this);
        binding.edtMtgname.setOnClickListener(this);
        binding.edtNameMembers.setOnClickListener(this);

        binding.tvSave.setOnClickListener(this);


    }

    @Override
    protected MtgMeetingPreseter createPresenter() {
        return new MtgMeetingPreseter(this);
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
        switch (v.getId()) {


            case R.id.edt_mtgname:
                mvpPresenter.getMtgName();
                break;
            case R.id.edt_meetingdate:
                mDatePickerDialog.getdate(getContext(), binding.edtMeetingdate);
                break;

            case R.id.edt_name_members:
                mvpPresenter.getMtgMemberList(mtg_id);
                break;

            case R.id.tv_save:
                try {
                    submitRecord();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;


        }
    }


    private void submitRecord() throws JSONException {


        if (binding.edtMeetingdate.getText().toString().isEmpty()) {

            Dialogs.showColorDialog(getContext(),getString(R.string.enter_date_meeting));
        } else if (binding.edtMtgname.getText().toString().isEmpty()) {

            Dialogs.showColorDialog(getContext(),getString(R.string.selcet_mtg_group));

        } else {

            String pashupalak_id = "";

            for (int i = 0; i < mtgMembers.size(); i++) {
                MtgMember mtgMember = mtgMembers.get(i);
                Log.e(tag, "checkelist" + mtgMember.isCheck());
                Log.e(tag, "" + mtgMember.getPashupalakId());

                if (mtgMember.isCheck()) {
                    pashupalak_id = pashupalak_id + mtgMember.getPashupalakId() + ",";

                    Log.e(tag, "pashupalak id" + pashupalak_id);
                }

            }


            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < 1; i++) {
                JsonObject jsonObject = new JsonObject();


                jsonObject.addProperty("mtggroup_id", mtg_id);
                jsonObject.addProperty("meeting_date", binding.edtMeetingdate.getText().toString());
                jsonObject.addProperty("pashupalak_id", pashupalak_id);
                jsonObject.addProperty("note", binding.edtNote.getText().toString());
                jsonArray.add(jsonObject);


            }
            JsonObject json = new JsonObject();

            json.addProperty("user_id", Utility.getIngerSharedPreferences(this, Constant.USER_ID));
            json.addProperty("form_id", form_id);
            json.add("data", jsonArray);
            Log.e(tag, "data is" + json.toString());

            mvpPresenter.saveForm(json);


        }

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Dialogs.showColorDialog(getContext(),commonResult.getMessage());

    }

    @Override
    public void onGramPanchayatSelected(GramPanchayat model, String type) {

        if (type.equalsIgnoreCase("mtggroup")) {
            binding.edtMtgname.setTextColor(getResources().getColor(R.color.black));
            binding.edtMtgname.setText(model.getGrampanchayatName());
            mtg_id = model.getGrampanchayatId();

        }

    }


    @Override
    public void onGetMemberList(MtgMemberResponse successResult) {


        Log.e(tag, successResult.getData().get(0).getPashupalakName());

        mtgMembers.clear();

        mtgMembers.addAll(successResult.getData());

        hideSoftKeyboard();

        final Dialog dialog = new Dialog(getContext());
        try {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.list_mtgmember);
            dialog.getWindow().setLayout(-1, -2);
            dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);

            RecyclerView rv_country = dialog.findViewById(R.id.rv_listmember);

            rv_country.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
            final MemberAdapter adapter = new MemberAdapter(getContext(), mtgMembers);

            rv_country.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftKeyboard();
                    dialog.dismiss();
                    binding.edtNameMembers.setText("");
                    for (int i = 0; i < mtgMembers.size(); i++) {
                        MtgMember mtgMember = mtgMembers.get(i);
                        Log.e(tag, "checkelist" + mtgMember.isCheck());
                        Log.e(tag, "" + mtgMember.getPashupalakId());

                        if (mtgMember.isCheck()) {
                            binding.edtNameMembers.setTextColor(getResources().getColor(R.color.black));
                            binding.edtNameMembers.append(mtgMember.getPashupalakName() + "\n");
                        }

                    }


                }
            });

            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {
        Dialogs.ShowCustomDialog(getContext(),successResult.getMessage(),this);


        binding.edtNote.setText("");
        binding.edtMtgname.setText(getString(R.string.mtg_name));
        binding.edtMeetingdate.setText("");
        binding.edtNameMembers.setText(getString(R.string.selcet_mtg_person));


    }

    @Override
    public void onSuccessfullyRetrived(RetrivedMtgMeetingResponse successResult) {


        binding.edtMeetingdate.setClickable(false);
        binding.edtMtgname.setClickable(false);
        binding.edtNameMembers.setClickable(false);
        binding.edtNote.setClickable(false);
        binding.edtNote.setEnabled(false);

        if (successResult.getData().getNote().equalsIgnoreCase("null")){
            binding.edtNote.setText("");
        }
        binding.edtNote.setText(String.valueOf(successResult.getData().getNote()));

        binding.edtMtgname.setTextColor(getResources().getColor(R.color.black));
        binding.edtNameMembers.setTextColor(getResources().getColor(R.color.black));
        binding.edtMeetingdate.setText(String.valueOf(successResult.getData().getMeetingDate()));
        binding.edtMtgname.setText(String.valueOf(successResult.getData().getMtggroupName()));

        String data  = String.valueOf(successResult.getData().getPashupalakName());

        String data1[] = data.split(",");
        for(int i= 0 ;i<data1.length;i++){
            binding.edtNameMembers.append(String.valueOf(data1[i])+"\n");
        }
        binding.tvSave.setVisibility(View.GONE);


    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 16);

        mvpPresenter.getFormRecordData(jsonObject);
    }


    private void ShowSelectionDialog() {
        final Dialog dialog = new Dialog(getContext());
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


            msg.setText(getString(R.string.form_16));

            dialog.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftKeyboard();
                    dialog.dismiss();
                }
            });
            dialog.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftKeyboard();
                    Intent intent = new Intent(getContext(), FormDataActivity.class);
                    intent.putExtra("form_id", form_id);
                    startActivity(intent);

                    dialog.dismiss();

                }
            });

            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onOkClickListner() {
        this.finish();
    }
}
