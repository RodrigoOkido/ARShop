package com.arshop.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.arshop.model.Product;
import com.arshop.recyclers.RecyclerMyFavoriteView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


/**
 * MyFavorite activity. Activity responsible to show users favorite products.
 */
public class ActivityMyFavorite extends AppCompatActivity {

    // Variables which deals with the users favorite products.
    private List<Product> productInMyFavorites;

    // Variables to get layout elements.
    private TextView favoriteEmptyTextStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);

        // Load the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        toolbar.setTitle("Meus Favoritos");
        setSupportActionBar(toolbar);

        // Configure the Bottom Navigation Menu
        BottomNavigationView bottomMenu = findViewById(R.id.bottom_menu);
        bottomMenu.setOnNavigationItemSelectedListener(listener);
        bottomMenu.getMenu().findItem(R.id.menuFavorite).setChecked(true);

        // Recieve the data of the products.
        productInMyFavorites = ((LoggedUser) this.getApplication()).getUsersFavoritesProducts();
        favoriteEmptyTextStatus = findViewById(R.id.favoriteEmptyText);

        if (productInMyFavorites.size() != 0) {
            // Remove the text about the empty products in my favorites.
            favoriteEmptyTextStatus.setText("");

            // Create the Recycler of the favorite view
            RecyclerView favoriteView = (RecyclerView) findViewById(R.id.recycler_favorite_list);
            RecyclerMyFavoriteView cartAdapter =
                    new RecyclerMyFavoriteView(this, productInMyFavorites);
            favoriteView.setLayoutManager(new LinearLayoutManager(this));
            favoriteView.setAdapter(cartAdapter);
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
                            Intent intent = new Intent(ActivityMyFavorite.this,
                                    ActivityProductCategory.class);

                            // Start Product Category activity.
                            startActivity(intent);
                            break;
                        case R.id.menuCart:
                            // Create intent
                            intent = new Intent(ActivityMyFavorite.this,
                                    ActivityMyCart.class);

                            // Start MyCart activity.
                            startActivity(intent);
                            break;
                        case R.id.menuFavorite: break;
                        case R.id.menuProfile: break;
                    }

                    return true;
                }
            };
}
