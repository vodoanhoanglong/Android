package Models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Product implements Serializable {
    private int productId;
    private String productName;
    private double productPrice;

    public Product(int productId, String productName, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @NonNull
    @Override
    public String toString() {
        return productId + ": " + productName + " > " + productPrice;
    }
}
