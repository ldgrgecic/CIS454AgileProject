package com.example.cis454agileproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    FloatingActionButton register;
    EditText  regName, regEmail, regAddress, regPassword, regDescription;
    //ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        initializeUI();

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                registerNewUser();
            }
        });
    }

    public void registerNewUser(){
        //progressBar.setVisibility(View.VISIBLE);

        String email, password, name, address, description;
        email = regEmail.getText().toString();
        password = regPassword.getText().toString();

        //System.out.println(email);
        //System.out.println(password);

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Please enter an email...", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Please enter a password...", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            DatabaseReference userRef = db.child("users");
                            User user = new User(regName.getText().toString(), regEmail.getText().toString(), regPassword.getText().toString(), regDescription.getText().toString(), regAddress.getText().toString());
                            userRef.child(mAuth.getCurrentUser().getUid()).setValue(user);


                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                            //progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }

    private void initializeUI(){
        regName = findViewById(R.id.cs_name_content);
        regEmail = findViewById(R.id.cs_email_content);
        regAddress = findViewById(R.id.cs_addressreg_content);
        regPassword = findViewById(R.id.cs_password_content);
        regDescription = findViewById(R.id.cs_description_content);

        register = findViewById(R.id.fab);
        //progressBar = findViewById(R.id.register_progress);
    }



    public void confirmreg(View v) {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }
}

