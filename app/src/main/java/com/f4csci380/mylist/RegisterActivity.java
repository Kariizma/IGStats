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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    EditText email;
    EditText password;
    EditText emailConfirm;
    EditText passwordConfirm;
    Button createAccount;
    final static String TAG = "Register Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        emailConfirm = findViewById(R.id.EmailConfirm);
        passwordConfirm = findViewById(R.id.PasswordConfirm);
        createAccount = findViewById(R.id.createAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String e = email.getText().toString();
                String p = password.getText().toString();
                String eC = emailConfirm.getText().toString();
                String pC = passwordConfirm.getText().toString();

                if(e.isEmpty() || p.isEmpty() || eC.isEmpty() || pC.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this,"Please Enter all Required Fields",Toast.LENGTH_LONG);
                }
                else if(e.compareTo(eC) != 0)
                {
                    Toast.makeText(RegisterActivity.this,"Email and Confirm Email dont match",Toast.LENGTH_LONG);
                }
                else if(p.compareTo(pC) != 0)
                {
                    Toast.makeText(RegisterActivity.this,"Password and Confirm Password dont match",Toast.LENGTH_LONG);
                }
                else if(e.equals(eC) && p.equals(pC))
                {
                    mAuth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
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
                                    Toast.makeText(RegisterActivity.this,invalidPassword.getReason(),Toast.LENGTH_LONG);
                                    Log.d("Register Activity", "Invalid Password");
                                }
                                catch (FirebaseAuthInvalidCredentialsException invalidEmail)
                                {
                                    Toast.makeText(RegisterActivity.this,"Something is wrong with your Email",Toast.LENGTH_LONG);
                                    Log.d("Register Activity","Invalid Email //Malformed");
                                }
                                catch (FirebaseAuthUserCollisionException existEmail)
                                {
                                    Toast.makeText(RegisterActivity.this,"Email already Exist",Toast.LENGTH_LONG);
                                    Log.d("Register Activity","Email already exist");
                                }
                                catch (Exception e)
                                {
                                    Log.e("Register Activity", "something wrong" + e.getMessage());
                                }
                            }
                            if(task.isSuccessful())
                            {
                                Toast.makeText(RegisterActivity.this,"Account Created",Toast.LENGTH_LONG);
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            }
                        }
                    });
                }

            }
        });


    }
}
