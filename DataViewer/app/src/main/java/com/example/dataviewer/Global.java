package com.example.dataviewer;
import android.app.Application;

public class Global extends Application {
    //Declare global values
    private String ipAddress;
    private String sqlSettings;
    private String username;
    private String password;

    //Set Getters and Setters
    public String getIpAddress(){
        return this.ipAddress;
    }

    public void SetIpAddress(String i){
        this.ipAddress=i;
    }

    public String getSqlSettings(){
        return this.sqlSettings;
    }

    public void SetSqlSettings(String s){
        this.sqlSettings=s;
    }

    public String getUsername(){
        return this.username;
    }

    public void SetUsername(String u){
        this.username=u;
    }

    public String getPassword(){
        return this.password;
    }

    public void SetPassword(String p){
        this.password=p;
    }


}
