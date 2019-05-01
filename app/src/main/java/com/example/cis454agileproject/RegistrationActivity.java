package com.example.cis454agileproject;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;
import com.google.firebase.database.ChildEventListener;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    Button register;
    EditText  regName, regEmail, regAddress, regPassword, regDescription;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regName = (EditText) findViewById(R.id.cs_name_content);
        regEmail = (EditText) findViewById(R.id.cs_email_content);
        regAddress = (EditText) findViewById(R.id.cs_addressreg_content);
        regPassword = (EditText) findViewById(R.id.cs_password_content);
        regDescription = (EditText) findViewById(R.id.cs_description_content);

        register.setOnClickListener(this); }

        @Override
        public void onClick(View v){
            final Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            switch(v.getId()) {

                case R.id.fab:

                    String email = regEmail.getText().toString();
                    String password = regPassword.getText().toString();
                    String name = regName.getText().toString();
                    String address = regAddress.getText().toString();
                    String description = regDescription.getText().toString();
                    final User user = new User(name, email, password, description, address);

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(user.getEmail()).exists()) {
                                Toast.makeText(RegistrationActivity.this, "The Email is already Exist!", Toast.LENGTH_SHORT).show();
                            } else {
                                myRef.child(user.getEmail()).setValue(user);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                break;
            }



        }



    /*public void confirmreg(View v) {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }*/
}

