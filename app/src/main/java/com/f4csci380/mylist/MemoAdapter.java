package com.f4csci380.mylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<Memo> memo;

    public MemoAdapter(Context context, ArrayList<Memo> memo)
    {
        this.context = context;
        this.memo = memo;
    }

    @NonNull
    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_listitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoAdapter.ViewHolder holder, int position)
    {
        holder.listitem.setText(memo.get(position).getTitle());
        /*holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TODO: open composeactivity to edit the text of this memo
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return memo.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView listitem;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            listitem = itemView.findViewById(R.id.listItem);
            linearLayout = itemView.findViewById(R.id.LL);
        }
    }
}
