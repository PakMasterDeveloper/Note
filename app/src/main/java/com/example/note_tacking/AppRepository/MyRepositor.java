package com.example.note_tacking.AppRepository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.note_tacking.DataBase.MyDataBase;
import com.example.note_tacking.Model.NoteEntites;
import com.example.note_tacking.Utills.SampleDataProviders;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyRepositor {
    private static  MyRepositor ourInstance;
    public LiveData<List<NoteEntites>> mList;
    private MyDataBase myDataBase;
    //Only run one thread bacground for one opration
    private Executor executor=Executors.newSingleThreadExecutor();
    public static MyRepositor getInstance(Context context)
    {
        return ourInstance=new MyRepositor(context);
    }
    private MyRepositor(Context context)
    {
        myDataBase=MyDataBase.getInstance(context);
        mList= GetAllNotes();
    }
    public void AddSampleData()
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                myDataBase.notesDAO().InsertAllNotes(SampleDataProviders.GetSampleData());
            }
        });
    }
    private LiveData<List<NoteEntites>> GetAllNotes()
    {
        return myDataBase.notesDAO().GetAllNotes();
    }

    public void DeleteAllNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                int result= myDataBase.notesDAO().DeleteAllNotes();
                Log.e("Error","Successful"+result);
            }
        });
    }

    public NoteEntites ReadNote(int note_id) {
        return myDataBase.notesDAO().GetSpecificNote(note_id);
    }


    public void UpdateNote(NoteEntites noteEntites) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                myDataBase.notesDAO().NoteInsert(noteEntites);
            }
        });
    }

    public void DeleteNote(NoteEntites value) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                myDataBase.notesDAO().DeleteNote(value);
            }
        });
    }
}
