package com.petruciostech.auxiliardeleitura.classesactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.petruciostech.auxiliardeleitura.R;
import com.petruciostech.auxiliardeleitura.bancodados.LivroCadastroDao;
import com.petruciostech.auxiliardeleitura.classeobjeto.Livro;

import java.util.ArrayList;
import java.util.List;

public class ListaLivrosEscolhidosActivity extends AppCompatActivity{
    private ListView listaCadastrados;
    private LivroCadastroDao dao;
    private List<Livro> livroListagem = new ArrayList<>();
    private List<Livro> livroListagemFiltro = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_livros_escolhidos);

        dao = new LivroCadastroDao(this);

        listaCadastrados = findViewById(R.id.listLivrosCadastrados);
        livroListagem = dao.read();
        livroListagemFiltro.addAll(livroListagem);


        System.out.println(dao.read().isEmpty());

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, livroListagemFiltro);

        listaCadastrados.setAdapter(adapter);


        listaCadastrados.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Seu codigo aqui
                final Livro chamada = livroListagemFiltro.get(position);
                chamar(chamada);

            }
        });

    }

    public void chamar(Livro livro){
        Intent intent = new Intent(this, MenuInformacoesActivity.class);
        intent.putExtra("com.petruciostech.auxiliardeleitura.classesactivity.classeobjeto.Livro", livro);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuinicial, menu);


        return super.onCreateOptionsMenu(menu);
    }


    public void adicionarLivro(MenuItem menuItem){
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);

    }

    public void onResume(){
        super.onResume();
        livroListagem = dao.read();
        livroListagemFiltro.clear();
        livroListagemFiltro.addAll(livroListagem);
        listaCadastrados.invalidateViews();
    }


}