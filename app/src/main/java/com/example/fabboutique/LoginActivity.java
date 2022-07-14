package com.example.fabboutique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fabboutique.DataBaseHandler.DataBaseHandler;
import com.example.fabboutique.Models.ProductCategory;
import com.example.fabboutique.Models.User;

public class LoginActivity extends AppCompatActivity {

    private Button btnSignUp,btnLogin;
    EditText edtUsername, edtPassword;

    //creating database instance
    DataBaseHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbh=new DataBaseHandler(getApplicationContext());
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtUsername = (EditText) findViewById(R.id.editTextEmail);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbh.searchData(edtUsername.getText().toString(), edtPassword.getText().toString());
                //if its null then display toast message
                if (cursor.getCount() == 0) {
                    Toast.makeText(LoginActivity.this, "Username or Password doesn't match", Toast.LENGTH_LONG).show();
                } else {
                    if (cursor.moveToFirst()) {
                            //initialize recipe class and set data and store object to arraylist
                            User user = new User();
                            user.setUserType(cursor.getString(6));
                        cursor.close();
                        dbh.close();
                            if(user.getUserType().equals("customer"))
                            {
                                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                // added data to intent using value-pair, can access data using productDetails
                                // intent.putExtra("username", edtUsername.getText().toString());
                                // switch to the ContentsActivity
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, AdminDashboard.class);
                                // added data to intent using value-pair, can access data using productDetails
                                // intent.putExtra("username", edtUsername.getText().toString());
                                // switch to the ContentsActivity
                                startActivity(intent);
                            }
                    }
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}