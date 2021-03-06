package com.example.cis454agileproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class CreateServiceActivity extends AppCompatActivity {

    // Firebase auth get current user
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    // Variables needed to add to Service object
    private double lat, lng, payment;
    private String address, title;

    // Reference to db
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    private EditText contentTitle, contentPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When FAB is clic
                submitService();
            }
        });

        // Get the lat, lng, and string address from the MapsActivity
        // these indicate the location where the user dropped a pin
        Bundle bundle = getIntent().getExtras();
        lat = bundle.getDouble("lat");
        lng = bundle.getDouble("lng");
        address = bundle.getString("address");

        // Get the views
        // Set the address content TextView to the address that the user selected
        contentTitle = findViewById(R.id.cs_title_content);
        contentPayment = findViewById(R.id.cs_payment_content);
        TextView contentAddress = findViewById(R.id.cs_address_content);
        contentAddress.setText(address);
    }

    // Get the contents of the EditText Fields inputted by the user
    // Error check : Make sure all fields have been entered else return an error
    // Put all data into a Service.java object and add to database
    // Notify the user on success/failure, and redirect to MainActivity
    private void submitService() {
        title = contentTitle.getText().toString();
        String paymentString = contentPayment.getText().toString();

        if (TextUtils.isEmpty(title)) {
            contentTitle.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(paymentString)) {
            contentPayment.setError("Required");
            return;
        }

        payment = Double.parseDouble(paymentString);

        final DatabaseReference servRef = db.child("services");
        final DatabaseReference userRef = db.child("users");

        // value event listener to retrieve current user data
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(user.getUid()).child("timeBank").getValue(Double.class) <= -200){
                    Toast.makeText(getApplicationContext(), "You have not contributed enough to our community, please help others so they may help you!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CreateServiceActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                // Since the mAuth object only contains the uid, we need to cross reference
                // to the actual Users table to get the user's name
                // Then we push all of the data to create a new service
                String name = dataSnapshot.child(user.getUid()).child("name").getValue(String.class);
                Service serv = new Service(user.getUid(), name, title, payment, address);
                String key = servRef.push().getKey();
                serv.setServiceId(key);
                servRef.child(key).setValue(serv);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };

        userRef.addListenerForSingleValueEvent(valueEventListener);


        Toast.makeText(getApplicationContext(), "Title: " + title + " Payment: " + payment, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Service Submitted", Toast.LENGTH_SHORT).show();

        // Send user to main page
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}
