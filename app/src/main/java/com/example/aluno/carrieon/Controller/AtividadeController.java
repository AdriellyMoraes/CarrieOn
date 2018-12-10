package com.example.aluno.carrieon.Controller;

import android.content.Context;
import android.widget.Toast;

import com.example.aluno.carrieon.BancoDeDados.BancoDados;
import com.example.aluno.carrieon.Model.Atividade;
import com.example.aluno.carrieon.Model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class AtividadeController {

    private BancoDados bd;
    private String m;

    public AtividadeController(Context context) {
        bd = BancoDados.getBancoDados(context);
    }

    public boolean cadastrarAtividade(Atividade atividade) {
        if (bd.atividadeDao().getAtividadeByCodigo(atividade.getCodigo()) != null){
            m = "Escolha uma atividade!";
            return false;
        }else{
                bd.atividadeDao().inserirAtividade(atividade);
                m = "Salva.";
                return true;
        }
    }

    public int buscarCodigoAtividadeByNome(String nomeAtividade){
        int c = bd.atividadeDao().getAtividadeByNome(nomeAtividade);
        return c;
    }

    public Atividade buscarAtividadeByCodigo(int code){
        if(bd.atividadeDao().getAtividadeByCodigo(code) != null) {
            Atividade atividade = bd.atividadeDao().getAtividadeByCodigo(code);
            return atividade;
        }else {
            m = "Sem atividades";
            return null;
        }
    }

    public Atividade[] listarAtividades(){
        List<Atividade> atividadeList = bd.atividadeDao().getAllAtividades();
        Atividade[] atividades = atividadeList.toArray(new Atividade[atividadeList.size()]);
        return atividades;
    }

    public void atualizarAtividade(int c, Atividade atividade){
        if(bd.atividadeDao().getAtividadeByCodigo(c) != null){
            bd.atividadeDao().atualizarAtividade(atividade);
            m = "Atividade atualizada!";
        }else{
            m = "ERRO! Impossível atualizar no momento!";
        }
    }

    public void excluirAtividade(Atividade atividade){
        if(bd.atividadeDao().getAtividadeByCodigo(atividade.getCodigo()) != null){
            bd.atividadeDao().excluirAtividade(atividade);
            this.m = "Atividade excluída!";
        }else{
            this.m = "ERRO! Impossível excluir atividade!";
        }
    }

    public String getM(){
        return m;
    }
}
