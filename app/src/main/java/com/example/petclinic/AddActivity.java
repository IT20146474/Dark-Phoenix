package com.example.petclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    EditText name,type,purl,owner,age,phone,email,city,date;
    Button btnAdd,btnBack;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText)findViewById(R.id.txtName);
        type = (EditText)findViewById(R.id.txtType);
        purl = (EditText)findViewById(R.id.txtImageUrl);
        owner = (EditText)findViewById(R.id.txtOwner);
        age = (EditText)findViewById(R.id.txtAge);
        phone = (EditText)findViewById(R.id.txtPhone);
        email = (EditText)findViewById(R.id.txtEmail);
        city = (EditText)findViewById(R.id.txtCity);
        date = (EditText)findViewById(R.id.txtDate);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

        //Initialize Validation Style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation For Name
        awesomeValidation.addValidation(this,R.id.txtName,
                RegexTemplate.NOT_EMPTY,R.string.invalide_name);
        //Add Validation For Type
        awesomeValidation.addValidation(this,R.id.txtType,
                RegexTemplate.NOT_EMPTY,R.string.invalide_type);
        //Add Validation For Age
        awesomeValidation.addValidation(this,R.id.txtAge,
                RegexTemplate.NOT_EMPTY,R.string.invalide_age);
        //Add Validation For Image
        awesomeValidation.addValidation(this,R.id.txtImageUrl,
                RegexTemplate.NOT_EMPTY,R.string.invalide_image);
        //Add Validation For Owner's Name
        awesomeValidation.addValidation(this,R.id.txtOwner,
                RegexTemplate.NOT_EMPTY,R.string.invalide_owner);
        //Add Validation For City
        awesomeValidation.addValidation(this,R.id.txtCity,
                RegexTemplate.NOT_EMPTY,R.string.invalide_city);


        //For Mobile Number
        awesomeValidation.addValidation(this,R.id.txtPhone,
                 "[5-9]{1}[0-9]{9}$",R.string.invalide_contact);

        //For Email
        awesomeValidation.addValidation(this,R.id.txtEmail,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);






        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (awesomeValidation.validate()) {
                   insertData();
                   clearAll();

               }else{
                       Toast.makeText(getApplicationContext()
                       ,"Validation Faild",Toast.LENGTH_SHORT).show();
                   }
               }



        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

    }
    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("type",type.getText().toString());
        map.put("purl",purl.getText().toString());
        map.put("owner",owner.getText().toString());
        map.put("age",age.getText().toString());
        map.put("phone",phone.getText().toString());
        map.put("email",email.getText().toString());
        map.put("city",city.getText().toString());
        map.put("date",date.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Pets").push()
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
        type.setText("");
        purl.setText("");
        owner.setText("");
        age.setText("");
        phone.setText("");
        email.setText("");
        city.setText("");
        date.setText("");
    }

}