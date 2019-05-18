package com.example.musthafa.carparking.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.musthafa.carparking.Activity.Activity.Mall;
import com.example.musthafa.carparking.R;

import java.util.List;

public class MallAdapter extends RecyclerView.Adapter<MallAdapter.MyViewHolder> {
    public interface RecyclerViewClickListener{
        public void recyclerViewListClicked(View v, int position);
    }
    private Context context;
    private List<Mall> mallList;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.mall_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Mall mall=mallList.get(position);
        holder.name.setText(mall.getName());
        holder.place.setText(mall.getPlace());
        String Url="http://parkme.fabstudioz.com/"+mall.getImage();
        Glide.with(mall.getContext()).load(Url).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mallList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView place;
        public ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.txt_mall_name);
            place=(TextView)itemView.findViewById(R.id.txt_mall_place);
            image=(ImageView)itemView.findViewById(R.id.imageview_mall);
        }
    }
    public MallAdapter(List<Mall> mallList){this.mallList=mallList;}
    public MallAdapter(Context context){
        this.context=context;
    }
}
