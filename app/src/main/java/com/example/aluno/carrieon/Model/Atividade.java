package com.example.aluno.carrieon.Model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.drawable.Icon;
import android.support.annotation.NonNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

@Entity (tableName = "Atividade")
public class Atividade {

    @NonNull
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "codigo")
    private int codigo;

    @NonNull
    @ColumnInfo (name = "nome")
    private String nome;

    @NonNull
    @ColumnInfo (name = "imagem")
    private String img;

    @Ignore
    public Atividade(){
        this.codigo = 0;
        this.nome = "";
        this.img = "";
    }

    public Atividade(String nome, String img){
        this.nome = nome;
        this.img = img;
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
}
