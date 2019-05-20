package com.tekzee.racp.ui.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekzee.racp.R;
import com.tekzee.racp.constant.GlideModuleConstant;
import com.tekzee.racp.ui.dashboard.model.Homemenu;
import com.tekzee.racp.ui.formselection.ListFormView;
import com.tekzee.racp.ui.formselection.model.FormData;

import java.util.ArrayList;
import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {

    private DashboardView mCallBack;
    private Context context;
    private List<Homemenu> homemenuList;

    private int mSelectedPosition;


    private View mSelectedView;


    public DashboardAdapter(Context context, List<Homemenu> homemenuList , DashboardView mCallBack) {
        this.context = context;
        this.homemenuList = homemenuList;
        this.mCallBack = mCallBack;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_gridview, viewGroup, false);
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

                    mCallBack.onItemSelected(viewHolder.getAdapterPosition(),homemenuList);
            }}
        }
        );
        return viewHolder;
    }

    public DashboardView getmCallBack() {
        return mCallBack;
    }

    public void setmCallBack(DashboardView mtgView) {
        this.mCallBack = mtgView;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        Homemenu data = homemenuList.get(i);

        holder.textView.setText(data.getMenuName());
        GlideModuleConstant.setImage(holder.imageView,context,data.getMenuImage());
        //holder.imageView.setImageResource(R.drawable._form);

    }

    @Override
    public int getItemCount() {
        return homemenuList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             imageView = itemView.findViewById(R.id.img_grid_img);
             textView = itemView.findViewById(R.id.tv_gridtext);

        }
    }



}
