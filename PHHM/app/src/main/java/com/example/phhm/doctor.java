package com.example.phhm;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.List;

public class doctor extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{
    private ListView listView;
    private List<Medicine> medicineList1;
    private medicinelist me;
    DatabaseReference databasemed;
    private DrawerLayout mdrawer;
    private ActionBarDrawerToggle mtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setLogo(R.drawable.menu);
        setContentView(R.layout.activity_doctor);
        NavigationView navigationView=findViewById(R.id.navigationid);
        listView=(ListView)findViewById(R.id.listview);
        databasemed= FirebaseDatabase.getInstance().getReference("medicine");
        medicineList1=new ArrayList<>();
        me=new medicinelist(doctor.this,medicineList1);
        mdrawer=(DrawerLayout)findViewById(R.id.drawerlayout);

        mtoggle=new ActionBarDrawerToggle(this,mdrawer, R.string.Open, R.string.Close);
        mdrawer.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        databasemed.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                medicineList1.clear();
                for(DataSnapshot  dataSnapshot1 : dataSnapshot.getChildren()){
                    Medicine medicine=dataSnapshot1.getValue(Medicine.class);
                    medicineList1.add(medicine);
                }
                listView.setAdapter(me);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {

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
