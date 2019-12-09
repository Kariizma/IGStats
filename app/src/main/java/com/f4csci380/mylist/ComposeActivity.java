package com.f4csci380.mylist;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashSet;

import static com.f4csci380.mylist.MainActivity.memoAdapter;
import static com.f4csci380.mylist.MainActivity.memos;

public class ComposeActivity extends AppCompatActivity {

    EditText title;
    EditText body;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        title = findViewById(R.id.title);
        body = findViewById(R.id.body);
        saveButton = findViewById(R.id.saveBtn);

        Intent intent = getIntent();
        final int memoID = intent.getIntExtra("MemoID",-1);

        if(memoID != -1)
        {
            Memo memo = memos.get(memoID);
            String getTitle = memo.getTitle();
            String getBody = memo.getBody();
            title.setText(getTitle);
            body.setText(getBody);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                memos.get(memoID).setTitle(title.getText().toString());
                memos.get(memoID).setBody(body.getText().toString());
                memoAdapter.notifyDataSetChanged();
            }
        });


    }
}
