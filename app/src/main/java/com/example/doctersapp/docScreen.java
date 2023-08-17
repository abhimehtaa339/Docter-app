package com.example.doctersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class docScreen extends AppCompatActivity {

    private TextView name_txt , email_txt , gender_txt  , month , year , id , note;
    private String json_string , doctorsId , name , email , gender ,  practiceMonth , practiceYear;
    private ImageButton backbutton;
    private MaterialButton move_forward;
    private Intent i = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_screen);

        name_txt = findViewById(R.id.name);
        email_txt = findViewById(R.id.mail);
        gender_txt = findViewById(R.id.gender);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);
        id = findViewById(R.id.docterid);
        note = findViewById(R.id.note);
        backbutton = findViewById(R.id.back_btn);
        move_forward = findViewById(R.id.forward_btn);


        i = getIntent();
        json_string = i.getStringExtra("url");
        Log.d("rr", json_string);

       fetchData();
       settingData();

       move_forward.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(docScreen.this , Dashbord_screen.class);
               intent.putExtra("gender" , gender);
               intent.putExtra("name" , name);
               startActivity(intent);
           }
       });

       backbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(docScreen.this , MainActivity.class));
               finish();
           }
       });
    }

    private void settingData() {
        name_txt.setText(String.format("%s%s", getString(R.string.namee), name));
        email_txt.setText(String.format("%s%s", getString(R.string.emailid), email));
        if (gender.equals("M")){
            gender_txt.setText(String.format("%s%s", getString(R.string.GENDER), "Male"));
        } else if (gender.equals("F")) {
            gender_txt.setText(String.format("%s%s", getString(R.string.GENDER), "Female"));
        }else{
            gender_txt.setText(String.format("%s%s", getString(R.string.GENDER), "Other"));
        }
        month.setText(String.format("%s%s", getString(R.string.practicemonth), practiceMonth));
        year.setText(String.format("%s%s", getString(R.string.practiceyear), practiceYear));
        id.setText(String.format("%s%s", getString(R.string.docter_id), doctorsId));


    }

    private void fetchData() {
        try {
            JSONObject jsonObject = new JSONObject(json_string);
            JSONObject dataObject = jsonObject.getJSONObject("d");
             doctorsId = dataObject.getString("doctors_id");
             name = dataObject.getString("name");
             email = dataObject.getString("email");
             gender = dataObject.getString("gender");
             practiceMonth = dataObject.getString("practice_frm_month");
             practiceYear = dataObject.getString("practice_frm_year");
             note.setVisibility(View.VISIBLE);

        } catch (JSONException e) {
            Toast.makeText(this, "Something went wrong..", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}