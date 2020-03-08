package com.arshop.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arshop.controller.R;
import com.arshop.model.Product;
import com.arshop.support.recyclers.RecyclerMyCartView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


/**
 * FinishPurchaseSection activity. This class activity represents the final step of the
 * product purchases. Called after the ActivityPaymentSection class, here is where the user can
 * check and confirm all the summary about what is being purchased, the subtotal and how this
 * will be payed.
 */
public class ActivityFinishPurchaseSection extends AppCompatActivity {

    // Private attributes.
    private List<Product> productsToPurchase;
    private String paymentMethodOptionChosed, shippingOptionChosed, subtotal;

    // Layout TextView fields.
    private TextView paymentFormValue, shippingValue, subtotalValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_purchase_section);

        // Load the activity toolbar
        loadToolbar();
        // Load and configure the Bottom Navigation Menu
        loadBottomMenuNavigation();
        // Associates each field of the layout to a variable
        getLayoutElements();

        Intent intent = getIntent();
        productsToPurchase = ((LoggedUser) this.getApplication()).getUsersCart();
        paymentMethodOptionChosed = intent.getExtras().getString("PaymentOption") ;
        shippingOptionChosed = intent.getExtras().getString("ShippingOption") ;
        subtotal = String.valueOf(((LoggedUser) this.getApplication()).getSubtotal());

        RecyclerView cartView = (RecyclerView) findViewById(R.id.recycler_listPurchaseSummary);
        RecyclerMyCartView cartAdapter = new RecyclerMyCartView(this,productsToPurchase);
        cartView.setLayoutManager(new LinearLayoutManager(this));
        cartView.setAdapter(cartAdapter);

        paymentFormValue.setText(paymentMethodOptionChosed);
        shippingValue.setText(shippingOptionChosed);
        subtotalValue.append(subtotal);

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
                            intent = new Intent(ActivityFinishPurchaseSection.this,
                                    ActivityProductCategory.class);

                            // Start ProductCategory activity.
                            startActivity(intent);
                            break;
                        case R.id.menuCart:
                            // Create intent
                            intent = new Intent(ActivityFinishPurchaseSection.this,
                                    ActivityMyCart.class);

                            // Start MyCart activity.
                            startActivity(intent);
                            break;
                        case R.id.menuFavorite:
                            // Create intent
                            intent = new Intent(ActivityFinishPurchaseSection.this,
                                    ActivityMyFavorite.class);

                            // Start MyFavorite activity.
                            startActivity(intent);
                            break;
                        case R.id.menuProfile:
                            // Create intent
                            intent = new Intent(ActivityFinishPurchaseSection.this,
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
        toolbar.setTitle("Confirmar Compra");
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
    }


    /**
     * Associates each element of the Layout (from the xml) to a variable.
     */
    public void getLayoutElements() {
        paymentFormValue = (TextView)findViewById(R.id.paymentFormSummaryValue);
        shippingValue = (TextView)findViewById(R.id.shippingSummaryValue);
        subtotalValue = (TextView)findViewById(R.id.subtotalSummaryValue);
    }

}