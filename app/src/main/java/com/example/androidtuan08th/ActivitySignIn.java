package com.example.androidtuan08th;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivitySignIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String email,password;
    private EditText edtEmail, edtPass;
    private Button btnSign;
    private  AlertDialog.Builder dg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtEmail = findViewById(R.id.editTextEmail);
        edtPass = findViewById(R.id.editTextPassword);
        btnSign = findViewById(R.id.buttonSignIn1);


        dg = new AlertDialog.Builder(this);
        LayoutInflater inflater	= this.getLayoutInflater();
        View dgView = inflater.inflate(R.layout.dialog,null);
        Button btnFinish = dgView.findViewById(R.id.buttonFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dg.setView(dgView);

        mAuth = FirebaseAuth.getInstance();


        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });



    }

    private void login() {
        email = edtEmail.getText().toString();
        password = edtPass.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            dg.show();
                        } else {
                            Toast.makeText(ActivitySignIn.this, "Email or password incorrect",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}