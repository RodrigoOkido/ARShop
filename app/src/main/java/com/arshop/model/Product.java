package com.arshop.model;

import java.util.ArrayList;

public class Product {

    private String id;
    private String name;
    private String price;
    private Category categgory;
    private ArrayList<String> images;


    public Product(String id, String name, String price, Category category,
                    ArrayList<String> images){
        this.id = id;
        this.name = name;
        this.price = price;
        this.categgory = category;
        this.images = images;

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

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
