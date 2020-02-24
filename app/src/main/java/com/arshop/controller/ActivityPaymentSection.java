package com.arshop.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.arshop.model.Product;
import com.arshop.model.User;

import java.util.ArrayList;
import java.util.List;


/**
 * This class activity is the second step to user purchase the products (After the
 * ActivityProductPurchase). Here all the informations about the payment method is dealt.
 */
public class ActivityPaymentSection extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Private attributes.
    private List<Product> productsToPurchase;
    private String shippingOption, paymentMethodOption ,subtotal;
    private User logged_user;
    
    // Attributes to Spinner of credit card.
    private Spinner creditCardSpinnerValues;
    private List<String> optionValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_section);

        //Load the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        toolbar.setTitle("Pagamento");
        setSupportActionBar(toolbar);


        // Recieve the data of the products to be purchased.
        logged_user = ((LoggedUser) this.getApplication()).getUser();
        subtotal = String.valueOf(((LoggedUser) this.getApplication()).getSubtotal());

        Intent intent = getIntent();
        shippingOption = intent.getExtras().getString("ShippingOption") ;
        paymentMethodOption = intent.getExtras().getString("PaymentOption") ;

        // Spinner giving options about how much user wants to afford per month.
        creditCardSpinnerValues = (Spinner) findViewById(R.id.creditCardSpinner);
        creditCardSpinnerValues.setOnItemSelectedListener(this);

        // Generate the dropdown values.
        optionValues = new ArrayList<>();
        generatePriceValues(optionValues, subtotal);

        // Creates the adapter for the spinner.
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, optionValues);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        creditCardSpinnerValues.setAdapter(dataAdapter);

    }


    /**
     * Toolbar Menu.
     *
     * @param menu The menu
     * @return Return an boolean of the menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * Toolbar menu action buttons.
     *
     * @param item The item of menu
     * @return Return an boolean of the item menu.
     */
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
     * Generate all the option values of the subtotal. This gives the possibility to user decide
     * how much per month he wants to pay using your credit card.
     *
     * @param values An empty list which will be added all the payments values.
     * @param subtotal The subtotal of the users cart.
     */
    private void generatePriceValues(List<String> values, String subtotal) {

        double subtotalValue = Double.parseDouble(subtotal);

        for (int i = 1; i < 11; i++) {
            if(i == 1) {
                values.add("" + i + "x - R$" + String.format("%.2f", subtotalValue / i) + " à vista");
            } else {
                values.add("" + i + "x - R$" + String.format("%.2f", subtotalValue / i) + " (sem juros)");
            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /**
     * Function activated when user clicks in confirmation button after processed the credit card
     * informations. If no credit card detected, the next screen is locked until user inform this.
     *
     * @param view The view context.
     */
    public void proceedConfirmationPurchase(View view){
        // Create intent
        Intent intent = new Intent(view.getContext(), ActivityFinishPurchaseSection.class);
        intent.putExtra("ShippingOption", shippingOption);
        intent.putExtra("PaymentOption", paymentMethodOption);

        // Start FinishPurchaseSection activity.
        this.startActivity(intent);
    }

}