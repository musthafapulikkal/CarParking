package com.example.musthafa.carparking.Activity.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

public class ExampleAreaAdd extends AppCompatActivity implements Spinner.OnItemSelectedListener{
    //Declaring an Spinner
    private Spinner spinner;
    ArrayAdapter<String> adapter_mall;
    //An ArrayList for Spinner Items
    private ArrayList<String> students;

    //JSON Array
    private JSONArray result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_area_add);
        students = new ArrayList<String>();
        spinner = (Spinner) findViewById(R.id.id_spinner_example_mall);
        spinner.setOnItemSelectedListener(this);
        getData();
    }

    private void getData() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.DATA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject j=null;
                try {
                    j=new JSONObject(response);
                    result=j.getJSONArray(Config.JSON_ARRAY);
                    getStudents(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getStudents(JSONArray result) {
        for(int i=0;i<result.length();i++){
            try {
                JSONObject json = result.getJSONObject(i);
                students.add(json.getString(Config.TAG_NAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        adapter_mall=new ArrayAdapter(ExampleAreaAdd.this, android.R.layout.simple_spinner_dropdown_item, students);
        spinner.setAdapter(adapter_mall);
    }
    private String getId(int position){
        String id="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            id = json.getString(Config.TAG_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return id;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
