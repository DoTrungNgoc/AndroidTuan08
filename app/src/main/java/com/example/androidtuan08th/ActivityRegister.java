package com.example.androidtuan08th;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityRegister extends AppCompatActivity {
    private Button btnSignIn;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPass;
    private EditText edtPassRe;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        edtPassRe = findViewById(R.id.edtPassRe);
        btnSignIn = findViewById(R.id.btnRegister);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()){
                    mAuth = FirebaseAuth.getInstance();
                    String name = edtName.getText().toString();
                    String email = edtEmail.getText().toString();
                    String pass = edtPass.getText().toString();

                    mAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(ActivityRegister.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        User userNew = new User(name,email,"","");

                                        FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).setValue(userNew);

                                        Toast.makeText(ActivityRegister.this, "Create account successfull: " + user.getEmail(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.d("tag",task.toString());
                                        Toast.makeText(ActivityRegister.this, "Create account fail: " + task.toString(),
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                }
            }
        });
    }

    private boolean checkData(){
        if (edtName.getText().length()==0){
            edtName.setError("Please enter your name");
            return false;
        }
        if (edtEmail.getText().length()==0){
            edtEmail.setError("Please enter you enail");
            return false;
        }
        if (edtPass.getText().length()==0){
            edtPass.setError("Please enter your password");
            return false;
        }
        if (!edtPass.getText().toString().equalsIgnoreCase(edtPassRe.getText().toString())){
            edtPassRe.setError("Please verify password again");
            return false;
        }

        return true;

    }

}