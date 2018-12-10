package com.example.aluno.carrieon.BancoDeDados;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.aluno.carrieon.Model.Humor;

import java.util.List;

@Dao
public interface HumorDao {

    @Query("SELECT * FROM Humor")
    List<Humor> getAllHumor();

    @Query("SELECT * FROM Humor WHERE codigo = :codigo")
    Humor getHumorByCodigo(int codigo);

    @Query("SELECT codigo FROM Humor WHERE nome = :nome")
    int getHumorByNome(String nome);

    @Insert
    void inserirHumor(Humor humor);

    @Update
    void atualizarHumor(Humor humor);

    @Delete
    void excluirHumor(Humor humor);

    @Query("DELETE FROM Humor")
    void deleteAllMoods();

}
