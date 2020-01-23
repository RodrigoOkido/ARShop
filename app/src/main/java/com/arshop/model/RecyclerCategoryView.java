package com.arshop.model;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.arshop.controller.ActivityProductCategory;
import com.arshop.controller.ActivityProductList;
import com.arshop.controller.R;

import java.io.Serializable;
import java.util.List;

public class RecyclerCategoryView extends RecyclerView.Adapter<RecyclerCategoryView.CategoryView>  {

    private Context context;
    private List<Category> categoryListData;


    public RecyclerCategoryView(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryListData = categoryList;
    }


    @Override
    public CategoryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.category_card_item_view,parent,false);
        return new CategoryView(view);
    }


    @Override
    public void onBindViewHolder(CategoryView holder, int position) {
        holder.categoryImage.setImageResource(categoryListData.get(position).getCategoryImage());
        holder.categoryName.setText(categoryListData.get(position).getCategoryName());
        holder.categoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ActivityProductList.class);

                // Passing the category name chose by the user to ProductList Activity
                intent.putExtra("CategoryName", (Serializable) categoryListData.get(position));
                // start the activity
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryListData.size();
    }


    public static class CategoryView extends RecyclerView.ViewHolder {

        private CardView categoryCardView;
        private ImageView categoryImage;
        private TextView categoryName;

        public CategoryView(View categoryItem){
            super(categoryItem);

            categoryImage = (ImageView) categoryItem.findViewById(R.id.category_image);
            categoryName = (TextView) categoryItem.findViewById(R.id.category_name);
            categoryCardView = (CardView) categoryItem.findViewById(R.id.category_cardview_id);
        }

    }
}
