package com.f4csci380.mylist;

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


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailAddress = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        signIn = findViewById(R.id.signInBtn);

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
                    mAuth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                           if(!task.isSuccessful())
                           {
                               try
                               {
                                   throw task.getException();
                               }
                               catch (FirebaseAuthWeakPasswordException invalidPassword)
                               {
                                   Toast.makeText(LoginActivity.this,invalidPassword.getReason(),Toast.LENGTH_LONG);
                                   Log.d("Register Activity", "Invalid Password");
                               }
                               catch (FirebaseAuthInvalidCredentialsException invalidEmail)
                               {
                                   Toast.makeText(LoginActivity.this,"Something is wrong with your Email",Toast.LENGTH_LONG);
                                   Log.d("Register Activity","Invalid Email //Malformed");
                               }
                               catch (FirebaseAuthUserCollisionException existEmail)
                               {
                                   Toast.makeText(LoginActivity.this,"Email already Exist",Toast.LENGTH_LONG);
                                   Log.d("Register Activity","Email already exist");
                               }
                               catch (Exception e)
                               {
                                   Log.e("Register Activity", "something wrong" + e.getMessage());
                               }
                           }
                        }
                    });
                }

            }
        });
    }
}
