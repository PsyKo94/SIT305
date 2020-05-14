package com.example.dataviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SQLSettings extends AppCompatActivity {

    //Initialise Preference for saved SQL settings
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String Ip = "ipKey";
    public static final String Sql = "sqlKey";
    public static final String Name = "nameKey";
    public static final String Password = "passwordKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_settings);

        //Initialise Shared Preference
        final SharedPreferences sharedpreferences;

        //Initialise fields
        final EditText ipAddress = (EditText) findViewById(R.id.input_ip);
        final EditText sqlSettings = (EditText) findViewById(R.id.input_sql_settings);
        final EditText username = (EditText) findViewById(R.id.input_username);
        final EditText password = (EditText) findViewById(R.id.input_password);

        //Get SQL Connection values and display
        sharedpreferences = getSharedPreferences("sql",MODE_PRIVATE);
        ipAddress.setText(sharedpreferences.getString(Ip, ""));
        sqlSettings.setText(sharedpreferences.getString(Sql, ""));
        username.setText(sharedpreferences.getString(Name, ""));
        password.setText(sharedpreferences.getString(Password, ""));


        //Return to Main Activity
        Button btnReturn = (Button) findViewById(R.id.button_save);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Set SQL Connection values
                Global g = (Global)getApplication();
                g.SetIpAddress(ipAddress.getText().toString());
                g.SetSqlSettings(sqlSettings.getText().toString());
                g.SetUsername(username.getText().toString());
                g.SetPassword(password.getText().toString());

                //Set persistent data
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Ip,g.getIpAddress());
                editor.putString(Sql,g.getSqlSettings());
                editor.putString(Name,g.getUsername());
                editor.putString(Password,g.getPassword());
                editor.commit();

                //Return
                finish();
            }
        });
    }
}
