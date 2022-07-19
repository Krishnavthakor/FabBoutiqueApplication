package com.example.fabboutique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {

    // One Button
    Button btnProductImage;

    // One Preview Image
    ImageView IVPreviewImage;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        //Spinner Controller Code
        Spinner categorySpinner = findViewById(R.id.spinnerCategory);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Select Category");
        arrayList.add("TOPS");
        arrayList.add("BOTTOMS");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(arrayAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String categoryName = adapterView.getItemAtPosition(position).toString();
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
                Toast.makeText(adapterView.getContext(), "Selected: " + status, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView <?> parent) {
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
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                }
            }
        }
    }
}