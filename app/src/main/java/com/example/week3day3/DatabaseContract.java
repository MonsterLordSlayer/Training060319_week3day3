package com.example.week3day3;

import java.util.Locale;

public class DatabaseContract {
    public static final String DATABASE_NAME="db_AngelKiller";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="celebrity_table";
    public static final String FIELD_NAME="name";
    public static final String FIELD_DOB="dob";
    public static final String FIELD_FAV="fav";


    public static String getCreateTableStatement(){
        return String.format(Locale.US,"CREATE TABLE %s(%s TEXT PRIMARY_KEY, %s TEXT, %s TEXT)",
                TABLE_NAME, FIELD_NAME,FIELD_DOB,FIELD_FAV);

    }
    public static String getSelectOneCelebrityItem(String name){
        return String.format(Locale.US,"SELECT * FROM %s WHERE %s = \"%s\"", TABLE_NAME,FIELD_NAME,name);
    }

    public static String getSelectAllCelebrityItems(){
        return String.format(Locale.US,"SELECT * FROM %s",TABLE_NAME);

    }
    public static String whereClauseForUpdate(String name){
        return String.format(Locale.US,"%s = \"%s\"",FIELD_NAME, name);
    }

}
