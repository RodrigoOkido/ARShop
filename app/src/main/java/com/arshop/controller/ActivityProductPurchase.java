package com.arshop.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arshop.model.Product;
import com.arshop.model.User;

import java.util.List;


/**
 * Class responsible to deal with the user purchases. This class activity is the first step
 * to user complete the products to purchase. All the information about the user, shipping and
 * payment method are informed here.
 */
public class ActivityProductPurchase extends AppCompatActivity {

    // Private attributes to control the list of products to purchase and subtotal.
    private String subtotal;
    private User logged_user;

    // Attributes to manipulate the XML elements
    private TextView subtotalValue, alertAddressMessage;
    private EditText userName, userAddress, userAddressNumber, userAddressComplement, userCep,
            userNeighborhood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_purchase);

        //Load the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        toolbar.setTitle("Identificação");
        setSupportActionBar(toolbar);

        // Recieve the data of the products to be purchased.
        logged_user = ((LoggedUser) this.getApplication()).getUser();
        subtotal = String.valueOf(((LoggedUser) this.getApplication()).getSubtotal());

        fillUserAddressBasicInformations(logged_user);

        subtotalValue = findViewById(R.id.subtotalValue);
        subtotalValue.append(subtotal);
    }


    /**
     * If registered, fill all the users basic address informations automatically. Otherwise a
     * message will pop informing that the user needs to register at least one address.
     *
     * @param logged_user Actual user logged to the app.
     */
    public void fillUserAddressBasicInformations(User logged_user) {
        getUserAddressFields();

        if (!userAddress.getText().toString().equals(null)) {
            //Fill all basic fields
            userName.setText(logged_user.getName());
            userAddress.setText(logged_user.getAddress());
            userAddressNumber.setText(String.valueOf(logged_user.getAddress_number()));
            userAddressComplement.setText(logged_user.getAddress_complement());
            userCep.setText(logged_user.getCEP());
            userNeighborhood.setText(logged_user.getNeighborhood());

            // Simple alert message in case the address do not corresponds to the users wishes.
            alertAddressMessage.setText("Não é este? Clique em editar endereço para alterar.");
            alertAddressMessage.setTextColor(Color.parseColor("#6B6E6F"));
        } else {
            alertAddressMessage.setText("Endereço não identificado. Para adicionar clique em " +
                    "Editar Endereço.");
        }
    }


    /**
     * Get all the EditText fields from the Product Purchase Layout to manipulate.
     */
    public void getUserAddressFields() {
        userName = findViewById(R.id.userName);
        userAddress = findViewById(R.id.userAddress);
        userAddressNumber = findViewById(R.id.userAddressNumber);
        userAddressComplement = findViewById(R.id.userAddressComplement);
        userCep = findViewById(R.id.userCep);
        userNeighborhood = findViewById(R.id.userNeighborhood);
        alertAddressMessage = findViewById(R.id.alertAddressMsg);
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
        intent.putExtra("ShippingOption", shippingOption);
        intent.putExtra("PaymentOption", paymentOption);



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
