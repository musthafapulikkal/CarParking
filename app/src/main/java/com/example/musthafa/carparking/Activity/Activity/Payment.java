package com.example.musthafa.carparking.Activity.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.musthafa.carparking.Activity.Activity.MainActivity.MyPREFERENCES;
import static com.example.musthafa.carparking.Activity.Activity.MainActivity.pref_email;

public class Payment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayAdapter<String>  adapter_hour;
    ArrayAdapter<String> adapter_time;
    ArrayAdapter<String> adapter_am;
    String[] time={"1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] am_pm={"AM","PM"};
    String[] hours={"select hour","1 hour","2 hour","3 hour","4 hour","5 hour"};
    TextView txt_amt;
    EditText edt_name,edt_card_no,edt_date,edt_cvv;
    Button btn_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        edt_name=(EditText)findViewById(R.id.id_name_on_card);
        edt_card_no=(EditText)findViewById(R.id.id_card_no);
        edt_date=(EditText)findViewById(R.id.id_exp_date);
        edt_cvv=(EditText)findViewById(R.id.id_cvv);
        btn_pay=(Button)findViewById(R.id.btn_payment);
        txt_amt=(TextView)findViewById(R.id.id_pay_amount);

        Date date=Calendar.getInstance().getTime();
        SimpleDateFormat df=new SimpleDateFormat("dd-mmm-yyyy");
        final String formatdate=df.format(date);

        final Spinner time_spinner=(Spinner)findViewById(R.id.id_spinne_time);
        time_spinner.setOnItemSelectedListener(this);
        adapter_time=new ArrayAdapter<>(this,R.layout.spinner_item,R.id.spinner_text_id,time);
        time_spinner.setAdapter(adapter_time);

        final Spinner am_pm_spinner=(Spinner)findViewById(R.id.id_spinner_am);
        am_pm_spinner.setOnItemSelectedListener(this);
        adapter_am=new ArrayAdapter(this,R.layout.spinner_item,R.id.spinner_text_id,am_pm);
        am_pm_spinner.setAdapter(adapter_am);

        final Spinner hour_spinner=(Spinner)findViewById(R.id.id_spinner_payment_hour);
        hour_spinner.setOnItemSelectedListener(this);
        adapter_hour=new ArrayAdapter(this,R.layout.spinner_item,R.id.spinner_text_id,hours);
        //adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hour_spinner.setAdapter(adapter_hour);

        Log.v("amount text", String.valueOf(txt_amt));
        Intent intent=getIntent();
        final String slot=intent.getStringExtra("slot");
        Log.v("slot",slot);
        final String area=intent.getStringExtra("area");
        Log.v("area=",area);
        final String mall_id=intent.getStringExtra("id");
        Log.v("mall_id=",mall_id);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String amount=txt_amt.getText().toString();
                Log.v("amount=",amount);
                String[] seperated=amount.split(":");
                final String n_amount=seperated[1];
                Log.v("new amount",n_amount);

                final String name=edt_name.getText().toString().trim();
                Log.v("name=",name);
                final String card_no=edt_card_no.getText().toString().trim();
                Log.v("card no=",card_no);
                final String date=edt_date.getText().toString().trim();
                Log.v("date=",date);
                final String cvv=edt_cvv.getText().toString().trim();
                Log.v("cvv=",cvv);
                final String time=time_spinner.getSelectedItem().toString();
                Log.v("time=",time);
                final String am_pm=am_pm_spinner.getSelectedItem().toString();
                final String hour=hour_spinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(name)){
                    edt_name.setError("please enter here");
                    edt_name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(card_no)){
                    edt_card_no.setError("please enter here");
                    edt_card_no.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(date)){
                    edt_date.setError("please enter here");
                    edt_date.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(cvv)){
                    edt_cvv.setError("please enter here");
                    edt_cvv.requestFocus();
                    return;
                }

                RequestQueue requestQueue=Volley.newRequestQueue(Payment.this);
                SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                final String email=prefs.getString(pref_email,null);
                Log.v("username",email);
                String Url="http://parkme.fabstudioz.com/payment.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("payment response",response);
                        if (response.equals("success")){
                         startActivity(new Intent(getApplicationContext(),PaymentSuccess.class));
                         finish();
                        }
                        else if (response.equals("failed")){
                            startActivity(new Intent(getApplicationContext(),PaymentError.class));
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
                        params.put("card_no",card_no);
                        params.put("edate",date);
                        params.put("cvv",cvv);
                        params.put("time",time);
                        params.put("am_pm",am_pm);
                        params.put("hour",hour);
                        params.put("slot",slot);
                        params.put("area",area);
                        params.put("mall_id",mall_id);
                        params.put("amount",n_amount);
                        params.put("date",formatdate);
                        return params;
                    }
                };

                requestQueue.add(stringRequest);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        final String hour=adapterView.getSelectedItem().toString();
        switch (hour){
            case "1 hour":
                Log.v("selected item",hour);
                txt_amt.setText("Rs:35");
                break;
            case "2 hour":
                Log.v("selected item",hour);
                txt_amt.setText("Rs:40");
                break;
            case "3 hour":
                Log.v("selected item",hour);
                txt_amt.setText("Rs:45");
                break;
            case "4 hour":
                txt_amt.setText("Rs:50");
                break;
            case "5 hour":
                txt_amt.setText("Rs:55");
                break;
            default:
                txt_amt.setText("Rs:35");
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}
