package com.arshop.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arshop.model.Address;
import com.arshop.model.CreditCard;
import com.arshop.model.Product;
import com.arshop.model.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Login Activity. This class has all the properties to correctly log any user inside the
 * application.
 *
 */
public class ActivityLogin extends AppCompatActivity {

    // Private User inputs checks to login.
    private String userEmail, userPassword;

    // User to login. The superUser is an debug for developer only.
    private User user, superUser;
    private boolean superUserActivated = true;

    // Interface XML elements.
    private EditText email, password;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getLayoutElements();

        //Developer Super User Mode.
        if(superUserActivated) {
            loadSuperAdmin();
        }

        //Load the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);

    }


    /**
     * Function called when user press the button to login. Verify the email and password fields,
     * if correct the user enters to the app. Otherwise a message will be displayed to recheck
     * the input fields.
     *
     * @param view The view.
     */
    public void login(View view){

        userEmail = email.getText().toString();
        userPassword = password.getText().toString();

        if (superUserActivated){
            enterToApp(view);
        } else {

            // Checks login Information



        }

    }


    /**
     * Function to enter to the application. If user login worked successfully, this function will
     * be called to proceed to the main screen of the app.
     *
     * @param view The view.
     */
    public void enterToApp(View view) {
        // Create intent
        Intent intent = new Intent(view.getContext(), ActivityProductCategory.class);

        // Start ProductCategory Activity (main activity).
        this.startActivity(intent);
    }


    /**
     * If user do not have an account associated. Clicking in the "Primeira vez?" button leaves
     * to the user registration activity.
     *
     * @param view The view.
     */
    public void registerNewUser(View view) {
        // Create intent
        Intent intent = new Intent(view.getContext(), ActivityCreateUser.class);

        // Start CreateUser Activity.
        this.startActivity(intent);
    }


    /**
     * Load super admin function. This is only for developer mode purpose. This gives an unique
     * access to the developer to login the application easily. The access created to the developer
     * has a bunch of fictional information only for debugging purpose.
     */
    public void loadSuperAdmin(){

        // Creates and add an generic Credit Card.
        CreditCard cc = new CreditCard(CreditCard.Credit.MASTERCARD, "Admin App Developer",
                "000000000","12/99",901, "02/02/94",
                "123.456.789-00");

        List<CreditCard> superCreditCards = new ArrayList<>();
        superCreditCards.add(cc);


        // Creates and add an generic Address.
        Address adr = new Address("Street 10", "Block 7", "00000-00",
                "No Complement.", 0);

        List<Address> superAddresses = new ArrayList<>();
        superAddresses.add(adr);


        // Create that super user with generic informations.
        superUser = new User ("admin@owner.com", "admin", "Admin App Developer",
                "123.456.789-00", "02/02/94", "00000-00", 26,
                superAddresses, superCreditCards);


        // Associates the super user to the logged user application.
        ((LoggedUser) this.getApplication()).setUser(superUser);
        ((LoggedUser) this.getApplication()).setUsersCart(new ArrayList<Product>());
        ((LoggedUser) this.getApplication()).setUsersFavoritesProducts(new ArrayList<Product>());


        // Fill the fields automatically for the SUPER USER.
        email.setText("admin@owner.com");
        password.setText("admin");

        loginButton = findViewById(R.id.loginButton);
        loginButton.setText("SUPER USER LOGIN");

    }


    /**
     * Associates each element of the Layout (from the xml) to a variable.
     */
    public void getLayoutElements() {
        email = findViewById(R.id.userEmailInput);
        password = findViewById(R.id.userPasswordInput);
    }

}
