package com.example.to_dolist.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.to_dolist.Model.Account;
import com.example.to_dolist.MyDataBase.MyDataBase;
import com.example.to_dolist.signInScr;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QLAccount {
    SQLiteDatabase db;


    public QLAccount(Context context) {
        MyDataBase myDB = new MyDataBase(context);
        db = myDB.getWritableDatabase();
    }

//    Check UserName và Email đã tồn tại hay chưa.
//    Nếu chưa tồn tại -> Tạo Account mới.
//    Nếu đã tồn tại -> Thông báo để User sử dụng tên khác.
    public int themAccount(Account account){
        int i = -1;
        ContentValues values = new ContentValues();
        String userName = account.getNameAcc();
        String userEmail = account.getEmailAcc();
        if(checkValidAccount(userName, userEmail) == 0){
            values.put("nameAcc", account.getNameAcc());
            values.put("emailAcc", account.getEmailAcc());
            values.put("passAcc", account.getPassAcc());
            i = (int) db.insert("Account", null, values);
        }
        if(i >= 0){
            return 1;
        } else {
            return -1;
        }
    }

    public int checkValidAccount(String name, String email){
        ArrayList<Account> listAcc = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Account", null);
        if(cursor.moveToFirst()){
            do{
                String userName = cursor.getString(1);
                String userEmail = cursor.getString(2);
                if((userName.equals(name)) && (userEmail.equals(email))){
                    Account account = new Account(name, email);
                    listAcc.add(account);
                }
            }   while (cursor.moveToNext());
        }
        return listAcc.size();
    }

//    Check xem Account đã tồn tại chưa
//    Nếu đã tồn tại -> LogIn.
//    Nếu chưa tồn tại -> Thông báo để User đăng ký tài khoản.
    public int checkAccount(Account account){
        String name = account.getNameAcc();
        String pass = account.getEmailAcc();
        ArrayList<Account> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Account", null);
        if(cursor.moveToFirst()){
            do{
                String userName = cursor.getString(1);
                String userPass = cursor.getString(3);
                if(userName.equals(name) && userPass.equals(pass)){
                    list.add(account);
                }
            }   while (cursor.moveToNext());
        }
        return list.size();
    }
}
