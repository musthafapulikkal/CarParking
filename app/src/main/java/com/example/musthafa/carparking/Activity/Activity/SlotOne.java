package com.example.musthafa.carparking.Activity.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SlotOne extends AppCompatActivity {
Button btn_a1,btn_a2,btn_a3,btn_a4,btn_a5;
private ArrayList<String> area_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_one);
        area_list=new ArrayList<String>();
        btn_a1=(Button)findViewById(R.id.area1);
        btn_a2=(Button)findViewById(R.id.area2);
        btn_a3=(Button)findViewById(R.id.area3);
        btn_a4=(Button)findViewById(R.id.area4);
        btn_a5=(Button)findViewById(R.id.area5);
        Intent intent=getIntent();
        final String slot=intent.getStringExtra("slot_name");
        final String mall_id=intent.getStringExtra("id");

        RequestQueue request1=Volley.newRequestQueue(SlotOne.this);
        String Url="http://parkme.fabstudioz.com/check_status.php";
        StringRequest srequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("staus response",response);
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    int length=jsonArray.length();
                    for (int i=0;i<=length;i++){
                        JSONObject json=(JSONObject)jsonArray.get(i);
                        String name=json.optString("area");
                        Log.v("name",name);
                        area_list.add(name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.v("aree", String.valueOf(area_list));
                if (area_list.contains("area1")){
                    btn_a1.setBackgroundColor(getResources().getColor(R.color.red));
                }
                else {
                    btn_a1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String area="area1";
                           Intent i=new Intent(getApplicationContext(),Payment.class);
                           i.putExtra("slot",slot);
                           i.putExtra("area",area);
                           i.putExtra("id",mall_id);
                           startActivity(i);
                           finish();
                        }
                    });
                }
                if (area_list.contains("area2")){
                    btn_a2.setBackgroundColor(getResources().getColor(R.color.red));
                }
                else {
                    btn_a2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String area="area2";
                            Intent i=new Intent(getApplicationContext(),Payment.class);
                            i.putExtra("slot",slot);
                            i.putExtra("area",area);
                            i.putExtra("id",mall_id);
                            startActivity(i);
                            finish();
                        }
                    });
                }
                if (area_list.contains("area3")){
                    btn_a3.setBackgroundColor(getResources().getColor(R.color.red));
                }
                else {
                    btn_a3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String area="area3";
                            Intent i=new Intent(getApplicationContext(),Payment.class);
                            i.putExtra("slot",slot);
                            i.putExtra("area",area);
                            i.putExtra("id",mall_id);
                            startActivity(i);
                            finish();
                        }
                    });
                }
                if (area_list.contains("area4")){
                    btn_a4.setBackgroundColor(getResources().getColor(R.color.red));
                }
                else{
                    btn_a4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String area="area4";
                            Intent i=new Intent(getApplicationContext(),Payment.class);
                            i.putExtra("slot",slot);
                            i.putExtra("area",area);
                            i.putExtra("id",mall_id);
                            startActivity(i);
                            finish();
                        }
                    });
                }
//                if (area_list.contains("area5")){
//                    btn_a5.setBackgroundColor(getResources().getColor(R.color.red));
//                }
//                else {
//                    String area="area5";
//                    Intent i=new Intent(getApplicationContext(),Payment.class);
//                    i.putExtra("slot",slot);
//                    i.putExtra("area",area);
//                    i.putExtra("id",mall_id);
//                    startActivity(i);
//                    finish();
//                }
          }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("mall_id",mall_id);
                return params;
            }
        };
        request1.add(srequest);
       //String area1=txt_area1.getText().toString();

       //Log.v("areeeee", String.valueOf(areas));

//
   }
}
