package com.example.phhm;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class patient extends AppCompatActivity {
    TextView textview1,textView2;
    Button button3;
    Spinner spinner;
    DatabaseReference databasemed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        databasemed= FirebaseDatabase.getInstance().getReference("medicine");
        textview1=(TextView)findViewById(R.id.textView1);

        button3=(Button)findViewById(R.id.button3);

        spinner=(Spinner)findViewById(R.id.spinner);
        Calendar calendar=Calendar.getInstance();
        String current= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textView2=findViewById(R.id.textView2);
        textView2.setText(current);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMedicine();

            }
        });
    }
    private void addMedicine(){
        String text=textView2.getText().toString();
        String spin=spinner.getSelectedItem().toString();
        if(!spin.isEmpty()){
            String id=databasemed.push().getKey();
            Medicine medicine=new Medicine(text,id,spin);
            databasemed.child(id).setValue(medicine);
            Toast.makeText(this,"Medicine Added",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"you should select a medicine name",Toast.LENGTH_LONG).show();

        }
    }
}
