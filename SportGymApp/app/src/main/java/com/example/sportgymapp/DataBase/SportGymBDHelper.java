package com.example.sportgymapp.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SportGymBDHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "SportGymDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Livros";

    public SportGymBDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }







    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
