package com.tekzee.racp.ui.Form.weighting_machine;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.FormWeightMachineBinding;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.Form.weighting_machine.model.DataWeighitngMachine;
import com.tekzee.racp.ui.Form.weighting_machine.model.Datum;
import com.tekzee.racp.ui.Form.weighting_machine.model.GetAnimalCetegoryResponse;
import com.tekzee.racp.ui.Form.weighting_machine.model.RetrivedWeightingMachineResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.CalenderUtils;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class WeightMachine extends MvpActivity <WeightingMachinePresenter> implements WeightinhMachineView, View.OnClickListener {

    private static String tag = WeightMachine.class.getSimpleName();
    String subanimaltype = "";
    private FormWeightMachineBinding binding;
    private List <DataWeighitngMachine> detailList = new ArrayList <>();
    private List <Datum> category_list;
    private String subAnimal_type = "";
    private int record_count = 1;
    private int recordNo = 1;
    private int category_id = 0;
    private int type_id = 0;
    private String bakriWeight = "";
    private int table_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_weight_machine);
        getSupportActionBar().setTitle(R.string.form_6);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        table_id = getIntent().getIntExtra("table_id", 0);
        if (table_id != 0) {
            getFormRecordData();
        } else {

            ShowDialog();

        }
        setDataInSpinner();

        DataWeighitngMachine.deleteAll(DataWeighitngMachine.class);


        binding.txtno.setText(String.valueOf(record_count));
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.checkProofNo.setOnClickListener(this);
        binding.checkProofYes.setOnClickListener(this);
        binding.checkUseNo.setOnClickListener(this);
        binding.checkUseYes.setOnClickListener(this);
        binding.spAnimalType.setOnClickListener(this);
        binding.spSubanimalType.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);


    }

    @Override
    protected WeightingMachinePresenter createPresenter() {
        return new WeightingMachinePresenter(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.sp_animal_type:
                mvpPresenter.getAnimelCetegory();
                break;

            case R.id.sp_subanimal_type:
                mvpPresenter.getAnimalTypeByCategory(category_id);
                break;

            case R.id.check_proof_yes:
                if (binding.checkProofYes.isChecked()) {

                    if (binding.checkProofNo.isChecked()) {
                        binding.checkProofNo.setChecked(false);
                    }
                    binding.checkProofYes.setChecked(true);
                } else {

                    binding.checkProofYes.setChecked(false);
                }
                break;


            case R.id.check_proof_no:
                if (binding.checkProofNo.isChecked()) {
                    if (binding.checkProofYes.isChecked()) {
                        binding.checkProofYes.setChecked(false);
                    }
                    binding.checkProofNo.setChecked(true);
                } else {
                    binding.checkProofNo.setChecked(false);

                }
                break;


            case R.id.check_use_yes:
                if (binding.checkUseYes.isChecked()) {

                    if (binding.checkUseNo.isChecked()) {
                        binding.checkUseNo.setChecked(false);
                    }
                    binding.checkUseYes.setChecked(true);
                } else {

                    binding.checkUseYes.setChecked(false);
                }
                break;

            case R.id.check_use_no:
                if (binding.checkUseNo.isChecked()) {
                    if (binding.checkUseYes.isChecked()) {
                        binding.checkUseYes.setChecked(false);
                    }
                    binding.checkUseNo.setChecked(true);
                } else {
                    binding.checkUseNo.setChecked(false);

                }
                break;

            case R.id.privious:
                privious();
                break;


            case R.id.tv_addRecord:
                addRecordinSqlite();
                /*if (addRecordinSqlite()) {

                    binding.day.setText("");
                    binding.spMonth.setSelection(0);
                    binding.spYear.setSelection(1);
                    binding.checkUseYes.setChecked(false);
                    binding.checkUseNo.setChecked(false);
                    binding.checkProofYes.setChecked(false);
                    binding.checkProofNo.setChecked(false);
                    binding.spAnimalType.setHint(R.string.animal_category);
                    binding.spSubanimalType.setHint(R.string.animal_type);
                    binding.edtTagNoAdult.setText("");
                    binding.edtAgeAdult.setText("");
                    binding.edtWeightAdult.setText("");
                    binding.edtTagnoBakra.setText("");
                    binding.edtTagnoBakri.setText("");
                    binding.edtTagnoProgeni.setText("");
                    binding.edtWeightProgeni.setText("");
                    binding.edtAgeProgeni.setText("");
                    binding.layoutProgeni.setVisibility(View.GONE);
                    binding.layoutAdult.setVisibility(View.GONE);

                    recordNo = recordNo + 1;
                    binding.txtno.setText(String.valueOf(recordNo));
                    binding.privious.setVisibility(View.VISIBLE);
                    record_count++;
                }
*/
                break;

            case R.id.next:
                next();
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

    private Boolean addRecordinSqlite() {

        Log.e(tag, "size is" + detailList.size());

        if (!binding.checkProofYes.isChecked() && !binding.checkProofNo.isChecked()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.physical_proof));
            return false;
        } else if (!binding.checkUseNo.isChecked() && !binding.checkUseYes.isChecked()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.in_use_or_not));
            return false;
        } else if (binding.spAnimalType.getText().toString().isEmpty()) {


            Dialogs.showColorDialog(getContext(), getString(R.string.select_animal_category));
            return false;
        } else if (binding.spSubanimalType.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_animal_type));
            return false;
        } else {


            String day;
            if (binding.day.getText().toString().isEmpty()) {
                day = "01";

            } else {
                day = binding.day.getText().toString();
                if (!mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString()),
                        binding.spMonth.getSelectedItemPosition() + 1,
                        Integer.valueOf(binding.spYear.getSelectedItem().toString()))) {

                    //mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString()),binding.spMonth.getSelectedItemPosition()+1,Integer.valueOf(binding.spYear.getSelectedItem().toString()));
                    Dialogs.showColorDialog(getContext(), getString(R.string.invalid_Date));
                    // Toast.makeText(this, getString(R.string.invalid_Date), Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            String proof, use;
            if (binding.checkUseYes.isChecked()) {
                use = binding.checkUseYes.getText().toString();

            } else {
                use = binding.checkUseNo.getText().toString();

            }


            if (binding.checkProofYes.isChecked()) {
                proof = binding.checkProofYes.getText().toString();
            } else {
                proof = binding.checkProofNo.getText().toString();
            }

            if (category_id == 1) {

                Log.e(tag, "" + category_id);
                if (binding.edtTagNoAdult.getText().toString().isEmpty()) {
                    Dialogs.showColorDialog(getContext(), getString(R.string.enter_tagno));
                    return false;
                } else if (binding.edtAgeAdult.getText().toString().isEmpty()) {
                    Dialogs.showColorDialog(getContext(), getString(R.string.enter_age));
                    return false;
                } else if (binding.edtWeightAdult.getText().toString().isEmpty()) {
                    Dialogs.showColorDialog(getContext(), getString(R.string.enter_weight));
                    return false;
                }
                if (type_id == 1) {
                    Log.e(tag, "type_id" + type_id);

                    DataWeighitngMachine dataWeighitngMachine = new DataWeighitngMachine(
                            binding.day.getText().toString(),
                            binding.spMonth.getSelectedItem().toString(),
                            binding.spMonth.getSelectedItemPosition(),
                            binding.spYear.getSelectedItem().toString(),
                            binding.spYear.getSelectedItemPosition(),
                            proof,
                            binding.checkProofYes.isChecked(),
                            use,
                            binding.checkUseYes.isChecked(),
                            binding.spAnimalType.getText().toString(),
                            category_id,
                            binding.spSubanimalType.getText().toString(),
                            type_id,
                            binding.edtTagNoAdult.getText().toString(),
                            binding.edtAgeAdult.getText().toString(),
                            binding.edtWeightAdult.getText().toString(),
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                    );
                    dataWeighitngMachine.save();


                } else if (type_id == 2) {
                    Log.e(tag, "type_id" + type_id);

                    DataWeighitngMachine dataWeighitngMachine = new DataWeighitngMachine(
                            binding.day.getText().toString(),
                            binding.spMonth.getSelectedItem().toString(),
                            binding.spMonth.getSelectedItemPosition(),
                            binding.spYear.getSelectedItem().toString(),
                            binding.spYear.getSelectedItemPosition(),
                            proof,
                            binding.checkProofYes.isChecked(),
                            use,
                            binding.checkUseYes.isChecked(),
                            binding.spAnimalType.getText().toString(),
                            category_id,
                            binding.spSubanimalType.getText().toString(),
                            type_id,
                            "",
                            "",
                            "",
                            binding.edtTagNoAdult.getText().toString(),
                            binding.edtAgeAdult.getText().toString(),
                            binding.edtWeightAdult.getText().toString(), "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                    );
                    dataWeighitngMachine.save();


                }
            } else if (category_id == 2) {

                Log.e(tag, "" + category_id);

                if (binding.edtTagnoBakra.getText().toString().isEmpty()) {
                    Dialogs.showColorDialog(getContext(), getString(R.string.enter_tagno_bakra));
                    return false;
                }
                if (binding.edtTagnoBakri.getText().toString().isEmpty()) {
                    Dialogs.showColorDialog(getContext(), getString(R.string.enter_tagno_bakri));
                    return false;
                }
                if (binding.edtTagnoProgeni.getText().toString().isEmpty()) {
                    Dialogs.showColorDialog(getContext(), getString(R.string.enter_tag_projeni));
                    return false;
                } else if (binding.edtAgeProgeni.getText().toString().isEmpty()) {
                    Dialogs.showColorDialog(getContext(), getString(R.string.enter_age));
                    return false;
                } else if (binding.edtWeightProgeni.getText().toString().isEmpty()) {
                    Dialogs.showColorDialog(getContext(), getString(R.string.enter_weight));
                    return false;
                }


                if (type_id == 3) {
                    Log.e(tag, "type_id" + type_id);

                    DataWeighitngMachine dataWeighitngMachine = new DataWeighitngMachine(
                            binding.day.getText().toString(),
                            binding.spMonth.getSelectedItem().toString(),
                            binding.spMonth.getSelectedItemPosition(),
                            binding.spYear.getSelectedItem().toString(),
                            binding.spYear.getSelectedItemPosition(),
                            proof,
                            binding.checkProofYes.isChecked(),
                            use,
                            binding.checkUseYes.isChecked(),
                            binding.spAnimalType.getText().toString(),
                            category_id,
                            binding.spSubanimalType.getText().toString(),
                            type_id,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            binding.edtTagnoProgeni.getText().toString(),
                            binding.edtAgeProgeni.getText().toString(),
                            binding.edtWeightProgeni.getText().toString(),
                            "",
                            "",
                            "",
                            binding.edtTagnoBakra.getText().toString(),
                            "",
                            "",
                            binding.edtTagnoBakri.getText().toString(),
                            "",
                            ""
                    );
                    dataWeighitngMachine.save();


                } else if (type_id == 4) {
                    Log.e(tag, "type_id" + type_id);

                    DataWeighitngMachine dataWeighitngMachine = new DataWeighitngMachine(
                            binding.day.getText().toString(),
                            binding.spMonth.getSelectedItem().toString(),
                            binding.spMonth.getSelectedItemPosition(),
                            binding.spYear.getSelectedItem().toString(),
                            binding.spYear.getSelectedItemPosition(),
                            proof,
                            binding.checkProofYes.isChecked(),
                            use,
                            binding.checkUseYes.isChecked(),
                            binding.spAnimalType.getText().toString(),
                            category_id,
                            binding.spSubanimalType.getText().toString(),
                            type_id,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            binding.edtTagnoProgeni.getText().toString(),
                            binding.edtAgeProgeni.getText().toString(),
                            binding.edtWeightProgeni.getText().toString(),
                            binding.edtTagnoBakra.getText().toString(),
                            "",
                            "",
                            binding.edtTagnoBakri.getText().toString(),
                            "",
                            ""
                    );
                    dataWeighitngMachine.save();

                }

            }


            recordNo = recordNo + 1;
            record_count = record_count + 1;
            binding.txtno.setText(String.valueOf(recordNo));
            binding.day.setText("");
            binding.day.setText("");
            binding.spMonth.setSelection(0);
            binding.spYear.setSelection(1);
            binding.checkUseYes.setChecked(false);
            binding.checkUseNo.setChecked(false);
            binding.checkProofYes.setChecked(false);
            binding.checkProofNo.setChecked(false);
            binding.spAnimalType.setText("");
            binding.spSubanimalType.setText("");
            binding.edtTagNoAdult.setText("");
            binding.edtAgeAdult.setText("");
            binding.edtWeightAdult.setText("");
            binding.edtTagnoBakra.setText("");
            binding.edtTagnoBakri.setText("");
            binding.edtTagnoProgeni.setText("");
            binding.edtWeightProgeni.setText("");
            binding.edtAgeProgeni.setText("");
            binding.layoutProgeni.setVisibility(View.GONE);
            binding.layoutAdult.setVisibility(View.GONE);
            binding.txtno.setText(String.valueOf(recordNo));
            binding.privious.setVisibility(View.VISIBLE);
            category_id = 0;
            type_id = 0;
            return true;

        }

    }

    private void next() {
        binding.privious.setVisibility(View.VISIBLE);
        record_count = record_count + 1;
        if (record_count == recordNo) {
            binding.next.setVisibility(View.GONE);
            binding.tvAddRecord.setVisibility(View.VISIBLE);
            binding.tvSave.setVisibility(View.VISIBLE);
        }
        binding.txtno.setText(String.valueOf(record_count));

        detailList.clear();
        detailList = DataWeighitngMachine.listAll(DataWeighitngMachine.class);

        if (record_count == DataWeighitngMachine.count(DataWeighitngMachine.class)) {

            DataWeighitngMachine detail = detailList.get(record_count - 1);

            if (detail.isPhysical_proof()) {
                binding.checkProofYes.setChecked(true);
                binding.checkProofNo.setChecked(false);
            } else {
                binding.checkProofNo.setChecked(true);
                binding.checkProofYes.setChecked(false);
            }

            if (detail.isUsability()) {

                binding.checkUseYes.setChecked(true);
                binding.checkUseNo.setChecked(false);

            } else {

                binding.checkUseNo.setChecked(true);
                binding.checkUseYes.setChecked(false);


            }

            if (detail.getAnimal_type_id() == 1) {
                binding.layoutAdult.setVisibility(View.VISIBLE);
                binding.layoutProgeni.setVisibility(View.GONE);
                binding.spAnimalType.setText(detail.getAninal_type());
                binding.spSubanimalType.setText(detail.getAnimal_sub_type());
                if (detail.getAnimal_sub_type_id() == 1) {
                    binding.edtTagNoAdult.setText(detail.getTagno_bakra());
                    binding.edtAgeAdult.setText(detail.getAge_bakra());
                    binding.edtWeightAdult.setText(detail.getWeight_bakra());
                }
                if (detail.getAnimal_sub_type_id() == 2) {
                    binding.edtTagNoAdult.setText(detail.getTagno_bakri());
                    binding.edtAgeAdult.setText(detail.getAge_bakri());
                    binding.edtWeightAdult.setText(detail.getWeight_bakri());
                }
            }
            if (detail.getAnimal_type_id() == 2) {
                binding.layoutAdult.setVisibility(View.GONE);
                binding.layoutProgeni.setVisibility(View.VISIBLE);
                binding.spAnimalType.setText(detail.getAninal_type());
                binding.spSubanimalType.setText(detail.getAnimal_sub_type());
                if (detail.getAnimal_sub_type_id() == 3) {
                    Log.e(tag, "subtype_id" + detail.getTagno_nar());
                    binding.edtTagnoBakra.setText(detail.getParent_bakre_ka_tag_no());
                    binding.edtTagnoBakri.setText(detail.getParent_bakri_ka_tag_no());
                    binding.edtTagnoProgeni.setText(detail.getTagno_nar());
                    binding.edtAgeProgeni.setText(detail.getAge_nar());
                    binding.edtWeightProgeni.setText(detail.getWeight_nar());
                }
                if (detail.getAnimal_sub_type_id() == 4) {

                    Log.e(tag, "subtype_id" + detail.getTagno_mada());
                    binding.edtTagnoBakra.setText(detail.getParent_bakre_ka_tag_no());
                    binding.edtTagnoBakri.setText(detail.getParent_bakri_ka_tag_no());
                    binding.edtTagnoProgeni.setText(detail.getTagno_mada());
                    binding.edtAgeProgeni.setText(detail.getAge_mada());
                    binding.edtWeightProgeni.setText(detail.getWeight_mada());
                }
            }

            binding.day.setText(detail.getDd());
            binding.spMonth.setSelection(detail.getMm_id());
            binding.spYear.setSelection(detail.getYY_id());


            binding.tvAddRecord.setVisibility(View.GONE);
            binding.next.setVisibility(View.VISIBLE);

        } else {

            binding.day.setText("");
            binding.spMonth.setSelection(0);
            binding.spYear.setSelection(1);
            binding.checkUseYes.setChecked(false);
            binding.checkUseNo.setChecked(false);
            binding.checkProofYes.setChecked(false);
            binding.checkProofNo.setChecked(false);
            binding.spAnimalType.setText("");
            binding.spSubanimalType.setText("");
            binding.edtTagNoAdult.setText("");
            binding.edtAgeAdult.setText("");
            binding.edtWeightAdult.setText("");
            binding.edtTagnoBakra.setText("");
            binding.edtTagnoBakri.setText("");
            binding.edtTagnoProgeni.setText("");
            binding.edtWeightProgeni.setText("");
            binding.edtAgeProgeni.setText("");

            binding.layoutProgeni.setVisibility(View.GONE);
            binding.layoutAdult.setVisibility(View.GONE);


        }

    }

    private void privious() {

        binding.tvSave.setVisibility(View.GONE);
        record_count = record_count - 1;
        if (record_count == 1) {
            binding.privious.setVisibility(View.GONE);
        }

        Log.e(tag, String.valueOf(DataWeighitngMachine.count(DataWeighitngMachine.class)));

        binding.txtno.setText(String.valueOf(record_count));

        detailList.clear();
        detailList = DataWeighitngMachine.listAll(DataWeighitngMachine.class);
        Log.e(tag, String.valueOf(detailList.size()));
        Log.e(tag, String.valueOf(record_count));

        DataWeighitngMachine detail = detailList.get(record_count - 1);

        if (detail.isPhysical_proof()) {
            binding.checkProofYes.setChecked(true);
            binding.checkProofNo.setChecked(false);
        } else {
            binding.checkProofNo.setChecked(true);
            binding.checkProofYes.setChecked(false);
        }
        if (detail.isUsability()) {
            binding.checkUseYes.setChecked(true);
            binding.checkUseNo.setChecked(false);
        } else {
            binding.checkUseNo.setChecked(true);
            binding.checkUseYes.setChecked(false);
        }

        if (detail.getAnimal_type_id() == 1) {
            binding.layoutAdult.setVisibility(View.VISIBLE);
            binding.layoutProgeni.setVisibility(View.GONE);
            binding.spAnimalType.setText(detail.getAninal_type());
            binding.spSubanimalType.setText(detail.getAnimal_sub_type());
            if (detail.getAnimal_sub_type_id() == 1) {
                binding.edtTagNoAdult.setText(detail.getTagno_bakra());
                binding.edtAgeAdult.setText(detail.getAge_bakra());
                binding.edtWeightAdult.setText(detail.getWeight_bakra());
            }
            if (detail.getAnimal_sub_type_id() == 2) {
                binding.edtTagNoAdult.setText(detail.getTagno_bakri());
                binding.edtAgeAdult.setText(detail.getAge_bakri());
                binding.edtWeightAdult.setText(detail.getWeight_bakri());
            }
        }
        if (detail.getAnimal_type_id() == 2) {
            binding.layoutAdult.setVisibility(View.GONE);
            binding.layoutProgeni.setVisibility(View.VISIBLE);
            binding.spAnimalType.setText(detail.getAninal_type());
            binding.spSubanimalType.setText(detail.getAnimal_sub_type());
            if (detail.getAnimal_sub_type_id() == 3) {
                Log.e(tag, "subtype_id" + detail.getTagno_nar());
                binding.edtTagnoBakra.setText(detail.getParent_bakre_ka_tag_no());
                binding.edtTagnoBakri.setText(detail.getParent_bakri_ka_tag_no());
                binding.edtTagnoProgeni.setText(detail.getTagno_nar());
                binding.edtAgeProgeni.setText(detail.getAge_nar());
                binding.edtWeightProgeni.setText(detail.getWeight_nar());
            }
            if (detail.getAnimal_sub_type_id() == 4) {

                Log.e(tag, "subtype_id" + detail.getTagno_mada());
                binding.edtTagnoBakra.setText(detail.getParent_bakre_ka_tag_no());
                binding.edtTagnoBakri.setText(detail.getParent_bakri_ka_tag_no());
                binding.edtTagnoProgeni.setText(detail.getTagno_mada());
                binding.edtAgeProgeni.setText(detail.getAge_mada());
                binding.edtWeightProgeni.setText(detail.getWeight_mada());
            }
        }

        binding.day.setText(detail.getDd());
        binding.spMonth.setSelection(detail.getMm_id());
        binding.spYear.setSelection(detail.getYY_id());

        binding.tvAddRecord.setVisibility(View.GONE);
        binding.next.setVisibility(View.VISIBLE);


    }

    private void submitRecord() throws JSONException {


        if (addRecordinSqlite()) {

            detailList.clear();
            detailList = DataWeighitngMachine.listAll(DataWeighitngMachine.class);
            Log.e(tag, "size is" + detailList.size());
            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < detailList.size(); i++) {
                JsonObject jsonObject = new JsonObject();
                DataWeighitngMachine detail = detailList.get(i);


                jsonObject.addProperty("animaltype_id", detail.getAnimal_type_id());
                jsonObject.addProperty("animaltype_sub_id", detail.getAnimal_sub_type_id());
                jsonObject.addProperty("physical_proof", detail.getProof());
                jsonObject.addProperty("usability", detail.getUse());

                jsonObject.addProperty("bakra_tag_no", detail.getTagno_bakra());
                jsonObject.addProperty("bakra_bhar", detail.getWeight_bakra());
                jsonObject.addProperty("bakra_age", detail.getAge_bakra());

                jsonObject.addProperty("bakri_tag_no", detail.getTagno_bakri());
                jsonObject.addProperty("bakri_bhar", detail.getWeight_bakri());
                jsonObject.addProperty("bakri_age", detail.getAge_bakri());

                jsonObject.addProperty("nar_tag_no", detail.getTagno_nar());
                jsonObject.addProperty("nar_bhar", detail.getWeight_nar());
                jsonObject.addProperty("nar_age", detail.getAge_nar());

                jsonObject.addProperty("mada_tag_no", detail.getTagno_mada());
                jsonObject.addProperty("mada_bhar", detail.getWeight_mada());
                jsonObject.addProperty("mada_age", detail.getAge_mada());


                jsonObject.addProperty("parent_bakre_ka_tag_no", detail.getParent_bakre_ka_tag_no());
                jsonObject.addProperty("parent_bakre_ka_bhar", detail.getParent_bakre_ka_bhar());
                jsonObject.addProperty("parent_bakre_ki_age", detail.getParent_bakre_ki_age());
                jsonObject.addProperty("parent_bakri_ka_tag_no", detail.getParent_bakri_ka_tag_no());
                jsonObject.addProperty("parent_bakri_ka_bhar", detail.getParent_bakri_ka_bhar());
                jsonObject.addProperty("parent_bakri_ki_age", detail.getParent_bakri_ki_age());


                jsonObject.addProperty("age", "");
                jsonObject.addProperty("dd", detail.getDd());
                jsonObject.addProperty("mm", detail.getMm_id() + 1);
                jsonObject.addProperty("yy", detail.getYy());
                jsonArray.add(jsonObject);

            }


            JsonObject json = new JsonObject();
            json.addProperty("user_id", Utility.getIngerSharedPreferences(this, Constant.USER_ID));
            json.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(this, Constant.mtg_group_id));
            json.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(this, Constant.mtg_member_id));
            json.addProperty("form_id", 6);
            json.addProperty("status_receipt", 1);


            json.add("data", jsonArray);

            Log.e(tag, "final data is" + json.toString());

            mvpPresenter.saveForm(json);

        }

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
    public void onBackPressed() {
        // TODO Auto-generated method stub
        this.finish();
    }


    private void setDataInSpinner() {

        CalenderUtils.loadMonths(getContext(), binding.spMonth, binding.spYear);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onGetAnimelCetegory(GetAnimalCetegoryResponse successResult) {


    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {

        Dialogs.showColorDialog(getContext(), commonResult.getMessage());

    }

    @Override
    public void onGramPanchayatSelected(GramPanchayat model, String type) {

        if (type.equalsIgnoreCase("animalcategory")) {
            category_id = model.getGrampanchayatId();
            binding.spAnimalType.setText(model.getGrampanchayatName());
            type_id = 0;

            binding.spSubanimalType.setText("");
            subAnimal_type = model.getGrampanchayatName();
            binding.layoutProgeni.setVisibility(View.GONE);
            binding.layoutAdult.setVisibility(View.GONE);


        }
        if (type.equalsIgnoreCase("animaltype")) {
            type_id = model.getGrampanchayatId();
            binding.spSubanimalType.setText(model.getGrampanchayatName());
            if (model.getGrampanchayatId() > 2) {
                binding.layoutProgeni.setVisibility(View.VISIBLE);
                binding.layoutAdult.setVisibility(View.GONE);
            } else {
                binding.layoutAdult.setVisibility(View.VISIBLE);
                binding.layoutProgeni.setVisibility(View.GONE);
            }
        }
    }

    private void ShowDialog() {

        Dialogs.ShowSelectionDialog(getContext(), getString(R.string.weighing_machine_availornot), new Dialogs.DialogClickListner() {
            @Override
            public void onOkClick() {

            }

            @Override
            public void onNoClick() {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("status_receipt", 0);
                jsonObject.addProperty("user_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.USER_ID));
                jsonObject.addProperty("form_id", 6);
                jsonObject.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_member_id));
                jsonObject.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_group_id));
                JsonArray jsonElements = new JsonArray();
                jsonObject.add("data", jsonElements);
                // mvpPresenter.saveForm(jsonObject);
                // mvpPresenter.saveForm(jsonObject);

                finish();
            }
        });

