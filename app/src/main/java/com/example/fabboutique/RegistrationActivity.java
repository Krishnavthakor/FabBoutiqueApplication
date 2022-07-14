package com.example.fabboutique;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabboutique.DataBaseHandler.DataBaseHandler;
import com.example.fabboutique.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtFirstName,edtLastName,edtPhoneNumber,edtEmail,edtPassword,edtConfirmPassword;
    Button btnSignUp;

    //declaring db instance
    DataBaseHandler dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edtFirstName=findViewById(R.id.editTextFirstName);
        edtLastName=findViewById(R.id.editTextLastName);
        edtPhoneNumber=findViewById(R.id.editTextPhoneNumber);
        edtEmail=findViewById(R.id.editTextEmail);
        edtPassword=findViewById(R.id.editTextPassword);

        btnSignUp=findViewById(R.id.btnRegister);

        //focus on this field during start up
        edtFirstName.requestFocus();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerMethod();
            }

            private void registerMethod() {
                String firstName = edtFirstName.getText().toString().trim();
                String lastName = edtLastName.getText().toString().trim();
                String phoneNumber= edtPhoneNumber.getText().toString();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();


                if (firstName.isEmpty()) {
                    edtFirstName.setError("First name is required");
                    edtFirstName.requestFocus();
                    return;
                }
                if (lastName.isEmpty()) {
                    edtLastName.setError("Last name is required");
                    edtLastName.requestFocus();
                    return;
                }
                if (phoneNumber.isEmpty()) {
                    edtPhoneNumber.setError("Phone Number is required");
                    edtPhoneNumber.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    edtEmail.setError("Email is required");
                    edtEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    edtEmail.setError("Please provide valid email");
                    edtEmail.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    edtPassword.setError("Password is required");
                    edtPassword.requestFocus();
                    return;
                }
                if (password.length() < 7)
                {
                    edtPassword.setError("Password Length must be 6");
                    edtPassword.requestFocus();
                    return;
                }

                dbh=new DataBaseHandler(RegistrationActivity.this  );
                //inserting user to database
                User user = new User( firstName,lastName,email,password,phoneNumber,"customer");

                boolean insertState = dbh.registerUser(user);

                if (insertState) {
                    Toast.makeText(RegistrationActivity.this, "User Registered Successfully! Now you can do Login", Toast.LENGTH_LONG).show();
                    Intent loginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(RegistrationActivity.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void btnLogin(View view) {
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}