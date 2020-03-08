package com.arshop.controller.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.arshop.controller.R;
import com.arshop.model.Category;
import com.arshop.support.recyclers.RecyclerCategoryView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the starter activity class. The app when opened starts here. This class is responsible
 * to exhibits all the categories of products. From here, the user can access almost all other areas
 * of the app.
 */
public class ActivityProductCategory extends AppCompatActivity {

    // Variable list of categories. Each category wanted to be created, need to be added in this
    // array.
    private List<Category> categoriesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);

        // Load the activity toolbar
        loadToolbar();
        // Load and configure the Bottom Navigation Menu
        loadBottomMenuNavigation();

        // Create a list of categories and populate the list with categories of products.
        categoriesList = new ArrayList<>();
        categoriesList.add(new Category("Cadeiras", R.drawable.cadeira_thumb));
        categoriesList.add(new Category("Sofás", R.drawable.sofa_thumb));
        categoriesList.add(new Category("Mesas", R.drawable.mesa_thumb));
        categoriesList.add(new Category("Eletrodomésticos", R.drawable.eletro_thumb));
        categoriesList.add(new Category("Decorativos", R.drawable.decorativo_thumb));

        // Show the categories list.
        showCategories(categoriesList);

    }


    /**
     * Show the categories list on the screen. Here all the products categories will be listed.
     *
     * @param categoriesList The categories list.
     */
    private void showCategories(List<Category> categoriesList) {
        // Creates the recycler of the categories.
        RecyclerView initialView = (RecyclerView) findViewById(R.id.recycler_categories_list);
        RecyclerCategoryView categoryAdapter = new RecyclerCategoryView(this,categoriesList);
        initialView.setLayoutManager(new LinearLayoutManager(this));
        initialView.setAdapter(categoryAdapter);
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
                            break;
                        case R.id.menuCart:
                            // Create intent
                            intent = new Intent(ActivityProductCategory.this,
                                    ActivityMyCart.class);

                            // Start MyCart activity.
                            startActivity(intent);
                            break;
                        case R.id.menuFavorite:
                            // Create intent
                            intent = new Intent(ActivityProductCategory.this,
                                    ActivityMyFavorite.class);

                            // Start MyFavorite activity.
                            startActivity(intent);
                            break;
                        case R.id.menuProfile:
                            // Create intent
                            intent = new Intent(ActivityProductCategory.this,
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
        toolbar.setTitle("Categorias");
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
    }

}
