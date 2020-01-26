package com.arshop.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


/**
 * Class to show the users cart. Each product wanted to purchased will be added to this Activity.
 */
public class ActivityMyCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
    }
}
