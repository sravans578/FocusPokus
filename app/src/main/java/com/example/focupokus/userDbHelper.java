package com.example.focupokus;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.database.Cursor;

import java.util.ArrayList;

public class userDbHelper extends SQLiteOpenHelper
{


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Score.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private SQLiteDatabase database;
    private String[] ScoreColumns = { UserScore.UserScoreEntry.COLUMN_USER_NAME,
            UserScore.UserScoreEntry.COLUMN_SCORE};

    private static final String SQL_CREATE_SCORE_TABLE=
            "CREATE TABLE IF NOT EXISTS " + UserScore.UserScoreEntry.TABLE_NAME + " (" +
                    UserScore.UserScoreEntry.COLUMN_USER_NAME + " TEXT_TYPE " + COMMA_SEP +
                    UserScore.UserScoreEntry.COLUMN_SCORE + INTEGER_TYPE +")";

    private static final String SQL_DELETE_SCORE_TABLET =
            "DROP TABLE IF EXISTS " + UserScore.UserScoreEntry.TABLE_NAME;

    public userDbHelper(Context context)
    {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_SCORE_TABLE);


    }

    public void addScoreInformation(String user,Integer score,SQLiteDatabase db)
    {
        ContentValues values = new ContentValues();
        values.put(UserScore.UserScoreEntry.COLUMN_USER_NAME,user);
        values.put(UserScore.UserScoreEntry.COLUMN_SCORE,score);
        long insertId= db.insert(UserScore.UserScoreEntry.TABLE_NAME, null,values);

    }

    public ArrayList<Integer> getAllScore(SQLiteDatabase db)
    {
        ArrayList<Integer> scoreList = new ArrayList<Integer>();

        Cursor cursor = db.query(UserScore.UserScoreEntry.TABLE_NAME,
                ScoreColumns,null,null,null,null, null);



        while (cursor.moveToNext())
        {
            String name =cursor.getString(0);
            Integer score =cursor.getInt(1);



        }
        cursor.close();

        return scoreList;
    }
    public ArrayList<UserScoreBean> getTopScore(SQLiteDatabase db)
    {
        ArrayList<UserScoreBean> scoreList = new ArrayList<UserScoreBean>();

        Cursor cursor = db.rawQuery("select distinct user_score , user_name from user_score_info order by user_score desc limit 3;",null);

        while (cursor.moveToNext())
        {
            UserScoreBean bean =new UserScoreBean();
            Integer score =cursor.getInt(0);
            String name =cursor.getString(1);
            bean.setName(name);
            bean.setScore(score);
            scoreList.add(bean);

        }
        cursor.close();

        return scoreList;
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_SCORE_TABLET);
        onCreate(db);
    }



}
