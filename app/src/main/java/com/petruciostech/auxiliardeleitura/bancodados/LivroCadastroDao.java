package com.petruciostech.auxiliardeleitura.bancodados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.petruciostech.auxiliardeleitura.classeobjeto.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroCadastroDao {
    private Helper helperBC;
    private SQLiteDatabase dataBank;

    public LivroCadastroDao(Context context){
        helperBC = new Helper(context);
        dataBank = helperBC.getWritableDatabase();

    }

    public long create(Livro livro){
        ContentValues valores = new ContentValues();
        valores.put("titulo", livro.getTitulo());
        valores.put("autor", livro.getAutor());
        valores.put("paginas", livro.getPaginas());
        valores.put("pagParou", livro.getPagParou());
        valores.put("comeco", 0);
        return dataBank.insert("estante", null, valores);
    }

    public List<Livro> read(){
        List<Livro> Mostrar = new ArrayList<>();
        Cursor cursor = dataBank.query("estante", new String[]{"_id", "titulo", "autor", "paginas", "pagParou", "comeco"},
                null, null, null, null, null);

        while(cursor.moveToNext()){
            Livro livro = new Livro();
            livro.set_id(cursor.getInt(0));
            livro.setTitulo(cursor.getString(1));
            livro.setAutor(cursor.getString(2));
            livro.setPaginas(cursor.getInt(3));
            livro.setPagParou(cursor.getInt(4));
            livro.setComeco(cursor.getLong(5));
            Mostrar.add(livro);
        }

        return Mostrar;
    }

    public void update(Livro livro){
        ContentValues values = new ContentValues();
        values.put("titulo", livro.getTitulo());
        values.put("autor", livro.getAutor());
        values.put("paginas", livro.getPaginas());
        values.put("pagParou", livro.getPagParou());
        values.put("comeco", livro.getComeco());
        dataBank.update("estante", values, "_id = ?", new String[]{Integer.toString(livro.get_id())});
    }

    public void delete(Livro livro){
        dataBank.delete("estante", "_id=?",
                new String[]{Integer.toString(livro.get_id())});
    }



}
