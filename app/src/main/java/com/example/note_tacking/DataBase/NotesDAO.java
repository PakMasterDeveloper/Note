package com.example.note_tacking.DataBase;

import android.icu.text.Replaceable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.note_tacking.Model.NoteEntites;

import java.util.List;

@Dao
public interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void NoteInsert(NoteEntites noteEntites);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAllNotes(List<NoteEntites> list);
    @Delete
    void DeleteNote(NoteEntites noteEntites);
    @Query("select * from noteentites where ID=:id")
    NoteEntites GetSpecificNote(int id);
    @Query("select * from noteentites order by date desc")
    LiveData<List<NoteEntites>> GetAllNotes();
    @Query("delete from noteentites")
    int DeleteAllNotes();
    @Query("select count(*) from noteentites" )
    int GetCount();
}
