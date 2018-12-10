package com.example.aluno.carrieon.BancoDeDados;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.aluno.carrieon.Model.Notacao;

import java.util.List;

@Dao
public interface NotacaoDao {

    @Query("SELECT * FROM Notacao")
    List<Notacao> getAllNotacao();

    @Query("SELECT * FROM Notacao WHERE numero = :numero")
    Notacao getNotacaoByNumero(int numero);

    @Query("SELECT * FROM Notacao WHERE codHumor = :codHumor")
    Notacao getNotacaoByHumor(int codHumor);

    @Query("SELECT * FROM Notacao WHERE emailPessoa = :emailPessoa")
    List<Notacao> getNotacaoByEmail(String emailPessoa);

    @Query("SELECT * FROM Notacao WHERE dataHoraCriacao LIKE :data")
    List<Notacao> getNotacaoByDate(String data);

    @Insert
    void inserirNotacao(Notacao notacao);

    @Update
    void atualizarNotacao(Notacao notacao);

    @Delete
    void excluirNotacao(Notacao notacao);

    @Query("DELETE FROM Notacao")
    void deleteAllNotes();
}
