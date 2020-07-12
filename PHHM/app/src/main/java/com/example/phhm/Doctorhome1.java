package com.example.phhm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Doctorhome1 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorhome1);
        Button medicineReminderDoctor=findViewById(R.id.medicineReminderDoctorId);
        Button prescriptionDoctor=findViewById(R.id.prescriptionDoctorId);
        Button adviceDoctor=findViewById(R.id.adviceDoctorId);
        Button dietPlanDoctor=findViewById(R.id.dietPlanDoctorId);
        medicineReminderDoctor.setOnClickListener(this);
        prescriptionDoctor.setOnClickListener(this);
        adviceDoctor.setOnClickListener(this);
        dietPlanDoctor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){



            case R.id.medicineReminderDoctorId:
                Intent intent=new Intent(getApplicationContext(),doctor.class);
                startActivity(intent);
                break;
            case R.id.prescriptionDoctorId:
                Intent intent1=new Intent(getApplicationContext(),Retrievedata.class);
                startActivity(intent1);
                break;
            case R.id.dietPlanDoctorId:
                Intent intent4=new Intent(getApplicationContext(),DietPlan.class);
                startActivity(intent4);
                break;
            case R.id.adviceDoctorId:
                Intent intent3=new Intent(getApplicationContext(),Advice.class);
                startActivity(intent3);
                break;



        }
    }
}
