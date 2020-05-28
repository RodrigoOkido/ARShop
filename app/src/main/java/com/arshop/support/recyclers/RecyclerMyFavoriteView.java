package com.arshop.support.recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.arshop.controller.R;
import com.arshop.model.Product;

import java.util.List;


/**
 * RecyclerMyFavoriteView class handles the exhibition of user favorite product list.
 */
public class RecyclerMyFavoriteView extends RecyclerView.Adapter<RecyclerMyFavoriteView.FavoriteView> {
    private Context context;
    private List<Product> myFavoriteProducts;

    public RecyclerMyFavoriteView (Context context, List<Product> myFavorites) {
        this.context = context;
        this.myFavoriteProducts = myFavorites;
    }


    @NonNull
    @Override
    public RecyclerMyFavoriteView.FavoriteView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.card_item_view,parent,false);
        return new RecyclerMyFavoriteView.FavoriteView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMyFavoriteView.FavoriteView holder, int position) {

        holder.productInFavoriteName.setText(myFavoriteProducts.get(position).getName());
        holder.productInFavoritePrice.setText("R$" + myFavoriteProducts.get(position).getPrice());
        holder.deleteProductFromFavorite.setImageResource(R.drawable.icon_favorite);
        holder.deleteProductFromFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFavoriteProducts.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myFavoriteProducts.size();
    }



    public static class FavoriteView extends RecyclerView.ViewHolder {

        private CardView favoriteCardView;
        private TextView productInFavoriteName;
        private TextView productInFavoritePrice;
        private ImageView deleteProductFromFavorite;

        public FavoriteView(View cartItem){
            super(cartItem);

            favoriteCardView = (CardView) cartItem.findViewById(R.id.generic_item_view);
            productInFavoriteName = (TextView) cartItem.findViewById(R.id.itemName);
            productInFavoritePrice = (TextView) cartItem.findViewById(R.id.itemPrice);
            deleteProductFromFavorite = (ImageButton) cartItem.findViewById(R.id.removeItem);

        }

    }
}
