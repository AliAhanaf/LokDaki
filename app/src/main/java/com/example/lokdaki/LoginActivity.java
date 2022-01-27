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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText loginmail, loginpass;
    private TextView signuptext;
    private Button buttonlogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Login Page");

        mAuth = FirebaseAuth.getInstance();

        loginmail = findViewById(R.id.loginEmailid);
        loginpass = findViewById(R.id.loginPasswordid);
        signuptext = findViewById(R.id.signupMessageid);
        buttonlogin = findViewById(R.id.loginbuttonid);

        signuptext.setOnClickListener(this);
        buttonlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.loginbuttonid:
                employerlogin();
                break;

            case R.id.signupMessageid:
                Intent signuppage = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(signuppage);
                break;
        }
    }

    private void employerlogin() {
        String Email = loginmail.getText().toString().trim();
        String Pass = loginpass.getText().toString().trim();

        //checking the validity of the email
        if(Email.isEmpty())
        {
            loginmail.setError("Enter an email address");
            loginpass.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            loginmail.setError("Enter a valid email address");
            loginmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if(Pass.isEmpty())
        {
            loginpass.setError("Enter a password");
            loginpass.requestFocus();
            return;
        }

        if(Pass.length()<8)
        {
            loginpass.setError("Minimum 8 Character password");
            loginpass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent employerprofiles = new Intent(getApplicationContext(),EmployerProfile.class);
                    employerprofiles.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(employerprofiles);
                }
                
                else
                {
                    Toast.makeText(getApplicationContext(), "login unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


}