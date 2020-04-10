package com.arshop.controller.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arshop.support.adapters.ImageSliderView;
import com.arshop.controller.R;
import com.arshop.model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * ProductDetail activity. Class responsible to show a detailed product information to the user.
 */
public class ActivityProductDetail extends AppCompatActivity {

    // Private attributes.
    private Product productPicked;
    private static List<Product> productWanted, productFavorite;

    // ViewPager for the product images and button to check the product in AR.
    private ViewPager prodImagesPager;
    private ImageSliderView slider;
    private ImageButton prodARViewButton, addToFavoriteButton;

    // Layout TextView fields.
    private TextView prodPrice, prodName, prodQuantity;
    private TextView prodBrand, prodWarranty, prodMP, prodLocation, prodDimensions;
    private EditText prodDescription;

    // Variable req to control all the requests from the API.
    private RequestQueue req;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Attributes the logged user
        productWanted = ((LoggedUser) this.getApplication()).getUsersCart();
        productFavorite = ((LoggedUser) this.getApplication()).getUsersFavoritesProducts();

        // Load the activity toolbar.
        loadToolbar();
        // Load and configure the Bottom Navigation Menu.
        loadBottomMenuNavigation();
        // Associates each field of the layout to a variable.
        getLayoutElements();


        req = Volley.newRequestQueue(this);

        // Recieve the data of the category selected.
        Intent intent = getIntent();
        productPicked = (Product) intent.getExtras().getSerializable("ProductCheck");

        // Setting images of the products
        slider = new ImageSliderView(this, productPicked.getImages());
        prodImagesPager.setAdapter(slider);

        // Configures the images page indicator.
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(prodImagesPager, true);

        prodARViewButton.setImageResource(R.drawable.ar_button);


        if (checkProductInFavorite()) {
            addToFavoriteButton.setImageResource(R.drawable.icon_favorite_64dp);
        } else {
            addToFavoriteButton.setImageResource(R.drawable.icon_favorite_bordered_64dp);
        }
        // Set all the informations about the product.
        setProductInfo();

    }


    /**
     * Function to determine if the product is in the favorite user list.
     *
     * @return True if the product is in users favorite list. False otherwise.
     */
    public boolean checkProductInFavorite(){
        for (int i = 0; i < productFavorite.size(); i++) {
            if (productPicked.getId().equals(productFavorite.get(i).getId())){
                return true;
            }
        }

        return false;
    }


    /**
     * Product information is set to each field. Another request is made to the API here to take
     * the product description informed by the vendor.
     */
    public void setProductInfo () {
        prodPrice.append(productPicked.getPrice());
        prodName.setText(productPicked.getName());
        prodQuantity.setText(productPicked.getQuantity() + " disponíveis");
        prodBrand.append(productPicked.getBrand());

        if (productPicked.getWarranty() != null) {
            prodWarranty.append(productPicked.getWarranty());
        } else {
            prodWarranty.setText("Não Informado");
        }


        String url_description = "https://api.mercadolibre.com/items/"+productPicked.getId()+
                "/description?access_token="+productPicked.getId();

        // Request JSON Object given URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url_description, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String product_description = response.getString("plain_text");
                            productPicked.setDescription(product_description);
                            prodDescription.setText(productPicked.getDescription());

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


        boolean prodMPAvailable = productPicked.isMercadoPagoCondition();
        if (prodMPAvailable) {
            prodMP.append("Garantido pelo Mercado Pago");
        } else {
            prodMP.append("Não Possui");
        }
        prodLocation.append(productPicked.getProductLocationCity()+" - "+
                productPicked.getProductLocationState());

        prodDimensions.append(productPicked.getDimensions());
    }


    /**
     * This function is called when the user clicks the button to add the product in the cart.
     * Once added to the cart, the Cart Activity starts with this (or more) product inside the list.
     *
     * @param view The view context.
     */
    public void addToCart (View view) {

        // If user clicks in the button, the product picked is added to the Product wanted List.
        productWanted.add(productPicked);

        // Create intent
        Intent intent = new Intent(view.getContext(), ActivityMyCart.class);

        // Start MyCart activity.
        this.startActivity(intent);

    }


    /**
     * Add an product wanted by the user to the users favorite list. Shows an toast informing
     * that product was added to the users wishlist.
     *
     * @param view The view context.
     */
    public void addProductToFavorite (View view) {
        productFavorite.add(productPicked);
        Toast.makeText(getApplicationContext(), "Produto adicionado aos favoritos!",
                Toast.LENGTH_SHORT).show();
        addToFavoriteButton.setImageResource(R.drawable.icon_favorite_64dp);

    }


    /**
     * Function to check the product in Augmented Reality. To be able to use the Augmented
     * Reality, the user needs to authorize the app to access the smartphone camera.
     *
     * @param view The view context.
     */
    public void checkProductAR (View view) {

        // Create intent
        Intent intent = new Intent(view.getContext(), ActivityModelDisplay.class);
        intent.putExtra("ARName", productPicked.getProdARName());
        // Start MyCart activity.
        this.startActivity(intent);
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
                            intent = new Intent(ActivityProductDetail.this,
                                    ActivityProductCategory.class);

                            // Start ProductCategory activity.
                            startActivity(intent);
                            break;
                        case R.id.menuCart:
                            // Create intent
                            intent = new Intent(ActivityProductDetail.this,
                                    ActivityMyCart.class);

                            // Start MyCart activity.
                            startActivity(intent);
                            break;
                        case R.id.menuFavorite:
                            // Create intent
                            intent = new Intent(ActivityProductDetail.this,
                                    ActivityMyFavorite.class);

                            // Start MyFavorite activity.
                            startActivity(intent);
                            break;
                        case R.id.menuProfile:
                            // Create intent
                            intent = new Intent(ActivityProductDetail.this,
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
        toolbar.setTitle("Sobre o Produto");
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


    /**
     * Associates each element of the Layout (from the xml) to a variable.
     */
    public void getLayoutElements() {
        prodImagesPager = (ViewPager)findViewById((R.id.prodImages_ViewPager));
        prodARViewButton = (ImageButton)findViewById(R.id.ar_view_product);
        addToFavoriteButton = (ImageButton) findViewById(R.id.addToFavorite);
        prodPrice = (TextView)findViewById(R.id.prodPrice);
        prodName = (TextView)findViewById(R.id.prodName);
        prodQuantity = (TextView)findViewById(R.id.prodAvailableQty);

        prodBrand = (TextView)findViewById(R.id.prodBrand);
        prodWarranty = (TextView)findViewById(R.id.prodWarranty);
        prodMP = (TextView)findViewById(R.id.MP_available);
        prodDescription = (EditText) findViewById(R.id.prodTextDescription);
        prodDescription.setEnabled(false);
        prodLocation = (TextView)findViewById(R.id.prodLocation);
        prodDimensions = (TextView)findViewById(R.id.prodDimensions);
    }
}
