package com.example.week3day3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.week3day3.DatabaseContract.DATABASE_NAME;
import static com.example.week3day3.DatabaseContract.DATABASE_VERSION;
import static com.example.week3day3.DatabaseContract.FIELD_DOB;
import static com.example.week3day3.DatabaseContract.FIELD_FAV;
import static com.example.week3day3.DatabaseContract.FIELD_NAME;
import static com.example.week3day3.DatabaseContract.TABLE_NAME;
import static com.example.week3day3.DatabaseContract.whereClauseForUpdate;
import static com.example.week3day3.DatabaseContract.DATABASE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseContract.getCreateTableStatement());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public void insertCelebrity(Celebrity celebrityToInsert){
        ContentValues contentValues=new ContentValues();
        contentValues.put(FIELD_NAME, celebrityToInsert.getName());
        contentValues.put(FIELD_DOB,celebrityToInsert.getDob());
        contentValues.put(FIELD_FAV, celebrityToInsert.getFav());
        SQLiteDatabase writeableDatabase=this.getWritableDatabase();

        writeableDatabase.insert(TABLE_NAME,null,contentValues);
        writeableDatabase.close();
    }
    public Celebrity queryForOneMusicRecord(String name){
        SQLiteDatabase readableDatabase=this.getReadableDatabase();
        Celebrity returnCelebrity=null;
        Cursor cursor=readableDatabase.rawQuery(DatabaseContract.getSelectOneCelebrityItem(name),null);
        if (cursor.moveToFirst()){
            String nameFromDB=cursor.getString(cursor.getColumnIndex(FIELD_NAME));
            String dobFromDB=cursor.getString(cursor.getColumnIndex(FIELD_DOB));
            String favFromDB=cursor.getString(cursor.getColumnIndex(FIELD_FAV));
            returnCelebrity=new Celebrity(nameFromDB,dobFromDB,favFromDB);
        }
        readableDatabase.close();
        return returnCelebrity;
    }
    public ArrayList<Celebrity> queryForAllCelebrityRecords(){
        SQLiteDatabase readableDatabase=this.getReadableDatabase();
        ArrayList<Celebrity> returnCelebrityList=null;
        Cursor cursor=readableDatabase.rawQuery(DatabaseContract.getSelectAllCelebrityItems(),null);
        if (cursor.moveToFirst()){
            returnCelebrityList=new ArrayList<>();
            do {
                Celebrity returnCelebrity=null;
                String nameFromDB=cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                String dobFromDB=cursor.getString(cursor.getColumnIndex(FIELD_DOB));
                String favFromDB=cursor.getString(cursor.getColumnIndex(FIELD_FAV));
                returnCelebrity=new Celebrity(nameFromDB,dobFromDB,favFromDB);
                returnCelebrityList.add(returnCelebrity);

            } while(cursor.moveToNext());
        }
        readableDatabase.close();
        return returnCelebrityList;
    }

    public void updateCelebrity(Celebrity celebrity){
        SQLiteDatabase writableDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FIELD_NAME, celebrity.getName());
        contentValues.put(FIELD_DOB, celebrity.getDob());

        contentValues.put(FIELD_FAV, celebrity.getFav());
        writableDatabase.update(TABLE_NAME, contentValues,whereClauseForUpdate(celebrity.getName()),null );
        writableDatabase.close();

    }
    public void updateCelebrity(Celebrity celebrity, String name){
        SQLiteDatabase writableDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_DOB, celebrity.getDob());

        contentValues.put(FIELD_FAV, celebrity.getFav());
        writableDatabase.update(TABLE_NAME, contentValues,whereClauseForUpdate(celebrity.getName()),null );
        writableDatabase.close();

    }
    public void deleteCelebrity(String name){
        SQLiteDatabase writableDatabase=this.getWritableDatabase();
        writableDatabase.delete(TABLE_NAME,whereClauseForUpdate(name),null);
        writableDatabase.close();
    }

}
