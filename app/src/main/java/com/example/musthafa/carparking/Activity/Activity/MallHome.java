package com.example.musthafa.carparking.Activity.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musthafa.carparking.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MallHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_home);
        final Intent i=getIntent();
        final String id=i.getStringExtra("id");

        findViewById(R.id.btn_view_loc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue=Volley.newRequestQueue(MallHome.this);
                String Url="http://parkme.fabstudioz.com/viewloc.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("location response",response);
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            int length=jsonArray.length();
                            for (int i=0;i<=length;i++){
                                JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                                String latitude=jsonObject.optString("latitude");
                                String longtitude=jsonObject.optString("longtitude");
                                Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                                intent.putExtra("lat",latitude);
                                intent.putExtra("longt",longtitude);
                                startActivity(intent);
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
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
                        params.put("id",id);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        findViewById(R.id.btn_view_mall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.equals("1")) {
                    Intent i = new Intent(getApplicationContext(), ShobhaMall.class);
                    i.putExtra("id", id);
                    startActivity(i);
                    //startActivity(new Intent(getApplicationContext(),ShobhaMall.class));
                    finish();
                }
            }
        });
    }
}
