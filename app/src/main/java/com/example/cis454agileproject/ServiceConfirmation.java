package com.example.cis454agileproject;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.google.firebase.database.FirebaseDatabase;

public class ServiceConfirmation extends AppCompatActivity {

    private RatingBar user_rating;
    private FloatingActionButton confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceconfirmation);

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
