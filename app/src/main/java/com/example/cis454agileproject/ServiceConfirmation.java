package com.example.cis454agileproject;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ServiceConfirmation extends AppCompatActivity {

    private RatingBar user_rating;
    private FloatingActionButton confirm;

    private TextView mName, mTitle, mTime, mLocation, mDistance;

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference servRef = rootRef.child("services").child("LdvY-Rd7tPI7vWBl2fC");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceconfirmation);

        mName = findViewById(R.id.cs_review_content);
        mTitle = findViewById(R.id.cs_servicerev_content);
        mTime = findViewById(R.id.cs_timespent_content);
        mLocation = findViewById(R.id.cs_loc_content);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mName.setText(dataSnapshot.child("poster").getValue(String.class));
                mTitle.setText(dataSnapshot.child("title").getValue(String.class));
                mTime.setText(dataSnapshot.child("payment").getValue(String.class));
                mLocation.setText(dataSnapshot.child("location").getValue(String.class));
                //mDistance.setText();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };

        servRef.addListenerForSingleValueEvent(valueEventListener);



        confirm = (FloatingActionButton) findViewById(R.id.fabconf);
        user_rating = (RatingBar) findViewById(R.id.ratingBar);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int stars = user_rating.getNumStars();
                float rating = user_rating.getRating();

                FirebaseDatabase database = FirebaseDatabase.getInstance();

            }
        });


    }


}
