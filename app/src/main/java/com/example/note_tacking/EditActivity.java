package com.example.note_tacking;

import android.icu.util.EthiopicCalendar;
import android.os.Bundle;

import com.example.note_tacking.Model.NoteEntites;
import com.example.note_tacking.ViewModel.EditorViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditActivity extends AppCompatActivity {
    private EditorViewModel editorViewModel;
    @BindView(R.id.Edit_Note_Text)
    EditText Edit_Note_Text;
    private boolean aBoolean;
    private boolean isEdit;
    private  CollapsingToolbarLayout toolBarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.check);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        ButterKnife.bind(this);
        if(savedInstanceState!=null)
        {
            isEdit=savedInstanceState.getBoolean("Editing_Key");
        }
        ViewModelInit();

    }

    private void ViewModelInit() {
        Observer<NoteEntites> entitesObserver=new Observer<NoteEntites>() {
            @Override
            public void onChanged(NoteEntites noteEntites) {
                //Less thn api level from 19
                if (noteEntites!=null && !isEdit)
                {
                    Edit_Note_Text.setText(noteEntites.getText());
                }
                //Handle null exception if device api level greater then or equal to 19
//                Edit_Note_Text.setText(Objects.requireNonNull(noteEntites.getText()));
            }
        };
        editorViewModel= new  ViewModelProvider(EditActivity.this).get(EditorViewModel.class);
        editorViewModel.editlist.observe(EditActivity.this,entitesObserver);
        Bundle bundle=getIntent().getExtras();
        if (bundle==null)
        {
            toolBarLayout.setTitle("New Note");
            //Some device support this method but some device support do not this method
//            setTitle("New Note");
            aBoolean=true;
        }
        else
        {
            toolBarLayout.setTitle("Edit Note");
            int note_id=bundle.getInt("Note_ID_Key");
            editorViewModel.ReadNote(note_id);
            aBoolean=false;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean("Editing_Key",true);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!aBoolean)
        {
            getMenuInflater().inflate(R.menu.menu_edit,menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            SaveandExit();
            finish();
            return true;
        }
        else if(item.getItemId()==R.id.Delete_Note)
        {
            DeleteNote();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void DeleteNote() {
        editorViewModel.DeleteNote();
    }

    private void SaveandExit() {
       editorViewModel.SaveData(Edit_Note_Text.getText().toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SaveandExit();
    }
}