package com.example.model;

import java.io.Serializable;

public class Beers implements Serializable {
    private int productThumb;
    private String name;
    private double price;

    public int getProductThumb() {
        return productThumb;
    }

    public void setProductThumb(int productThumb) {
        this.productThumb = productThumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Beers(int imageID, String name, double price) {
        this.productThumb = imageID;
        this.name = name;
        this.price = price;
    }
}
