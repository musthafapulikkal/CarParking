package com.example.musthafa.carparking.Activity.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String pref_email = "usenamekey";
    SharedPreferences sharedpreferences;
    EditText edt_username,edt_password;
    Button btn_login,btn_sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        TextView textView=(TextView)findViewById(R.id.text);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"ariblk.ttf");
        textView.setTypeface(typeface);
        edt_username=(EditText)findViewById(R.id.id_email);
        edt_password=(EditText)findViewById(R.id.id_password);
        btn_sign_up=(Button)findViewById(R.id.btn_signup);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username=edt_username.getText().toString().trim();
                final String password=edt_password.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    edt_username.setError("please enter your username");
                    edt_username.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    edt_password.setError("please enter your password");
                    edt_password.requestFocus();
                    return;
                }
                if (username.equals("admin@gmail.com")&&password.equals("admin")){
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),AdminHome.class));
                }
                else
                {
                    userLogin();
                }
            }
        });
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Registration.class));
                finish();
            }
        });

    }

    private void userLogin() {
        final String username=edt_username.getText().toString().trim();
        final String password=edt_password.getText().toString().trim();

        final RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(pref_email,username);
        editor.apply();
        String Url="http://parkme.fabstudioz.com/login.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("response main",response);
            if (response.equals(username)){
                startActivity(new Intent(getApplicationContext(),UserHome.class));
            }
            else {
                Toast.makeText(getApplicationContext(),"invalid username or password",Toast.LENGTH_SHORT).show();
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
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
