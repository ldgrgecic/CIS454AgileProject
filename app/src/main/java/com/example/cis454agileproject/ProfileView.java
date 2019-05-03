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

    // References to text view fields on user profile
    private TextView mName;
    private TextView mEmail;
    private TextView mAddress;
    private TextView mDescription;
    private TextView mTimebank;
    private TextView mRating;

    // Reference to db for current user
    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference userRef = rootRef.child("users").child(user.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set to profile view xml file
        setContentView(R.layout.activity_profile_view);

        // reference each field to fill
        mName = findViewById(R.id.cs_nameprof_content);
        mEmail = findViewById(R.id.cs_emailprof_content);
        mAddress = findViewById(R.id.cs_addressprof_content);
        mDescription = findViewById(R.id.cs_descriptionprof_content);
        mTimebank = findViewById(R.id.cs_balanceprof_content);
        mRating = findViewById(R.id.cs_ratingprof_content);

        // Event listener for current user data
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mName.setText(dataSnapshot.child("name").getValue(String.class));
                mEmail.setText(dataSnapshot.child("email").getValue(String.class));
                mAddress.setText(dataSnapshot.child("location").getValue(String.class));
                mDescription.setText(dataSnapshot.child("description").getValue(String.class));
                mTimebank.setText(Float.toString(dataSnapshot.child("timeBank").getValue(Float.class)));
                mRating.setText((Double.toString(dataSnapshot.child("avgRating").getValue(Double.class))));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };

        userRef.addListenerForSingleValueEvent(valueEventListener);

    }


    // return to home screen
    public void returnhome(View v) {
        Intent register = new Intent(this, MainActivity.class);
        startActivity(register);
    }
}