package com.arshop.controller.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.arshop.controller.R;
import com.arshop.model.User;
import com.arshop.support.recyclers.RecyclerMySettingView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


/**
 * MySettings activity. This is a generic class where defines all configurations options. This
 * activity is only to show the options for the users. The configuration options itself is defined
 * in ActivityMySettingOption.
 */
public class ActivityMySettings extends AppCompatActivity {

    // Variables which deals with the users settings.
    private List<String> settingOptions;
    private User logged_user;

    // Variables to deal layout elements.
    private TextView logged_user_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);

        // Load the activity toolbar.
        loadToolbar();
        // Load and configure the Bottom Navigation Menu.
        loadBottomMenuNavigation();

        logged_user= ((LoggedUser) this.getApplication()).getUser();
        logged_user_text = findViewById(R.id.settingOptionText);
        logged_user_text.append(logged_user.getName() + "! O que deseja fazer?");

        settingOptions = new ArrayList<>();
        settingOptions.add("Meus Dados");
        settingOptions.add("Meus Endereços");
        settingOptions.add("Meus Cartões");
        settingOptions.add("Minhas Compras");
        settingOptions.add("Sair da Conta (Deslogar)");


        // Show the application settings.
        showSettings(settingOptions);

    }


    /**
     * Show the application settings on the screen to the user.
     *
     * @param settingOptions
     */
    private void showSettings(List<String> settingOptions) {
        // Create the Recycler of the favorite view
        RecyclerView settingView = (RecyclerView) findViewById(R.id.recycler_my_settings);
        RecyclerMySettingView settingAdapter =
                new RecyclerMySettingView(this, settingOptions);
        settingView.setLayoutManager(new LinearLayoutManager(this));
        settingView.setAdapter(settingAdapter);
    }


    /**
     * Bottom navigation menu actions.
     */
    public BottomNavigationView.OnNavigationItemSelectedListener listener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.menuHome:
                            // Create intent
                            Intent intent = new Intent(ActivityMySettings.this,
                                    ActivityProductCategory.class);

                            // Start Product Category activity.
                            startActivity(intent);
                            break;
                        case R.id.menuCart:
                            // Create intent
                            intent = new Intent(ActivityMySettings.this,
                                    ActivityMyCart.class);

                            // Start MyCart activity.
                            startActivity(intent);
                            break;
                        case R.id.menuFavorite:
                            // Create intent
                            intent = new Intent(ActivityMySettings.this,
                                    ActivityMyFavorite.class);

                            // Start MyFavorite activity.
                            startActivity(intent);
                            break;
                        case R.id.menuProfile: break;
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
        toolbar.setTitle("Meu Perfil");
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
        bottomMenu.getMenu().findItem(R.id.menuProfile).setChecked(true);
    }
}
