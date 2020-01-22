package com.arshop.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.arshop.model.Category;
import com.arshop.model.RecyclerCategoryView;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the MAIN CLASS. The MAIN SECTION of the app when opened starts here. This class is
 * responsible to exhibits all the categories of products. From here, the user can access the
 * all almost all other areas of the app.
 */
public class ActivityProductCategory extends AppCompatActivity {

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



//         ["MLB1040282676","MLB810162721","MLB887866260","MLB1031104504","MLB1127456411","MLB1134192837","MLB1122773422"]
//        var arrayCadeiras: [String] = ["MLB898094955","MLB1091011505","MLB880663138","MLB1142593979","MLB778945078","MLB688334853","MLB998765671","MLB840808616"]
//        var arrayEletro:[String] = ["MLB771468129","MLB805104535","MLB937652025","MLB1108379810","MLB869005659","MLB747292932","MLB967263695","MLB1143116459"]
//        var arraySofa:[String] = ["MLB830687652","MLB1130894410","MLB830692038","MLB830688004","MLB925457196","MLB1178661430","MLB1178204238","MLB1178665106"]
//        var arrayDeco:[String] = ["MLB1090662845","MLB1139342736","MLB1015564068","MLB1156324011","MLB1099412621","MLB1084725297","MLB959932927"]

        RecyclerView initialView = (RecyclerView) findViewById(R.id.recycler_categories_list);
        RecyclerCategoryView categoryAdapter = new RecyclerCategoryView(this,categoriesList);
        initialView.setLayoutManager(new LinearLayoutManager(this));
        initialView.setAdapter(categoryAdapter);
    }
}
