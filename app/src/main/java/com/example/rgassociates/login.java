package com.example.rgassociates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText e1, e2;
        Button button;

        e1 = findViewById(R.id.usernamee);
        e2 = findViewById(R.id.pass);
        button = findViewById(R.id.loginbtn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().toString().equals("admin")  && e2.getText().toString().equals("1234")) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
                else if(e1.getText().toString().equals("user")  && e2.getText().toString().equals("1234")) {
                    Intent i = new Intent(getApplicationContext(),Usermodule.class);
                    startActivity(i);
                }
                else {
                    if(e1.getText().toString().equals( "admin") && e2.getText().toString() != "1234") {
                        Toast.makeText(login.this, "password missmatch", Toast.LENGTH_SHORT).show();
                        e2.getText().clear();
                    }
                    else if (e1.getText().toString() != "admin" && e2.getText().toString().equals("1234")) {
                        Toast.makeText(login.this, "username missmatch", Toast.LENGTH_SHORT).show();
                        e1.getText().clear();
                    }
                    else {
                        Toast.makeText(login.this, "record missmatch", Toast.LENGTH_SHORT).show();
                        e1.getText().clear();
                        e2.getText().clear();
                    }
                }

                //user

            }

        });

    }
}