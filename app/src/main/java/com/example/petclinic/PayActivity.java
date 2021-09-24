package com.example.petclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class PayActivity extends AppCompatActivity {
    EditText name,city,card,amount,mail;
    Button btnAdd,btnBack;

    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        name = (EditText)findViewById(R.id.txtName);
        city = (EditText)findViewById(R.id.txtCity);
        card = (EditText)findViewById(R.id.txtCard);
        amount = (EditText)findViewById(R.id.txtAmount);
        mail = (EditText)findViewById(R.id.txtMail);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

        //Initialize Validation Style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation For Name
        awesomeValidation.addValidation(this,R.id.txtName,
                RegexTemplate.NOT_EMPTY,R.string.invalide_pname);









        //For Email
        awesomeValidation.addValidation(this,R.id.txtMail,
                Patterns.EMAIL_ADDRESS,R.string.invalid_mail);

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
        map.put("city",city.getText().toString());
        map.put("card",card.getText().toString());
        map.put("amount",amount.getText().toString());
        map.put("mail",mail.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Pay").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PayActivity.this, "Payment Successfully", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PayActivity.this, "Error While Inserting Data", Toast.LENGTH_SHORT).show();

                    }
                });


    }
    private void clearAll()
    {
        name.setText("");
        city.setText("");
        card.setText("");
        amount.setText("");
        mail.setText("");

    }

}