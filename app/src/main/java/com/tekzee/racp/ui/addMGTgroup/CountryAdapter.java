package com.tekzee.racp.ui.addMGTgroup;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.tekzee.racp.R;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.utils.KeyboardUtils;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> implements Filterable {
    private ArrayList<GramPanchayat> arrayList;
    private RowSelect listener;

    public CountryAdapter(ArrayList <GramPanchayat> arrayList, RowSelect listener) {
        this.arrayList = arrayList;
        this.listener = listener;
    }

    private Filter fRecords;

    @Override
    public Filter getFilter() {
        if (fRecords == null) {
            fRecords = new RecordFilter();
        }
        return fRecords;
    }


    //filter class
    private class RecordFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            //Implement filter logic
            // if edittext is null return the actual list
            if (constraint == null || constraint.length() == 0) {
                //No need for filter
                results.values = arrayList;
                results.count = arrayList.size();

            } else {
                //Need Filter
                // it matches the text  entered in the edittext and set the data in adapter list
                ArrayList<GramPanchayat> fRecords = new ArrayList<>();

                for (GramPanchayat model : arrayList) {
                    if (model.getGrampanchayatName().toUpperCase().trim().contains(constraint.toString().toUpperCase().trim())) {
                        fRecords.add(model);
                    }
                }
                results.values = fRecords;
                results.count = fRecords.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            //it set the data from filter to adapter list and refresh the recyclerview adapter
            arrayList = (ArrayList<GramPanchayat>) results.values;
            notifyDataSetChanged();
        }
    }

    public interface RowSelect {
        void onSelect(GramPanchayat model);
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


        public void bind(final GramPanchayat model, final RowSelect listener) {
            tv_name.setText(model.getGrampanchayatName());
            cvRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onSelect(model);
                }
            });
        }
    }
}