package com.f4csci380.mylist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "MainActivity";

    static ArrayList<Memo> memos;
    RecyclerView memoList;
    static MemoAdapter memoAdapter;
    Button addMemo;
    Button saveMemoList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memoList = findViewById(R.id.memoList);
        addMemo = findViewById(R.id.addMemo);
        saveMemoList = findViewById(R.id.saveMemoList);

        //Initalize memos with empty arraylist
        loadData();
        memoAdapter = new MemoAdapter(this,memos);

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

        saveMemoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(memos);
                editor.putString("tasklist",json);
                editor.apply();
            }
        });
    }

    private void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tasklist",null);
        Type type = new TypeToken<ArrayList<Memo>>() {}.getType();
        memos = gson.fromJson(json,type);

        if(memos == null)
        {
            memos = new ArrayList<>();
        }
    }
}
