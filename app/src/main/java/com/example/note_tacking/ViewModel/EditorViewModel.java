package com.example.note_tacking.ViewModel;

import android.app.Application;
import android.provider.ContactsContract;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.note_tacking.AppRepository.MyRepositor;
import com.example.note_tacking.Model.NoteEntites;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel {
    public  MutableLiveData<NoteEntites> editlist=new MutableLiveData<>();
    private MyRepositor myRepositor;
    private Executor executor= Executors.newSingleThreadExecutor();
    public EditorViewModel(@NonNull Application application) {
        super(application);
        myRepositor=MyRepositor.getInstance(application.getApplicationContext());
    }



    public void ReadNote(int note_id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                NoteEntites noteEntites=myRepositor.ReadNote(note_id);
                editlist.postValue(noteEntites);
            }
        });
    }

    public void SaveData(String text) {
        NoteEntites update=editlist.getValue();
        if(update==null)
        {
            if(TextUtils.isEmpty(text.trim()))
            {
                return;
            }
            else
            {
                update=new NoteEntites(new Date(),text.trim());
            }
        }
        else
        {
            update.setDate(new Date());
            update.setText(text.trim());
        }
        myRepositor.UpdateNote(update);

    }

    public void DeleteNote() {
        myRepositor.DeleteNote(editlist.getValue());
    }
}
