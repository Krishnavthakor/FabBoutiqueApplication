package com.example.fabboutique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fabboutique.DataBaseHandler.DataBaseHandler;
import com.example.fabboutique.Models.Products;

import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {

    private Button btnAddProduct, btnProductImage;
    EditText edtProductName, edtProductDesc, edtProductPrice, edtProductQuantity;
    Spinner spCategory, spProductStatus;
    String selectedCategoryName;
    String selectedProductStatus;
    Bitmap productImage;
    ImageView IVPreviewImage; // One Preview Image
    DataBaseHandler dbh; //creating database instance

    private Bitmap imageToStore;
    private Uri imageFilePath;
    private static final int Pick_Image_Request=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        dbh=new DataBaseHandler(getApplicationContext());
        btnAddProduct = (Button) findViewById(R.id.btnAddProduct);
        edtProductName = (EditText) findViewById(R.id.editTextName);
        edtProductDesc = (EditText) findViewById(R.id.editTextDescription);
        edtProductPrice = (EditText) findViewById(R.id.editTextPrice);
        edtProductQuantity = (EditText) findViewById(R.id.editTextQuantity);
        spCategory = (Spinner) findViewById(R.id.spinnerCategory);
        spProductStatus = (Spinner) findViewById(R.id.spinnerStatus);

        //Spinner Controller Code
        Spinner categorySpinner = findViewById(R.id.spinnerCategory);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Select Category");
        arrayList.add("TShirts");
        arrayList.add("Shirts");
        arrayList.add("Kurtas");
        arrayList.add("Jeans");
        arrayList.add("Kurta Pants");
        arrayList.add("Casual Pants");
        arrayList.add("Formal Pants");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(arrayAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String categoryName = adapterView.getItemAtPosition(position).toString();
                selectedCategoryName = categoryName;
                Toast.makeText(adapterView.getContext(), "Selected: " + categoryName, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        Spinner statusSpinner = findViewById(R.id.spinnerStatus);
        ArrayList<String> statusArrayList = new ArrayList<>();
        statusArrayList.add("Select Status of product");
        statusArrayList.add("AVAILABLE");
        statusArrayList.add("NOT AVAILABLE");
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, statusArrayList);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);
        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String status = adapterView.getItemAtPosition(position).toString();
                selectedProductStatus = status;
                Toast.makeText(adapterView.getContext(), "Selected: " + status, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        //Add Category into database
        //focus on this field during start up
        edtProductName.requestFocus();

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { addProduct(); }

            private void addProduct() {
                String categoryName = selectedCategoryName;
                String status = selectedProductStatus;
                String productName = edtProductName.getText().toString().trim();
                String productDesc = edtProductDesc.getText().toString().trim();
                String productPrice = edtProductPrice.getText().toString().trim();
                int price = Integer.parseInt(productPrice);
                String productQuantity = edtProductQuantity.getText().toString().trim();
                int quantity = Integer.parseInt(productQuantity);

                if (productName.isEmpty()) {
                    edtProductName.setError("Product name is required");
                    edtProductName.requestFocus();
                    return;
                }
                if (productDesc.isEmpty()) {
                    edtProductDesc.setError("Product Description required");
                    edtProductDesc.requestFocus();
                    return;
                }
                if (productPrice.isEmpty()) {
                    edtProductPrice.setError("Product price is required");
                    edtProductPrice.requestFocus();
                    return;
                }
                if (productQuantity.isEmpty()) {
                    edtProductQuantity.setError("Product quantity required");
                    edtProductQuantity.requestFocus();
                    return;
                }

                dbh=new DataBaseHandler(AddProduct.this);
                //inserting Product to database
                Products products = new Products(productName,productDesc, price, quantity, imageToStore, 1);
                boolean insertState = dbh.addProduct(products);

                if (insertState) {
                    Toast.makeText(AddProduct.this, "Product Added Successfully..!!", Toast.LENGTH_LONG).show();
                    Intent loginIntent = new Intent(AddProduct.this, MainActivity.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(AddProduct.this, "Product insertion failed..!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Upload Image Code
        // register the UI widgets with their appropriate IDs
        btnProductImage = findViewById(R.id.buttonUploadPImage);
        IVPreviewImage = findViewById(R.id.IVPreviewPImage);

        // handle the Choose Image button to trigger
        // the image chooser function
        btnProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
    }

    void imageChooser() {
        // create an instance of the
        // intent of the type image
        try {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);

            startActivityForResult(i, Pick_Image_Request);
        }catch (Exception e) {
            Toast.makeText(AddProduct.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == Pick_Image_Request && resultCode==RESULT_OK && data!=null && data.getData()!=null)
            {
                imageFilePath=data.getData();
                imageToStore= MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);
                IVPreviewImage.setImageBitmap(imageToStore);
            }
        }catch (Exception e) {
            Toast.makeText(AddProduct.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}