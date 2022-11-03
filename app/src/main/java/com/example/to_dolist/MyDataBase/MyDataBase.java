package com.example.to_dolist.MyDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Struct;

public class MyDataBase extends SQLiteOpenHelper {

    public static final String dbName = "toDoList";

    public static final String tblAccount = "Account";
    public static final String clNameAcc = "nameAcc";
    public static final String clEmailAcc = "emailAcc";
    public static final String clPassAcc = "passAcc";

    public static final String tblUser = "User";
    public static final String clNameUser = "nameUser";
    public static final String clTaskUser = "taskUser";
    public static final String clTaskStatus = "taskStatus";

    public MyDataBase(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + tblAccount +"(idAcc integer primary key autoincrement, " + clNameAcc + " text, " + clEmailAcc + " text, " + clPassAcc + " text);";
        db.execSQL(sql);

        sql = "create table " + tblUser + "(idUser integer primary key autoincrement, " + clNameUser + " text, " + clTaskUser + " text, " + clTaskStatus + " boolean);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tblAccount);
        db.execSQL("DROP TABLE IF EXISTS " + tblUser);
        onCreate(db);
    }
}
