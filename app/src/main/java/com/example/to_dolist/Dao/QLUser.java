package com.example.to_dolist.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.to_dolist.MainActivity2;
import com.example.to_dolist.Model.User;
import com.example.to_dolist.MyDataBase.MyDataBase;

import java.util.ArrayList;

public class QLUser {
    SQLiteDatabase db;


    public QLUser(Context context) {
        MyDataBase myDB = new MyDataBase(context);
        db = myDB.getWritableDatabase();
    }

    //  Them task vào bảng User
    public int themTask(User user) {
        int i = -1;
        ContentValues values = new ContentValues();
        values.put("nameUser", user.getNameUser());
        values.put("taskUser", user.getTaskUser());
        values.put("taskStatus", user.isTaskStatus());
        i = (int) db.insert("User", null, values);
        if (i >= 0) {
            return 1;
        } else {
            return -1;
        }
    }

    //    Lấy ra danh sách các task
    public ArrayList<User> getAllTask(String name) {
        ArrayList<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from User", null);
        if (cursor.moveToFirst()) {
            do {
                String userName = cursor.getString(1);
                String taskName = cursor.getString(2);
                boolean taskStatus = cursor.getString(3).equals("1");
                if (userName.equals(name)) {
                    User user = new User(taskName, taskStatus);
                    list.add(user);
                }
            } while (cursor.moveToNext());
        }
        return list;
    }

    public int xoaTask(String taskName) {
        int i = db.delete("User", "taskUser=?", new String[]{taskName + ""});
        return i;
    }
}