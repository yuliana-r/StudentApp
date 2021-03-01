package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentapp.model.StudentLoginDetail;
import com.example.studentapp.util.CustomToast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText emailId, firstName, lastName, studentId, password;
    Button btnRegister;
    TextView tvSignIn;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailId = findViewById(R.id.editTextEmail);
        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        studentId = findViewById(R.id.editTextStudentID);
        password = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.registerButton);
        tvSignIn = findViewById(R.id.textViewSignIn);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailId.getText().toString();
                final String fName = firstName.getText().toString();
                final String lName = lastName.getText().toString();
                final String sId = studentId.getText().toString();
                final String pwd = password.getText().toString();

                if(email.isEmpty())
                {
                    emailId.setError("Please enter your email address");
                    emailId.requestFocus();
                } else if(fName.isEmpty())
                {
                    firstName.setError("Please enter your first name");
                    firstName.requestFocus();
                } else if(lName.isEmpty())
                {
                    lastName.setError("Please enter your last name");
                    lastName.requestFocus();
                } else if(sId.isEmpty())
                {
                    studentId.setError("Please enter your student ID number");
                    studentId.requestFocus();
                } else if(pwd.isEmpty())
                {
                    password.setError("Please enter your password");
                    password.requestFocus();
                } else if (!(email.isEmpty() && pwd.isEmpty()))
                {
                    mAuth.createUserWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        CustomToast.createToast(MainActivity.this,
                                                "Registration unsuccessful, please try again!"
                                        + task.getException().getMessage(), true);
                                    }
                                    else
                                    {
                                        StudentLoginDetail studentLoginDetail = new StudentLoginDetail(fName, lName, sId);
                                        String userID = task.getResult().getUser().getUid();
                                        firebaseDatabase.getReference(userID).setValue(studentLoginDetail)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Intent intent = new Intent(MainActivity.this,
                                                                HomeActivity.class);
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        intent.putExtra("name", fName+" "+lName);
                                                        startActivity(intent);
                                                    }
                                                });
                                    }
                                }
                            });
                } else {
                    CustomToast.createToast(MainActivity.this, "Error occured!", true);
                }

            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, SignInActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }
}