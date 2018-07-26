package com.example.hp.dbexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

public class VivzDatabaseAdapter {
    private ArrayList<Integer> Id = new ArrayList<>();
    private ArrayList<String> Name = new ArrayList<>();
    private ArrayList<String> Password = new ArrayList<>();
    VivzHelper helper;
    Context context;

    public VivzDatabaseAdapter(Context context)
    {
        this.context=context;
        helper=new VivzHelper(context);
    }

    public long insertData(String name, String password) {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(VivzHelper.NAME, name);
        contentValues.put(VivzHelper.PASSWORD, password);
       long id= db.insert(VivzHelper.TABLE_NAME , null, contentValues);
       return id;
    }
    public String getAllData() {
        SQLiteDatabase db=helper.getWritableDatabase();
        String[] columns={VivzHelper.UID, VivzHelper.NAME, VivzHelper.PASSWORD};
        Cursor cursor= db.query(VivzHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while (cursor.moveToNext()) {
            int index1=cursor.getColumnIndex(VivzHelper.UID);
            int index2=cursor.getColumnIndex(VivzHelper.NAME);
            int index3=cursor.getColumnIndex(VivzHelper.PASSWORD);
            int cid= cursor.getInt(index1);
            String name=cursor.getString(index2);
            String password=cursor.getString(index3);
            buffer.append(cid+" "+name+" "+password+"\n");
        }
        return buffer.toString();
    }

    public String getData(String name, String password) {
        SQLiteDatabase db=helper.getWritableDatabase();
        String[] columns={VivzHelper.UID};
        String[] selectionArgs={name, password};
        Cursor cursor= db.query(VivzHelper.TABLE_NAME, columns, VivzHelper.NAME+ "=? AND "+VivzHelper.PASSWORD+"=? ", selectionArgs, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while (cursor.moveToNext()) {
            int index1=cursor.getColumnIndex(VivzHelper.UID);
            int personId= cursor.getInt(index1);
            buffer.append(personId+"\n");
        }
        return buffer.toString();
    }
    public int updateName(String oldName, String newName) {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(VivzHelper.NAME, newName);
        String[] whereArgs={oldName};
        int count=db.update(VivzHelper.TABLE_NAME, values, VivzHelper.NAME+"=?", whereArgs);
        return count;
    }

    public int deleteRow() {
        SQLiteDatabase db=helper.getWritableDatabase();
        String[] whereArgs={"hunny"};
        int count=db.delete(VivzHelper.TABLE_NAME, VivzHelper.NAME+"=?", whereArgs);
        return count;
    }
    public Cursor getDetails() {
        SQLiteDatabase db=helper.getReadableDatabase();
        String selectQuery="SELECT * FROM "+VivzHelper.TABLE_NAME;
        Cursor cursor=db.rawQuery(selectQuery,null);
        while (cursor.moveToNext()) {
            int index1=cursor.getColumnIndex(VivzHelper.UID);
            int index2=cursor.getColumnIndex(VivzHelper.NAME);
            int index3=cursor.getColumnIndex(VivzHelper.PASSWORD);
            Id.add(cursor.getInt(index1));
             Name.add(cursor.getString(index2));
             Password.add(cursor.getString(index3));
        }
        return cursor;
    }

   static class VivzHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME="vivzdatabase";
        private static final String TABLE_NAME="VIVZTABLE";
        private static final int DATABASE_VERSION=6;
        private static final String UID="_id";
        private static final String NAME="Name";
        private static final String PASSWORD="Password";
        private static final String CREATE_TABLE="Create table "+TABLE_NAME+ "("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" TEXT," +
                PASSWORD+" TEXT);";
        private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;
        public VivzHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                Message.message(context, "onCreate called");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                Message.message(context, "onUpgrade called");
                onCreate(db);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
