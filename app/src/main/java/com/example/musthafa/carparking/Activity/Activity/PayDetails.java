package com.example.musthafa.carparking.Activity.Activity;

import android.content.Context;

public class PayDetails {
    private String id;
    private String email;
    private String slot;
    private String area;
    private String amount;
    private Context context;
    public PayDetails()
    {

    }
    public PayDetails(String id,String email,String slot,String area,String amount,Context context){
        this.id=id;
        this.email=email;
        this.slot=slot;
        this.area=area;
        this.amount=amount;
        this.context=context;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
