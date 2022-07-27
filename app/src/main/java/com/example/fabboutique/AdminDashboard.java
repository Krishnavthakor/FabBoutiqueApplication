package com.example.fabboutique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminDashboard extends AppCompatActivity {

    private static Button btnManageCustomer;
    private static Button btnManageInventory;
    private static Button btnManageOrders;
    private static Button btnManageCustomOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        OnMCustomerButtonClickListener();
    }

    public void OnMCustomerButtonClickListener() {
        btnManageCustomer = (Button)findViewById(R.id.btnManageCustomers);
        btnManageCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, AddCategory.class);
                AdminDashboard.this.startActivity(intent);
            }
        });

        btnManageInventory = (Button) findViewById(R.id.btnManageInventory);
        btnManageInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, AddProduct.class);
                AdminDashboard.this.startActivity(intent);
            }
        });

        btnManageOrders = (Button) findViewById(R.id.btnManageOrders);
        btnManageOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, ManageOrders.class);
                AdminDashboard.this.startActivity(intent);
            }
        });

        btnManageCustomOrders = (Button) findViewById(R.id.btnManageCustomOrders);
        btnManageCustomOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, ManageCustomOrders.class);
                AdminDashboard.this.startActivity(intent);
            }
        });
    }
}