/*
        ColorDialog dialog = new ColorDialog(this);
        dialog.setCancelable(false);

        dialog.setTitle(getResources().getString(R.string.form_6));
        dialog.setColor("#FF6500");
        dialog.setColor("#ff6500");
        dialog.setContentText(R.string.availornot);
        dialog.setPositiveListener(getText(R.string.yes), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {

                dialog.dismiss();
            }
        })
                .setNegativeListener(getText(R.string.no), new ColorDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {

                        dialog.dismiss();

                        finish();
                    }
                }).show();*/
    }

    @Override
    public String getBakriWeight() {
        return bakriWeight;
    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(), new Dialogs.okClickListner() {
            @Override
            public void onOkClickListner() {
                DataWeighitngMachine.deleteAll(DataWeighitngMachine.class);
                finish();
            }
        }, "  ");

        record_count = 1;
        recordNo = 1;

        binding.txtno.setText(String.valueOf(record_count));

        binding.day.setText("");
        binding.spMonth.setSelection(0);
        binding.spYear.setSelection(1);
        binding.checkUseYes.setChecked(false);
        binding.checkUseNo.setChecked(false);
        binding.checkProofYes.setChecked(false);
        binding.checkProofNo.setChecked(false);
        binding.spAnimalType.setHint(R.string.animal_category);
        binding.spSubanimalType.setHint(R.string.animal_type);
        binding.edtTagNoAdult.setText("");
        binding.edtAgeAdult.setText("");
        binding.edtWeightAdult.setText("");
        binding.edtTagnoBakra.setText("");
        binding.edtTagnoBakri.setText("");
        binding.edtTagnoProgeni.setText("");
        binding.edtWeightProgeni.setText("");
        binding.edtAgeProgeni.setText("");
        binding.layoutProgeni.setVisibility(View.GONE);
        binding.layoutAdult.setVisibility(View.GONE);


    }

    @Override
    public void onSuccessfullyRetrived(RetrivedWeightingMachineResponse successResult) {

        binding.edtTagNoAdult.setFocusable(false);
        binding.edtTagnoProgeni.setFocusable(false);

        binding.edtTagnoBakra.setFocusable(false);
        binding.edtTagnoBakri.setFocusable(false);

        binding.edtAgeProgeni.setFocusable(false);
        binding.edtAgeAdult.setFocusable(false);
        binding.edtWeightAdult.setFocusable(false);
        binding.edtWeightProgeni.setFocusable(false);
        binding.checkUseNo.setClickable(false);
        binding.checkUseYes.setClickable(false);
        binding.checkProofNo.setClickable(false);
        binding.checkProofYes.setClickable(false);

        binding.receiptDate.setVisibility(View.VISIBLE);
        binding.receiptDate.setText(mDatePickerDialog.changeFormate(String.valueOf(successResult.getData().getDateReceipt())));

        if (successResult.getData().getAnimaltypeId() == 1) {
            binding.spAnimalType.setText(getString(R.string.adult));
            binding.spAnimalType.setClickable(false);
            binding.layoutProgeni.setVisibility(View.GONE);
            binding.layoutAdult.setVisibility(View.VISIBLE);

            if (String.valueOf(successResult.getData().getBakraTagNo()).isEmpty() || String.valueOf(successResult.getData().getBakraTagNo()).equalsIgnoreCase("null")) {
                binding.spSubanimalType.setText(getString(R.string.bakari));
                binding.spSubanimalType.setClickable(false);

                binding.edtTagNoAdult.setText(String.valueOf(successResult.getData().getBakriTagNo()));
                binding.edtAgeAdult.setText(String.valueOf(successResult.getData().getBakriAge()));
                binding.edtWeightAdult.setText(String.valueOf(successResult.getData().getBakriBhar()));
            } else {
                binding.spSubanimalType.setText(getString(R.string.bakra));
                binding.spSubanimalType.setClickable(false);

                binding.edtTagNoAdult.setText(String.valueOf(successResult.getData().getBakraTagNo()));
                binding.edtAgeAdult.setText(String.valueOf(successResult.getData().getBakraAge()));
                binding.edtWeightAdult.setText(String.valueOf(successResult.getData().getBakraBhar()));

            }

        } else {
            binding.spAnimalType.setText(getString(R.string.projeny));
            binding.spAnimalType.setClickable(false);

            binding.layoutAdult.setVisibility(View.GONE);
            binding.layoutProgeni.setVisibility(View.VISIBLE);
            if (String.valueOf(successResult.getData().getNarBhar()).isEmpty() || String.valueOf(successResult.getData().getNarBhar()).equalsIgnoreCase("null")) {

                binding.spSubanimalType.setText(getString(R.string.memni));
                binding.spSubanimalType.setClickable(false);

                binding.edtTagnoBakra.setText(String.valueOf(successResult.getData().getParentBakreKaTagNo()));
                binding.edtTagnoBakri.setText(String.valueOf(successResult.getData().getParentBakriKaTagNo()));
                binding.edtAgeProgeni.setText(String.valueOf(successResult.getData().getMadaAge()));
                binding.edtTagnoProgeni.setText(String.valueOf(successResult.getData().getMadaTagNo()));
                binding.edtWeightProgeni.setText(String.valueOf(successResult.getData().getMadaBhar()));
            } else {

                binding.spSubanimalType.setText(getString(R.string.memna));
                binding.spSubanimalType.setClickable(false);

                binding.edtTagnoBakra.setText(String.valueOf(successResult.getData().getParentBakreKaTagNo()));
                binding.edtTagnoBakri.setText(String.valueOf(successResult.getData().getParentBakriKaTagNo()));
                binding.edtAgeProgeni.setText(String.valueOf(successResult.getData().getNarAge()));
                binding.edtTagnoProgeni.setText(String.valueOf(successResult.getData().getNarTagNo()));
                binding.edtWeightProgeni.setText(String.valueOf(successResult.getData().getNarBhar()));
            }

        }

        if (successResult.getData().getPhysicalProof().equalsIgnoreCase(getString(R.string.yes))) {

            binding.checkProofYes.setChecked(true);

        } else {
            binding.checkProofNo.setChecked(true);
        }
        if (successResult.getData().getUsability().equalsIgnoreCase(getString(R.string.yes))) {

            binding.checkUseYes.setChecked(true);

        } else {
            binding.checkUseNo.setChecked(true);
        }

        binding.receiptLayout1.setVisibility(View.GONE);
        binding.receiptLayout2.setVisibility(View.GONE);
        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);


    }


    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 6);

        mvpPresenter.getFormRecordData(jsonObject);
    }

}
