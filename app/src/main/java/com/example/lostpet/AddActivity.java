package com.example.lostpet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText name,gender,breed,lost,purl,oname,phone,address,description;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText)findViewById(R.id.txtPetName);
        gender = (EditText)findViewById(R.id.txtGender);
        breed = (EditText)findViewById(R.id.txtBreed);
        lost=(EditText)findViewById(R.id.txtLost);
        purl = (EditText)findViewById(R.id.txtImageUrl);
        oname = (EditText)findViewById(R.id.txtOwnerName);
        phone = (EditText)findViewById(R.id.txtPhone);
        address = (EditText)findViewById(R.id.txtAddress);
        description = (EditText)findViewById(R.id.txtDescription);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("gender",gender.getText().toString());
        map.put("breed",breed.getText().toString());
        map.put("lost",lost.getText().toString());
        map.put("purl",purl.getText().toString());
        map.put("oname",oname.getText().toString());
        map.put("phone",phone.getText().toString());
        map.put("address",address.getText().toString());
        map.put("description",description.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Pet").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this, "Error While Inserting Data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll()
    {
        name.setText("");
        gender.setText("");
        breed.setText("");
        lost.setText("");
        purl.setText("");
        oname.setText("");
        phone.setText("");
        address.setText("");
        description.setText("");
    }
}