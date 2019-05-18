package com.example.musthafa.carparking.Activity.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

import static com.example.musthafa.carparking.Activity.Activity.MainActivity.MyPREFERENCES;
import static com.example.musthafa.carparking.Activity.Activity.MainActivity.pref_email;

public class ViewMySlot extends AppCompatActivity {
    TextView txt_name,txt_email,txt_slot,txt_area,txt_time,txt_am_pm,txt_hour,txt_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_slot);
        txt_name=(TextView)findViewById(R.id.id_s_name);
        txt_email=(TextView)findViewById(R.id.id_s_email);
        txt_slot=(TextView)findViewById(R.id.id_s_slot);
        txt_area=(TextView)findViewById(R.id.id_s_area);
        txt_time=(TextView)findViewById(R.id.id_s_time);
        txt_am_pm=(TextView)findViewById(R.id.id_s_am_pm);
        txt_hour=(TextView)findViewById(R.id.id_s_hour);
        txt_date=(TextView)findViewById(R.id.id_s_date);
        RequestQueue requestQueue=Volley.newRequestQueue(ViewMySlot.this);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        final String email=prefs.getString(pref_email,null);
        String Url="http://parkme.fabstudioz.com/vieMySlot.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("slot response",response);
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    int length=jsonArray.length();
                    for (int i=0;i<=length;i++){
                        JSONObject json=(JSONObject)jsonArray.get(i);
                        String name=json.optString("name");
                        txt_name.setText(name);
                        String email=json.optString("email");
                        txt_email.setText(email);
                        String slot=json.optString("slot");
                        txt_slot.setText(slot);
                        String area=json.optString("area");
                        txt_area.setText(area);
                        String time=json.optString("time");
                        txt_time.setText(time);
                        String am_pm=json.optString("am_pm");
                        txt_am_pm.setText(am_pm);
                        String hour=json.optString("hour");
                        txt_hour.setText(hour);
                        String date=json.optString("date");
                        txt_date.setText(date);
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
                params.put("email",email);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
}
