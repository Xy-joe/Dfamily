package com.lightedcode.family;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JOE on 11/15/2016.
 */
public class Database extends SQLiteOpenHelper {
    private static  final  int Database_version = 1;
    private static final String Database_Name = "data.db";
    private static final String Table_Name = "data";
    private static final String Coloumn_Name = "name";
    private static final String Coloumn_ID = "id";
    private static final String UserName = "uname";
    private static final String Password = "password";
    private static final String Email = "email";
    private static final String Gender = "gender";
    private static final String Pone_Number = "phone";

    SQLiteDatabase db;
    private static final String Table_Create = "create table data (id integer primary key no null, name text not null" +
            "  name text not null, uname text not null, email text not null, password text not null, gender text not null, phone int not null )";

    public Database(Context context){
        super(context, Database_Name, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Table_Create);

    }
    //MEthod for inserting Content in the Database
    public  void insertContact(DataInfo c){
        db = this.getWritableDatabase();   // Makes the database Writable
        ContentValues values = new ContentValues();
        String querry = "select * from data";
        Cursor cursor  = db.rawQuery(querry, null);
        int count = cursor.getCount();

        values.put(Coloumn_ID, count);
        values.put(Coloumn_Name, c.getName());
        values.put(UserName,   c.getUsername());
        values.put(Password, c.getPassword());
        values.put(Pone_Number, c.getPhone());
        values.put(Email, c.getEmail());
        values.put(Gender, c.getGender());

        db.insert(Table_Name, null, values);//String table, null and content value
        db.close();
    }
    public String searchPass(String uname){
        db = this.getReadableDatabase();// We are reading it and not writting it
        String query = "select uname, password from "+ Table_Name;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b = "not found";
        if (cursor.moveToFirst()){
            do {
                a = cursor.getString(0);//uname index = 0
                if (a.equals(uname)){
                    b = cursor.getString(1);//pass index = 1
                    break;
                }
            }while (cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXIT" + Table_Name;
        db.execSQL(query);
        this.onCreate(db);

    }
}
