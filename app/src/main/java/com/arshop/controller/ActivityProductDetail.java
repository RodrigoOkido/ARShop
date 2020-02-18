package com.arshop.controller;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.arshop.adapters.ImageSliderView;
import com.arshop.model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Class responsible to show a detailed product information to the user.
 */
public class ActivityProductDetail extends AppCompatActivity {

    // ViewPager for the product images and button to check the product in AR.
    private ViewPager prodImagesPager;
    private ImageSliderView slider;
    private ImageButton prodARView;

    // Layout TextView fields.
    private TextView prodPrice, prodName, prodQuantity;
    private TextView prodBrand, prodWarranty, prodMP, prodLocation, prodDimensions;

    // Private attributes.
    private Product productPicked;
    private List<Product> productWanted;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        // Creates an Arraylist of Product. This will be used to add a possible product to the cart.
        productWanted = new ArrayList<>();

        // Recieve the data of the category selected.
        Intent intent = getIntent();
        productPicked = (Product) intent.getExtras().getSerializable("ProductCheck");

        // Get each field of the product detail activity layout XML.
        getLayoutElements();


        // Setting all fields from XML with values
        slider = new ImageSliderView(this, productPicked.getImages());
        prodImagesPager.setAdapter(slider);
        prodARView.setImageResource(R.drawable.ar_button);

        prodPrice.append(productPicked.getPrice());
        prodName.setText(productPicked.getName());
        prodQuantity.setText(productPicked.getQuantity() + " disponíveis");
        prodBrand.append(productPicked.getBrand());

        if (productPicked.getWarranty() != null) {
            prodWarranty.append(productPicked.getWarranty());
        } else {
            prodWarranty.setText("Não Informado");
        }

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
     * Associates each field of the Layout (from the xml) to the variables.
     */
    public void getLayoutElements() {
        prodImagesPager = (ViewPager)findViewById((R.id.prodImages_ViewPager));
        prodARView = (ImageButton)findViewById(R.id.ar_view_product);
        prodPrice = (TextView)findViewById(R.id.prodPrice);
        prodName = (TextView)findViewById(R.id.prodName);
        prodQuantity = (TextView)findViewById(R.id.prodAvailableQty);

        prodBrand = (TextView)findViewById(R.id.prodBrand);
        prodWarranty = (TextView)findViewById(R.id.prodWarranty);
        prodMP = (TextView)findViewById(R.id.MP_available);
        prodLocation = (TextView)findViewById(R.id.prodLocation);
        prodDimensions = (TextView)findViewById(R.id.prodDimensions);
    }


    /**
     * This function is called when the user clicks the button to add the product in the cart.
     * Once added to the cart, the Cart Activity starts with this (or more) product inside the list.
     *
     * @param view The actual activity context.
     */
    public void addToCart (View view) {

        // If user clicks in the button, the product picked is added to the Product wanted List.
        productWanted.add(productPicked);

        // Create intent
        Intent intent = new Intent(view.getContext(), ActivityMyCart.class);
        intent.putExtra("ProductWanted", (Serializable)productWanted);

        // Start MyCart activity.
        this.startActivity(intent);

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

        // Start MyCart activity.
        this.startActivity(intent);
    }

}
