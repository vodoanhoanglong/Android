package com.example.model;

import androidx.annotation.NonNull;

public class Product {
    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public Product(String productname, String productColor) {
        this.productname = productname;
        this.productColor = productColor;
    }

    @NonNull
    @Override
    public String toString() {
        return  this.productname + " - " + this.productColor;
    }

    private String productname;
    private String productColor;
}
