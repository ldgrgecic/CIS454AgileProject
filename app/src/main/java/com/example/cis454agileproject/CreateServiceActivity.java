package com.example.cis454agileproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class CreateServiceActivity extends AppCompatActivity {

    // Firebase auth
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
        // TODO: once login functionality is added, put into Service object and add to database

        DatabaseReference servRef = db.child("services");
        Service serv = new Service(user.getDisplayName(), title, payment, address);
        servRef.setValue(serv);


        Toast.makeText(getApplicationContext(), "Title: " + title + " Payment: " + payment, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Service Submitted", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}
