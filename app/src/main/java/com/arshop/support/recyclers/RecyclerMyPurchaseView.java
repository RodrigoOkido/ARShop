package com.arshop.support.recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.arshop.controller.R;
import com.arshop.model.Product;

import java.util.List;

public class RecyclerMyPurchaseView
        extends RecyclerView.Adapter<RecyclerMyPurchaseView.PurchasesView> {


    private Context context;
    private List<List<Product>> myPurchases ;


    public RecyclerMyPurchaseView (Context context, List<List<Product>> myPurchases) {
        this.context = context;
        this.myPurchases = myPurchases;
    }


    @NonNull
    @Override
    public RecyclerMyPurchaseView.PurchasesView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.card_item_view,parent,false);
        return new RecyclerMyPurchaseView.PurchasesView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMyPurchaseView.PurchasesView holder, int position) {

        holder.cardPurchaseText.setText("#1");

    }


    @Override
    public int getItemCount() {
        return myPurchases.size();
    }



    public static class PurchasesView extends RecyclerView.ViewHolder {

        private CardView purchasesView;
        private TextView cardPurchaseText;


        public PurchasesView(View cardItem){
            super(cardItem);

            purchasesView = (CardView) cardItem.findViewById(R.id.generic_item_view);
            cardPurchaseText = (TextView) cardItem.findViewById(R.id.itemName);

        }
    }




}
