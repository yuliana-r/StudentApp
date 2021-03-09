package com.example.studentapp;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailEt, fNameEt, lNameEt, sIdEt, passwordEt1, passwordEt2;
    private Button registerButton;
    private TextView signInTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        firebaseAuth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.editTextEmail);
        fNameEt = findViewById(R.id.editTextFirstName);
        lNameEt = findViewById(R.id.editTextLastName);
        sIdEt = findViewById(R.id.editTextStudentID);
        passwordEt1 = findViewById(R.id.editTextPassword);
        passwordEt2 = findViewById(R.id.editTextPassword2);
        registerButton = findViewById(R.id.registerButton);
        progressDialog = new ProgressDialog(this);
        signInTv = findViewById(R.id.textViewSignIn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        signInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private void Register() {
        String email = emailEt.getText().toString();
        String fName = fNameEt.getText().toString();
        String lName = lNameEt.getText().toString();
        String sId = sIdEt.getText().toString();
        String password1 = passwordEt1.getText().toString();
        String password2 = passwordEt2.getText().toString();
        if(TextUtils.isEmpty(email)) {
            emailEt.setError("Please enter your email address");
            return;
        }
        else if(TextUtils.isEmpty(fName)) {
            fNameEt.setError("Please enter your first name");
            return;
        }
        else if(TextUtils.isEmpty(lName)) {
            lNameEt.setError("Please enter your last name");
            return;
        }
        else if(TextUtils.isEmpty(sId)) {
            sIdEt.setError("Please enter your student ID");
            return;
        }
        else if(TextUtils.isEmpty(password1)) {
            passwordEt1.setError("Please enter your password");
            return;
        }
        else if(TextUtils.isEmpty(password2)) {
            passwordEt2.setError("Please confirm your password");
            return;
        }
        else if(!password1.equals(password2)) {
            passwordEt2.setError("Passwords don't match");
            return;
        }
        else if(password1.length()<8) {
            passwordEt1.setError("Password should be at least 8 characters long");
            return;
        }
        else if(!isValidEmail(email)) {
            emailEt.setError("Invalid email address");
            return;
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Registration unsuccessful, please try again", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    private Boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
