package com.arshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.arshop.controller.R;
import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Class responsible to build an image slider. This is used in the product detail activity.
 * Used when the user wants to see more images about an determined product.
 */
public class ImageSliderView extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<String> productImages;

    public ImageSliderView (Context context, List<String> product_images){
        this.context = context;
        this.productImages = product_images;
    }

    @Override
    public int getCount() {
        return productImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View prodImageView = inflater.inflate(R.layout.activity_product_detail, container, false);

        ImageView productImg = (ImageView) prodImageView.findViewById(R.id.prodImages);


        Glide.with(context)
                .load(productImages.get(position).trim())
                .into(productImg);


        container.addView(prodImageView);

        return prodImageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((LinearLayout) object);
    }
}
