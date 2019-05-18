package com.example.musthafa.carparking.Activity.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.musthafa.carparking.Activity.Adapter.MallAdapter;
import com.example.musthafa.carparking.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewMall extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayAdapter<CharSequence> adapter_state;
    ArrayAdapter<CharSequence> adapter_dist;
    Button btn_search;
//    ProgressDialog progrssbar;
//    private int progressBarStatus = 0;
//    private Handler progressBarHandler = new Handler();
//    private long fileSize =0;
    String[] state={"Kerala","Tamilnadu"};
    String[] district={"Trivandrum","Pathanamthitta","Alappuzha","Kottayam","Idukki","Ernakulam","Thrissur","Malappuram","Palakkad","Vayanad","Kollam","Kozhikkod","Kannur","Kasargod"};
    private List<Mall> mallList=new ArrayList<>();
    private RecyclerView recyclerView;
    private MallAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mall);
        btn_search=(Button)findViewById(R.id.btn_search);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final Spinner state_spinner=(Spinner)findViewById(R.id.id_mall_state_spinner);
        final Spinner dist_spinner=(Spinner)findViewById(R.id.id_mall_dist_spinner);
        state_spinner.setOnItemSelectedListener(this);

        adapter_state=new ArrayAdapter(this,android.R.layout.simple_spinner_item, state);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state_spinner.setAdapter(adapter_state);

        dist_spinner.setOnItemSelectedListener(this);
        adapter_dist=new ArrayAdapter(this,android.R.layout.simple_spinner_item,district);
        adapter_dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dist_spinner.setAdapter(adapter_dist);

        mAdapter = new MallAdapter(mallList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListner(getApplicationContext(), recyclerView, new RecyclerTouchListner.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final Mall mall=mallList.get(position);
                final String id=mall.getId();
                Log.v("mallid",id);
                Intent intent=new Intent(getApplicationContext(),MallHome.class);
                intent.putExtra("id",id);
                startActivity(intent);

//                startActivity(new Intent(getApplicationContext(),MallHome.class));
                finish();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mallList.clear();
                mAdapter.notifyDataSetChanged();
                final String state=state_spinner.getSelectedItem().toString();
                final String district=dist_spinner.getSelectedItem().toString();
                RequestQueue requestQueue=Volley.newRequestQueue(ViewMall.this);
                String Url="http://parkme.fabstudioz.com/viewmall.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("view mall response",response);
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            int length=jsonArray.length();
                            Context context=getApplicationContext();
                            for (int i=0;i<=length;i++){
                                JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                                String mall_id=jsonObject.optString("id");
                                String mall_name=jsonObject.optString("name");
                                String mall_place=jsonObject.optString("place");
                                String mall_image=jsonObject.optString("image");
                                Mall mall=new Mall(mall_id,mall_name,mall_place,mall_image,context);
                                mallList.add(mall);
                                mAdapter.notifyDataSetChanged();
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
                        params.put("state",state);
                        params.put("district",district);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);

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
