package com.example.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 5/20/17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    // constant for Database Name
    private static final String DATABASE_NAME = "habits.db";
    // Database version value
    private static final int DATABASE_VERSION = 1;

    // string for creating habit table

    private static final String CREATE_TABLE = "CREATE TABLE " + HabitsContract.HabitsEntry.TABLE_NAME+ "(" +
            HabitsContract.HabitsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HabitsContract.HabitsEntry.HABIT_NAME + " TEXT, " +
            HabitsContract.HabitsEntry.HABIT_RESOURCE + " TEXT, " +
            HabitsContract.HabitsEntry.HABIT_TIME_SPENT + " INTEGER, " +
            HabitsContract.HabitsEntry.HABIT_MODULE_NAME + " TEXT, " +
            HabitsContract.HabitsEntry.HABIT_IS_MODULE_FINISHED + " INTEGER);";


    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /**
     * method to insert data into habit tracker
     * @param habitName name of habit
     * @param habitResource source of habit module done today(eg:youtube videos)
     * @param timeSpent number of hours spent today on Habit module
     * @param modulename this one is habit module(eg:learning to cut wood in your carpenter habit)
     * @param isModuleDone integer to denote is module finished
     *                     1 - done
     *                     0 - not done
     * @return id id of the newly inserted row
     */
    public long insertHabitAttributes(String habitName , String habitResource , int timeSpent ,
                                      String modulename , int isModuleDone){
        SQLiteDatabase mSqLiteDatabase = this.getWritableDatabase();
        // content values to fill date
        ContentValues mContentValues = new ContentValues();
        mContentValues.put(HabitsContract.HabitsEntry.HABIT_NAME , habitName);
        mContentValues.put(HabitsContract.HabitsEntry.HABIT_RESOURCE , habitResource );
        mContentValues.put(HabitsContract.HabitsEntry.HABIT_TIME_SPENT , timeSpent);
        mContentValues.put(HabitsContract.HabitsEntry.HABIT_MODULE_NAME , modulename);
        mContentValues.put(HabitsContract.HabitsEntry.HABIT_IS_MODULE_FINISHED , isModuleDone);

        long id  =mSqLiteDatabase.insert(HabitsContract.HabitsEntry.TABLE_NAME , null , mContentValues);

        return id;
    }


    /**
     * method queries from db through Cursor
     * @return mCursor returns the cursor object
     */
    public Cursor getHabitsLIst(){
        List<HabitData> mHabitDataList = new ArrayList<>();
        SQLiteDatabase mSqLiteDatabase = this.getReadableDatabase();

        // create projection to get the needed data from table
        String Projection[] = {
                HabitsContract.HabitsEntry._ID ,
                HabitsContract.HabitsEntry.HABIT_NAME ,
                HabitsContract.HabitsEntry.HABIT_RESOURCE ,
                HabitsContract.HabitsEntry.HABIT_TIME_SPENT ,
                HabitsContract.HabitsEntry.HABIT_MODULE_NAME ,
                HabitsContract.HabitsEntry.HABIT_IS_MODULE_FINISHED
        };

        // cursor to represent the data selected from habit table
        Cursor mCursor = mSqLiteDatabase.query(
                HabitsContract.HabitsEntry.TABLE_NAME ,
                Projection ,
                null ,
                null ,
                null ,
                null ,
                null
        );

        while(mCursor.moveToNext()){

            // extracting data from cursor
            String habitName = mCursor.getString(mCursor.getColumnIndex(HabitsContract.HabitsEntry.HABIT_NAME));
            String habitResource = mCursor.getString(mCursor.getColumnIndex(HabitsContract.HabitsEntry.HABIT_RESOURCE));
            int habitTimeSpent = mCursor.getInt(mCursor.getColumnIndex(HabitsContract.HabitsEntry.HABIT_TIME_SPENT));
            String habitModuleName = mCursor.getString(mCursor.getColumnIndex(HabitsContract.HabitsEntry.HABIT_MODULE_NAME));
            int habitIsModuleDone = mCursor.getInt(mCursor.getColumnIndex(HabitsContract.HabitsEntry.HABIT_IS_MODULE_FINISHED));

            // making the habit data
            HabitData mHabitData = new HabitData(
                    habitName , habitResource , habitTimeSpent , habitModuleName , habitIsModuleDone);
            // add the habit row into list
            mHabitDataList.add(mHabitData);
        }
        mSqLiteDatabase.close();
        mCursor.close();

        return mCursor;
    }
}
