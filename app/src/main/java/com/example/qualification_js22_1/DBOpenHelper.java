package com.example.qualification_js22_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    private static final String DB_NAME = "Database.db";

    public final static String CARTS = "Carts",
            NAME = "name",
            PRICE = "price",
            ID = "id",
            QTY = "quantity",
            IMG = "image";


    public final static String TRANSACTIONS = "Transactions",
            T_ID = "t_id",
            T_TOTAL = "t_total",
            T_TIME = "t_time";


    private static DBOpenHelper db = null;

    public DBOpenHelper(@Nullable Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    public static DBOpenHelper getInstance(Context context)
    {
        return db = (db == null ) ? new DBOpenHelper(context) : db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ CARTS +
                "("  +
                ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                NAME + " TEXT NOT NULL," +
                PRICE + " INTEGER NOT NULL," +
                QTY + " INTEGER NOT NULL," +
                IMG + " INTEGER NOT NULL)"
                );

        db.execSQL("CREATE TABLE "+ TRANSACTIONS +
                "("  +
                T_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                T_TOTAL + " INTEGER NOT NULL," +
                T_TIME + " TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
