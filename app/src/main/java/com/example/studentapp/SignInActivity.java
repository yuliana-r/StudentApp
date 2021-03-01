package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.model.StudentLoginDetail;
import com.example.studentapp.util.CustomToast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    EditText emailId, password;
    Button btnSignIn;
    TextView tvRegister;

    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        emailId = findViewById(R.id.editTextEmailSignIn);
        password = findViewById(R.id.editTextPasswordSignIn);
        btnSignIn = findViewById(R.id.signInButton);
        tvRegister = findViewById(R.id.textViewRegister);

        fDatabase = FirebaseDatabase.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                if(mFirebaseUser!=null){
                    moveToHomeActivity(mFirebaseUser);
                }
                else
                {
                    CustomToast.createToast(SignInActivity.this, "Please sign in", false);
                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();

                if(email.isEmpty()) {
                    emailId.setError("Please enter your email address");
                    emailId.requestFocus();
                }else if(pwd.isEmpty()) {
                    password.setError("Please enter your password");
                    password.requestFocus();
                }else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "Email and password not provided",
                            Toast.LENGTH_LONG).show();
                }else if(!(email.isEmpty() && pwd.isEmpty())) {
                    fAuth.signInWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(SignInActivity.this,
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(!task.isSuccessful()) {
                                                Toast.makeText(SignInActivity.this, "Login unsuccessful, please try again",
                                                        Toast.LENGTH_LONG).show();
                                            } else {
                                                moveToHomeActivity(task.getResult().getUser());
                                            }
                                        }
                                    });
                } else {
                    Toast.makeText(SignInActivity.this, "Error occured!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intRegister = new Intent(SignInActivity.this, MainActivity.class);
                intRegister.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intRegister);
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(mAuthStateListener);
    }

    private void moveToHomeActivity(FirebaseUser mFirebaseUser) {
        fDatabase.getReference().child(mFirebaseUser.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        StudentLoginDetail studentLoginDetail = snapshot.getValue(StudentLoginDetail.class);
                        String name = studentLoginDetail.getFirstName()+" "+studentLoginDetail.getSurname();
                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                        CustomToast.createToast(getApplicationContext(), "Login successful", false);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.putExtra("name", name);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}