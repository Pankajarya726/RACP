package com.tekzee.racp.ui.selectMtgPreson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tekzee.racp.R;
import com.tekzee.racp.ui.selectMtgPreson.model.Datum;

import java.util.ArrayList;
import java.util.List;

public class MgtPersonAdapter extends RecyclerView.Adapter<MgtPersonAdapter.MyViewHolder> {

    private MgtPersonView mtgView;
    private Context context;
    private List <Datum> mtgperson;
    private View mSelectedView;
    private int mSelectedPosition;

    public MgtPersonAdapter(Context context, List <Datum> mgtgroup,MgtPersonView mtgView) {
        this.context = context;
        this.mtgperson = mgtgroup;
        this.mtgView = mtgView;
    }

    @NonNull
    @Override
    public MgtPersonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);
        final MgtPersonAdapter.MyViewHolder viewHolder = new MgtPersonAdapter.MyViewHolder(v);
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
                                                       if (mtgView != null)
                                                           mtgView.onItemSelected(viewHolder.getAdapterPosition(),mtgperson);
                                                   }
                                               }
        );
        return viewHolder;
    }


    public MgtPersonView getMtgView() {
        return mtgView;
    }

    public void setMtgView(MgtPersonView mtgView) {
        this.mtgView = mtgView;
    }

    @Override
    public void onBindViewHolder(@NonNull MgtPersonAdapter.MyViewHolder holder, int i) {
        Datum data = mtgperson.get(i);
        holder.textView.setText(data.getPashupalakName());



    }

    @Override
    public int getItemCount() {
        return mtgperson.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_name_mtggroup);

        }
    }


    public void filterList(ArrayList <Datum> filteredList) {
        mtgperson = filteredList;
        notifyDataSetChanged();
    }
}
