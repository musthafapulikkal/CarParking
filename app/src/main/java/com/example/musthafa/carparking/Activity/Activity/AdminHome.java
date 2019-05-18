package com.example.musthafa.carparking.Activity.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.musthafa.carparking.R;

public class AdminHome extends AppCompatActivity {
Button btn_mall,btn_area,btn_payment,btn_location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        btn_mall=(Button)findViewById(R.id.id_mall);
        btn_area=(Button)findViewById(R.id.id_admin_area);
        btn_payment=(Button)findViewById(R.id.id_view_payment);
        btn_location=(Button)findViewById(R.id.id_location_add);

        btn_mall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddMall.class));
            }
        });
        btn_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),AddArea.class));
            }
        });
        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewPayment.class));

            }
        });
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LocationFinder.class));
            }
        });

    }
}
