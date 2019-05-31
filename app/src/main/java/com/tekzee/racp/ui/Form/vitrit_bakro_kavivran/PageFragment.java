package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.tekzee.racp.R;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.BakariDataModel;

public class PageFragment extends Fragment {
    private static final String ARG_TEXT = "param1";
//    private TextViewBold tvName;
//    private TextViewRegular tvDescription;
//    private RecyclerView rvContents;
//    private ColorDialog mColorDialog;

    private EditText edt_tagno;
    private EditText edt_age,edt_weight,edt_average;
    private TextView edt_nasl;
    private BakariDataModel model;

    public PageFragment() {

    }

    public static PageFragment newInstance(BakariDataModel model) {
        PageFragment fragment = new PageFragment();
        Bundle mBundle = new Bundle();
        //mBundle.putSerializable(ARG_TEXT, model);
        fragment.setArguments(mBundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            model = (BakariDataModel) getArguments().getSerializable(ARG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_bakari_detail, container, false);
        init(view);

      /*  tvName.setText(model.getName());
        tvDescription.setText(model.getCurrency() + model.getAmount() + " Per Month");
*/
       /* view.findViewById(R.id.btnGetItNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final double total = (Integer.parseInt(model.getDuration()) * Double.parseDouble(model.getAmount()));
                String message = "Thank you for selecting " + model.getName() + " your payable amount is " + model.getCurrency() + total + ". Should we continue with this?";
                mColorDialog = new ColorDialog(getActivity());
                mColorDialog.setTitle(getActivity().getResources().getString(R.string.app_name));
                mColorDialog.setContentText(message);
                mColorDialog.setPositiveListener(getActivity().getResources().getString(R.string.ok), new ColorDialog.OnPositiveListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {

                        onStartPaymentActivity(total, model.getCurrency());
                    }
                })
                        .setNegativeListener(getActivity().getResources().getString(R.string.cancel), new ColorDialog.OnNegativeListener() {
                            @Override
                            public void onClick(ColorDialog dialog) {

                                dialog.dismiss();
                            }
                        }).show();


            }
        });
*/

        return view;
    }

    private void init(View mView) {

        edt_tagno = mView.findViewById(R.id.et_tagNo);
        edt_age = mView.findViewById(R.id.et_age);
        edt_weight = mView.findViewById(R.id.et_weight);
        edt_average = mView.findViewById(R.id.et_avarage);
        edt_nasl = mView.findViewById(R.id.spnasl);


    }


   /* private void onStartPaymentActivity(double total, String currency) {
        Intent intent = new Intent(getActivity(), MyPaymentActivity.class);
        intent.putExtra("model", model);
        startActivity(intent);

    }*/
}