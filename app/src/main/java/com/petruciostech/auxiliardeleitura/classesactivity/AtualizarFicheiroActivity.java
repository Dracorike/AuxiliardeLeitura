package com.petruciostech.auxiliardeleitura.classesactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.petruciostech.auxiliardeleitura.R;
import com.petruciostech.auxiliardeleitura.bancodados.LivroCadastroDao;
import com.petruciostech.auxiliardeleitura.classeobjeto.Livro;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AtualizarFicheiroActivity extends AppCompatActivity {
    private CalendarView calendario;
    private EditText paginas_Lidas;
    private LivroCadastroDao dao;
    private TextView data;
    private Livro liv;
    private Date DATA;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.atualizarficheiro);

        SimpleDateFormat formatar = new SimpleDateFormat("yyyy-MM-dd");
        calendario = findViewById(R.id.calendario);
        data = findViewById(R.id.lblDataMostrar);
        paginas_Lidas = findViewById(R.id.txtNumPag);
        dao = new LivroCadastroDao(this);
        Intent it = getIntent();

        liv = (Livro) it.getSerializableExtra("com.petruciostech.auxiliardeleitura.classesactivity.classeobjeto.Livro");
        if(!liv.isEmptyDate()){
            DATA = new Date(liv.getComeco());
            calendario.setDate(liv.getComeco());
            data.setText(formatar.format(DATA));
            data.setVisibility(View.VISIBLE);
        }

        if(!liv.isEmptyPagParou()){
            paginas_Lidas.setText(Integer.toString(liv.getPagParou()));
        }

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                data.setVisibility(View.VISIBLE);
                data.setText(dayOfMonth + "/" + month + "/" + year);
                DATA = new Date((year - 1900), (month), dayOfMonth);
            }
        });

    }

    public void atualizar(View view) {//Metodo utilizado para atualizar especificamente a data e quantas páginas foram lidas
        Livro livro = new Livro();
        livro.set_id(liv.get_id());
        livro.setTitulo(liv.getTitulo());
        livro.setAutor(liv.getAutor());
        livro.setPaginas(liv.getPaginas());
        try{//Verifica se há uma atualização requisitada
            livro.setPagParou(Integer.parseInt(paginas_Lidas.getText().toString()));
        }catch (Exception ex){//caso não tenha, irá preencher com o if/else
            if(!liv.isEmptyPagParou()){//Preenche caso tenha conteúdo na classe do livro
                livro.setPagParou(liv.getPagParou());
            }else{//Senão "pagParou" recebe 0
                livro.setPagParou(0);
            }
        }
        livro.setComeco(DATA.getTime());
        dao.update(livro);
    }

}
