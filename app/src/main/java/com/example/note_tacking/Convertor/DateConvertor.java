package com.example.note_tacking.Convertor;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConvertor {
    @TypeConverter
    public static Long TimesStamp(Date date)
    {
        return date==null?null:date.getTime();
    }
    @TypeConverter
    public static Date DateStamp(Long TimeStamp)
    {
        return TimeStamp==null?null:new Date(TimeStamp);
    }
}
