package com.example.musthafa.carparking.Activity.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musthafa.carparking.Activity.Adapter.MallAdapter;
import com.example.musthafa.carparking.Activity.Adapter.PaymentAdapter;
import com.example.musthafa.carparking.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewPayment extends AppCompatActivity {
    private List<PayDetails> paylist=new ArrayList<>();
    private RecyclerView recyclerView;
    private PaymentAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_payment);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_payment);
        mAdapter = new PaymentAdapter(paylist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        RequestQueue requestQueue=Volley.newRequestQueue(ViewPayment.this);
        String Url="http://parkme.fabstudioz.com/viewpayment.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("viewpayment response",response);
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    int length=jsonArray.length();
                    Context context=getApplicationContext();
                    for (int i=0;i<=length;i++){
                        JSONObject json=(JSONObject)jsonArray.get(i);
                        String id=json.optString("id");
                        String email=json.optString("email");
                        String slot=json.optString("slot");
                        String area=json.optString("area");
                        String amount=json.optString("amount");
                        PayDetails pay=new PayDetails(id,email,slot,area,amount,context);
                        paylist.add(pay);
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
        });
        requestQueue.add(stringRequest);
    }
}
