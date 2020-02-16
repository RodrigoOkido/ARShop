package com.arshop.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.arshop.model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityPaymentSection extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<Product> productsToPurchase;
    private String shippingOption, paymentMethodOption ,subtotal;
    
    // Attributes to Spinner of credit card.
    private Spinner creditCardSpinnerValues;
    private List<String> optionValues;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_section);

        // Recieve the data of the products to be purchased.
        Intent intent = getIntent();
        productsToPurchase = (List<Product>) intent.getExtras().getSerializable("PurchasingProducts");
        shippingOption = intent.getExtras().getString("ShippingOption") ;
        paymentMethodOption = intent.getExtras().getString("PaymentOption") ;
        subtotal = intent.getExtras().getString("Subtotal") ;

        // Show the spinner with how much the user wants to afford.
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
     * Generate all the option values of the subtotal. This gives the user to decide how much per
     * month user wants to pay using your credit card.
     *
     * @param values An empty list which will be added all the payments options.
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