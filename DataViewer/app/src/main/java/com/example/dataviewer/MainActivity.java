package com.example.dataviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Initialise Drawer Layout
    private DrawerLayout drawer;

    //Initialise Preference for saved SQL settings
    public static final String PREFS_NAME = "MyPrefsFile";

    // Declaring connection variables
    Connection con;
    String un,pass,db,ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // Declaring Server ip, username, database name and password
        final SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("sql",MODE_PRIVATE);
        ip = sharedpreferences.getString("ipKey", "");
        db = sharedpreferences.getString("sqlKey", "");
        un = sharedpreferences.getString("nameKey", "");
        pass = sharedpreferences.getString("passwordKey", "");

        //If app launches for first time use Global instead of Shared Preference
        Global g = (Global)getApplication();
        if(ip.equals(""))
        {
            ip = g.getIpAddress();
        }
        if(db.equals(""))
        {
            db = g.getSqlSettings();
        }
        if(un.equals(""))
        {
            un = g.getUsername();
        }
        if(pass.equals(""))
        {
            pass = g.getPassword();
        }

        // Setting up the function when button login is clicked
        Button btnLogin = (Button) findViewById(R.id.button_login);
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //This calls the Check login to the SQL server
                CheckLogin checkLogin = new CheckLogin();
                checkLogin.execute("");
            }
        });
    }

    //Check login
    public class CheckLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPostExecute(String r)
        {
            Toast.makeText(MainActivity.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Toast.makeText(MainActivity.this , "Login successful" , Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected String doInBackground(String... params)
        {
            Global g = (Global)getApplication();
            String username = un;
            String password = pass;
            if(username.trim().equals("")|| password.trim().equals(""))
                z = "Please enter Username and Password";
            else
            {
                try
                {
                    // Connect to database
                    con = connectionclass(un, pass, db, ip);
                    if (con == null)
                    {
                        z = "Check Your Internet Access!";
                    }
                    else
                    {
                        //Test query
                        String query = "select TOP 1 * from proc_materials";
                        //String query = "select * from login where user_name= '" + username.toString() + "' and pass_word = '"+ password.toString() +"'  ";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);

                        if(rs.next())
                        {
                            z = "Login successful";
                            isSuccess=true;
                            con.close();
                        }
                        else
                        {
                            z = "Invalid Credentials!";
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.getMessage();
                }
            }
            return z;
        }

        //Report 1 query
        protected ArrayList query1(ArrayList... params)
        {
            ArrayList<String> list = new ArrayList<String>();
            try
            {
                // Connect to database
                con = connectionclass(un, pass, db, ip);
                if (con == null)
                {
                    z = "Check Your Internet Access!";
                }
                else
                {
                    //SQL query and list
                    String query = "select name from proc_materials";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    // Fetch each row from the results
                    int i = 0;
                    while (rs.next()) {
                        String str = rs.getString("name");
                        list.add(i, str);
                        i++;
                    }
                }
            }
            catch (Exception ex)
            {
                isSuccess = false;
                z = ex.getMessage();
            }
            Toast.makeText(MainActivity.this , z , Toast.LENGTH_LONG).show();
            return list;
        }

    }


    @SuppressLint("NewApi")
    public Connection connectionclass(String user, String password, String database, String server)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + server + "/" + database + ";user=" + user + ";password=" + password + ";";
            //ConnectionURL = "jdbc:jtds:sqlserver://BNEPC5133/InnovaPackingTraining;user=testlogin;password=Password1;";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
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
                //Get SQL data and send to fragment
                ArrayList<String> MaterialList;
                CheckLogin checkLogin = new CheckLogin();
                MaterialList = checkLogin.query1();

                //Send list to Global and Start Report 1 Activity
                Global g = (Global)getApplication();
                g.SetMaterialList(MaterialList);
                Intent intent = new Intent(this, Report1.class);
                startActivity(intent);
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
