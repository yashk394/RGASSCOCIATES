package com.example.rgassociates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addcase extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7;
    Button b;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    casedata cdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcase);

        b = findViewById(R.id.btn_cancle);
        e1 = findViewById(R.id.ctitle);
        e2 = findViewById(R.id.cnum);
        e3 = findViewById(R.id.cliname);
        e4 = findViewById(R.id.location);
        e5 = findViewById(R.id.cat);
        e6 = findViewById(R.id.cstage);
        e7 = findViewById(R.id.des);
        firebaseDatabase = FirebaseDatabase.getInstance();
        cdata = new casedata();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty() ||
                        e3.getText().toString().isEmpty() || e4.getText().toString().isEmpty() ||
                        e5.getText().toString().isEmpty() || e6.getText().toString().isEmpty() ||
                        e7.getText().toString().isEmpty()) {
                    Snackbar.make(view,"please, Fill the data" , Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
                else {
                    addDatatoFirebase(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),
                            e4.getText().toString(),e5.getText().toString(), e6.getText().toString(), e7.getText().toString());
                    Snackbar.make(view, "Case Added", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    e1.getText().clear();
                    e2.getText().clear();
                    e3.getText().clear();
                    e4.getText().clear();
                    e5.getText().clear();
                    e6.getText().clear();
                    e7.getText().clear();

                }
            }
        });


    }
    private void addDatatoFirebase(String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
        cdata.setCt(s1);
        cdata.setCnum(s2);
        cdata.setCliname(s3);
        cdata.setLocation(s4);
        cdata.setCt(s5);
        cdata.setSctg(s6);
        cdata.setDes(s7);
        databaseReference = firebaseDatabase.getReference(s1);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(cdata);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });

    }
}