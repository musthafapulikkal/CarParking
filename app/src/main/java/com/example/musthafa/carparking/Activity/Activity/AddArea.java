package com.example.musthafa.carparking.Activity.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import java.util.HashMap;
import java.util.Map;

public class AddArea extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayAdapter<String> adapter_mall;
    private ArrayList<String> mall;
    private ArrayList<String> mall_id;
    private JSONArray result;
    Button btn_add;
    Spinner mall_spinner;
    //ArrayAdapter<String> adapter_mall_id;
    ArrayAdapter<String> adapter_area;
    String[] area={"select area","1","2","3","4","5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_area);
        btn_add=(Button)findViewById(R.id.btn_add_area);
        final Spinner area_spinner=(Spinner)findViewById(R.id.id_spinner_area);
        area_spinner.setOnItemSelectedListener(this);
        adapter_area=new ArrayAdapter(this,android.R.layout.simple_spinner_item, area);
        adapter_area.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area_spinner.setAdapter(adapter_area);
        mall_id=new ArrayList<String>();
        mall=new ArrayList<String>();
        mall_spinner=(Spinner)findViewById(R.id.id_spinner_mall);
        mall_spinner.setOnItemSelectedListener(this);
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        String Url="http://parkme.fabstudioz.com/getmall.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("area response",response);
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    int length=jsonArray.length();
                    for (int i=0;i<length;i++){
                        JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                        String id=jsonObject.optString("id");
                        String name=jsonObject.optString("name");
                        Log.v("name",name);
                        mall.add(name);
                        mall_id.add(id);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter_mall=new ArrayAdapter(AddArea.this,android.R.layout.simple_spinner_dropdown_item,mall);
                mall_spinner.setAdapter(adapter_mall);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
      requestQueue.add(stringRequest);
      btn_add.setOnClickListener(new View.OnClickListener() {
          @RequiresApi(api = Build.VERSION_CODES.N)
          @Override
          public void onClick(View view) {
              final String mall_name=mall_spinner.getSelectedItem().toString();
              int list_id= (int) mall_spinner.getSelectedItemId();
              final String area=area_spinner.getSelectedItem().toString();
              final String id=mall_id.get(list_id);
              RequestQueue requestQueue1=Volley.newRequestQueue(AddArea.this);
              String Url="http://parkme.fabstudioz.com/addarea.php";
              StringRequest stringRequest1=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {
                  Log.v("area response",response);
                  if (response.equals("success")){
                      Toast.makeText(AddArea.this, "successfully added", Toast.LENGTH_SHORT).show();
                      startActivity(new Intent(getApplicationContext(),AdminHome.class));
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
                      params.put("mall_id",id);
                      params.put("mall_name",mall_name);
                      params.put("mall_area",area);
                      return params;
                  }
              };
              requestQueue1.add(stringRequest1);

          }
      });

    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
