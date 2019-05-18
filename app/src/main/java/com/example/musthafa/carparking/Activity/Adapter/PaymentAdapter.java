package com.example.musthafa.carparking.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musthafa.carparking.Activity.Activity.Mall;
import com.example.musthafa.carparking.Activity.Activity.PayDetails;
import com.example.musthafa.carparking.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {
    private Context context;
    private List<PayDetails> paylist ;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final PayDetails pay=paylist.get(position);
        holder.email.setText("Email:"+pay.getEmail());
        holder.slot.setText("Slot:"+pay.getSlot());
        holder.area.setText("Area:"+pay.getArea());
        holder.amount.setText("Amount:"+pay.getAmount());


    }

    @Override
    public int getItemCount() {
        return paylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView email;
        TextView slot;
        TextView area;
        TextView amount;
        ImageButton delete;
        public MyViewHolder(View itemView) {
            super(itemView);
            email=(TextView)itemView.findViewById(R.id.pay_txt_name_id);
            slot=(TextView)itemView.findViewById(R.id.pay_txt_slot_id);
            area=(TextView)itemView.findViewById(R.id.pay_txt_area_id);
            amount=(TextView)itemView.findViewById(R.id.pay_txt_amount_id);
            delete=(ImageButton)itemView.findViewById(R.id.btn_pay_delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteAt(getAdapterPosition());
                }
            });
        }
    }

    private void deleteAt(int adapterPosition) {
        if (paylist.size()==0){

        }
        else{
            Log.v("Paylist Size", String.valueOf(paylist.size()));
            Log.v("Adapter Position", String.valueOf(adapterPosition));
            final PayDetails pay=paylist.get(adapterPosition);
            paylist.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
            notifyItemRangeChanged(adapterPosition,paylist.size());
            final String id=pay.getId();
            RequestQueue requestQueue=Volley.newRequestQueue(pay.getContext());
            String Url="http://parkme.fabstudioz.com/delete_payment.php";
            StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.v("delete response",response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    params.put("id",id);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }

    public PaymentAdapter(List<PayDetails> paylist){this.paylist=paylist;}
    public PaymentAdapter(Context context){
        this.context=context;
    }
}
