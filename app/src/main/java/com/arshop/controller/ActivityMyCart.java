package com.arshop.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.arshop.model.Product;
import com.arshop.recyclers.RecyclerMyCartView;

import java.io.Serializable;
import java.util.List;


/**
 * Class to show the users cart. Each product wanted to purchased will be added to this Activity.
 */
public class ActivityMyCart extends AppCompatActivity {


    // Variables which deals with the users cart information.
    private List<Product> productInCart;
    private TextView cartSubtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        // Recieve the data of the products.
        Intent intent = getIntent();
        productInCart = (List<Product>) intent.getExtras().getSerializable("ProductWanted");

        Log.d("CART", productInCart.get(0).getName());


        // Create the Recycler of the cart view
        RecyclerView cartView = (RecyclerView) findViewById(R.id.recycler_cart_list);
        RecyclerMyCartView cartAdapter = new RecyclerMyCartView(this,productInCart);
        cartView.setLayoutManager(new LinearLayoutManager(this));
        cartView.setAdapter(cartAdapter);

        setSubtotal(productInCart);
    }


    /**
     * Receives all the user products added to Cart and return the subtotal of them.
     *
     * @param productInCart List of users wanted products.
     */
    public void setSubtotal(List<Product> productInCart) {

        double subtotal = 0;

        for (int i = 0 ; i < productInCart.size() ; i++) {
            subtotal = subtotal + Double.valueOf(productInCart.get(i).getPrice());
        }

        cartSubtotal = (TextView)findViewById(R.id.subtotal_price);
        cartSubtotal.setText(String.valueOf(subtotal));
    }


    /**
     * Once in the cart screen, user can return to the beginning to keep buying other products.
     * If so this function will be called.
     *
     * @param view The view context.
     */
    public void returnToShop(View view){

        // Create intent to the ProductCategory Activity
        Intent intent = new Intent(view.getContext(), ActivityProductCategory.class);

        // Turn back to the main activity.
        this.startActivity(intent);
    }


    /**
     * If user wants to conclude the purchase and click on the "Finish Purchase" button, this function
     * will be called.
     *
     * @param view The view context.
     */
    public void finishPurchase(View view){

        // Create intent to ProductPurchase Activity
        Intent intent = new Intent(view.getContext(), ActivityProductPurchase.class);
        intent.putExtra("PurchasingProducts", (Serializable)productInCart);
        intent.putExtra("Subtotal", cartSubtotal.getText());

        // Start ProductPurchase activity.
        this.startActivity(intent);

    }

}
