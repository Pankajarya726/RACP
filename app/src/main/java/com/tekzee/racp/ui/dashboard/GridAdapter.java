package com.tekzee.racp.ui.dashboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekzee.racp.R;
import com.tekzee.racp.constant.GlideModuleConstant;
import com.tekzee.racp.ui.dashboard.model.Homemenu;

import java.util.List;


public class GridAdapter extends BaseAdapter {

    private static final String tag = GridAdapter.class.getSimpleName();

    Context context;
    List <Homemenu> homemenus;


    public GridAdapter(Context context, List <Homemenu> homemenus) {
        this.context = context;
        this.homemenus = homemenus;
        // this.gridtext = gridViewString;
    }

    @Override
    public int getCount() {
        return homemenus.size();
    }

    @Override
    public Object getItem(int position) {
        return new Object();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Homemenu homemenu = homemenus.get(position);

        View view;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            view = new View(context);

            view = inflater.inflate(R.layout.row_gridview, null);

            TextView textViewAndroid = view.findViewById(R.id.tv_gridtext);
            ImageView imageViewAndroid = view.findViewById(R.id.img_grid_img);

            /*textViewAndroid.setText(gridtext[position]);
            imageViewAndroid.setImageResource(gridimage[position]);*/

            GlideModuleConstant.setImage(imageViewAndroid, context, homemenu.getMenuImage());
            Log.e(tag, "" + homemenu.getMenuName());
            textViewAndroid.setText(String.valueOf(homemenu.getMenuName()));


        } else {
            view = (View) convertView;
        }

        return view;
    }


}
