package com.f4csci380.mylist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "MainActivity";

    static ArrayList<Memo> memos;
    RecyclerView memoList;
    static MemoAdapter memoAdapter;
    Button addMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memoList = findViewById(R.id.memoList);
        addMemo = findViewById(R.id.addMemo);

        //Initalize memos with empty arraylist
        memos = new ArrayList<>();
        memoAdapter = new MemoAdapter(this,memos);
        memos.add(new Memo("coos","wasbruh"));
        memos.add(new Memo("coockies","wasgodbruh"));
        memos.add(new Memo("YUURRRRR WE OUT HERE","wasgoodbruh"));

        //set layoutManager and adapter to RecyclerView
        memoList.setLayoutManager(new LinearLayoutManager(this));
        memoList.setAdapter(memoAdapter);

        addMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memos.add(new Memo("New Memo", "New Memo"));
                memoAdapter.notifyDataSetChanged();

            }
        });

        memoAdapter.setOnItemClickListner(new MemoAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v)
            {
                Intent intent = new Intent(getApplicationContext(),ComposeActivity.class);
                intent.putExtra("MemoID",position);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position, View v)
            {
                memos.remove(position);
                memoAdapter.notifyDataSetChanged();
            }
        });

    }
}
