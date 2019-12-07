package com.f4csci380.mylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    RecyclerView memoList;
    Button addMemo;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memoList = findViewById(R.id.memoList);
        addMemo = findViewById(R.id.addMemo);
    }
}
