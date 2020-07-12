package com.example.phhm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.phhm.model.Userdata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class  Mytextviewdemo extends AppCompatActivity implements View.OnClickListener {
    RadioGroup radiogroup;
    Button savebutton;
    RadioButton accountbutton,doctorbutton,patientbutton;
    FirebaseAuth mAuth;
    String straccount,child;
    EditText nameedit,passwordedit,numberedit,usernameedit,confirmedit,emailedit;
   String account,name,email;
     ProgressBar progressBar;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytextviewdemo);
        mAuth = FirebaseAuth.getInstance();
        //Log.e("key", "hellow");
       // final TextView[] name = {(TextView) findViewById(R.id.name)};
      //  final TextView[] email = {(TextView) findViewById(R.id.email)};
       // TextView number = (TextView) findViewById(R.id.phone);
        // TextView address = (TextView) findViewById(R.id.address);
       // final TextView[] username = {(TextView) findViewById(R.id.username)};
     //   final TextView password = (TextView) findViewById(R.id.password);
       // TextView confirm = (TextView) findViewById(R.id.confirm_password);

        Log.e("key", "hellow");
         nameedit = (EditText) findViewById(R.id.name_field);
         emailedit = (EditText) findViewById(R.id.email_field);
         numberedit = (EditText) findViewById(R.id.phone_field);
        // EditText addressedit = (EditText) findViewById(R.id.address_field);
         usernameedit = (EditText) findViewById(R.id.username_field);
         passwordedit = (EditText) findViewById(R.id.password_field);
        Log.e("key", "hellow");
         confirmedit = (EditText) findViewById(R.id.confirm_password_field);
         progressBar=findViewById(R.id.progressbarId1);

        Log.e("key", "hellow");
        //Button patient = (Button) findViewById(R.id.button_1);
        Log.e("key", "hellow");
       // Button doctor = (Button) findViewById(R.id.button_2);
        Log.e("key", "hellow");
        doctorbutton = findViewById(R.id.button_1);
        doctorbutton.setOnClickListener(this);
        patientbutton = findViewById(R.id.button_2);
        patientbutton.setOnClickListener(this);
        savebutton = findViewById(R.id.save_id);
        radiogroup = findViewById(R.id.radiogroupid);




         mAuth=FirebaseAuth.getInstance();

       // mReference=mDatabase.getReference();
        //savebutton.setOnClickListener(this);

   /* radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            // accountButton=radiogroup.findViewById(checkedId);
            switch (checkedId) {
                case R.id.button_1:
                    mDatabase = FirebaseDatabase.getInstance().getReference("Doctors");
                    break;

                case R.id.button_2:
                    mDatabase=FirebaseDatabase.getInstance().getReference("Patients");
                    break;


                default:

            }
        }
    });*/


        savebutton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                   final String name =nameedit.getText().toString();
                  final String email=emailedit.getText().toString();
                  final String pass=passwordedit.getText().toString();
                 final String number =numberedit.getText().toString();
                  final String username=usernameedit.getText().toString();
                  final String confirmpass=confirmedit.getText().toString();

                if(email.isEmpty()){
                    emailedit.setError("Enter an email address");
                    emailedit.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailedit.setError("Enter a valid email address");
                    emailedit.requestFocus();
                    return;

                }
                if(pass.isEmpty()){
                    passwordedit.setError("Enter a password");
                    passwordedit.requestFocus();
                    return;
                }
                if(pass.length()<6){
                    passwordedit.setError("Minimum length of password should be 6");
                    passwordedit.requestFocus();
                    return;
                }
               /* if(!accountbutton.isChecked()){
                    Toast.makeText(getApplicationContext(), "Please select your user type", Toast.LENGTH_SHORT).show();
                }*/
               if(name.isEmpty()){
                    nameedit.setError("Enter your name");
                    nameedit.requestFocus();
                    return;
                }
                if(number.isEmpty()){
                    numberedit.setError("Enter your phone number");
                    numberedit.requestFocus();
                    return;
                }
                if(confirmpass.isEmpty()){
                    confirmedit.setError("Please confirm your password");
                    confirmedit.requestFocus();
                    return;
                }
                if(username.isEmpty()){
                    usernameedit.setError("Please enter your username");
                    usernameedit.requestFocus();
                    return;
                }

                if(!confirmpass.equals(pass)){
                    confirmedit.setError("PASSWORD DIDNOT MATCH!");
                    confirmedit.requestFocus();
                    return;
                }
                if(doctorbutton.isChecked()){
                    account="Doctor";
                    //mDatabase = FirebaseDatabase.getInstance().getReference("Doctors");
                }
                if(patientbutton.isChecked()){
                    account="Patient";
                    // mDatabase = FirebaseDatabase.getInstance().getReference("Patients");
                }
                progressBar.setVisibility(View.VISIBLE);


                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Toast.makeText(getApplicationContext(), "Register is successful", Toast.LENGTH_SHORT).show();
                            //  int radioId = radiogroup.getCheckedRadioButtonId();
                            // doctorbutton = findViewById(radioId);
                            //  patientbutton=findViewById(radioId);
                            Userdata information=new Userdata(name,
                                    email,
                                    number,
                                    username,
                                    pass,
                                    confirmpass,
                                    account);
                         /*   if(doctorbutton.isChecked()) {

                                FirebaseDatabase.getInstance().getReference("Doctors")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getApplicationContext(), "Register is successful", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(), Doctorhome.class);
                                        startActivity(intent);

                                    }
                                });
                                if (patientbutton.isChecked()) {

                                    FirebaseDatabase.getInstance().getReference("Patients")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getApplicationContext(), "Register is successful", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(getApplicationContext(), Patienthome.class);
                                            startActivity(intent);

                                        }
                                    });


                                }
                            }*/




                        /*   if (doctorbutton.getId() == R.id.button_1) {
                                Intent intent = new Intent(getApplicationContext(), Doctorhome.class);
                                startActivity(intent);
                            }
                            if (patientbutton.getId() == R.id.button_2) {
                                Intent intent = new Intent(getApplicationContext(), Patienthome.class);
                                startActivity(intent);
                            }*/
                            FirebaseDatabase.getInstance().getReference(account)
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), "Register is successful", Toast.LENGTH_SHORT).show();
                                    if (doctorbutton.isChecked()) {
                                        Intent intent = new Intent(getApplicationContext(), Doctorhome1.class);
                                        startActivity(intent);

                                    }
                                    if (patientbutton.isChecked()) {
                                        Intent intent = new Intent(getApplicationContext(), Patienthome.class);
                                        startActivity(intent);
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });




            }
        });
    }














    @Override
    public void onClick(View v) {

    }

    public void checkButton(View view) {
    }


    //.setOnClickListener(this);

    //doctor.setOnClickListener(this);







}





