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
import com.demba.mediquick.models.Disease;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateUpdateSymptoms extends AppCompatActivity {
    TextView textView3;
    EditText dis_name,s01,s02,s03,s04,s05,s06,s07,s08,s09,s10;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_update_symptoms);

//        Initialize and declare the variables that are available in the disease management page
        textView3 = findViewById(R.id.textView3);
        dis_name = findViewById(R.id.disease_name);
        s01 = findViewById(R.id.symptom01);
        s02 = findViewById(R.id.symptom02);
        s03 = findViewById(R.id.symptom03);
        s04 = findViewById(R.id.symptom04);
        s05 = findViewById(R.id.symptom05);
        s06 = findViewById(R.id.symptom06);
        s07 = findViewById(R.id.symptom07);
        s08 = findViewById(R.id.symptom08);
        s09 = findViewById(R.id.symptom09);
        s10 = findViewById(R.id.symptom10);
        save = findViewById(R.id.save);

//        Set on click listener for the save button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save();
            }
        });
    }

    private void Save() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("diseases");
        String disease_name = dis_name.getText().toString();
        String symptom01 = s01.getText().toString();
        String symptom02 = s02.getText().toString();
        String symptom03 = s03.getText().toString();
        String symptom04 = s04.getText().toString();
        String symptom05 = s05.getText().toString();
        String symptom06 = s06.getText().toString();
        String symptom07 = s07.getText().toString();
        String symptom08= s08.getText().toString();
        String symptom09 = s09.getText().toString();
        String symptom10 = s10.getText().toString();
        String disease_id = reference.push().getKey();
        if (disease_name.isEmpty() || symptom01.isEmpty() || symptom02.isEmpty() || symptom03.isEmpty()
        || symptom04.isEmpty() || symptom05.isEmpty()){
            dis_name.setError("Please enter disease name and atleast 5 symptoms");
        }else {
            Disease disease = new Disease();
            disease.setDisease_name(disease_name);
            disease.setSymptom01(symptom01);
            disease.setSymptom02(symptom02);
            disease.setSymptom03(symptom03);
            disease.setSymptom04(symptom04);
            disease.setSymptom05(symptom05);
            disease.setSymptom06(symptom06);
            disease.setSymptom07(symptom07);
            disease.setSymptom08(symptom08);
            disease.setSymptom09(symptom09);
            disease.setSymptom10(symptom10);
            disease.setDisease_id(disease_id);

            reference.child(disease_id).setValue(disease);

//            Redirecting user to the next activity
            Intent intent = new Intent(CreateUpdateSymptoms.this, Prescription.class);
            intent.putExtra("disease_name",disease_name);
            intent.putExtra("disease_id",disease_id);
            startActivity(intent);
            Toast.makeText(CreateUpdateSymptoms.this, "Disease and symptoms added successfully", Toast.LENGTH_LONG).show();
        }
    }

}