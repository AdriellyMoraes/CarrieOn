package com.example.aluno.carrieon.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Blob;
import java.util.Date;

@Entity (tableName = "Pessoa")
public class Pessoa {

    @NonNull
    @PrimaryKey
    @ColumnInfo (name = "email")
    private String email;

    @NonNull
    @ColumnInfo (name = "nome")
    private String nome;

    @NonNull
    @ColumnInfo (name ="dataNasc")
    private String dataNasc;

    @NonNull
    @ColumnInfo (name ="senha")
    private String senha;

    @Ignore
    public Pessoa(){
        this.email = "";
        this.nome = "";
        this.dataNasc = "";
        this.senha = "";
    }

    public Pessoa(String email, String nome, String dataNasc, String senha) {
        this.email = email;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
