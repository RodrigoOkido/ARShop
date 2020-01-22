package com.arshop.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arshop.model.Category;
import com.arshop.model.RecyclerProductCardView;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Class responsible to parse and exhibits all the products by the chosen category.
 */
public class ActivityProductList extends AppCompatActivity {

    private Category categoryPicked;

    // Variable req to control all the requests from the API.
    private RequestQueue req;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        req = Volley.newRequestQueue(this);


//        // Recieve the data of the products
//        Intent intent = getIntent();
//        String chosenCategory = intent.getExtras().getString("Name");

        String chosenCategory = "";
        parseJsonUrl(chosenCategory);

        RecyclerView productExhibition = (RecyclerView) findViewById(R.id.recycler_products_list);
        RecyclerProductCardView prodCardAdapter = new RecyclerProductCardView(this,lstBook);
        productExhibition.setLayoutManager(new GridLayoutManager(this,2));
        productExhibition.setAdapter(prodCardAdapter);



    }


    /**
     * Responsible to parse an JSON which is requested given an URL. This function gets all the
     * necessary information of the product.
     *
     * @param category Receives the users wanted category.
     */
    private void parseJsonUrl(String category){
        String url = "https://api.mercadolibre.com/items/MLB1040282676";

        // Request JSON Object given URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            // Basic informations of the product
                            String prodThumb = response.getString("secure_thumbnail");
                            String prodName = response.getString(("title"));
                            String prodPrice = response.getString("price");

                            Glide.with(ActivityProductList.this).load(prodThumb)
                                    .fitCenter()
                                    .into(productThumbImage);
                            productName.setText(prodName);
                            price.setText(prodPrice);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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


}
