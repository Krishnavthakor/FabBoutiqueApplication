package com.example.fabboutique;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fabboutique.Models.UserEncrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class sample extends AppCompatActivity {

    EditText edt;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        edt=(EditText) findViewById(R.id.editTextPassword);
        btn=(Button) findViewById(R.id.btnLogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = "myPassword";
                String encryptedpassword = null;
                try
                {
                    /* MessageDigest instance for MD5. */
                    MessageDigest m = MessageDigest.getInstance("MD5");

                    /* Add plain-text password bytes to digest using MD5 update() method. */
                    m.update(password.getBytes());

                    /* Convert the hash value into bytes */
                    byte[] bytes = m.digest();

                    /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
                    StringBuilder s = new StringBuilder();
                    for(int i=0; i< bytes.length ;i++)
                    {
                        s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }

                    /* Complete hashed password in hexadecimal format */
                    encryptedpassword = s.toString();
                }
                catch (NoSuchAlgorithmException e)
                {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(),encryptedpassword.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}