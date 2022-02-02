package com.example.lokdaki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WorkerSignup extends AppCompatActivity implements View.OnClickListener {

    private EditText Wsignupmail, Wsignuppass;
    private TextView Wlogintext;
    private Button Wbuttonsignup;
    private EditText workername, workerphone, profession;

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_signup);
        this.setTitle("Workers Sign Up Page");

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("workers");

        Wsignupmail = findViewById(R.id.workersignupemailId);
        Wsignuppass = findViewById(R.id.workerpasswordId);
        Wlogintext = findViewById(R.id.workerloginmessagetId);
        Wbuttonsignup = findViewById(R.id.workersignupbuttonId);

        workername = findViewById(R.id.workernameId);
        workerphone = findViewById(R.id.workerphoneId);
        profession = findViewById(R.id.professionId);

        Wlogintext.setOnClickListener(this);
        Wbuttonsignup.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.workersignupbuttonId:
                workerRegister();
                break;

            case R.id.workerloginmessagetId:
                Intent Workerloginpage = new Intent (getApplicationContext(),WorkerLogin.class);
                startActivity(Workerloginpage);
                break;
        }

    }

    private void workerRegister() {

        String wEmail = Wsignupmail.getText().toString().trim();
        String wPass = Wsignuppass.getText().toString().trim();

        //checking the validity of the email
        if(wEmail.isEmpty())
        {
            Wsignupmail.setError("Enter an email address");
            Wsignuppass.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(wEmail).matches())
        {
            Wsignupmail.setError("Enter a valid email address");
            Wsignupmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if(wPass.isEmpty())
        {
            Wsignuppass.setError("Enter a password");
            Wsignuppass.requestFocus();
            return;
        }

        if(wPass.length()<8)
        {
            Wsignuppass.setError("Minimum 8 Character password");
            Wsignuppass.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(wEmail,wPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    String wName = workername.getText().toString();
                    String wPhone = workerphone.getText().toString();
                    String wProfession = profession.getText().toString();

                    String key = databaseReference.push().getKey();
                    workers workerinfo = new workers(wName,wPhone,wProfession);
                    databaseReference.child(key).setValue(workerinfo);

                    finish();
                    Intent workerprofile = new Intent(getApplicationContext(),WorkerProfile.class);
                    workerprofile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(workerprofile);

                }
                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"User is already Registered",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


       /* String wName = workername.getText().toString();
        String wPhone = workerphone.getText().toString();
        String wProfession = profession.getText().toString();

        String key = databaseReference.push().getKey();

        workers workerinfo = new workers(wName,wPhone,wProfession);
        databaseReference.child(key).setValue(workerinfo);*/


    }




}