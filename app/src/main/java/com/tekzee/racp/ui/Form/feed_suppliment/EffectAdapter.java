package com.tekzee.racp.ui.Form.feed_suppliment;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tekzee.racp.R;

import java.util.ArrayList;

public class EffectAdapter extends RecyclerView.Adapter<EffectAdapter.ViewHolder>  {
private ArrayList <String > arrayList;
private RowSelect listener;

public EffectAdapter(ArrayList <String> arrayList, RowSelect listener) {
        this.arrayList = arrayList;
        this.listener = listener;
        }




//filter class


public interface RowSelect {
    void onSelect(String effedt);
}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_grampanchayat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(arrayList.get(i), listener);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

class ViewHolder extends RecyclerView.ViewHolder {
    private CardView cvRoot;
    private TextView tv_name;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        cvRoot = itemView.findViewById(R.id.cvRoot);
        tv_name = itemView.findViewById(R.id.tv_name);
    }


    public void bind(final String model, final RowSelect listener) {
        tv_name.setText(model);
        cvRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onSelect(model);
            }
        });
    }
}
}