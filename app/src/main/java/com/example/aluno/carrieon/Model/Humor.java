package com.example.aluno.carrieon.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Humor")
public class Humor {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "codigo")
    private int codigo;

    @NonNull
    @ColumnInfo (name = "nome")
    private String nome;

    @NonNull
    @ColumnInfo (name = "imagem")
    private String img;

    @NonNull
    @ColumnInfo (name = "valor")
    private int valor;

    @Ignore
    public Humor(){
        this.codigo = 0;
        this.nome = "";
        this.img = "";
        this.valor = 0;
    }

    public Humor(String nome, String img, int valor){
        this.nome = nome;
        this.img = img;
        this.valor = valor;
    }

    @NonNull
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(@NonNull int codigo) {
        this.codigo = codigo;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    @NonNull
    public String getImg() {
        return img;
    }

    public void setImg(@NonNull String img) {
        this.img = img;
    }

    @NonNull
    public int getValor() {
        return valor;
    }

    public void setValor(@NonNull int valor) {
        this.valor = valor;
    }
}
