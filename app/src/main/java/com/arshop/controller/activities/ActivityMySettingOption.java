package com.arshop.controller.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.arshop.controller.R;
import com.arshop.controller.fragments.FragmentMyAddress;
import com.arshop.controller.fragments.FragmentMyCreditCards;
import com.arshop.controller.fragments.FragmentMyProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;


/**
 * MySettingOption activity. This is the Activity where the options views is displayed to the user.
 */
public class ActivityMySettingOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting_option);

        // Load the activity toolbar.
        loadToolbar();
        // Load and configure the Bottom Navigation Menu.
        loadBottomMenuNavigation();

        // Recieve the data of the option item selected.
        Intent intent = getIntent();
        String optionText = (String) intent.getExtras().getString("SettingName");

        loadFragmentSettingOption(optionText);

    }


    /**
     * Load the fragment setting option according to the selected by the user.
     *
     * @param option The option name.
     */
    private void loadFragmentSettingOption(String option) {

        switch (option) {

            case "Meus Dados":
                Fragment myProfileData = new FragmentMyProfile();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_option_view,
                        myProfileData).commit();
                break;
            case "Meu Endereço":
                Fragment myAddressData = new FragmentMyAddress();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_option_view,
                        myAddressData).commit();
                break;
            case "Meus Cartões":
                Fragment myCardsData = new FragmentMyCreditCards();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_option_view,
                        myCardsData).commit();
                break;
            case "Minhas Compras":
                Toast.makeText(getApplicationContext(), "Content not available."
                        , Toast.LENGTH_SHORT).show();
                break;
            case "Sair da Conta (Deslogar)":
                ((LoggedUser) this.getApplication()).setUser(null);
                ((LoggedUser) this.getApplication()).setUsersCart(null);
                ((LoggedUser) this.getApplication()).setUsersFavoritesProducts(null);
                Intent intent = new Intent(this, ActivityLogin.class);
                this.startActivity(intent);
                Toast.makeText(getApplicationContext(), "Conta deslogada com sucesso!"
                        , Toast.LENGTH_SHORT).show();
                break;

        }

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
                            Intent intent = new Intent(ActivityMySettingOption.this,
                                    ActivityProductCategory.class);

                            // Start Product Category activity.
                            startActivity(intent);
                            break;
                        case R.id.menuCart:
                            // Create intent
                            intent = new Intent(ActivityMySettingOption.this,
                                    ActivityMyCart.class);

                            // Start MyCart activity.
                            startActivity(intent);
                            break;
                        case R.id.menuFavorite:
                            // Create intent
                            intent = new Intent(ActivityMySettingOption.this,
                                    ActivityMyFavorite.class);

                            // Start MyFavorite activity.
                            startActivity(intent);
                            break;
                        case R.id.menuProfile:
                            // Create intent
                            intent = new Intent(ActivityMySettingOption.this,
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
        toolbar.setTitle("Meu Perfil");
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
        bottomMenu.getMenu().findItem(R.id.menuProfile).setChecked(true);
    }
}
