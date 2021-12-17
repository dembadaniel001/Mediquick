package com.demba.mediquick.general;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demba.mediquick.R;
import com.demba.mediquick.models.Prescription;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FindPrescription extends AppCompatActivity {
    EditText edt_search;
    ImageButton btn_search;
    RecyclerView prescription_list;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_prescription);

//        Declaring and defining the variables
        edt_search = findViewById(R.id.search);
        btn_search = findViewById(R.id.btn_search);
        prescription_list = findViewById(R.id.prescription_list);
        prescription_list.setHasFixedSize(true);
        prescription_list.setLayoutManager(new LinearLayoutManager(this));
        reference = FirebaseDatabase.getInstance().getReference().child("prescriptions");

//        Defining the functions of the search button
//                Adding on click listener to the search button
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = edt_search.getText().toString();
                Search(search);
            }
        });
    }

    public void Search(String search) {
        Query userSearchQuery = reference
                .orderByChild("disease_name")
                .startAt(search).endAt(search + "\uf8ff");
        FirebaseRecyclerOptions<Prescription> options = new FirebaseRecyclerOptions
                .Builder<Prescription>()
                .setQuery(userSearchQuery,Prescription.class)
                .build();
        FirebaseRecyclerAdapter<Prescription, PrescriptionViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Prescription, PrescriptionViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PrescriptionViewHolder holder, int position,
                                            @NonNull Prescription model) {
            holder.dname.setText(""+model.getDisease_name());
            holder.prescription_01.setText(""+model.getPrescription1());
            holder.prescription_02.setText(""+model.getPrescription2());
            holder.prescription_03.setText(""+model.getPrescription3());
            }

            @NonNull
            @Override
            public PrescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.prescription_list, parent,false);
               PrescriptionViewHolder viewHolder = new PrescriptionViewHolder(v);
                return viewHolder;
            }
        };
        firebaseRecyclerAdapter.startListening();
        prescription_list.setAdapter(firebaseRecyclerAdapter);
    }

    public static class PrescriptionViewHolder extends RecyclerView.ViewHolder {
        TextView prescription_01, prescription_02, prescription_03,dname;
        EditText edt_search;
        ImageButton btn_search;
        View view;

        public PrescriptionViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            prescription_01 = itemView.findViewById(R.id.prescription_01);
            prescription_02 = itemView.findViewById(R.id.prescription_02);
            prescription_03 = itemView.findViewById(R.id.prescription_03);
            dname = itemView.findViewById(R.id.dname);
            btn_search = itemView.findViewById(R.id.btn_search);
            edt_search = itemView.findViewById(R.id.search);
        }
    }
}