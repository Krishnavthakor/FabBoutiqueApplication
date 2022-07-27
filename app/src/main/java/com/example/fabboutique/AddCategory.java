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
import com.example.fabboutique.Models.ProductCategory;
import com.example.fabboutique.Models.User;

import java.io.IOException;
import java.util.ArrayList;

public class AddCategory extends AppCompatActivity {

    private Button btnAddCategory, btnCategoryImage;
    EditText edtSubCategoryName, edtSubCategoryDesc;
    Spinner category;
    String selectedCategoryName;
    int categoryImage;

    //creating database instance
    DataBaseHandler dbh;

    // One Preview Image
    ImageView IVPreviewImage;
    private Bitmap imageToStore;
    private Uri imageFilePath;
    private static final int Pick_Image_Request=100;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        dbh=new DataBaseHandler(getApplicationContext());
        btnAddCategory = (Button) findViewById(R.id.btnAddCategory);
        edtSubCategoryName = (EditText) findViewById(R.id.editTextCName);
        edtSubCategoryDesc = (EditText) findViewById(R.id.editTextCDescription);
        category = (Spinner) findViewById(R.id.spinnerCategoryTitle);

        //Spinner Controller Code
        Spinner categorySpinner = findViewById(R.id.spinnerCategoryTitle);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Select Category");
        arrayList.add("Tops");
        arrayList.add("Bottoms");
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

        // register the UI widgets with their appropriate IDs
        btnCategoryImage = findViewById(R.id.buttonUploadCImage);
        IVPreviewImage = findViewById(R.id.IVPreviewCImage);

        // handle the Choose Image button to trigger
        // the image chooser function
        btnCategoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        //Add Category into database
        //focus on this field during start up
        edtSubCategoryName.requestFocus();

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { addCategory(); }

            private void addCategory() {
                String categoryName = selectedCategoryName;
                String subCategoryName = edtSubCategoryName.getText().toString().trim();
                String subCategoryDesc = edtSubCategoryDesc.getText().toString().trim();

                if (subCategoryName.isEmpty()) {
                    edtSubCategoryName.setError("Sub-category name is required");
                    edtSubCategoryName.requestFocus();
                    return;
                }
                if (subCategoryDesc.isEmpty()) {
                    edtSubCategoryDesc.setError("Category Description required");
                    edtSubCategoryDesc.requestFocus();
                    return;
                }

                dbh=new DataBaseHandler(AddCategory.this  );
                //inserting user to database
                ProductCategory category = new ProductCategory(categoryName,subCategoryName,subCategoryDesc, imageToStore);

                boolean insertState = dbh.addProductsCategory(category);

                if (insertState) {
                    Toast.makeText(AddCategory.this, "Sub-Category Added Successfully..!!", Toast.LENGTH_LONG).show();
                    Intent loginIntent = new Intent(AddCategory.this, MainActivity.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(AddCategory.this, "Sub-Category insertion failed..!!", Toast.LENGTH_LONG).show();
                }
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
            Toast.makeText(AddCategory.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
            Toast.makeText(AddCategory.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}