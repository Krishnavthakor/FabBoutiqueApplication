package com.example.fabboutique.Models;

public class ProductCategory {
    public String categoryTitle,categoryName,categoryDescription;
    public int categoryImage;

    public ProductCategory()
    {}

    public ProductCategory(String categoryTitle, String categoryName, String categoryDescription) {
        this.categoryTitle = categoryTitle;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public ProductCategory(String categoryTitle, String categoryName, String categoryDescription, int categoryImage)
    {
        this.categoryDescription=categoryDescription;
        this.categoryImage=categoryImage;
        this.categoryName=categoryName;
        this.categoryTitle=categoryTitle;
    }
    public String getCategoryDescription() {
        return this.categoryDescription;
    }

    public int getCategoryImage() {
        return this.categoryImage;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String getCategoryTitle() {
        return this.categoryTitle;
    }

    public void setCategoryDescription(String categoryDescription) {

         this.categoryDescription=categoryDescription;
    }

    public void setCategoryImage(int categoryImage) {
         this.categoryImage=categoryImage;
    }

    public void setCategoryName(String categoryName) {
         this.categoryName=categoryName;
    }

    public void setCategoryTitle(String categoryTitle) {

        this.categoryTitle=categoryTitle;
    }
}
