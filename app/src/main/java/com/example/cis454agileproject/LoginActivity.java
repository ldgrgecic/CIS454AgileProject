package com.example.cis454agileproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{




    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.


        mPasswordView = (EditText) findViewById(R.id.password);
    }
    public void login(View v) {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }

    public void register(View v) {
        Intent register = new Intent(this, RegistrationActivity.class );
        startActivity(register);
    }
}
