package com.example.note_tacking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.note_tacking.Adapter.MyRecyclerViewAdapter;
import com.example.note_tacking.Model.NoteEntites;
import com.example.note_tacking.Utills.SampleDataProviders;
import com.example.note_tacking.ViewModel.ListActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    ListActivityViewModel listActivityViewModel;
    @BindView(R.id.Recycler_Vew)
    RecyclerView Recycler_View;
    @OnClick(R.id.AddButton)
    void OnButton()
    {
        Intent intent=new Intent(MainActivity.this,EditActivity.class);
        startActivity(intent);
    }
    private List<NoteEntites> arrayList=new ArrayList<>();
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewModelInit();
        ButterKnife.bind(this);
        RecyclerViewInit();

    }
    private void ViewModelInit()
    {
        Observer<List<NoteEntites>> observer=new Observer<List<NoteEntites>>() {
            @Override
            public void onChanged(List<NoteEntites> list) {
                arrayList.clear();
                arrayList.addAll(list);
                if(myRecyclerViewAdapter==null)
                {
                    myRecyclerViewAdapter=new MyRecyclerViewAdapter(MainActivity.this,arrayList);
                    Recycler_View.setAdapter(myRecyclerViewAdapter);
                }
                else
                {
                    myRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        };
        listActivityViewModel=new ViewModelProvider(MainActivity.this).get(ListActivityViewModel.class);
        listActivityViewModel.m_List.observe(MainActivity.this,observer);
    }
    private void  RecyclerViewInit()
    {
        Recycler_View.hasFixedSize();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        Recycler_View.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(Recycler_View.getContext(),linearLayoutManager.getOrientation());
        Recycler_View.addItemDecoration(dividerItemDecoration);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                DeleteNote(myRecyclerViewAdapter.GetItemAtPosition(viewHolder.getAdapterPosition()));
            }
        });
        itemTouchHelper.attachToRecyclerView(Recycler_View);
    }

    private void DeleteNote(NoteEntites getItemAtPosition) {
        listActivityViewModel.DeleteNote(getItemAtPosition);
        Toast.makeText(this, "Note Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.Add_Sample_Data:
                AddSampleData();
                return true;
            case R.id.Delete_All_Data:
                DeleteAllNotes();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void AddSampleData()
    {
        listActivityViewModel.AddSampleData();
    }
    private void DeleteAllNotes()
    {
        listActivityViewModel.DeleteAllNotes();
    }
}