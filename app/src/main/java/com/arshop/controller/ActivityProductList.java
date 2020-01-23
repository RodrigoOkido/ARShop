package com.arshop.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arshop.model.Category;
import com.arshop.model.Product;
import com.arshop.model.RecyclerProductCardView;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Class responsible to parse and exhibits all the products by the chosen category.
 */
public class ActivityProductList extends AppCompatActivity {

    private Category categoryPicked;
    private List<String> productsIds;

    // Variable req to control all the requests from the API.
    private RequestQueue req;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        req = Volley.newRequestQueue(this);


        // Recieve the data of the category selected.
        Intent intent = getIntent();
        categoryPicked = (Category) intent.getExtras().getSerializable("CategoryName");

        // Load the products which belongs to the category chosen by the user
        loadProdcutsId(categoryPicked);
//        Log.d("CHECKING", productsIds.toString());

        // Process the products informations givin this ID to the API.
        // This ID will be append to an URL and be processed as an request to the server.
        processProductsJsonUrl(productsIds);
//        Log.d("CHECKING", String.valueOf(categoryPicked.getProductList().get(0)));

    }

    /**
     * Loads all the products IDs based on the category the user selected.
     *
     * @param chosenCategory Passes the category the user selected.
     */
    private void loadProdcutsId(Category chosenCategory) {

        switch (chosenCategory.getCategoryName()){
            case "Mesas": productsIds = new ArrayList<String>(Arrays.asList("MLB1040282676",
                    "MLB810162721","MLB887866260","MLB1031104504","MLB1127456411",
                    "MLB1134192837","MLB1122773422"));
                break;
            case "Cadeiras": productsIds = new ArrayList<String>(Arrays.asList("MLB898094955",
                    "MLB1091011505","MLB880663138","MLB1142593979","MLB778945078","MLB688334853",
                    "MLB998765671","MLB840808616"));
                break;
            case "Eletro": productsIds = new ArrayList<String>(Arrays.asList("MLB771468129",
                    "MLB805104535","MLB937652025","MLB1108379810","MLB869005659","MLB747292932",
                    "MLB967263695","MLB1143116459"));
                break;
            case "Sofa": productsIds = new ArrayList<String>(Arrays.asList("MLB830687652",
                    "MLB1130894410","MLB830692038","MLB830688004","MLB925457196","MLB1178661430",
                    "MLB1178204238","MLB1178665106"));
                break;
            case "Decorativos": productsIds = new ArrayList<String>(Arrays.asList("MLB1090662845",
                    "MLB1139342736","MLB1015564068","MLB1156324011","MLB1099412621","MLB1084725297",
                    "MLB959932927"));
                break;
             default: break;
        }
    }



    /**
     * Responsible to parse an JSON which is requested given an URL. This function gets all the
     * necessary information of the product.
     *
     * @param productsIds Receives the users wanted category.
     */
    private void processProductsJsonUrl(List<String> productsIds){
        String url = "https://api.mercadolibre.com/items/MLB1040282676";

        // Request JSON Object given URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            // Basic informations of the product
                            String prodId = response.getString("id");
                            Log.d("DEBUG",prodId);
                            String prodThumb = response.getString("secure_thumbnail");
                            Log.d("DEBUG",prodThumb);
                            String prodName = response.getString(("title"));
                            Log.d("DEBUG",prodName);
                            String prodPrice = response.getString("price");
                            Log.d("DEBUG",prodPrice);
                            Product newProduct = new Product (prodId,prodName,prodPrice, new ArrayList<>());
                            categoryPicked.addProduct(newProduct);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        showProducts(categoryPicked.getProductList());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });

        // Add the JSON object request in the request queue.
        req.add(jsonObjectRequest);
    }

    public void showProducts(List<Product> products){
        RecyclerView productExhibition = (RecyclerView) findViewById(R.id.recycler_products_list);
        RecyclerProductCardView prodCardAdapter = new RecyclerProductCardView(this, categoryPicked.getProductList());
        productExhibition.setLayoutManager(new GridLayoutManager(this,3));
        productExhibition.setAdapter(prodCardAdapter);
    }
}
