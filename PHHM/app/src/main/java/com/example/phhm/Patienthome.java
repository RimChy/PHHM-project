package com.example.phhm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class Patienthome extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    private CardView medicineReminder, bloodSugar, bloodPressure, advice, dietPlan, prescription;
    private DrawerLayout mdrawer;
    private ActionBarDrawerToggle mtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setLogo(R.drawable.menu);
        setContentView(R.layout.activity_patienthome);
        NavigationView navigationView=findViewById(R.id.navigationid);
       // this.setTitle("Homepage");
        medicineReminder = findViewById(R.id.medicineReminderId);
        bloodSugar = findViewById(R.id.bloodSugarId);
        bloodPressure = findViewById(R.id.bloodPressureId);
        advice = findViewById(R.id.adviceId);
        dietPlan = findViewById(R.id.dietPlanId);
        prescription = findViewById(R.id.prescriptionId);

        mdrawer=(DrawerLayout)findViewById(R.id.drawerlayout);

        mtoggle=new ActionBarDrawerToggle(this,mdrawer, R.string.Open, R.string.Close);
        mdrawer.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        medicineReminder.setOnClickListener(this);
        bloodPressure.setOnClickListener(this);
        bloodSugar.setOnClickListener(this);
        advice.setOnClickListener(this);
        dietPlan.setOnClickListener(this);
        prescription.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.medicineReminderId)
        {
            Intent intent=new Intent(getApplicationContext(),MedicineReminder.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.bloodPressureId)
        {Intent intent=new Intent(getApplicationContext(),BloodPressure.class);
            startActivity(intent);

        }
        else if (v.getId() == R.id.bloodSugarId)
        {Intent intent=new Intent(getApplicationContext(),BloodSugar.class);
            startActivity(intent);

        }
        else if (v.getId() == R.id.adviceId)
        {Intent intent=new Intent(getApplicationContext(),Advice.class);
            startActivity(intent);

        }
        else if (v.getId() == R.id.prescriptionId)
        {
            Intent intent=new Intent(getApplicationContext(),Retrievedata.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.dietPlanId)
        {
            Intent intent=new Intent(getApplicationContext(),DietPlan.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.nav_account){
            Intent intent=new Intent(this,Myprofile.class);
            startActivity(intent);
        }
        if(menuItem.getItemId()==R.id.nav_logout){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        return false;
    }

}

