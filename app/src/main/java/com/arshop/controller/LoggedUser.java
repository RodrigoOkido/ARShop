package com.arshop.controller;

import android.app.Application;

import com.arshop.model.Product;
import com.arshop.model.User;

import java.util.List;


/**
 * This is an global class which represents the logged User.
 */
public class LoggedUser extends Application {

    private User user;
    private List<Product> usersCart;
    private double subtotal;


    public User getUser() {
        return user;
    }


    public List<Product> getUsersCart() {
        return usersCart;
    }


    public double getSubtotal() {
        return subtotal;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public void setUsersCart(List<Product> usersCart) {
        this.usersCart = usersCart;
    }


    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
