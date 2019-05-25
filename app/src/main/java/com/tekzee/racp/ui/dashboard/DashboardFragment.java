package com.tekzee.racp.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.FragmentDashboardBinding;
import com.tekzee.racp.sqlite.SqliteDB;
import com.tekzee.racp.ui.Form.adoption.Adoption;
import com.tekzee.racp.ui.Form.bakara_rotation.RotationActivity;
import com.tekzee.racp.ui.Form.milk_info_bakari.MilkInfoActivity;
import com.tekzee.racp.ui.Form.mtg_meeting.MtgMeeting;
import com.tekzee.racp.ui.Form.mtg_prasikshan.MtgTraining;
import com.tekzee.racp.ui.Form.pashu_chiktsak_shivir.PahsuChikitsha;
import com.tekzee.racp.ui.Form.vipran_talika.VipranTableActivity;
import com.tekzee.racp.ui.addMGTgroup.AddMtgActivity;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.add_animal_owner.AddOwnerActivity;
import com.tekzee.racp.ui.base.MvpFragment;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.dashboard.model.DashboardDataResponse;
import com.tekzee.racp.ui.dashboard.model.Homemenu;
import com.tekzee.racp.ui.formdata.FormDataActivity;
import com.tekzee.racp.ui.selectMtgGroup.SelectMtgActivity;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends MvpFragment <DashboardPresenter> implements DashboardView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static DashboardFragment dashboardFragment;
    private static String tag = DashboardFragment.class.getSimpleName();
    GridAdapter gridAdapter;
    SqliteDB sqliteDB = new SqliteDB(getContext());
    private DashboardAdapter dashboardAdapter;
    private FragmentDashboardBinding binding;
    private List <Homemenu> homemenuList = new ArrayList <>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public DashboardFragment() {

    }

    public static DashboardFragment createInstance() {
        if (dashboardFragment == null) {
            dashboardFragment = new DashboardFragment();
        }
        return dashboardFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    protected DashboardPresenter createPresenter() {
        return new DashboardPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);

        getDashboardData();

        //binding.gridview.setOnItemClickListener(this);
        return binding.getRoot();

    }

    private void getDashboardData() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
        jsonObject.addProperty("mobile", Utility.getSharedPreferences(getContext(), Constant.Mobile));
        mvpPresenter.getDashBoardData(jsonObject);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


