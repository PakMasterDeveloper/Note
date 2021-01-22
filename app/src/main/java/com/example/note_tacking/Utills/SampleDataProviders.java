package com.example.note_tacking.Utills;

import com.example.note_tacking.Model.NoteEntites;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleDataProviders {
    private static final String SAMPLE_TEXT_1="A simple note";
    private static final String SAMPLE_TEXT_2="A note with a\n Line feed";
    private static final String SAMPLE_TEXT_3="Good Day";
    private static Date getDate(int DiffAmmount)
    {
        GregorianCalendar calendar=new GregorianCalendar();
        calendar.add(Calendar.MILLISECOND,DiffAmmount);
        return calendar.getTime();
    }
    public static List<NoteEntites> GetSampleData()
    {
        List<NoteEntites> list=new ArrayList<>();
        list.add(new NoteEntites(getDate(0),SAMPLE_TEXT_1));
        list.add(new NoteEntites(getDate(-1),SAMPLE_TEXT_2));
        list.add(new NoteEntites(getDate(-2),SAMPLE_TEXT_3));
        return list;
    }
}
