package com.example.fabboutique.Models;

public class Products {

    public String productName, productDesc, productImage;
    public int categoryId, productId,productPrice, productQty;

    public Products() {}

    public Products(String productName, String productDesc, int productPrice, int productQty) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productQty = productQty;
    }

    public Products(String productName, String productDesc, int productPrice, int productQty, String productImage, int categoryId) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productQty = productQty;
        this.productImage = productImage;
        this.categoryId=categoryId;
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

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
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

    public String getProductImage() {
        return this.productImage;
    }

    public void setProductImage(String imageUrl) {
        this.productImage = imageUrl;
    }

    public Integer getProductid() {
        return this.productId;
    }

    public void setProductid(Integer productid) {
        this.productId = productid;
    }
}
