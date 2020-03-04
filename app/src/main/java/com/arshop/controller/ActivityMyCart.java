package com.arshop.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arshop.model.Product;
import com.arshop.recyclers.RecyclerMyCartView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
    private TextView cartEmptyTextStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        // Load the activity toolbar
        loadToolbar();
        // Load and configure the Bottom Navigation Menu
        loadBottomMenuNavigation();

        // Recieve the data of the products.
        productInCart = ((LoggedUser) this.getApplication()).getUsersCart();
        cartEmptyTextStatus = findViewById(R.id.cartEmptyText);

        if (productInCart.size() != 0) {
            // Remove the text about the empty cart.
            cartEmptyTextStatus.setText("");

            // Create the Recycler of the cart view
            RecyclerView cartView = (RecyclerView) findViewById(R.id.recycler_cart_list);
            RecyclerMyCartView cartAdapter = new RecyclerMyCartView(this, productInCart);
            cartView.setLayoutManager(new LinearLayoutManager(this));
            cartView.setAdapter(cartAdapter);
        }
        setSubtotal(productInCart);
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

        // Set the subtotal value to the view.
        cartSubtotal = (TextView)findViewById(R.id.subtotal_price);
        cartSubtotal.setText(String.valueOf(subtotal));

        // Set subtotal to global logged user.
        ((LoggedUser) this.getApplication()).setSubtotal(subtotal);

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

            // Start ProductPurchase activity.
            this.startActivity(intent);

    }


    /**
     * Bottom navigation menu actions.
     */
    public BottomNavigationView.OnNavigationItemSelectedListener listener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()){
                        case R.id.menuHome:
                            // Create intent
                            intent = new Intent(ActivityMyCart.this,
                                    ActivityProductCategory.class);

                            // Start ProductCategory activity.
                            startActivity(intent);
                            break;
                        case R.id.menuCart:
                            break;
                        case R.id.menuFavorite:
                            // Create intent
                            intent = new Intent(ActivityMyCart.this,
                                    ActivityMyFavorite.class);

                            // Start MyFavorite activity.
                            startActivity(intent);
                            break;
                        case R.id.menuProfile:
                            // Create intent
                            intent = new Intent(ActivityMyCart.this,
                                    ActivityMySettings.class);

                            // Start MySettings activity.
                            startActivity(intent);
                            break;
                    }

                    return true;
                }
            };


    /**
     * Load the toolbar of the Activity. This is the function where the name of the
     * Activity can be set in the toolbar.
     */
    public void loadToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        toolbar.setTitle("Meu Carrinho");
        setSupportActionBar(toolbar);

    }


    /**
     * Load the bottom navigation menu to the Activity. Adds an listener to the item selected
     * listener where all the actions will be defined (Search for "listener" in this class to
     * check more about this).
     */
    public void loadBottomMenuNavigation() {
        BottomNavigationView bottomMenu = findViewById(R.id.bottom_menu);
        bottomMenu.setOnNavigationItemSelectedListener(listener);
        bottomMenu.getMenu().findItem(R.id.menuCart).setChecked(true);
    }

}
