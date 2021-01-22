package com.example.note_tacking.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.note_tacking.Convertor.DateConvertor;
import com.example.note_tacking.Model.NoteEntites;

@Database(entities = {NoteEntites.class},version = 1,exportSchema = false)
@TypeConverters(DateConvertor.class)
public abstract class MyDataBase extends RoomDatabase {
    public static final String DATABASE_NAME="NotesDataBase.db";
    public static volatile MyDataBase instance;
    private static final Object Lock=new Object();
    public abstract NotesDAO notesDAO();
    public static MyDataBase getInstance(Context context)
    {
        if (instance==null)
        {
            synchronized (Lock)
            {
                if (instance==null)
                {
                    instance= Room.databaseBuilder(context.getApplicationContext(),MyDataBase.class,DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
