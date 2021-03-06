package com.arshop.controller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arshop.controller.R;
import com.arshop.model.Product;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;


/**
 * FinishedSection activity. Final page of the users purchases. From here user can return to tha
 * main section of the app.
 */
public class ActivityFinishedSection extends AppCompatActivity {

    // Progress state bar
    private String[] descriptionData = {"Identificação", "Pagamento", "Confirmação", "Concluído"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_section);

        ((LoggedUser) this.getApplication()).setUsersCart(new ArrayList<Product>());

        // Load state bar progress
        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.purchase_bar_identification);
        stateProgressBar.setStateDescriptionData(descriptionData);
    }


    /**
     * Back to the main activity after user confirms the purchase.
     *
     * @param view The view context.
     */
    public void backToMain(View view){
        Intent intent = new Intent(ActivityFinishedSection.this,
                ActivityProductCategory.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    /**
     * Load the toolbar of the Activity. This is the function where the name of the
     * Activity can be set in the toolbar.
     */
    public void loadToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        toolbar.setTitle("Confirmação");
        setSupportActionBar(toolbar);

    }

}
