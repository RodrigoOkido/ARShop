package com.arshop.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.arshop.controller.R;
import com.arshop.model.Product;
import com.arshop.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;
import java.util.List;


/**
 * PaymentSection activity. This class activity is the second step to user purchase the products
 * (After the ActivityProductPurchase). Here all the informations about the payment method is dealt.
 */
public class ActivityPaymentSection extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Private attributes.
    private List<Product> productsToPurchase;
    private String shippingOption, paymentMethodOption ,subtotal;
    private User logged_user;
    
    // Attributes to Spinner of credit card.
    private Spinner creditCardSpinnerValues;
    private List<String> optionValues;

    // Attributes to deal with the EditText fields of layout.
    private EditText userCreditCardName, userCreditCardNumber, userCreditCardExpiringDate,
        userCreditCardCvv, userBornDate, userCpf;

    // Progress state bar
    private String[] descriptionData = {"Identificação", "Pagamento", "Confirmação", "Concluído"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_section);

        // Load the activity toolbar.
        loadToolbar();
        // Load and configure the Bottom Navigation Menu.
        loadBottomMenuNavigation();

        // Load state bar progress
        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.purchase_bar_identification);
        stateProgressBar.setStateDescriptionData(descriptionData);


        // Recieve the data of the products to be purchased.
        logged_user = ((LoggedUser) this.getApplication()).getUser();
        subtotal = String.valueOf(((LoggedUser) this.getApplication()).getSubtotal());

        Intent intent = getIntent();
        shippingOption = intent.getExtras().getString("ShippingOption") ;
        paymentMethodOption = intent.getExtras().getString("PaymentOption") ;


        fillUserCreditCardInfo(logged_user);
        lockElements();


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
     * If registered, fill all the user credit card information automatically. Otherwise a
     * message will pop informing that the user needs to register at least one credit card.
     *
     * @param logged_user Actual user logged to the app.
     */
    public void fillUserCreditCardInfo(User logged_user) {
        getLayoutElements();

        if(!userCreditCardNumber.getText().equals(null)){
            userCreditCardName.setText(logged_user.getUser_cards().get(0).getTitularName());
            userCreditCardNumber.setText(logged_user.getUser_cards().get(0).getCardNumber());
            userCreditCardExpiringDate.setText(logged_user.getUser_cards().get(0).getExpirationDate());
            userCreditCardCvv.setText(String.valueOf(logged_user.getUser_cards().get(0).getCvv()));
            userBornDate.setText(logged_user.getUser_cards().get(0).getBornDate());
            userCpf.setText(logged_user.getUser_cards().get(0).getCPF());
        }

    }


    /**
     * Lock all EditText elements for edition. The fields are unlocked only when the user wants
     * to modify and edit any data. Otherwise the fields keeps it only for readonly.
     */
    public void lockElements() {
        userCreditCardName.setEnabled(false);
        userCreditCardNumber.setEnabled(false);
        userCreditCardExpiringDate.setEnabled(false);
        userCreditCardCvv.setEnabled(false);
        userBornDate.setEnabled(false);
        userCpf.setEnabled(false);
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
                            intent = new Intent(ActivityPaymentSection.this,
                                    ActivityProductCategory.class);

                            // Start ProductCategory activity.
                            startActivity(intent);
                            break;
                        case R.id.menuCart:
                            // Create intent
                            intent = new Intent(ActivityPaymentSection.this,
                                    ActivityMyCart.class);

                            // Start MyCart activity.
                            startActivity(intent);
                            break;
                        case R.id.menuFavorite:
                            // Create intent
                            intent = new Intent(ActivityPaymentSection.this,
                                    ActivityMyFavorite.class);

                            // Start MyFavorite activity.
                            startActivity(intent);
                            break;
                        case R.id.menuProfile:
                            // Create intent
                            intent = new Intent(ActivityPaymentSection.this,
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
        toolbar.setTitle("Pagamento");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
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
        userCreditCardName = findViewById(R.id.userCreditCardName);
        userCreditCardNumber = findViewById(R.id.userCreditCardNumber);
        userCreditCardExpiringDate = findViewById(R.id.userCreditCardExpiringDate);
        userCreditCardCvv = findViewById(R.id.userCreditCardCvv);
        userBornDate = findViewById(R.id.userBornDate);
        userCpf = findViewById(R.id.userCpf);
    }

}