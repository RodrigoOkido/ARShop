package com.arshop.model;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Product class. Its an generic class which defines any type of sellable product.
 */
public class Product implements Serializable {

    // Private attributes of the Product
    private String id;
    private String name;
    private String price;
    private String quantity;
    private ArrayList<String> images;
    private String brand;
    private String warranty;
    private boolean mercadoPagoCondition;
    private String productLocationCity;
    private String productLocationState;
    private String dimensions;



    /**
     * Product class constructor. To create a sellable product, it needs all the product information.
     * This requires a lot of parameters.
     *
     * @param id Product id.
     * @param name Product name.
     * @param brand Product brand.
     * @param images Product list of images.
     * @param price Product actual price.
     * @param quantity Product quantity number of units available.
     * @param warranty Product warranty information.
     * @param mercadoPagoCondition Product Mercado Pago condition.
     * @param city Product city. Where the product is being selling.
     * @param state Product state. After the city, name the state of this city.
     * @param dimensions Product dimensions.
     * */
    public Product(String id, String name, String brand, ArrayList<String> images, String price,
                   String quantity, String warranty, boolean mercadoPagoCondition,
                   String city, String state, String dimensions){
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.images = images;
        this.price = price;
        this.quantity = quantity;
        this.warranty = warranty;
        this.mercadoPagoCondition = mercadoPagoCondition;
        this.productLocationCity = city;
        this.productLocationState = state;
        this.dimensions = dimensions;
    }

    // Product GETTERS functions
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getWarranty() {
        return warranty;
    }

    public boolean isMercadoPagoCondition() {
        return mercadoPagoCondition;
    }

    public String getProductLocationCity() {
        return productLocationCity;
    }

    public String getProductLocationState() {
        return productLocationState;
    }

    public String getDimensions() {
        return dimensions;
    }



    // Product SETTERS functions
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public void setMercadoPagoCondition(boolean mercadoPagoCondition) {
        this.mercadoPagoCondition = mercadoPagoCondition;
    }

    public void setProductLocationCity(String productLocationCity) {
        this.productLocationCity = productLocationCity;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public void setProductLocationState(String productLocationState) {
        this.productLocationState = productLocationState;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
}
