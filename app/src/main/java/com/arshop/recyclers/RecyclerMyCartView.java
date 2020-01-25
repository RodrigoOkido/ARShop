package com.arshop.recyclers;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerMyCartView extends RecyclerView.Adapter<RecyclerMyCartView.CartView> {


    @NonNull
    @Override
    public RecyclerMyCartView.CartView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMyCartView.CartView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class CartView extends RecyclerView.ViewHolder {

        public CartView(View cartItem){
            super(cartItem);
        }

    }
}
