package com.petruciostech.auxiliardeleitura.bancodados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "bancoDeLivros";
    private  static final int VERSAO_BANCO = 2;


    public Helper(@Nullable Context context){
        super (context, NOME_BANCO, null, VERSAO_BANCO);
        Log.d("Helper", "Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Helper", "Create");
        String sqlConficuracao = "CREATE TABLE IF NOT EXISTS biblioteca(" +
                "_id INTEGER PRIMARY  KEY AUTOINCREMENT," +
                "titulo VARCHAR(255), " +
                "autor VARCHAR(255)," +
                "paginas INTEGER," +
                "pagParou INTEGER);";

        String sqlConficuracao2 = "CREATE TABLE IF NOT EXISTS estante(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo VARCHAR(255)," +
                "autor VARCHAR(255)," +
                "paginas INTEGER," +
                "pagParou INTEGER," +
                "comeco BIGINT);";
        db.execSQL(sqlConficuracao);
        db.execSQL(sqlConficuracao2);

        this.onUpgrade(db, 1, VERSAO_BANCO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Helper", "Upgrade");
    }
}
