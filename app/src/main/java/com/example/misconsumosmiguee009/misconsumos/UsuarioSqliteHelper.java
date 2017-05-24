package com.example.misconsumosmiguee009.misconsumos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by migue on 22/05/17.
 */

public class UsuarioSqliteHelper extends SQLiteOpenHelper {

    String sql ="CREATE TABLE Usuario(Monto Integer, Tipo TEXT,Descripcion TEXT,IngOGas TEXT)";

    public UsuarioSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
