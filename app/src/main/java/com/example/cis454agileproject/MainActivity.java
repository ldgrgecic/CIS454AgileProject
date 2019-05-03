package com.example.cis454agileproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;

/*
 *
 * Home page displayed after user logs in
 * Displays all nearby requests the user can complete
 * User's can create new requests through the FAB
 * Nav Menu links to most app features
 *
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Toolbar, FAB, Drawer, and Nav View
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);                          // set nav drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(this);


        /*RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);

        ServiceAdapter serviceAdapter = new ServiceAdapter(services(30));

        recyclerView.setAdapter(serviceAdapter);*/

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        switch (id) {
            case R.id.nav_profile:
                Intent profview = new Intent(this, ProfileView.class );
                startActivity(profview);
                return true;
            case R.id.nav_create:
                Intent create = new Intent(this, MapsActivity.class);
                startActivity(create);
                return true;
            case R.id.nav_logout:
                Intent logout = new Intent(this, LoginActivity.class );
                mAuth.signOut();
                startActivity(logout);
                return true;


        }
        return true;
    }

    // Reference to db
    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference servRef = rootRef.child("services");
    private DatabaseReference userRef = rootRef.child("users");

    private List<Service> services(int size){
        final List<Service> result = new ArrayList<Service>();

            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for ( DataSnapshot servSnapshot : dataSnapshot.getChildren()){
                        Service service = new Service();

                        service.setPoster(servSnapshot.child("poster").getValue(String.class));
                        service.setTitle(servSnapshot.child("title").getValue(String.class));
                        service.setLocation(servSnapshot.child("location").getValue(String.class));
                        service.setPayment(servSnapshot.child("payment").getValue(double.class));

                        result.add(service);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            };

            servRef.addListenerForSingleValueEvent(valueEventListener);

        return result;
    }

}