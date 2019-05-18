package com.example.musthafa.carparking.Activity.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.musthafa.carparking.R;

public class  ShobhaMall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shobha_mall);
        Intent intent=getIntent();
        final String id=intent.getStringExtra("id");

        findViewById(R.id.slot1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String slot="slot1";
                Intent intent=new Intent(getApplicationContext(),SlotOne.class);
                intent.putExtra("slot_name",slot);
                intent.putExtra("id",id);
                startActivity(intent);
                //finish();

            }
        });

    }
}
