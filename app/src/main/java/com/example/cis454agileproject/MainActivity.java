package com.example.cis454agileproject;

import android.content.Intent;
import android.os.Bundle;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
 *
 * Home page displayed after user logs in
 * Displays all nearby requests the user can complete
 * User's can create new requests through the FAB
 * Nav Menu links to most app features
 *
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    //List<Service> services = new ArrayList<Service>(30);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);

        ServiceAdapter serviceAdapter = new ServiceAdapter(services(30));

        recyclerView.setAdapter(serviceAdapter);

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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        // TODO: add functionality to nav item buttons
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
                startActivity(logout);
                return true;


        }
        return true;
    }

    private List<Service> services(int size){
        List<Service> result = new ArrayList<Service>();
        for(int i = 1; i <= size; i++){
            Service service = new Service();

            service.setPoster("testPoster");
            service.setTitle("testTitle");
            service.setLocation("testLocation");
            service.setPayment(40);

            result.add(service);
        }

        return result;
    }

}