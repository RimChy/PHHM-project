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

import com.google.android.material.navigation.NavigationView;

public class MedicineReminder extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
    Button button1,button2;
    private DrawerLayout mdrawer;
    private ActionBarDrawerToggle mtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setLogo(R.drawable.menu);
        setContentView(R.layout.activity_medicine_reminder);
        NavigationView navigationView=findViewById(R.id.navigationid);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        mdrawer=(DrawerLayout)findViewById(R.id.drawerlayout);

        mtoggle=new ActionBarDrawerToggle(this,mdrawer, R.string.Open, R.string.Close);
        mdrawer.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button1.setOnClickListener(this);

        button2.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){


            case R.id.button1:
                Intent intent=new Intent(getApplicationContext(),patient.class);
                startActivity(intent);
                break;

            case R.id.button2:
                Intent intent2=new Intent(getApplicationContext(),doctor.class);
                startActivity(intent2);
                break;

        }

    }
    @Override
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

