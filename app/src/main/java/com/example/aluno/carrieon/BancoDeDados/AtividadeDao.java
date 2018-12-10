package com.example.aluno.carrieon.BancoDeDados;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.aluno.carrieon.Model.Atividade;

import java.util.List;

@Dao
public interface AtividadeDao {

    @Query("SELECT * FROM Atividade")
    List<Atividade> getAllAtividades();

    @Query("SELECT * FROM Atividade WHERE codigo = :codigo")
    Atividade getAtividadeByCodigo(int codigo);

    @Query("SELECT codigo FROM Atividade WHERE nome = :nome")
    int getAtividadeByNome(String nome);

    @Insert
    void inserirAtividade(Atividade atividade);

    @Update
    void atualizarAtividade(Atividade atividade);

    @Delete
    void excluirAtividade(Atividade atividade);

    @Query("DELETE FROM Atividade")
    void deleteAllActivitys();
}
