package com.arshop.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.arshop.model.Product;

import java.io.Serializable;
import java.util.List;

public class ActivityPaymentSection extends AppCompatActivity {

    private List<Product> productsToPurchase;
    private String shippingOption, paymentMethodOption ,subtotal;

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