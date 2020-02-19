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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityPaymentSection extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Private attributes.
    private List<Product> productsToPurchase;
    private String shippingOption, paymentMethodOption ,subtotal;
    
    // Attributes to Spinner of credit card.
    private Spinner creditCardSpinnerValues;
    private List<String> optionValues;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_section);

        //Load the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Pagamento");
        setSupportActionBar(toolbar);

        // Recieve the data of the products to be purchased.
        Intent intent = getIntent();
        productsToPurchase = (List<Product>) intent.getExtras().getSerializable("PurchasingProducts");
        shippingOption = intent.getExtras().getString("ShippingOption") ;
        paymentMethodOption = intent.getExtras().getString("PaymentOption") ;
        subtotal = intent.getExtras().getString("Subtotal") ;

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


    public void proceedConfirmationPurchase(View view){
        // Create intent
        Intent intent = new Intent(view.getContext(), ActivityFinishPurchaseSection.class);
        intent.putExtra("PurchasingProducts", (Serializable)productsToPurchase);
        intent.putExtra("ShippingOption", shippingOption);
        intent.putExtra("PaymentOption", paymentMethodOption);
        intent.putExtra("Subtotal", subtotal);

        // Start FinishPurchaseSection activity.
        this.startActivity(intent);
    }

}