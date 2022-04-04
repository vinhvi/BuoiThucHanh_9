package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataUser extends SQLiteOpenHelper {
    public DataUser(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="CREATE TABLE user (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL)";
        db.execSQL(sql);
    }
//add user
    public void addUser(com.example.myapplication.User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",user.getName());

        db.insert("user",null,values);

    }
    public List<com.example.myapplication.User> getAll(){
        List<com.example.myapplication.User> userList = new ArrayList<>();
        String sql = "select * from user";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                com.example.myapplication.User user = new com.example.myapplication.User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                userList.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }
    public int removeUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete("User","ID = ?",new String[] {String.valueOf(id)} );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
