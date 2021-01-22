package com.example.note_tacking.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note_tacking.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyViewHolderClass extends RecyclerView.ViewHolder {
    @BindView(R.id.Note_Text)
    TextView Note_Text;
    @BindView(R.id.Note_Edit_Button)
    FloatingActionButton Note_Edit_Button;
    public MyViewHolderClass(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
