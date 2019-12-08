package com.f4csci380.mylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class LoginActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    EditText emailAddress;
    EditText password;
    Button signIn;
    Button registerBtn;
    final static String TAG = "Login Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailAddress = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        signIn = findViewById(R.id.signInBtn);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = emailAddress.getText().toString();
                String p = password.getText().toString();

                if(e.isEmpty())
                {
                    emailAddress.setError("Please enter a valid email");
                    emailAddress.requestFocus();
                }
                else if(p.isEmpty())
                {
                    password.setError("Please enter a valid password");
                    password.requestFocus();
                }
                else if(e.isEmpty() && p.isEmpty())
                {
                    Toast.makeText(LoginActivity.this,"Enter a Email and Password please",Toast.LENGTH_LONG);
                }
                else if(!(e.isEmpty() && p.isEmpty()))
                {
                    mAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (!task.isSuccessful())
                            {
                                try {
                                    throw task.getException();
                                }
                                // if user enters wrong email.
                                catch (FirebaseAuthInvalidUserException invalidEmail) {
                                    Toast.makeText(LoginActivity.this,"Email does not Exist",Toast.LENGTH_LONG);
                                    Log.d(TAG, "onComplete: invalid_email");

                                    // TODO: take your actions!
                                }
                                // if user enters wrong password.
                                catch (FirebaseAuthInvalidCredentialsException wrongPassword) {
                                    Toast.makeText(LoginActivity.this,"Wrong Password",Toast.LENGTH_LONG);
                                    Log.d(TAG, "onComplete: wrong_password");

                                    // TODO: Take your action
                                } catch (Exception e) {
                                    Toast.makeText(LoginActivity.this,"Something broke on our end. Sorry for the trouble",Toast.LENGTH_LONG);
                                    Log.d(TAG, "onComplete: " + e.getMessage());
                                }
                            }
                            if(task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this,"You have logged in!!",Toast.LENGTH_LONG);
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                        }
                    });
                }
            }
        });
    }
}
