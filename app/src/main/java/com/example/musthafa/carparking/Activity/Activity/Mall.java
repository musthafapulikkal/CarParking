package com.example.musthafa.carparking.Activity.Activity;

import android.content.Context;

public class Mall {
    private String id;
    private String name;
    private String place;
    private String image;
    private Context context;
    public Mall(){

    }
    public Mall(String id,String name,String place,String image,Context context){
        this.id=id;
        this.name=name;
        this.place=place;
        this.image=image;
        this.context=context;
    }
    public String getId(){return id;}
    public void setId(String id){this.id=id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getPlace(){return place;}
    public void setPlace(String place){this.place=place;}
    public String getImage(){return image;}
    public void setImage(String image){this.image=image;}

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
