package com.example.note_tacking.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.note_tacking.AppRepository.MyRepositor;
import com.example.note_tacking.Model.NoteEntites;
import com.example.note_tacking.Utills.SampleDataProviders;

import java.util.ArrayList;
import java.util.List;

public class ListActivityViewModel extends AndroidViewModel {
    public LiveData<List<NoteEntites>> m_List;
    private MyRepositor myRepositor;

    public ListActivityViewModel(@NonNull Application application) {
        super(application);
        myRepositor=MyRepositor.getInstance(application.getApplicationContext());
        m_List=myRepositor.mList;
    }
    public void AddSampleData()
    {
        myRepositor.AddSampleData();
    }

    public void DeleteAllNotes() {
        myRepositor.DeleteAllNotes();
    }

    public void DeleteNote(NoteEntites getItemAtPosition) {
        myRepositor.DeleteNote(getItemAtPosition);
    }
}
