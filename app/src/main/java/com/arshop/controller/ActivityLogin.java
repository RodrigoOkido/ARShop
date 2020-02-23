package com.arshop.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Load the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);

    }
}
