package com.petruciostech.auxiliardeleitura.classesactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.petruciostech.auxiliardeleitura.R;
import com.petruciostech.auxiliardeleitura.bancodados.LivroDao;
import com.petruciostech.auxiliardeleitura.classeobjeto.Livro;

public class CadastroActivity extends AppCompatActivity {
    private LivroDao dao;
    private EditText txtTitulo;
    private EditText txtAutor;
    private EditText txtPaginas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtAutor = findViewById(R.id.txtAutor);
        txtPaginas = findViewById(R.id.txtPaginas);

        dao = new LivroDao(this);

        if(txtTitulo.isActivated()){
            txtTitulo.setText(" ");
        }

    }

    public void cadastrar(View view){//Cadastrar o livro no banco de dados
        try {
            Livro livro = new Livro();
            livro.setTitulo(txtTitulo.getText().toString());
            livro.setAutor(txtAutor.getText().toString());
            livro.setPaginas(Integer.parseInt(txtPaginas.getText().toString()));
            livro.setPagParou(0);
            long mensagem = dao.create(livro);

            Toast.makeText(this, "Livro (" + mensagem + ") Cadastrado", Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            Toast.makeText(this,"Digite um número inteiro no campo páginas", Toast.LENGTH_SHORT).show();
        }
    }



}
