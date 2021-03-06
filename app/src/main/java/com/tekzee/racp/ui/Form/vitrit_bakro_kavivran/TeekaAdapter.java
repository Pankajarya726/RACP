package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tekzee.racp.R;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Immunization;
import com.tekzee.racp.utils.mDatePickerDialog;

import java.util.List;

public class TeekaAdapter extends RecyclerView.Adapter <TeekaAdapter.MyViewHolder> {


    private FormView mCallBack;
    private Context context;
    private List <Immunization> immunizations;
    private View mSelectedView;
    private int mSelectedPosition;

    public TeekaAdapter(Context context, List <Immunization> immunizations,FormView mCallBack) {
        this.context = context;
        this.immunizations = immunizations;
        this.mCallBack = mCallBack;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        final MyViewHolder viewHolder = new MyViewHolder(v);
        viewHolder.itemView.setClickable(true);
        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final Immunization data = immunizations.get(i);
        myViewHolder.tv_name.setText(data.getTeekaname());
        myViewHolder.tv_date.setText(mDatePickerDialog.changeFormate(data.getTeekadate()));
        myViewHolder.tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onDateSelected(myViewHolder.getAdapterPosition(),immunizations,myViewHolder.tv_date);

            }
        });



    }

    @Override
    public int getItemCount() {
        return immunizations.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.teekaname);
            tv_date = itemView.findViewById(R.id.teekadate);



        }
    }
}
