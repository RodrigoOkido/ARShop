package com.arshop.controller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.arshop.controller.R;
import com.arshop.model.User;


/**
 * CreateUser activity. This is an Activity responsible to register a new user to the
 * application. If user do not have an Account associated to the app, this is the class responsible
 * to register this user.
 */
public class ActivityCreateUser extends AppCompatActivity {

    // Private attributes to get the EditText fields.
    private EditText newUserFirstName, newUserLastName, newUserCpf, newUserBornDate, newUserEmail,
            newUserPassword, newUserPasswordConfirmation;

    // Private attributes to get user informations.
    private String userFirstName, userLastName, userCpf, userBornDate, userEmail, userPassword,
            userPasswordConfirmation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        // Load the activity toolbar.
        loadToolbar();


        // Load the layout element fields to use in the new account creation.
        // This is loaded to be use in the confirmRegister action button to register.
        getLayoutElements();
    }


    /**
     * Creates a new User account for the application. Use the loaded fields from getLayoutElements()
     * to create. Each field is mandatory, so if empty the account could not be made.
     *
     * @param view The view.
     */
    public void confirmRegister(View view){
        // Context for toast if necessary.
        Context context = getApplicationContext();

        // Take each EditText field value.
        userFirstName = newUserFirstName.getText().toString();
        userLastName = newUserLastName.getText().toString();
        userCpf = newUserCpf.getText().toString();
        userBornDate = newUserBornDate.getText().toString();
        userEmail = newUserEmail.getText().toString();
        userPassword = newUserPassword.getText().toString();
        userPasswordConfirmation = newUserPasswordConfirmation.getText().toString();

        if ( checkFieldsIntegrity(userFirstName, userLastName, userCpf, userBornDate, userEmail,
                userPassword, userPasswordConfirmation) == true ) {

            // Create new user with the basic users informations. Other info is filled with
            // null. This need to be correctly informed after.
            User newUser = new User(userEmail, userPassword, userFirstName + " " + userLastName
                    , userCpf, userBornDate,null, 0, null, null);

            CharSequence text = "Conta criada com sucesso! Use o login cadastrado para entrar no app.";
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

            // Create intent
            Intent intent = new Intent(view.getContext(), ActivityLogin.class);

            // Start Login Activity.
            this.startActivity(intent);

        }
    }


    /**
     * Check all the fields of the new User registration. All fields CANNOT be empty.
     *
     * @return True if all fields are minimal acceptable. False otherwise.
     * @param userFirsname User name.
     * @param userLastname User lastname.
     * @param userCpf User CPF.
     * @param userBornDate User born date.
     * @param userEmail User email.
     * @param userPassword User password.
     * @param userPasswordConfirmation User password confirmation.
     */
    private boolean checkFieldsIntegrity(String userFirsname, String userLastname, String userCpf,
                                         String userBornDate, String userEmail, String userPassword,
                                         String userPasswordConfirmation) {
        // Context for toast if necessary.
        Context context = getApplicationContext();

        // First checks if any fields are empty.
        if (userFirsname.equals("") || userLastname.equals("") || userCpf.equals("")  ||
                userBornDate.equals("")  || userEmail.equals("") || userPassword.equals("")
                || userPasswordConfirmation.equals("") ){

            CharSequence text = "Todos campos são obrigatórios!";
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check if CPF field contains 11 numbers.
        else if (this.userCpf.length() != 11) {
            CharSequence text = "Campo CPF não contém 11 números (xxx.xxx.xxx-xx).";
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check if Email contains the "@" special key for email.
        else if (!this.userEmail.contains("@")) {
            CharSequence text = "Insira um email válido.";
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check if userPasswordConfirmation matches the password wanted.
        else if (!this.userPassword.equals(this.userPasswordConfirmation)){
            CharSequence text = "Senha de confirmação não confere a senha desejada.";
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }


    /**
     * Load the toolbar of the Activity. This is the function where the name of the
     * Activity can be set in the toolbar.
     */
    public void loadToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        toolbar.setTitle("Cadastro");
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
     * Associates each element of the Layout (from the xml) to a variable.
     */
    public void getLayoutElements() {
        newUserFirstName = (EditText)findViewById(R.id.newUserFirstname);
        newUserLastName = (EditText)findViewById(R.id.newUserLastname);
        newUserCpf = (EditText)findViewById(R.id.newUserCpf);
        newUserBornDate = (EditText)findViewById(R.id.newUserBornDate);
        newUserEmail = (EditText)findViewById(R.id.newUserEmail);
        newUserPassword = (EditText)findViewById(R.id.newUserPassword);
        newUserPasswordConfirmation = (EditText)findViewById(R.id.newUserPasswordConfirmation);

    }

}
