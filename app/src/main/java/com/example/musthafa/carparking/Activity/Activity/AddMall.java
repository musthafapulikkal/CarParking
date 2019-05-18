package com.example.musthafa.carparking.Activity.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AddMall extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayAdapter<CharSequence> adapter_state;
    ArrayAdapter<CharSequence> adapter_dist;
    ImageView imageView;
    ImageButton choose;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private String imagepath=null;
    EditText edt_mall,edt_place;
    Button btn_add;
    String[] state={"Kerala","Tamilnadu"};
    String[] district={"Trivandrum","Pathanamthitta","Alappuzha","Kottayam","Idukki","Ernakulam","Thrissur","Malappuram","Palakkad","Vayanad","Kollam","Kozhikkod","Kannur","Kasargod"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mall);
        btn_add=(Button)findViewById(R.id.btn_id_add_mall);
        edt_mall=(EditText)findViewById(R.id.id_mall_name);
        edt_place=(EditText)findViewById(R.id.id_mall_place);
        imageView=(ImageView)findViewById(R.id.id_image);
        choose=(ImageButton)findViewById(R.id.id_choose_image);
        final Spinner state_spinner=(Spinner)findViewById(R.id.id_state_spinner);
        final Spinner dist_spinner=(Spinner)findViewById(R.id.id_dist_spinner);
        state_spinner.setOnItemSelectedListener(this);
        adapter_state=new ArrayAdapter(this,android.R.layout.simple_spinner_item, state);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state_spinner.setAdapter(adapter_state);

        dist_spinner.setOnItemSelectedListener(this);
        adapter_dist=new ArrayAdapter(this,android.R.layout.simple_spinner_item,district);
        adapter_dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dist_spinner.setAdapter(adapter_dist);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,RESULT_LOAD_IMAGE);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String state=state_spinner.getSelectedItem().toString();
                final String dist=dist_spinner.getSelectedItem().toString();
                final String mall_name=edt_mall.getText().toString().trim();
                final String mall_place=edt_place.getText().toString().trim();
                Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                final byte[] imageInByte=baos.toByteArray();
                final String image=Base64.encodeToString(imageInByte,Base64.DEFAULT);

                if (TextUtils.isEmpty(mall_name)){
                    edt_mall.setError("please enter here");
                    edt_mall.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(mall_place)){
                    edt_place.setError("please enter here");
                    edt_place.requestFocus();
                    return;
                }

                RequestQueue requestQueue=Volley.newRequestQueue(AddMall.this);
                String Url="http://parkme.fabstudioz.com/addmall.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("response",response);
                        if (response.equals("success"))
                        {
                            Toast.makeText(AddMall.this, "added", Toast.LENGTH_SHORT).show();
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
                        params.put("state",state);
                        params.put("district",dist);
                        params.put("mall",mall_name);
                        params.put("place",mall_place);
                        params.put("image",image);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_LOAD_IMAGE&&resultCode==RESULT_OK&&null != data){
            Uri selectedImage=data.getData();
            String[] filepathColumn={MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filepathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex=cursor.getColumnIndex(filepathColumn[0]);
            String picturePath=cursor.getString(columnIndex);
            cursor.close();
           imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));


        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//      if (adapterView.getId()==R.id.id_state_spinner){
//          String item=adapterView.getSelectedItem().toString();
//          Toast.makeText(this, item, Toast.LENGTH_SHORT).show();
//
//      }
//      else if (adapterView.getId()==R.id.id_dist_spinner){
//          String item=adapterView.getSelectedItem().toString();
//          Toast.makeText(this, item, Toast.LENGTH_SHORT).show();
//      }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