//    @Override
//    public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
//
//
//        Homemenu m = homemenuList.get(position);
//
//        switch (position) {
//
//            case 0:
//                Log.e(tag, "id at position 0" + m.getMenuId());
//                startActivity(new Intent(getActivity(), AddOwnerActivity.class));
//                break;
//
//            case 1:
//                startActivity(new Intent(getActivity(), AddMtgActivity.class));
//                break;
//
//            case 2:
//                startActivity(new Intent(getActivity(), SelectMtgActivity.class));
//                break;
//
//
//            case 3:
//                Intent intent = new Intent(getActivity(), SelectMtgActivity.class);
//                intent.putExtra("filled_form", 1);
//                startActivity(intent);
//                break;
//
//            case 4:
//
//                Intent intent5 = new Intent(getActivity(), PahsuChikitsha.class);
//                intent5.putExtra("form_id", m.getMenuId());
//                startActivity(intent5);
//                break;
//
//            case 5:
//                Intent intent6 = new Intent(getActivity(), MtgTraining.class);
//                intent6.putExtra("form_id", m.getMenuId());
//                startActivity(intent6);
//                Log.e("TAG", "id" + Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
//                break;
//
//            case 6:
//                Intent intent7 = new Intent(getActivity(), MtgMeeting.class);
//                intent7.putExtra("form_id", m.getMenuId());
//                startActivity(intent7);
//                Log.e("TAG", "id" + Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
//                break;
//
//            case 7:
//                Intent intent8 = new Intent(getActivity(), Adoption.class);
//                intent8.putExtra("form_id", m.getMenuId());
//                startActivity(intent8);
//                Log.e("TAG", "id" + Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
//                break;
//
//            case 8:
//                Intent intent9 = new Intent(getActivity(), RotationActivity.class);
//                intent9.putExtra("form_id", m.getMenuId());
//                startActivity(intent9);
//                Log.e("TAG", "id" + Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
//                break;
//
//            case 9:
//                Intent intent10 = new Intent(getActivity(), VipranTableActivity.class);
//                intent10.putExtra("form_id", m.getMenuId()+1);
//                startActivity(intent10);
//                break;
//           case 10:
//                Intent intent11 = new Intent(getActivity(), MilkInfoActivity.class);
//                intent11.putExtra("form_id", m.getMenuId());
//                startActivity(intent11);
//                break;
//
//        }
//
//    }

    @Override
    public void onNoInternetConnectivity(CommonResult result) {

        Log.e(tag, result.getMessage());

        SqliteDB sqliteDB = new SqliteDB(getActivity());
        Cursor cursor = sqliteDB.getHomeData();
        if (cursor.getCount() == 0) {
            Dialogs.showColorDialog(getContext(), result.getMessage());
        } else {
            homemenuList = new ArrayList <>();
            while (cursor.moveToNext()) {
                homemenuList.add(new Homemenu(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }
            setUpGridView(homemenuList);

        }


    }

    @Override
    public void onSuccess(DashboardDataResponse successResult) {

        Log.e(DashboardFragment.class.getSimpleName(), successResult.getMessage());
        SqliteDB sqliteDB = new SqliteDB(getContext());
        sqliteDB.clearHomeData();


        homemenuList.addAll(successResult.getData().getHomemenu());
        // homemenuList.add(new Homemenu(20,getString(R.string.form19),"https://image.flaticon.com/icons/png/512/1691/1691086.png"));

        Log.e(tag, "" + homemenuList.size());

        for (int i = 0; i < homemenuList.size(); i++) {
            Homemenu homemenu = successResult.getData().getHomemenu().get(i);
            Boolean success = sqliteDB.addHomeData(homemenu.getMenuId(), String.valueOf(homemenu.getMenuName()), homemenu.getMenuImage());
            Log.e(tag, "" + success);
            Log.e(tag, "name is " + homemenu.getMenuName());
        }

        setUpGridView(homemenuList);

    }

    @Override
    public void onNoFailure(CommonResult commonResult) {
        Dialogs.showColorDialog(getContext(), commonResult.getMessage());

    }

    @Override
    public void onItemSelected(int adapterPosition, List <Homemenu> homemenuList) {
        Homemenu mHomeMenu = homemenuList.get(adapterPosition);

        ArrayList <GramPanchayat> arrayList = new ArrayList <>();
        arrayList.add(new GramPanchayat(1, getResources().getString(R.string.fill_new_form)));
        arrayList.add(new GramPanchayat(2, getResources().getString(R.string.show_fille_fom)));

        switch (mHomeMenu.getMenuId()) {

            case 1:
                /**
                 * Add Pashu Palak
                 */
                Log.e(tag, "id at position 0" + mHomeMenu.getMenuId());
                startActivity(new Intent(getActivity(), AddOwnerActivity.class));
                break;


            case 4:
                /**
                 * Add MTG
                 */
                startActivity(new Intent(getActivity(), AddMtgActivity.class));
                break;

            case 5:
                /**
                 * Form Bhare
                 */
                startActivity(new Intent(getActivity(), SelectMtgActivity.class));
                break;


            case 7:
                /**
                 * Bhara hua form
                 */
                Intent intent = new Intent(getActivity(), SelectMtgActivity.class);
                intent.putExtra("filled_form", 1);
                startActivity(intent);
                break;

            case 14:
                /**
                 * Pashu chikitsha shivir
                 */

                mvpPresenter.openSelector(14, arrayList, getResources().getString(R.string.form_14));

                break;

            case 15:

                mvpPresenter.openSelector(15, arrayList, getResources().getString(R.string.form_15));


                break;

            case 16:

                mvpPresenter.openSelector(16, arrayList, getResources().getString(R.string.form_16));

                break;

            case 17:
                mvpPresenter.openSelector(17, arrayList, getResources().getString(R.string.form_17));

                break;

            case 18:
                mvpPresenter.openSelector(18, arrayList, getResources().getString(R.string.form_18));
                break;

            case 19:
                mvpPresenter.openSelector(19, arrayList, getResources().getString(R.string.vipran_talika));
                break;
            case 20:
                mvpPresenter.openSelector(20, arrayList, getResources().getString(R.string.form19));
                break;

        }

    }

    @Override
    public void onOptionSelected(int menuId, GramPanchayat model) {
        if (model.getGrampanchayatId() == 1) {

            switch (menuId) {
                case 14:
                    Intent intent5 = new Intent(getActivity(), PahsuChikitsha.class);
                    intent5.putExtra("form_id", menuId);
                    startActivity(intent5);
                    break;

                case 15:
                    Intent intent15 = new Intent(getActivity(), MtgTraining.class);
                    intent15.putExtra("form_id", menuId);
                    startActivity(intent15);
                    break;

                case 16:
                    Intent i16 = new Intent(getActivity(), MtgMeeting.class);
                    i16.putExtra("form_id", menuId);
                    startActivity(i16);
                    break;

                case 17:
                    Intent i17 = new Intent(getActivity(), Adoption.class);
                    i17.putExtra("form_id", menuId);
                    startActivity(i17);
                    break;

                case 18:
                    Intent i18 = new Intent(getActivity(), RotationActivity.class);
                    i18.putExtra("form_id", menuId);
                    startActivity(i18);
                    break;

                case 19:
                    Intent i19 = new Intent(getActivity(), VipranTableActivity.class);
                    i19.putExtra("form_id", menuId);
                    startActivity(i19);
                    break;

                case 20:
                    Intent i20 = new Intent(getActivity(), MilkInfoActivity.class);
                    i20.putExtra("form_id", menuId);
                    startActivity(i20);
                    break;


            }


        } else {
            Intent intent = new Intent(getContext(), FormDataActivity.class);
            intent.putExtra("form_id", menuId);
            startActivity(intent);

        }


    }

    private void setUpGridView(List <Homemenu> homemenus) {

        dashboardAdapter = new DashboardAdapter(getActivity(), homemenus, this);
        binding.gridview.setAdapter(dashboardAdapter);

     /*   gridAdapter = new GridAdapter(getActivity(), homemenus);
        binding.gridview.setAdapter(gridAdapter);*/

    }
}
