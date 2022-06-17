package com.vodoanhoanglong.sqlite_ex2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Databases extends SQLiteOpenHelper {
    public static final String DB_NAME = "product_database.sqlite";
    public static final int DB_VERSION = 1;

    public static final String TB_NAME = "Product";
    public static final  String COL_ID = "ProductId";
    public static final  String COL_NAME = "ProductName";
    public static final String COL_PRICE = "ProductPrice";

    public Databases(@Nullable Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TB_NAME + " (" + COL_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " VARCHAR(200), " + COL_PRICE + " REAL)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(sqLiteDatabase);
    }

    public void execQuery(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public Cursor getData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return  db.rawQuery(sql, null);
    }

    public int getNumbOfProduct(){
        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_NAME, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void createSomeDefaultProduct(){
        int count = this.getNumbOfProduct();
        if(count == 0){
            execQuery("INSERT INTO " + TB_NAME + " VALUES(null, 'Heineken', 19000)");
            execQuery("INSERT INTO " + TB_NAME + " VALUES(null, 'Tiger', 18000)");
            execQuery("INSERT INTO " + TB_NAME + " VALUES(null, 'Sapporo', 22000)");
        }
    }
}
