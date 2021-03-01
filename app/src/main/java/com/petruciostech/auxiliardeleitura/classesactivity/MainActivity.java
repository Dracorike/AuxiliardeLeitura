package com.petruciostech.auxiliardeleitura.classesactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
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
import com.petruciostech.auxiliardeleitura.bancodados.LivroDao;
import com.petruciostech.auxiliardeleitura.classeobjeto.Livro;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lista;
    private List<Livro> livroLista = new ArrayList<>();
    private List<Livro> livroLista_Filtro = new ArrayList<>();
    private LivroDao dao;
    private LivroCadastroDao cadastrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new LivroDao(this);
        lista = findViewById(R.id.listaLivro);
        cadastrado = new LivroCadastroDao(this);
        livroLista = dao.read();
        livroLista_Filtro.addAll(livroLista);
        ArrayAdapter<Livro> adapatador = new ArrayAdapter<Livro>(this, android.R.layout.simple_expandable_list_item_1, livroLista_Filtro);

        lista.setAdapter(adapatador);
        registerForContextMenu(lista);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuprincipal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void cadastrar(MenuItem menuItem){
        Intent it = new Intent(this, CadastroActivity.class);
        startActivity(it);
    }


    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo){
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menucontext, contextMenu);
    }



    @Override
    public void onResume(){
        super.onResume();
        livroLista = dao.read();
        livroLista_Filtro.clear();
        livroLista_Filtro.addAll(livroLista);
        lista.invalidateViews();
    }

    public void adicionar(MenuItem menuItem){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        final Livro livro_adicionar = livroLista_Filtro.get(menuInfo.position);

        Livro adiciona = new Livro();
        adiciona.setTitulo(livro_adicionar.getTitulo());
        adiciona.setAutor(livro_adicionar.getAutor());
        adiciona.setPaginas(livro_adicionar.getPaginas());
        adiciona.setPagParou(livro_adicionar.getPagParou());

        long mensagem = cadastrado.create(adiciona);
        Toast.makeText(this, "Livro " + mensagem + " foi adicionado", Toast.LENGTH_SHORT).show();

    }



}