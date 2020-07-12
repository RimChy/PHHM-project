package com.example.phhm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class BloodPressure extends AppCompatActivity  implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mdrawer;
    private ActionBarDrawerToggle mtoggle;
    Button bloodpressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setLogo(R.drawable.menu);
        setContentView(R.layout.activity_blood_pressure);
        NavigationView navigationView=findViewById(R.id.navigationid);
        mdrawer=(DrawerLayout)findViewById(R.id.drawerlayout);

        mtoggle=new ActionBarDrawerToggle(this,mdrawer, R.string.Open, R.string.Close);
        mdrawer.addDrawerListener(mtoggle);
        mtoggle.syncState();
        bloodpressure=findViewById(R.id.bloodSugarTakenButtonId);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        bloodpressure.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bloodSugarTakenButtonId :
        Toast.makeText(getApplicationContext(),"Uploading in progress",Toast.LENGTH_LONG).show();;

    }}

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.nav_account){
            Intent intent=new Intent(this,Myprofile.class);
            startActivity(intent);
        }
        if(menuItem.getItemId()==R.id.nav_logout){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if(menuItem.getItemId()==R.id.nav_home){
            Intent intent=new Intent(this,Patienthome.class);
            startActivity(intent);
        }
        return false;
    }
}