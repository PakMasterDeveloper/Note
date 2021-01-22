package com.example.note_tacking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note_tacking.EditActivity;
import com.example.note_tacking.Model.NoteEntites;
import com.example.note_tacking.R;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolderClass> {
    private Context context;
    private List<NoteEntites> list;

    public MyRecyclerViewAdapter(Context context, List<NoteEntites> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.note_item_layout,parent,false);
        MyViewHolderClass myViewHolderClass=new MyViewHolderClass(view);
        return myViewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderClass holder, int position) {
        NoteEntites noteEntites=list.get(position);
        holder.Note_Text.setText(noteEntites.getText());
        holder.Note_Edit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EditActivity.class);
                intent.putExtra("Note_ID_Key",noteEntites.getID());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public NoteEntites GetItemAtPosition(int position)
    {
        return list.get(position);
    }
}
