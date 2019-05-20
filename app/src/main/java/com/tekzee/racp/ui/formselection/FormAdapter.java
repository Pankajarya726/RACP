package com.tekzee.racp.ui.formselection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekzee.racp.R;
import com.tekzee.racp.constant.GlideModuleConstant;
import com.tekzee.racp.ui.formselection.model.FormData;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.MyViewHolder> {

    private ListFormView mCallBack;
    private Context context;
    private List<FormData> formData;

    private int mSelectedPosition;


    private View mSelectedView;


    public FormAdapter(Context context, List<FormData> formData ,ListFormView mCallBack) {
        this.context = context;
        this.formData = formData;
        this.mCallBack = mCallBack;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_info, viewGroup, false);
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

    public ListFormView getmCallBack() {
        return mCallBack;
    }

    public void setmCallBack(ListFormView mtgView) {
        this.mCallBack = mtgView;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        FormData data = formData.get(i);
        holder.textView.setText(data.getFormName());
        GlideModuleConstant.setImage(holder.imageView,context,data.getFormImage());
        //holder.imageView.setImageResource(R.drawable._form);

    }

    @Override
    public int getItemCount() {
        return formData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             imageView = itemView.findViewById(R.id.img_heading);
             textView = itemView.findViewById(R.id.tv_heading);

        }
    }


    public void filterList(ArrayList<FormData> filteredList) {
        formData = filteredList;
        notifyDataSetChanged();
    }
}
