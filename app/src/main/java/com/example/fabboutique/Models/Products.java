package com.example.fabboutique.Models;

public class Products {

    int productId;
    String productName;
    int productQty;
    int productPrice;
    int productImage;
    int categoryId;

    public Products(int productid, String productName, int productQty, int productPrice, int imageUrl,int categoryId) {

        this.productId = productid;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.productImage = imageUrl;
        this.categoryId=categoryId;
    }

    public Products() {

    }
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductImage() {
        return this.productImage;
    }

    public void setProductImage(int imageUrl) {
        this.productImage = imageUrl;
    }

    public Integer getProductid() {
        return this.productId;
    }

    public void setProductid(Integer productid) {
        this.productId = productid;
    }
}
