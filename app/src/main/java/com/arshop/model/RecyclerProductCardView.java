package com.arshop.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.arshop.controller.R;

public class RecyclerProductCardView extends RecyclerView.Adapter<RecyclerProductCardView.ProductView> {

    private Context context;
    private Category productListData;


    public RecyclerProductCardView(Context context, Category productList) {
        this.context = context;
        this.productListData = productList;
    }

    @NonNull
    @Override
    public RecyclerProductCardView.ProductView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.product_card_item_view,parent,false);
        return new ProductView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerProductCardView.ProductView holder, int position) {
//        holder.productImage.setImageResource(productListData.getProductList().get(position).ge());
        holder.productName.setText(productListData.getProductList().get(position).getName());
        holder.productPrice.setText(productListData.getProductList().get(position).getPrice());
        holder.productCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(context,.class);
//
//                // passing data to the book activity
//                intent.putExtra("Title",productListData.get(position).getTitle());
//                intent.putExtra("Description",productListData.get(position).getDescription());
//                intent.putExtra("Thumbnail",productListData.get(position).getThumbnail());
//                // start the activity
//                context.startActivity(intent);
             }
        });



    }


    @Override
    public int getItemCount() {
        return productListData.getProductList().size();
    }


    public static class ProductView extends RecyclerView.ViewHolder {

        private CardView productCardView;
        private ImageView productImage;
        private TextView productName, productPrice;

        public ProductView(View productItem){
            super(productItem);

            productCardView = (CardView)productItem.findViewById(R.id.card_product_view);
            productImage = (ImageView)productItem.findViewById(R.id.product_card_image);
            productName = (TextView)productItem.findViewById(R.id.product_card_name);
            productPrice = (TextView)productItem.findViewById(R.id.product_card_price);
        }

    }
}
