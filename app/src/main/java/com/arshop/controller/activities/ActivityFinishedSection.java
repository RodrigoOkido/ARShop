package com.arshop.controller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arshop.controller.R;

public class ActivityFinishedSection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_section);
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
