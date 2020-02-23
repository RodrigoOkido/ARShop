package com.arshop.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arshop.model.Category;
import com.arshop.recyclers.RecyclerCategoryView;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the starter activity class. The app when opened starts here. This class is
 * responsible to exhibits all the categories of products. From here, the user can access almost
 * all other areas of the app.
 */
public class ActivityProductCategory extends AppCompatActivity {

    // Variable list of categories. Each category wanted to be created, need to be added in this
    // array.
    private List<Category> categoriesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);

        //Load the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        toolbar.setTitle("Categorias");
        setSupportActionBar(toolbar);

        // Create a list of categories and populate the list with categories of products.
        categoriesList = new ArrayList<>();
        categoriesList.add(new Category("Cadeiras", R.drawable.cadeira_thumb));
        categoriesList.add(new Category("Sofas", R.drawable.sofa_thumb));
        categoriesList.add(new Category("Mesas", R.drawable.mesa_thumb));
        categoriesList.add(new Category("Eletrodomesticos", R.drawable.eletro_thumb));
        categoriesList.add(new Category("Decorativos", R.drawable.decorativo_thumb));


        // Creates the recycler of the categories.
        RecyclerView initialView = (RecyclerView) findViewById(R.id.recycler_categories_list);
        RecyclerCategoryView categoryAdapter = new RecyclerCategoryView(this,categoriesList);
        initialView.setLayoutManager(new LinearLayoutManager(this));
        initialView.setAdapter(categoryAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuCart:
                // Create intent
                Intent intent = new Intent(this, ActivityMyCart.class);

                // Start MyCart activity.
                this.startActivity(intent);
                break;
            case R.id.menuProfile: break;

        }
        return super.onOptionsItemSelected(item);
    }
}
