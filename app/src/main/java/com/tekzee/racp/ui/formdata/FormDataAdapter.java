package com.tekzee.racp.ui.formdata;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tekzee.racp.R;
import com.tekzee.racp.ui.formdata.model.Datum;

import java.util.List;

public class FormDataAdapter extends  RecyclerView.Adapter <FormDataAdapter.MyViewHolder> {

    private FormDataView mCallBack;
    private Context context;
    private List<Datum> formData;

    private int mSelectedPosition;


    private View mSelectedView;


    public FormDataAdapter(Context context, List<Datum> formData ,FormDataView mCallBack) {
        this.context = context;
        this.formData = formData;
        this.mCallBack = mCallBack;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item2, viewGroup, false);
        final MyViewHolder viewHolder = new MyViewHolder(v);
        viewHolder.itemView.setClickable(true);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       if (mSelectedView != null) {
                                                           mSelectedView.setSelected(false);
                                                       }
                                                       mSelectedPosition = viewHolder.getAdapterPosition();
                                                       v.setSelected(true);
                                                       mSelectedView = v;
                                                       if (mCallBack != null)
                                                       {

                                                           mCallBack.onItemSelected(viewHolder.getAdapterPosition(),formData);
                                                       }}
                                               }
        );
        return viewHolder;
    }

    public FormDataView getmCallBack() {
        return mCallBack;
    }

    public void setmCallBack(FormDataView mtgView) {
        this.mCallBack = mtgView;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        Datum data = formData.get(i);

        if (String.valueOf(data.getTagNo()).isEmpty() || String.valueOf(data.getTagNo()).equalsIgnoreCase("null")){
            holder.tagno.setText(String.valueOf(data.getCreatedAt()));

        }else {
            holder.tagno.setText(String.valueOf(data.getCreatedAt()));

        }
    }

    @Override
    public int getItemCount() {
        return formData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tagno;
        TextView date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tagno = itemView.findViewById(R.id.tagNo);
            date = itemView.findViewById(R.id.date_receipt);

        }
    }



}
