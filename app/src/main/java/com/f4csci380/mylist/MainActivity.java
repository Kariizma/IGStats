package com.f4csci380.mylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    ArrayList<Memo> memos;
    RecyclerView memoList;
    MemoAdapter memoAdapter;
    Button addMemo;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memoList = findViewById(R.id.memoList);
        addMemo = findViewById(R.id.addMemo);
        memoAdapter = new MemoAdapter(this,memos);
        memoList.setAdapter(memoAdapter);
    }
}
