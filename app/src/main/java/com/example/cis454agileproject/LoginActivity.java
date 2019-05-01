package com.example.cis454agileproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;
import com.google.firebase.database.ChildEventListener;

import java.net.Authenticator;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // UI references.
    Button signin;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.email_sign_in_button);

        signin.setOnClickListener(this);}

            //@Override
        public void onClick(View v){

            Intent intent1 =  new Intent(LoginActivity.this, MainActivity.class);
            Intent intent2 = new Intent(LoginActivity.this, RegistrationActivity.class );
            switch(v.getId()){
                case R.id.email_sign_in_button:

                    String email = mEmailView.getText().toString();
                    String password = mPasswordView.getText().toString();
                    final User user = new User(email, password);

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(user.getEmail()).exists()){
                                if(dataSnapshot.child(user.getPassword()).exists()){
                                    Toast.makeText(LoginActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(LoginActivity.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    startActivity(intent1);

                break;

                case R.id.register_button:
                    startActivity(intent2);
                    break;

            }
        }
    /*public void login(View v) {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }

    public void register(View v) {
        Intent register = new Intent(this, RegistrationActivity.class );
        startActivity(register);
    }*/
}