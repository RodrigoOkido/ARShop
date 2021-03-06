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

import com.arshop.controller.activities.ActivityMyCart;
import com.arshop.controller.R;
import com.arshop.model.Product;

import java.util.List;


/**
 * RecyclerMyCartView handles the exhibition of the list of products wished by the user.
 */
public class RecyclerMyCartView extends RecyclerView.Adapter<RecyclerMyCartView.CartView> {

    private Context context;
    private List<Product> myCart;

    public RecyclerMyCartView (Context context, List<Product> myCart) {
        this.context = context;
        this.myCart = myCart;
    }


    @NonNull
    @Override
    public RecyclerMyCartView.CartView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.card_item_view,parent,false);
        return new CartView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMyCartView.CartView holder, int position) {

        holder.productInCartName.setText(myCart.get(position).getName());
        holder.productInCartPrice.setText("R$"+myCart.get(position).getPrice());
        if(context.getClass().getSimpleName().equals("ActivityMyCart")) {
            holder.deleteProductFromCart.setImageResource(R.drawable.delete_button);
            holder.deleteProductFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myCart.remove(position);
                    notifyDataSetChanged();
                    ((ActivityMyCart) context).setSubtotal(myCart);
//
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return myCart.size();
    }



    public static class CartView extends RecyclerView.ViewHolder {

        private CardView cartCardView;
        private TextView productInCartName;
        private TextView productInCartPrice;
        private ImageView deleteProductFromCart;

        public CartView(View cartItem){
            super(cartItem);

            cartCardView = (CardView) cartItem.findViewById(R.id.generic_item_view);
            productInCartName = (TextView) cartItem.findViewById(R.id.itemName);
            productInCartPrice = (TextView) cartItem.findViewById(R.id.itemPrice);
            deleteProductFromCart = (ImageButton) cartItem.findViewById(R.id.removeItem);

        }

    }
}
