package com.tekzee.racp.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekzee.racp.R;
import com.tekzee.racp.constant.GlideModuleConstant;
import com.tekzee.racp.ui.home.model.NavItem;
import com.tekzee.racp.ui.home.model.Sidemenu;

import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.MyViewHolder> {
    List<Sidemenu> navItemList;
    Context context;

    private NavigationDrawerCallBack mNavigationDrawerCallbacks;
    private View mSelectedView;
    private int mSelectedPosition;

    public NavigationAdapter(List <Sidemenu> navItemList, Context context) {
        this.context = context;
        this.navItemList = navItemList;
    }

    public NavigationDrawerCallBack getNavigationDrawerCallbacks() {
        return mNavigationDrawerCallbacks;
    }

    public void setNavigationDrawerCallbacks(NavigationDrawerCallBack navigationDrawerCallbacks) {
        mNavigationDrawerCallbacks = navigationDrawerCallbacks;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.navigation_row, viewGroup, false);
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
                                                       if (mNavigationDrawerCallbacks != null)
                                                           mNavigationDrawerCallbacks.onNavigationDrawerItemSelected(viewHolder.getAdapterPosition(),navItemList,NavigationAdapter.this);
                                                   }
                                               }
        );
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Sidemenu navItem = navItemList.get(i);

        Log.e("tag--",navItem.getMenuName());
        Log.e("tag--",navItem.getMenuImage());

        GlideModuleConstant.setImage(myViewHolder.imageView,context,navItem.getMenuImage());
        //myViewHolder.imageView.setImageResource(navItem.getImg());
        myViewHolder.textView.setText(navItem.getMenuName());


    }

    @Override
    public int getItemCount() {
        return navItemList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_nav);
            imageView = itemView.findViewById(R.id.image_nav);
        }
    }
}
