package com.arshop.controller.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arshop.controller.R;
import com.arshop.model.Category;
import com.arshop.model.Product;
import com.arshop.support.recyclers.RecyclerProductCardView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * ProductList activity. Class responsible to parse and exhibits all the products by the chosen
 * category.
 */
public class ActivityProductList extends AppCompatActivity {

    // Private variables to deal with the user selected category and the products Ids
    // within this category.
    private Category categoryPicked;
    private List<String> productsIds;

    // Variable req to control all the requests from the API.
    private RequestQueue req;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // Load the activity toolbar.
        loadToolbar();
        // Load and configure the Bottom Navigation Menu.
        loadBottomMenuNavigation();

        req = Volley.newRequestQueue(this);

        // Recieve the data of the category selected.
        Intent intent = getIntent();
        categoryPicked = (Category) intent.getExtras().getSerializable("CategoryName");

        // Load the products which belongs to the category chosen by the user
        loadProdcutsId(categoryPicked);

        // Process the products informations givin this ID to the API.
        // This ID will be append to an URL and be processed as an request to the server.
        for (int i = 0; i < productsIds.size(); i++) {
            processProductsJsonUrl(productsIds.get(i));
        }

    }


    /**
     * Loads all the products IDs based on the category the user selected.
     *
     * @param chosenCategory Passes the category the user selected.
     */
    private void loadProdcutsId(Category chosenCategory) {

        switch (chosenCategory.getCategoryName()){
            case "Mesas": productsIds = new ArrayList<String>(Arrays.asList("MLB1197287312",
                    "MLB810162721","MLB887866260","MLB1031104504","MLB1127456411",
                    "MLB1134192837","MLB1060580939"));
                break;
            case "Cadeiras": productsIds = new ArrayList<String>(Arrays.asList("MLB1152392407",
                    "MLB1091011505","MLB880663138","MLB1142593979","MLB778945078","MLB688334853",
                    "MLB998765671","MLB840808616"));
                break;
            case "Eletrodomésticos": productsIds = new ArrayList<String>(Arrays.asList("MLB771468129",
                    "MLB805104535","MLB937652025","MLB1108379810","MLB869005659","MLB747292932",
                    "MLB967263695","MLB1143116459"));
                break;
            case "Sofás": productsIds = new ArrayList<String>(Arrays.asList("MLB830687652",
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
     * @param productId Process the productId of one product.
     */
    private void processProductsJsonUrl(String productId){

            String url = "https://api.mercadolibre.com/items/"+productId;
            ArrayList<String> prodImages = new ArrayList<>();

            // Request JSON Object given URL.
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            // Gather all basic informations of the product

                            // Images of the product
                            JSONArray images = response.getJSONArray("pictures");
                            for (int image = 0; image < images.length(); image++){
                                prodImages.add(images.getJSONObject(image).getString("secure_url"));
//                                Log.d("DEBUG", images.getJSONObject(image).getString("secure_url"));
                            }

                            // ID
                            String prodId = response.getString("id");
//                            Log.d("DEBUG", prodId);

                            // Thumb
                            String prodThumb = response.getString("secure_thumbnail");
//                            Log.d("DEBUG", prodThumb);

                            // Name
                            String prodName = response.getString(("title"));
//                            Log.d("DEBUG", prodName);

                            // Atributes array inside JSON.
                            // Brand and Dimension attributes will be taken here
                            JSONArray attr = response.getJSONArray("attributes");

                            // The default value will be "Não Informado" in case this field be empty
                            // in the product pushed in the API.
                            String prodBrand = "Não Informado";
                            String prodDimensions = "Não Informado";
                            for (int attribute = 0; attribute < attr.length(); attribute++){
                                if(attr.getJSONObject(attribute).getString("id").contains("BRAND")) {
                                    prodBrand = attr.getJSONObject(attribute)
                                            .getString("value_name");
                                }
                                if(attr.getJSONObject(attribute).getString("id").contains("HEIGHT")) {
                                    prodDimensions = attr.getJSONObject(attribute)
                                            .getString("value_name");
                                }

                                if(attr.getJSONObject(attribute).getString("id").contains("WIDTH")) {
                                    prodDimensions = prodDimensions.concat(" x " + attr.getJSONObject(attribute)
                                            .getString("value_name"));
                                }
                            }

                            // Price
                            String prodPrice = response.getString("price");
//                            Log.d("DEBUG", prodPrice);

                            // Quantity Available
                            String prodQuantity = response.getString("available_quantity");
//                            Log.d("DEBUG", prodQuantity);

                            // Warranty
                            String prodWarranty = response.getString("warranty");
                            if (prodWarranty == null || prodWarranty == "null") {
                                prodWarranty = "Verificar no site do Fabricante";
                            }
//                            Log.d("DEBUG", prodWarranty);

                            // Mercado Pago accepted?
                            boolean prodMercadoPago = response.getBoolean("accepts_mercadopago");
//                            Log.d("DEBUG", String.valueOf(prodMercadoPago));

                            // City and State
                            JSONObject prodLocation = response.getJSONObject("seller_address");
                            JSONObject prodLocationCity = prodLocation.getJSONObject("city");
                            JSONObject prodLocationState = prodLocation.getJSONObject("state");
                            String prodCity = prodLocationCity.getString("name");
                            String prodState = prodLocationState.getString("name");

                            // AR Load file (this is supposed to be the file.sfb)
                            String prodAR = "";

                            // Creates a new Product with all gathered information from the API
                            Product newProduct = new Product(prodId, prodName, prodBrand, prodImages,
                                    prodPrice, prodQuantity, prodWarranty, prodMercadoPago,
                                    "", prodCity, prodState, prodDimensions,
                                    prodAR);

                            //############  DEBUG TEMPORARY 3D ############
                            setupAR(newProduct);

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


    /**
     * TEMPORARY FUNCTION TO DEAL WITH THE AR.
     */
    private void setupAR(Product product) {
        switch (categoryPicked.getCategoryName()){

            case "Mesas": product.setProdARName("ModernDesk.sfb");
                break;
            case "Cadeiras": product.setProdARName("Wooden Chair.sfb");
                break;
            case "Eletrodomésticos": product.setProdARName("ModernDesk.sfb") ;
                break;
            case "Sofás": product.setProdARName("table.sfb");
                break;
            case "Decorativos": product.setProdARName("ModernDesk.sfb");
                break;
            default: break;

        }
    }


    /**
     * Show the product list on the screen based on the category chosen by the user.
     *
     * @param products The list of the products to be exhibits.
     */
    public void showProducts(List<Product> products){
        RecyclerView productExhibition = (RecyclerView) findViewById(R.id.recycler_products_list);
        RecyclerProductCardView prodCardAdapter = new RecyclerProductCardView(this, categoryPicked.getProductList());
        productExhibition.setLayoutManager(new GridLayoutManager(this,2));
        productExhibition.setAdapter(prodCardAdapter);
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
                            // Create intent
                            intent = new Intent(ActivityProductList.this,
                                    ActivityProductCategory.class);

                            // Start ProductCategory activity.
                            startActivity(intent);
                            break;
                        case R.id.menuCart:
                            // Create intent
                            intent = new Intent(ActivityProductList.this,
                                    ActivityMyCart.class);

                            // Start MyCart activity.
                            startActivity(intent);
                            break;
                        case R.id.menuFavorite:
                            // Create intent
                            intent = new Intent(ActivityProductList.this,
                                    ActivityMyFavorite.class);

                            // Start MyFavorite activity.
                            startActivity(intent);
                            break;
                        case R.id.menuProfile:
                            // Create intent
                            intent = new Intent(ActivityProductList.this,
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
        toolbar.setTitle("Produtos");
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
    }
}
