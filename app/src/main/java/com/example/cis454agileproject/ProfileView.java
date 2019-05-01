package com.example.cis454agileproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ChildEventListener;

public class ProfileView extends AppCompatActivity {
    //Firebase auth get current user
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private TextView mName;
    private TextView mEmail;
    private TextView mAddress;
    private TextView mDescription;
    private TextView mTimebank;

    // Reference to db
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        mName = findViewById(R.id.cs_nameprof_content);
        mEmail = findViewById(R.id.cs_emailprof_content);
        mAddress = findViewById(R.id.cs_addressprof_content);
        mDescription = findViewById(R.id.cs_descriptionprof_content);
        mTimebank = findViewById(R.id.cs_balanceprof_content);


        DatabaseReference userData = db.child(user.getUid());
        String name = (userData.child("name")).toString();
        String email = (userData.child("email")).toString();
        String address = (userData.child("address")).toString();
        String description = (userData.child("description")).toString();
        String timebank = (userData.child("timebank")).toString();

        mName.setText(name);
        mEmail.setText(email);
        mAddress.setText(address);
        mDescription.setText(description);
        mTimebank.setText(timebank);
    }


    public void returnhome(View v) {
        Intent register = new Intent(this, MainActivity.class);
        startActivity(register);
    }
}