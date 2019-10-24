package com.e.homework2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){
        super(context, "Login.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE user  (firstname text, lastname text, username text PRIMARY KEY, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
    }
    public boolean insert(String firstname, String lastname, String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname",firstname);
        contentValues.put("lastname",lastname);
        contentValues.put("username",username);
        contentValues.put("password",password);

        long ins = db.insert("user", null, contentValues);
        if (ins==-1) return false;

        else  return true;

    }
    public boolean chkemail(String username){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from user where username=?", new String[]{username});
        if (cursor.getCount()>0) return  false;
        else return true;
    }

    public boolean emailpassword(String username, String password){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from user where username=? and password=?" , new String[]{username,password});
        if (cursor.getCount()>0) return  true;
        else return false;
    }
    public Cursor SelectUsersData(){
        SQLiteDatabase market = this.getReadableDatabase();
        Cursor rows = market.rawQuery(
                "SELECT firstname,lastname, username, password FROM user",null);
        return rows;
    }
    public void eliminar(String username){
        SQLiteDatabase db=this.getWritableDatabase();



    }
}
