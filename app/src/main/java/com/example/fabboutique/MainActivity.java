package com.example.fabboutique;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fabboutique.Fragments.HomeFragment;
import com.example.fabboutique.Fragments.WishlistFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    WishlistFragment fragment_wishlist =new WishlistFragment();
    HomeFragment fragment_home=new HomeFragment();

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment_home).commit();

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
}