package com.example.aluno.carrieon.BancoDeDados;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.aluno.carrieon.Model.Atividade;
import com.example.aluno.carrieon.Model.Humor;
import com.example.aluno.carrieon.Model.Notacao;
import com.example.aluno.carrieon.Model.Pessoa;
import static com.example.aluno.carrieon.Config.DATABASE_NAME;
import static com.example.aluno.carrieon.Config.DATABASE_VERSION;

@Database(entities = {Pessoa.class, Atividade.class, Humor.class, Notacao.class}, version = DATABASE_VERSION)
public abstract class BancoDados extends RoomDatabase {

    public abstract PessoaDao pessoaDao();
    public abstract AtividadeDao atividadeDao();
    public abstract HumorDao humorDao();
    public abstract NotacaoDao notacaoDao();
    private static BancoDados bancoDados;

    public static BancoDados getBancoDados(Context context){

            bancoDados = Room.databaseBuilder(context, BancoDados.class, DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
            return bancoDados;
    }
}
