package com.example.doctersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private ImageButton back_button , forward_button ;
    private EditText name , email_id , month , year;
    private RadioGroup gender;
    private String   full_name , mail , month_txt , year_txt;
    private final String BASE_URL = "http://199.192.26.248:8000";
    private final String ENDPOINT = "/sap/opu/odata/sap/ZCDS_TEST_REGISTER_CDS/ZCDS_TEST_REGISTER";
    private String selected_text = "M";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gender = findViewById(R.id.Radio_buttons);
        name = findViewById(R.id.name);
        email_id = findViewById(R.id.email);
        month = findViewById(R.id.moths);
        year = findViewById(R.id.years);
        back_button = findViewById(R.id.back_button);
        forward_button = findViewById(R.id.forward_button);


        gender.setOnCheckedChangeListener((group, checkedId) -> {

            for (int i = 0 ; i <gender.getChildCount(); i++){
                RadioButton btn = (RadioButton) group.getChildAt(i);
                btn.setBackground(getDrawable(R.drawable.radio_background));
                btn.setTextColor(getColor(R.color.orange));
            }
            RadioButton btn = findViewById(checkedId);
            if (btn != null){
                btn.setBackground(getDrawable(R.drawable.radio_button_clicked));
                btn.setTextColor(getColor(R.color.whitee));
//                selected_text = btn.getText().toString();
                if(btn.getText().toString().equals("Male")){
                    selected_text = "M";
                }else if(btn.getText().toString().equals("Female")){
                    selected_text = "F";
                }else{
                    selected_text = "O";
                }
            }
        });


        forward_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettingText();
                if(TextUtils.isEmpty(full_name) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(month_txt) || TextUtils.isEmpty(year_txt) || TextUtils.isEmpty(selected_text)){
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    callApi();
                }
            }
        });
    }

    private void callApi() {
        try {
            RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
            JSONObject requestBody = new JSONObject();
            requestBody.put("name", full_name);
            requestBody.put("email", mail);
            requestBody.put("gender", selected_text);
            requestBody.put("practice_frm_month", month_txt);
            requestBody.put("practice_frm_year", year_txt);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    BASE_URL + ENDPOINT,
                    requestBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Toast.makeText(MainActivity.this, "Registering.",Toast.LENGTH_LONG).show();
                            Log.d("gg" , response.toString());
                            Intent i = new Intent(MainActivity.this , docScreen.class);
                            i.putExtra("url" , response.toString());
                            startActivity(i);
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(MainActivity.this, "Error while registering.",Toast.LENGTH_LONG).show();
                            Log.e("TAG","Error "+error.toString());
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Accept", "application/json");
                    headers.put("X-Requested-With", "X");
                    return headers;
                }
            };

            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void gettingText() {
        full_name = name.getText().toString();
        mail = email_id.getText().toString();
        month_txt = month.getText().toString();
        year_txt = year.getText().toString();
    }

}