package com.tekzee.racp.ui.selectMtgGroup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tekzee.racp.R;
import com.tekzee.racp.ui.selectMtgGroup.model.Data;

import java.util.ArrayList;
import java.util.List;

public class SelectMtgAdapter extends RecyclerView.Adapter<SelectMtgAdapter.MyViewHolder> {

    private SelectMtgView mtgView;
    private Context context;
   private List<Data> mgtgroup;

 //   private List<MtgGroup> mgtgroup;
    private View mSelectedView;
    private int mSelectedPosition;

    public SelectMtgAdapter(Context context, List <Data> mgtgroup, SelectMtgView mtgView) {
        this.context = context;
        this.mgtgroup = mgtgroup;
        this.mtgView = mtgView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);
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
                                                       if (mtgView != null) {


                                                           mtgView.onItemSelected(viewHolder.getAdapterPosition(),mgtgroup);
                                                   }  }
                                               }
        );
        return viewHolder;
    }


    public SelectMtgView getMtgView() {
        return mtgView;
    }

    public void setMtgView(SelectMtgView mtgView) {
        this.mtgView = mtgView;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Data data = mgtgroup.get(i);
        //MtgGroup data = mgtgroup.get(i);
        myViewHolder.textView.setText(data.getMtggroupName());



    }

    @Override
    public int getItemCount() {
        return mgtgroup.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_name_mtggroup);

        }
    }

    public void filterList(ArrayList <Data> filteredList) {
        mgtgroup = filteredList;
        notifyDataSetChanged();
    }
}
