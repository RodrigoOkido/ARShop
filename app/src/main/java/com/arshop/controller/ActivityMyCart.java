package com.arshop.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private double subtotal;

    // Variables to get layout elements.
    private TextView cartSubtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        //Load the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Meu Carrinho");
        setSupportActionBar(toolbar);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuCart:
                // Create intent
                Intent intent = new Intent(this, ActivityMyCart.class);

                // Start MyCart activity.
                this.startActivity(intent);
                break;
            case R.id.menuProfile: break;

        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Receives all the user products added to Cart and return the subtotal of them.
     *
     * @param productInCart List of users wanted products.
     */
    public void setSubtotal(List<Product> productInCart) {

        subtotal = 0;

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

        if (subtotal == 0) {
            CharSequence text = "Seu Carrinho está vazio.";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(view.getContext(), text, duration).show();
            return;
        }

            // Create intent to ProductPurchase Activity
            Intent intent = new Intent(view.getContext(), ActivityProductPurchase.class);
            intent.putExtra("PurchasingProducts", (Serializable) productInCart);
            intent.putExtra("Subtotal", cartSubtotal.getText());

            // Start ProductPurchase activity.
            this.startActivity(intent);

    }

}
