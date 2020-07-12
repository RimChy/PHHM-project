package com.example.phhm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText signInEmailEditText,signInPasswordEditText;
    private TextView signUpTextView;
    private Button signInButton;
    RadioButton accountbutton1,doctorbutton1,patientbutton1;
    RadioGroup radiogroup1;
    private FirebaseAuth mAuth;
    DatabaseReference mreference1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        this.setTitle("Sign In Activity");
        signInEmailEditText=findViewById(R.id.signInEmailEditTextId);
        signInPasswordEditText=findViewById(R.id.signInPasswordEditTextId);
        signInButton=findViewById(R.id.signInButtonId);
        signUpTextView=findViewById(R.id.signUpTextViewId);
        doctorbutton1= findViewById(R.id.button_3);
        doctorbutton1.setOnClickListener(this);
        patientbutton1 = findViewById(R.id.button_4);
        patientbutton1.setOnClickListener(this);
        radiogroup1 = findViewById(R.id.radiogroupid);

        signUpTextView.setOnClickListener(this);
       // mreference1=FirebaseDatabase.getInstance().getReference().child(user.)

        signInButton.setOnClickListener(this);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1=signInEmailEditText.getText().toString().trim();
                String pass1=signInPasswordEditText.getText().toString().trim();

                if(email1.isEmpty()){
                    signInEmailEditText.setError("Enter an email address");
                    signInEmailEditText.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
                    signInEmailEditText.setError("Enter a valid email address");
                    signInEmailEditText.requestFocus();
                    return;

                }
                if(pass1.isEmpty()){
                    signInPasswordEditText.setError("Enter a password");
                    signInPasswordEditText.requestFocus();
                    return;
                }
                if(pass1.length()<6){
                    signInPasswordEditText.setError("Minimum length of password should be 6");
                    signInPasswordEditText.requestFocus();
                    return;
                }
              /*  if(!accountbutton1.isChecked()){
                    Toast.makeText(getApplicationContext(),"Please select your user type",Toast.LENGTH_SHORT).show();
                }*/
                mAuth.signInWithEmailAndPassword(email1, pass1)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    // Sign in success, update UI with the signed-in user's information;
                                    if(doctorbutton1.isChecked()){
                                        Intent intent=new Intent(getApplicationContext(),Doctorhome1.class);
                                        startActivity(intent);
                                    }
                                    if(patientbutton1.isChecked()){
                                        Intent intent=new Intent(getApplicationContext(),Patienthome.class);
                                        startActivity(intent);
                                    }

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(),"Login failed or user is not available",Toast.LENGTH_SHORT).show();

                                }


                            }
                        });
            }
        });
    }

    @Override
    public void onClick(View v) {
       if(v.getId()==R.id.signUpTextViewId){
           Intent intent=new Intent(getApplicationContext(),Mytextviewdemo.class);
           startActivity(intent);

        }

    }


    public void checkButton(View view) {
    }
}
