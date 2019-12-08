package com.f4csci380.mylist;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class ComposeActivity extends AppCompatActivity {

    EditText title;
    EditText body;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        title = findViewById(R.id.title);
        body = findViewById(R.id.body);
    }
}
