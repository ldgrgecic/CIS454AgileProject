package com.example.cis454agileproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    Button signin;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private ProgressBar progressBar;

   private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        //connect to database
        mAuth = FirebaseAuth.getInstance();

        initializeUI();

        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                loginUserAccount();
            }
        });

    }

    public void loginUserAccount(){
        //when login is processing, show progress bar
        progressBar.setVisibility(View.VISIBLE);

        //information variables
        String email, password;
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();

        if(TextUtils.isEmpty(email)){
            //if email is empty prompt user
            Toast.makeText(getApplicationContext(), "Please enter an email...", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //if password is empty prompt user
            Toast.makeText(getApplicationContext(), "Please enter a password...", Toast.LENGTH_LONG).show();
            return;
        }

        //authorize user information with database
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //if user is found send to main page
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            //if user is not found do nothing and promt to reenter information
                            Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }

    //collect input datafields from user and assign them to usable variables
    public void initializeUI(){
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);

        signin = findViewById(R.id.email_sign_in_button);
        progressBar = findViewById(R.id.login_progress);
    }

    //when login button is pressed send to main page
    public void login(View v) {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }

    //if register button is pressed send to
    public void register(View v) {
        Intent register = new Intent(this, RegistrationActivity.class );
        startActivity(register);
    }
}