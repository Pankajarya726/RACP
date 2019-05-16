package com.tekzee.racp.ui.Form.mtg_meeting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.ui.Form.mtg_meeting.model.MtgMember;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;

import java.util.ArrayList;
import java.util.List;

public class MemberAdapter extends  RecyclerView.Adapter <MemberAdapter.MyViewHolder> {


    //private FormView mCallBack;
    private Context context;
    private List <MtgMember> memberList;
    private View mSelectedView;
    private int mSelectedPosition;

    public MemberAdapter(Context context, List <MtgMember> memberList) {
        this.context = context;
        this.memberList = memberList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_memberlist, viewGroup, false);
        final MyViewHolder viewHolder = new MyViewHolder(v);
        viewHolder.itemView.setClickable(true);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final MtgMember data = memberList.get(i);

        Log.e("adapter",data.getPashupalakName());



       myViewHolder.tv_name.setText(data.getPashupalakName());
        //myViewHolder.tv_name.setText("adadfa");
        myViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!myViewHolder.checkBox.isChecked()){
                    myViewHolder.checkBox.setChecked(false);
                    data.setCheck(false);


                }else {
                    myViewHolder.checkBox.setChecked(true);
                    data.setCheck(true);
                }
            }
        });
    }



/*
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final MtgMember data = memberList.get(i);

        Log.e("adapter",data.getPashupalakName());



//        myViewHolder.tv_name.setText(data.getPashupalakName());
        myViewHolder.tv_name.setText("adadfa");
        myViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewHolder.checkBox.isChecked()){
                    myViewHolder.checkBox.setChecked(false);
                    data.setCheck(false);


                }else {
                    myViewHolder.checkBox.setChecked(true);
                    data.setCheck(true);
                }
            }
        });

    }*/

    @Override
    public int getItemCount() {
        return memberList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.member_name);
            checkBox = itemView.findViewById(R.id.check_box);
        }
    }
}
