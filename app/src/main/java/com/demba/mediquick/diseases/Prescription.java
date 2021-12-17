package com.demba.mediquick.diseases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demba.mediquick.R;
import com.demba.mediquick.general.HomeActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Prescription extends AppCompatActivity {
    TextView textView3;
    EditText dis_name,pres1,pres2,pres3;
    Button savePrescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

//        Initialize the variables
        textView3 = findViewById(R.id.textView3);
        dis_name = findViewById(R.id.disease_name);
        pres1 = findViewById(R.id.prescription01);
        pres2 = findViewById(R.id.prescription02);
        pres3 = findViewById(R.id.prescription03);
        savePrescription = findViewById(R.id.savePrescription);

//        Getting values passed by the intent
        dis_name.setText(""+getIntent().getStringExtra("disease_name"));

//        Create the on click listener to the Save Prescription button
        savePrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save();
            }
        });
    }

    private void Save() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("prescriptions");
        String disease_name = dis_name.getText().toString();
        String prescription1 = pres1.getText().toString();
        String prescription2 = pres2.getText().toString();
        String prescription3 = pres3.getText().toString();
        String dis_id = getIntent().getStringExtra("disease_id");
        if (disease_name.isEmpty() || prescription1.isEmpty()){
            pres1.setError("Please enter give atleast one prescription");

        }else {
            com.demba.mediquick.models.Prescription prescription =
                    new com.demba.mediquick.models.Prescription();

            prescription.setDisease_name(disease_name);
            prescription.setPrescription1(prescription1);
            prescription.setPrescription2(prescription2);
            prescription.setPrescription3(prescription3);
            prescription.setDisease_id(dis_id);

            reference.child(dis_id).setValue(prescription);

            //            Redirecting user to the next activity
            Intent intent = new Intent(Prescription.this, HomeActivity.class);
            startActivity(intent);
            Toast.makeText(Prescription.this, "Disease and symptoms added successfully", Toast.LENGTH_LONG).show();

        }


    }
}