package com.petruciostech.auxiliardeleitura.bancodados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.petruciostech.auxiliardeleitura.classeobjeto.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroDao {
    private Helper helperBC;
    private SQLiteDatabase dataBank;

    public LivroDao(Context context){
        helperBC = new Helper(context);
        dataBank = helperBC.getWritableDatabase();
    }

    public long create(Livro livroCreate){
        ContentValues valores = new ContentValues();
        valores.put("titulo", livroCreate.getTitulo());
        valores.put("autor", livroCreate.getAutor());
        valores.put("paginas", livroCreate.getPaginas());
        valores.put("pagParou", livroCreate.getPagParou());
        return dataBank.insert("biblioteca",null, valores);
    }

    public List<Livro> read(){
        List<Livro> livros = new ArrayList<>();
        Cursor cursor = dataBank.query("biblioteca", new String[]{"_id", "titulo", "autor", "paginas", "pagParou"},null,
                null, null, null, null);

        while(cursor.moveToNext()){
            Livro dados = new Livro();
            dados.set_id(cursor.getInt(0));
            dados.setTitulo(cursor.getString(1));
            dados.setAutor(cursor.getString(2));
            dados.setPaginas(cursor.getInt(3));
            dados.setPagParou(cursor.getInt(4));
            livros.add(dados);
        }

        return livros;
    }

}
