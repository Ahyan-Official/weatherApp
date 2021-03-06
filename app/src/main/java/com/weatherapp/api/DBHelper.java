package com.weatherapp.api;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, Contants.DB_NAME, null, Contants.DB_VERSION);
    }

    //WHEN TB IS CREATED
    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(Contants.CREATE_TB);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    //UPGRADE TB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS"+ Contants.TB_NAME);
        onCreate(db);
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        String clearDBQuery = "DELETE FROM " + Contants.TB_NAME;
        db.execSQL(clearDBQuery);
    }
}
