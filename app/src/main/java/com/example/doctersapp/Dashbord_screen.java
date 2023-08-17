package com.example.doctersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Dashbord_screen extends AppCompatActivity {

    private ImageView doc_img;
    private TextView doc_name;

    private String name , gender;
    private MaterialButton scan , clinic , vaccine , Mybooking , ambulance , nurse;
    private Intent i = new Intent();


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord_screen);

        doc_img = findViewById(R.id.doc_img);
        doc_name = findViewById(R.id.doc_name);
        scan = findViewById(R.id.scan);
        clinic = findViewById(R.id.clinic);
        vaccine = findViewById(R.id.vaccine);
        Mybooking = findViewById(R.id.mybooking);
        ambulance = findViewById(R.id.ambulance);
        nurse = findViewById(R.id.nurse);

        i = getIntent();
        name = i.getStringExtra("name");
        assert name != null;
        doc_name.setText(name.concat("!"));

        gender = i.getStringExtra("gender");
        if (gender.equals("M")){
            doc_img.setImageDrawable(getDrawable(R.drawable.baseline_male_24));
        } else if (gender.equals("F")) {
            doc_img.setImageDrawable(getDrawable(R.drawable.baseline_female_24));
        }else {
            doc_img.setImageDrawable(getDrawable(R.drawable.baseline_transgender_24));
        }

        doc_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashbord_screen.this, "Docter Image", Toast.LENGTH_SHORT).show();
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashbord_screen.this, "Scan button", Toast.LENGTH_SHORT).show();
            }
        });

        vaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashbord_screen.this, "Vaccine button", Toast.LENGTH_SHORT).show();
            }
        });

        Mybooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashbord_screen.this, "my booking button", Toast.LENGTH_SHORT).show();
            }
        });

        clinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashbord_screen.this, "clinic button", Toast.LENGTH_SHORT).show();
            }
        });

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashbord_screen.this, "ambulance button", Toast.LENGTH_SHORT).show();
            }
        });

        nurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashbord_screen.this, "nurse button", Toast.LENGTH_SHORT).show();
            }
        });


    }
}