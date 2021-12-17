package com.demba.mediquick.diseases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.demba.mediquick.R;
import com.demba.mediquick.adapters.DiseaseAdapter;
import com.demba.mediquick.general.HomeActivity;
import com.demba.mediquick.manage_users.ManageUsers;
import com.demba.mediquick.models.Disease;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageDiseasesActivity extends AppCompatActivity {
    FloatingActionButton actionButton;
    RecyclerView recyclerView;
    DatabaseReference reference;
    DiseaseAdapter diseaseAdapter;
    ArrayList<Disease> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_diseases);
//        Declaring variables
            recyclerView = findViewById(R.id.disease_list);
            reference = FirebaseDatabase.getInstance().getReference("diseases");
            recyclerView.hasFixedSize();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            list = new ArrayList<>();
            diseaseAdapter = new DiseaseAdapter(this,list);
            recyclerView.setAdapter(diseaseAdapter);

//            Adding value event listener
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    Disease disease = snapshot1.getValue(Disease.class);
                    list.add(disease);
                }
                diseaseAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

//        Setting the action for the floating action button
        actionButton = findViewById(R.id.action_button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageDiseasesActivity.this, CreateUpdateSymptoms.class);
                startActivity(intent);
            }
        });
    }
}