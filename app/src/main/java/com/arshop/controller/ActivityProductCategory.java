package com.arshop.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.arshop.model.Category;
import com.arshop.recyclers.RecyclerCategoryView;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the MAIN ACTIVITY. The MAIN SECTION of the app when opened starts here. This class is
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

        categoriesList = new ArrayList<>();

        categoriesList.add(new Category("Cadeiras", R.drawable.cadeira_thumb));
        categoriesList.add(new Category("Sofas", R.drawable.sofa_thumb));
        categoriesList.add(new Category("Mesas", R.drawable.mesa_thumb));
        categoriesList.add(new Category("Eletrodomesticos", R.drawable.eletro_thumb));
        categoriesList.add(new Category("Decorativos", R.drawable.decorativo_thumb));


        RecyclerView initialView = (RecyclerView) findViewById(R.id.recycler_categories_list);
        RecyclerCategoryView categoryAdapter = new RecyclerCategoryView(this,categoriesList);
        initialView.setLayoutManager(new LinearLayoutManager(this));
        initialView.setAdapter(categoryAdapter);
    }
}
