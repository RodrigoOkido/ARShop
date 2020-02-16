package com.arshop.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arshop.model.Product;

import java.io.Serializable;
import java.util.List;

public class ActivityProductPurchase extends AppCompatActivity {

    private List<Product> productsToPurchase;
    private String subtotal;

    private TextView subtotalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_purchase);

        // Recieve the data of the products to be purchased.
        Intent intent = getIntent();
        productsToPurchase = (List<Product>) intent.getExtras().getSerializable("PurchasingProducts");
        subtotal = intent.getExtras().getString("Subtotal") ;

        subtotalValue = findViewById(R.id.subtotalValue);
        subtotalValue.append(subtotal);
    }


    /**
     * Function invoked when user clicks in the button to proceed purchasing products.
     *
     * @param view The view context.
     */
    public void proceedPurchase(View view){

        // Check if the user selected the shipping and payment method options
        String shippingOption = checkShipping();
        String paymentOption = checkPaymentMethod();

        // Check each option. If Null a toast will be generated.
        if (shippingOption == null) {
            Context context = getApplicationContext();
            CharSequence text = "Frete não selecionado.";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
            return;
        }

        else if (paymentOption == null) {
            Context context = getApplicationContext();
            CharSequence text = "Forma de pagamento não selecionado.";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
            return;
        }

        // Create intent and guards all the necessary informations to pass to the next Activity.
        Intent intent = new Intent(view.getContext(), ActivityPaymentSection.class);
        intent.putExtra("PurchasingProducts", (Serializable)productsToPurchase);
        intent.putExtra("ShippingOption", shippingOption);
        intent.putExtra("PaymentOption", paymentOption);
        intent.putExtra("Subtotal", subtotal);


        // Start PaymentSection activity.
        this.startActivity(intent);
    }


    /**
     * Check the shipping option selected by the user. The option is based on the radio button.
     *
     * @return Return the String text of the selected radio button. Null if none is selected.
     */
    public String checkShipping(){

        String selectedOption = null;

        RadioGroup option = (RadioGroup) findViewById(R.id.shippingRadioGroup);
        int selected = option.getCheckedRadioButtonId();

        if(selected != -1) {
            RadioButton optionChosed = (RadioButton) findViewById(selected);
            selectedOption = "(" + optionChosed.getText().toString()+ ") R$15";
        }

        return selectedOption;
    }


    /**
     * Check the payment option selected by the user. The option is based on the radio button.
     *
     * @return Return the String text of the selected radio button. Null if none is selected.
     */
    public String checkPaymentMethod() {

        String selectedOption = null;

        RadioGroup option = (RadioGroup) findViewById(R.id.paymentMethodRadioGroup);
        int selected = option.getCheckedRadioButtonId();

        if(selected != -1) {
            RadioButton optionChosed = (RadioButton) findViewById(selected);
            selectedOption = optionChosed.getText().toString();
        }

        return selectedOption;
    }

}
