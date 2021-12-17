package com.demba.mediquick.general;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demba.mediquick.diseases.ManageDiseasesActivity;
import com.demba.mediquick.manage_users.ManageUsers;
import com.demba.mediquick.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    DatabaseReference reference;
    String userId;
    TextView speak_doctor,prescription,firstaid;
    String role1="normal"; String role2="doctor"; String role3="admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        Defining the variables
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        speak_doctor = findViewById(R.id.speak_doctor);
        prescription = findViewById(R.id.prescription);
        firstaid = findViewById(R.id.firstaid);

//        Set on click lostener for the prescription textview
        prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,FindPrescription.class);
                startActivity(intent);
            }
        });
//            Toolbar actions
        setSupportActionBar(toolbar);

//        Navigation Drawer setting up
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        Make the navigation items clickable
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent intent1 = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_profile:
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_users:
                Intent a = new Intent(HomeActivity.this, ManageUsers.class);
                startActivity(a);
                break;
            case R.id.nav_diseases:
                Intent b = new Intent(HomeActivity.this, ManageDiseasesActivity.class);
                startActivity(b);
                break;
            case R.id.nav_logout:
                Toast.makeText(this,"Logout",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_terms:
                Intent c = new Intent(HomeActivity.this, Terms.class);
                startActivity(c);
                break;
            case R.id.nav_rate:
                Intent d = new Intent(HomeActivity.this, Rate.class);
                startActivity(d);
                Toast.makeText(this,"Share this application",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_share:
                Intent e = new Intent(HomeActivity.this, Share.class);
                startActivity(e);
                Toast.makeText(this,"Rate this application",Toast.LENGTH_LONG).show();
                break;
        }
//         Hide Items on navidation drawer
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

       reference = FirebaseDatabase.getInstance().getReference()
                .child("user")
                .child(userId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String role = snapshot.child("role").getValue().toString();
                if (role.equals(role1)){
                    Menu menu = navigationView.getMenu();
                    menu.findItem(R.id.nav_users).setVisible(false);
                    menu.findItem(R.id.nav_diseases).setVisible(false);

                }else if (role.equals(role2)){
                    Menu menu = navigationView.getMenu();
                    menu.findItem(R.id.nav_users).setVisible(false); }
                else if (role.equals(role3)){
                    navigationView.getMenu();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return true;
    }
}