package com.arshop.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * This class was made to keep all products better organized. It creates an object which contains
 * an category name and a list of Product which corresponds to that category.
 */
public class Category implements Serializable {

    // Attributes of the Category
    private int categoryImage;
    private String categoryName;
    private List<Product> productList;

    /**
     * Category class constructor.
     *
     * @param category Category name.
     * @param image Background image id.
     */
    public Category(String category, int image){
        categoryName = category;
        categoryImage = image;
        productList = new ArrayList<Product>();
    }


    // Category GETTERS functions
    public int getCategoryImage() {
        return categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Product> getProductList() {
        return productList;
    }


    // Category SETTERS functions
    public void setCategoryImage(int image) {
        this.categoryImage = image;
    }

    public void setCategoryName(String category) {
        this.categoryName = category;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }


    /**
     * Add an new produtct to the list of the products.
     *
     * @param product the Product object to be added
     */
    public void addProduct ( Product product ){
        productList.add(product);

    }

}
