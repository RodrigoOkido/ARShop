package com.arshop.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.arshop.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;


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

        // Load the activity toolbar
        loadToolbar();
        // Load and configure the Bottom Navigation Menu
        loadBottomMenuNavigation();

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
        getLayoutElements();

        if (!userAddress.getText().toString().equals(null)) {
            //Fill all basic fields
            userName.setText(logged_user.getName());
            userAddress.setText(logged_user.getAddresses().get(0).getAddress());
            userAddressNumber.setText(String.valueOf(logged_user.getAddresses().get(0).getAddress_number()));
            userAddressComplement.setText(logged_user.getAddresses().get(0).getAddress_complement());
            userCep.setText(logged_user.getAddresses().get(0).getCEP());
            userNeighborhood.setText(logged_user.getAddresses().get(0).getNeighborhood());

            // Simple alert message in case the address do not corresponds to the users wishes.
            alertAddressMessage.setText("Não é este? Clique em editar endereço para alterar.");
            alertAddressMessage.setTextColor(Color.parseColor("#6B6E6F"));
        } else {
            alertAddressMessage.setText("Endereço não identificado. Para adicionar clique em " +
                    "Editar Endereço.");
        }
    }


    /**
     * Function invoked when user clicks in the button to proceed purchasing products.
     *
     * @param view The view context.
     */
    public void proceedPurchase(View view){

        // Context for toast if necessary.
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        // Check if the user selected the shipping and payment method options.
        String shippingOption = checkShipping();
        String paymentOption = checkPaymentMethod();

        // Check each option. If Null a toast will be generated.
        if (shippingOption == null) {
            CharSequence text = "Frete não selecionado.";
            Toast.makeText(context, text, duration).show();
            return;
        }

        else if (paymentOption == null) {
            CharSequence text = "Forma de pagamento não selecionado.";
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
                            intent = new Intent(ActivityProductPurchase.this,
                                    ActivityProductCategory.class);

                            // Start ProductCategory activity.
                            startActivity(intent);
                            break;
                        case R.id.menuCart:
                            // Create intent
                            intent = new Intent(ActivityProductPurchase.this,
                                    ActivityMyCart.class);

                            // Start MyCart activity.
                            startActivity(intent);
                            break;
                        case R.id.menuFavorite:
                            // Create intent
                            intent = new Intent(ActivityProductPurchase.this,
                                    ActivityMyFavorite.class);

                            // Start MyFavorite activity.
                            startActivity(intent);
                            break;
                        case R.id.menuProfile:
                            // Create intent
                            intent = new Intent(ActivityProductPurchase.this,
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
        toolbar.setTitle("Identificação");
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
        userName = findViewById(R.id.userName);
        userAddress = findViewById(R.id.userAddress);
        userAddressNumber = findViewById(R.id.userAddressNumber);
        userAddressComplement = findViewById(R.id.userAddressComplement);
        userCep = findViewById(R.id.userCep);
        userNeighborhood = findViewById(R.id.userNeighborhood);
        alertAddressMessage = findViewById(R.id.alertAddressMsg);
    }

}
