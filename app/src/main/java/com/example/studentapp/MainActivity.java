package com.example.studentapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private EditText emailEt, passwordEt;
    private Button signInButton;
    private TextView registerTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.editTextEmailSignIn);
        passwordEt = findViewById(R.id.editTextPasswordSignIn);
        signInButton = findViewById(R.id.signInButton);
        progressDialog = new ProgressDialog(this);
        registerTv = findViewById(R.id.textViewRegister);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void Login() {
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        if(TextUtils.isEmpty(email)) {
            emailEt.setError("Please enter your email address");
            return;
        }
        else if(TextUtils.isEmpty(password)) {
            passwordEt.setError("Please enter your password");
            return;
        }

        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "Login unsuccessful, please try again", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    private Boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    }


