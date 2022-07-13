package com.example.fabboutique;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fabboutique.adapter.ProductAdapter;
import com.example.fabboutique.adapter.ProductCategoryAdapter;
import com.example.fabboutique.model.ProductCategory;
import com.example.fabboutique.model.Products;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    WishlistFragment fragment_wishlist =new WishlistFragment();
    HomeFragment fragment_home=new HomeFragment();
    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler, prodItemRecycler;
    ProductAdapter productAdapter;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment_home).commit();
        List<ProductCategory> productCategoryList = new ArrayList<>();
        setProductRecycler(productCategoryList);

        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products(1, "Light Blue Shirt", "M", "$ 17.00", R.drawable.shirt1));
        productsList.add(new Products(2, "Blue Shirt", "L", "$ 25.00", R.drawable.shirt2));
        productsList.add(new Products(1, "Light Blue Shirt", "S", "$ 17.00", R.drawable.shirt1));
        productsList.add(new Products(2, "Blue Shirt", "L", "$ 25.00", R.drawable.shirt2));
        productsList.add(new Products(1, "Light blue Shirt", "S", "$ 17.00", R.drawable.shirt1));
        productsList.add(new Products(2, "Blue Shirt", "XL", "$ 25.00", R.drawable.shirt2));

        setProdItemRecycler(productsList);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment_home).commit();
                        return true;
                    case R.id.wishlist:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment_wishlist).commit();
                        return true;

                }
                return false;
            }
        });
    }







    private void setProductRecycler(List<ProductCategory> productCategoryList){

        productCatRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(this, productCategoryList);
        productCatRecycler.setAdapter(productCategoryAdapter);

    }

    private void setProdItemRecycler(List<Products> productsList){

        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, productsList);
        prodItemRecycler.setAdapter(productAdapter);

    }




}