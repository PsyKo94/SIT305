package com.example.expensemanager;

import android.app.Application;

public class Global extends Application {
    //Declare global values
    private float globalrent =0;

    public float getRent(){
        return this.globalrent;
    }

    public void setRent(float d){
        this.globalrent=d;
    }

    private float globalfood =0;

    public float getFood(){
        return this.globalfood;
    }

    public void setFood(float d){
        this.globalfood=d;
    }

    private float globaltravel =0;

    public float getTravel(){
        return this.globaltravel;
    }

    public void setTravel(float d){
        this.globaltravel=d;
    }

    private float globalshopping =0;

    public float getShopping(){
        return this.globalshopping;
    }

    public void setShopping(float d){
        this.globalshopping=d;
    }
}
