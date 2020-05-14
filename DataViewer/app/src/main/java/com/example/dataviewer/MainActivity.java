package com.example.dataviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Initialise Drawer Layout
    private DrawerLayout drawer;

    //Initialise Preference for saved SQL settings
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set SQL settings
        /*
        Global g = (Global)getApplication();
        SharedPreferences sqlSettings = getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = sqlSettings.edit();
        editor.putString("sql",g.getSqlSettings());
        editor.commit();

         */

        //Set toolbar as action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set reports drawer
        drawer = findViewById(R.id.drawer_layout);

        //Assign Navigation View
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Create Toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Assign Settings button and start Settings activity
        Button btnSettings = (Button) findViewById(R.id.button_settings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSQLSettings();
            }
        });
    }

    //Method to start SQL Settings Activity
    public void openSQLSettings() {
        Intent intent = new Intent(this, SQLSettings.class);
        startActivity(intent);
    }

    //Callback to menu, return selection true
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.report1:
                Toast.makeText(this, "report1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.report2:
                Toast.makeText(this, "report2", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Close drawer instead of closing app
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
