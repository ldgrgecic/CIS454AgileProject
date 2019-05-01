package com.example.cis454agileproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

public class ProfileView extends AppCompatActivity {
    // Firebase auth get current user
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private TextView mName;
    private TextView mEmail;
    private TextView mAddress;
    private TextView mDescription;
    private TextView mTimebank;

    // Reference to db
    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference uidRef = rootRef.child("users").child(user.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        mName = findViewById(R.id.cs_nameprof_content);
        mEmail = findViewById(R.id.cs_emailprof_content);
        mAddress = findViewById(R.id.cs_addressprof_content);
        mDescription = findViewById(R.id.cs_descriptionprof_content);
        mTimebank = findViewById(R.id.cs_balanceprof_content);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mName.setText(dataSnapshot.child("name").getValue(String.class));
                mEmail.setText(dataSnapshot.child("email").getValue(String.class));
                mAddress.setText(dataSnapshot.child("location").getValue(String.class));
                mDescription.setText(dataSnapshot.child("description").getValue(String.class));
                mTimebank.setText(Float.toString(dataSnapshot.child("timeBank").getValue(float.class)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };

        uidRef.addListenerForSingleValueEvent(valueEventListener);

        /*db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot){
                DataSnapshot userData = dataSnapshot.child("users").child(user.getUid());
                mName.setText(userData.child("name").getValue(String.class));
                mEmail.setText(userData.child("email").getValue(String.class));
                mAddress.setText(userData.child("location").getValue(String.class));
                mDescription.setText(userData.child("description").getValue(String.class));
                mTimebank.setText(String.valueOf(userData.child("timebank").getValue(float.class)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });*/
    }



    public void returnhome(View v) {
        Intent register = new Intent(this, MainActivity.class);
        startActivity(register);
    }
}