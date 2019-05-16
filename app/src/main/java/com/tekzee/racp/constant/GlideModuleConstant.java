package com.tekzee.racp.constant;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.tekzee.racp.R;
import com.tekzee.racp.utils.GlideCircleTransformation;

@GlideModule
public class GlideModuleConstant extends AppGlideModule {


    public static void setImage(ImageView view, Context context, String url) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .circleCrop()
                .transform(new GlideCircleTransformation(context))
                .placeholder(R.drawable.user)
                .error(R.drawable.user)
                .into(view);
    }

    public static void setImageBitmap(ImageView view, Context context, Bitmap url) {

        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .transform(new GlideCircleTransformation(context))
                .placeholder(R.drawable.menu)
                .error(R.drawable.menu)
                .into(view);
    }

}