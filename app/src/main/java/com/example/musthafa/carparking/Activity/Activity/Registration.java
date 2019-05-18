package com.example.musthafa.carparking.Activity.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musthafa.carparking.R;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    EditText edt_name,edt_email,edt_phone,edt_pass;
    Button btn_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        edt_email=(EditText)findViewById(R.id.id_reg_email);
        edt_name=(EditText)findViewById(R.id.id_reg_name);
        edt_phone=(EditText)findViewById(R.id.id_reg_phone);
        edt_pass=(EditText)findViewById(R.id.id_reg_pass);
        btn_reg=(Button)findViewById(R.id.btn_id_reg);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=edt_email.getText().toString().trim();
                final String name=edt_name.getText().toString().trim();
                final String phone=edt_phone.getText().toString().trim();
                final String password=edt_pass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    edt_email.setError("please enter here");
                    edt_email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(name)){
                    edt_name.setError("please enter here");
                    edt_name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(phone)){
                    edt_phone.setError("please enter here");
                    edt_phone.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    edt_pass.setError("please enter here");
                    edt_pass.requestFocus();
                    return;
                }

                RequestQueue requestQueue=Volley.newRequestQueue(Registration.this);
                String Url="http://parkme.fabstudioz.com/users.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("response",response);
                        if (response.equals(name)){
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("email",email);
                        params.put("name",name);
                        params.put("phone",phone);
                        params.put("password",password);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}
