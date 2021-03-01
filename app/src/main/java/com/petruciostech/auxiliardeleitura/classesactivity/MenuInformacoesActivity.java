package com.petruciostech.auxiliardeleitura.classesactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.petruciostech.auxiliardeleitura.R;
import com.petruciostech.auxiliardeleitura.classeobjeto.Livro;
import java.util.Date;

public class MenuInformacoesActivity extends AppCompatActivity {
    private TextView txtTitulo;
    private TextView txtAutor;
    private TextView txtPaginas;
    private TextView txtPagLidas;
    private TextView txtPorcento;
    private TextView lblData;
    private Date DATA;
    private Livro liv;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuinformacoes);

        txtTitulo = findViewById(R.id.txtTituloLivro);
        txtAutor = findViewById(R.id.txtAutorLivro);
        txtPaginas = findViewById(R.id.txtPaginasLivro);
        txtPagLidas = findViewById(R.id.txtPaginasLidas);
        txtPorcento = findViewById(R.id.txtPorcentagemLida);
        lblData = findViewById(R.id.lblData);

        Intent it = getIntent();

        liv = (Livro)it.getSerializableExtra("com.petruciostech.auxiliardeleitura.classesactivity.classeobjeto.Livro");
        DATA = new Date(liv.getComeco());
        int te =  porcentagem(liv.getPaginas(), liv.getPagParou());

        txtTitulo.setText(liv.getTitulo());
        txtAutor.setText(liv.getAutor());
        txtPaginas.setText(Integer.toString(liv.getPaginas()));
        txtPagLidas.setText(Integer.toString(liv.getPagParou()));
        txtPorcento.setText(te + "%");
        lblData.setText(Long.toString(calcularData(DATA)));

    }

    public int porcentagem(int pagTot, int pagPare){//Lógica usada na Porcentagem
        if(pagPare != 0) {
            float conta =  (pagPare * 100) / pagTot;
            int tot = (int) conta;
            return tot;
        }else {
            return 0;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuatualizar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void atualizarFicha(MenuItem menuItem){
        Intent it = new Intent(this, AtualizarFicheiroActivity.class);
        it.putExtra("com.petruciostech.auxiliardeleitura.classesactivity.classeobjeto.Livro", liv);
        startActivity(it);
    }

    public long calcularData(Date dataInicio){//Lógica para calcular a quantos dias o usuário está lendo
        Date atual = new Date(System.currentTimeMillis());
        long diferencaDeDias = (atual.getTime() - dataInicio.getTime()) / (1000 * 60 * 60 * 24);
        return diferencaDeDias;
    }

}
