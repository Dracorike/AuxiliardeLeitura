package com.petruciostech.auxiliardeleitura.classeobjeto;

import java.io.Serializable;
import java.util.Date;

public class Livro implements Serializable {
    private int _id;
    private String titulo;
    private String autor;
    private int paginas;
    private int pagParou;
    private long comeco;

    public long getComeco() {
        return comeco;
    }

    public boolean isEmptyPagParou(){
        int pagina = this.getPagParou();
        if(pagina == 0){
            return true;
        }else{
            return false;
        }

    }

    public boolean isEmptyDate(){
        if(this.comeco == 0){
            return true;
        }else{
            return false;
        }
    }

    public void setComeco(long comeco) {
        this.comeco = comeco;
    }

    public int getPagParou() {
        return pagParou;
    }

    public void setPagParou(int pagParou) {
        this.pagParou = pagParou;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    @Override
    public String toString(){
        return this.titulo;
    }
}
