package com.arshop.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.arshop.model.Product;
import com.arshop.recyclers.RecyclerMyCartView;

import java.util.List;


/**
 * Class to show the users cart. Each product wanted to purchased will be added to this Activity.
 */
public class ActivityMyCart extends AppCompatActivity {

    private List<Product> productInCart;
    private TextView cartSubtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        // Recieve the data of the category selected.
        Intent intent = getIntent();
        productInCart = (List<Product>) intent.getExtras().getSerializable("ProductWanted");

        Log.d("CART", productInCart.get(0).getName());

        RecyclerView cartView = (RecyclerView) findViewById(R.id.recycler_cart_list);
        RecyclerMyCartView cartAdapter = new RecyclerMyCartView(this,productInCart);
        cartView.setLayoutManager(new LinearLayoutManager(this));
        cartView.setAdapter(cartAdapter);

        setTotalSubtotal(productInCart);
    }


    public void setTotalSubtotal(List<Product> productInCart) {

        double subtotal = 0;

        for (int i = 0 ; i < productInCart.size() ; i++) {
            subtotal = subtotal + Double.valueOf(productInCart.get(i).getPrice());
        }

        cartSubtotal = (TextView)findViewById(R.id.subtotal_price);
        cartSubtotal.setText(String.valueOf(subtotal));
    }

}
