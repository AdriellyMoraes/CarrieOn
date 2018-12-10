package com.example.aluno.carrieon.BancoDeDados;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.aluno.carrieon.Model.Pessoa;

import java.util.List;

@Dao
public interface PessoaDao {

   @Query("SELECT * FROM Pessoa")
    List<Pessoa> getAllPessoas();

    @Query("SELECT * FROM Pessoa ")
    Pessoa pessoaLogada();

    @Query("SELECT * FROM Pessoa WHERE email = :email")
    Pessoa getUserByEmail(String email);

    @Query("SELECT * FROM Pessoa WHERE email = :email AND senha = :senha")
    Pessoa login(String email, String senha);

   @Insert
   void inserirPessoa( Pessoa pessoa);

   @Update
   void atualizarPessoa(Pessoa pessoa);

   @Delete
    void excluirPessoa(Pessoa pessoa);

   @Query("DELETE FROM Pessoa")
   void deleteAllUsers();
}
