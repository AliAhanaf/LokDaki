package com.example.lokdaki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class WorkerLogin extends AppCompatActivity implements View.OnClickListener{

    private EditText workerloginmail, workerloginpass;
    private TextView workersignupmessage;
    private Button workerloginbtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_login);
        this.setTitle("Worker Login Page");

        mAuth = FirebaseAuth.getInstance();

        workerloginmail = findViewById(R.id.workerloginemailId);
        workerloginpass = findViewById(R.id.workerloginpasswordId);
        workersignupmessage = findViewById(R.id.workersignupessageId);
        workerloginbtn = findViewById(R.id.workerloginbuttonId);

        workersignupmessage.setOnClickListener(this);
        workerloginbtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.workerloginbuttonId:
                Workerlogin();
                break;

            case R.id.workersignupessageId:
                Intent workersignuppage = new Intent(getApplicationContext(),WorkerSignup.class);
                startActivity(workersignuppage);
                break;
        }
    }

    private void Workerlogin() {
        String Email = workerloginmail.getText().toString().trim();
        String Pass = workerloginpass.getText().toString().trim();

        //checking the validity of the email
        if(Email.isEmpty())
        {
            workerloginmail.setError("Enter an email address");
            workerloginpass.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            workerloginmail.setError("Enter a valid email address");
            workerloginmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if(Pass.isEmpty())
        {
            workerloginpass.setError("Enter a password");
            workerloginpass.requestFocus();
            return;
        }

        if(Pass.length()<8)
        {
            workerloginpass.setError("Minimum 8 Character password");
            workerloginpass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent workerprofiles = new Intent(getApplicationContext(),WorkerProfile.class);
                    workerprofiles.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(workerprofiles);
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "login unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }





}