package com.demba.mediquick.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demba.mediquick.R;
import com.demba.mediquick.general.HomeActivity;
import com.demba.mediquick.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextView alreadyHaveAccount;
    EditText inputEmail,inputPassword,inputConfirmPassword;
    Button register;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth fAuth;
    FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        Declare the variables available in the register page

        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        register = findViewById(R.id.register);
        progressDialog = new ProgressDialog(this);
        fAuth=FirebaseAuth.getInstance();
        fUser=fAuth.getCurrentUser();

//        make a fullscreen sized page

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

  // set on click listener to the already have an account link

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

//        Set on click listener to the register button

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//      method to perform the authentication
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
        String email =inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        if (!email.matches(emailPattern)){
            inputEmail.setError("Please enter a correct email");
            inputEmail.requestFocus();
        }else if (password.isEmpty()){
            inputPassword.setError("Please input your password");
        }else if (password.length()<8){
            inputPassword.setError("Ensure your password contains at least 8 characters");
        }else if (!password.equals(confirmPassword)){
            inputConfirmPassword.setError("The Passwords do not match");
        }else{
            progressDialog.setMessage("Please wait while registering ...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
//                        Saving the users data into the database
                        User user = new User();
                        user.setName(email.substring(0, email.indexOf("@")));
                        user.setEmail(email);
                        user.setProfile_image("");
                        user.setRole("normal");
                        user.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());

                        FirebaseDatabase.getInstance().getReference()
                                .child("user")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user);


//                        Redirecting the user to the home screen
                        sendUserToNextActivity();
                        Toast.makeText(RegisterActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void sendUserToNextActivity() {
        Intent intent=new Intent(RegisterActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}