package com.example.aluno.carrieon.Model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "Notacao")
public class Notacao {

    @NonNull
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "numero")
    private int numero;

    @NonNull
    @ColumnInfo (name = "descricao")
    private String descricao;

    @NonNull
    @ColumnInfo (name = "dataHoraCriacao")
    private String dataHoraCriacao;

    @NonNull
    @ColumnInfo (name="titulo")
    private String titulo;

    @NonNull
    @ColumnInfo (name = "salva")
    private boolean salva;

    @NonNull
    @ForeignKey( entity = Pessoa.class, parentColumns = "email", childColumns = "emailPessoa")
    private String emailPessoa;

    @NonNull
    @ForeignKey( entity = Atividade.class, parentColumns = "codigo", childColumns = "codAtividade")
    private int codAtividade;

    @NonNull
    @ForeignKey( entity = Humor.class, parentColumns = "codigo", childColumns = "codHumor")
    private int codHumor;

    @Ignore
    public Notacao() {
        this.numero = 0;
        this.descricao = "";
        this.dataHoraCriacao = "";
        this.titulo = "";
        this.salva = false;
        this.emailPessoa = "";
        this.codAtividade = 0;
        this.codHumor = 0;
    }

    public Notacao(String descricao, String dataHoraCriacao, String titulo, boolean salva, String emailPessoa, int codAtividade, int codHumor){
        this.descricao = descricao;
        this.dataHoraCriacao = dataHoraCriacao;
        this.titulo = titulo;
        this.salva = salva;
        this.emailPessoa = emailPessoa;
        this.codAtividade = codAtividade;
        this.codHumor = codHumor;
    }

    @NonNull
    public int getNumero() {
        return numero;
    }

    public void setNumero(@NonNull int numero) {
        this.numero = numero;
    }

    @NonNull
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NonNull String descricao) {
        this.descricao = descricao;
    }

    @NonNull
    public String getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(@NonNull String dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    @NonNull
    public boolean isSalva() {
        return salva;
    }

    public void setSalva(@NonNull boolean salva) {
        this.salva = salva;
    }

    @NonNull
    public String getEmailPessoa() {
        return emailPessoa;
    }

    public void setEmailPessoa(@NonNull String emailPessoa) {
        this.emailPessoa = emailPessoa;
    }

    @NonNull
    public int getCodAtividade() {
        return codAtividade;
    }

    public void setCodAtividade(@NonNull int codAtividade) {
        this.codAtividade = codAtividade;
    }

    @NonNull
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NonNull String titulo) {
        this.titulo = titulo;
    }

    @NonNull
    public int getCodHumor() {
        return codHumor;
    }

    public void setCodHumor(@NonNull int codHumor) {
        this.codHumor = codHumor;
    }
}
