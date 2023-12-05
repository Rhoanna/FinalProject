package com.example.lab8;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "RSS.db";
    public static final String TABLE_NAME = "RSS_TABLE";
    public static final String COL_ID = "ID";
    public static final String COL_TITLE = "TITLE";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_DATES = "DATE";
    public static final String COL_LINK = "LINK";


    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
     //   setContentView(R.layout.fragment_nav_favourites);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DESCRIPTION TEXT, DATE TEXT, LINK TEXT)" );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData (String title, String description, String date, String link){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_TITLE, title);
        contentValues.put(COL_DESCRIPTION, description);
        contentValues.put(COL_DATES, date);
        contentValues.put(COL_LINK, link);
        long resultFlag  = db.insert(TABLE_NAME, null, contentValues); // -1 is bad
        if(resultFlag==-1)
            return false;
        else
            return true;

    }
// thought of two methods to get data, one using cursor object and one using an array list
    // to see which is easier to display
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;

    }
}

