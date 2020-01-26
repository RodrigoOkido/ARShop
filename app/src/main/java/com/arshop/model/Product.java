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
    private String warranty;
    private boolean mercadoPagoCondition;
    private String productLocationCity;
    private String productLocationState;
    private ArrayList<String> images;


    /**
     * Product class constructor
     *
     * @param id Product id
     * @param name Product name
     * @param price Product actual price
     * @param images Product list of images
     * @param warranty Product warranty information
     * @param mercadoPagoCondition Product Mercado Pago condition
     * @param city Product city. Where the product is being selling
     * @param state Product state. After the city, name the state of this city.
     * */
    public Product(String id, String name, String price, ArrayList<String> images, String warranty,
                   boolean mercadoPagoCondition, String city, String state){
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.warranty = warranty;
        this.mercadoPagoCondition = mercadoPagoCondition;
        this.productLocationCity = city;
        this.productLocationState = state;
    }

    // Product GETTERS functions
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
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

    public ArrayList<String> getImages() {
        return images;
    }


    // Product SETTERS functions
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
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

}
