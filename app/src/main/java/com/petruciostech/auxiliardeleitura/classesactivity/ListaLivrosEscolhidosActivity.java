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
import android.widget.SearchView;

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
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, livroListagemFiltro);

        listaCadastrados.setAdapter(adapter);
        listaCadastrados.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Livro chamada = livroListagemFiltro.get(position);
                chamar(chamada);//Gambiarra por não conseguir usar context dentro da função onClick
            }
        });


        registerForContextMenu(listaCadastrados);
    }

    public void chamar(Livro livro){//A Pequena Gambiarra
        Intent intent = new Intent(this, MenuInformacoesActivity.class);
        intent.putExtra("com.petruciostech.auxiliardeleitura.classesactivity.classeobjeto.Livro", livro);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuinicial, menu);

        SearchView achar = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        achar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procurarLivro(newText);
                return false;
            }
        }
        );

        return super.onCreateOptionsMenu(menu);
    }

    public void procurarLivro(String titulo){
        livroListagemFiltro.clear();
        for(Livro livro: livroListagem){
            if(livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                livroListagemFiltro.add(livro);
            }
        }

        listaCadastrados.invalidateViews();
    }

    public void adicionarLivro(View view){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    public void onResume(){
        super.onResume();
        livroListagem = dao.read();
        livroListagemFiltro.clear();
        livroListagemFiltro.addAll(livroListagem);
        listaCadastrados.invalidateViews();
    }

    public void deletarLivro(MenuItem menuItem){
        AdapterView.AdapterContextMenuInfo adaptador
                = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        Livro apagar = livroListagemFiltro.get(adaptador.position);
        dao.delete(apagar);
        onResume();

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menudelete, menu);
    }

}