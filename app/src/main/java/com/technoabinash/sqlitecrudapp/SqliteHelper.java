package com.technoabinash.sqlitecrudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class SqliteHelper extends SQLiteOpenHelper {

    public SqliteHelper(Context context) {
        super(context, ParameterForSql.DB_NAME, null, ParameterForSql.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String queryCreate="CREATE TABLE "+ParameterForSql.TABLE_NAME+"("+
                            ParameterForSql.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                            ParameterForSql.KEY_NAME+" TEXT,"+
                            ParameterForSql.KEY_EMAIL+" TEXT,"+
                            ParameterForSql.KEY_PHONE+" TEXT,"+
                            ParameterForSql.KEY_ADDRESS+" TEXT"+
                                ")";
        Log.e("my database", "onCreate: "+queryCreate);
        database.execSQL(queryCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addUser(User user){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ParameterForSql.KEY_NAME,user.getName());
        values.put(ParameterForSql.KEY_EMAIL,user.getEmail());
        values.put(ParameterForSql.KEY_PHONE,user.getMobile());
        values.put(ParameterForSql.KEY_ADDRESS,user.getAddress());
        database.insert(ParameterForSql.TABLE_NAME,null,values);

    }
    public ArrayList<User> getUser(){
        ArrayList<User> u=new ArrayList<>();
        SQLiteDatabase database=this.getReadableDatabase();
        String query="SELECT * FROM "+ParameterForSql.TABLE_NAME;

        Cursor cursor=database.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                User user=new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setMobile(cursor.getString(3));
                user.setAddress(cursor.getString(4));
                u.add(user);
            }while (cursor.moveToNext());
        }
        return  u;
    }

    public void updateUser(User user) {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ParameterForSql.KEY_NAME,user.getName());
        values.put(ParameterForSql.KEY_EMAIL,user.getEmail());
        values.put(ParameterForSql.KEY_PHONE,user.getMobile());
        values.put(ParameterForSql.KEY_ADDRESS,user.getAddress());
        database.update(ParameterForSql.TABLE_NAME ,values,ParameterForSql.KEY_ID+ " =?",new String[]{String.valueOf(user.getId())});
    }

    public void deletUserByUserId(int id) {
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete(ParameterForSql.TABLE_NAME ,ParameterForSql.KEY_ID+ " =?",new String[]{String.valueOf(id)});

    }
}
