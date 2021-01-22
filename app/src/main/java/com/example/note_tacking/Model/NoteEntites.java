package com.example.note_tacking.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class NoteEntites {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    private Date date;
    private String Text;

    public NoteEntites(int ID, Date date, String text) {
        this.ID = ID;
        this.date = date;
        Text = text;
    }

    public NoteEntites(Date date, String text) {
        this.date = date;
        Text = text;
    }
    public NoteEntites() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
