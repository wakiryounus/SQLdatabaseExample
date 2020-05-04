package com.example.sqldatabaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactsDatabase {
    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_NAME = "person_name";
    public static final String KEY_CELL = "_cell";

    private final String DATABASE_NAME = "ContactsDataBase";
    private final String DATABASE_TABLE = "ContactsTable";
    private final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDataBase;

    ContactsDatabase(Context context){
        ourContext = context;
    }
    private class DBHelper extends SQLiteOpenHelper{

        public DBHelper( Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            /*
                CREATE TABLE ContactsTable (_id INTEGER PRIMARY KEY AUTOINCREMENT,
                person_name TEXT NOT NULL, _cell TEXT NOT NULL);
            */
            String sqlCode = "CREATE TABLE "+DATABASE_TABLE+" ("
                    +KEY_ROW_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +KEY_NAME+" TEXT NOT NULL, "
                    +KEY_CELL+" TEXT NOT NULL);";

            db.execSQL(sqlCode);
        }
    }
    public ContactsDatabase open() throws SQLException {
        ourHelper = new DBHelper(ourContext);
        ourDataBase = ourHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        ourHelper.close();
    }
    public long createEntry(String name, String cell){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_CELL, cell);
        return  ourDataBase.insert(DATABASE_TABLE, null, contentValues);
    }

    public String getData(){
        String[] columns = new String[]{KEY_ROW_ID, KEY_NAME, KEY_CELL};
        Cursor cursor = ourDataBase.query(DATABASE_TABLE, columns, null, null, null,null,null);
        String result = "";
        int iRowID = cursor.getColumnIndex(KEY_ROW_ID);
        int iName = cursor.getColumnIndex(KEY_NAME);
        int iCell = cursor.getColumnIndex(KEY_CELL);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            result = result + cursor.getString(iRowID)+" : " +cursor.getString(iName)+" : "+cursor.getString(iCell)+" \n";
        }
        cursor.close();
        return result;
    }

    public long deleteEntry(String rowId){
        return ourDataBase.delete(DATABASE_TABLE, KEY_ROW_ID+"=?", new String[]{rowId});
    }
}
