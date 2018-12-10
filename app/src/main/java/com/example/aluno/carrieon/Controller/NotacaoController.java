package com.example.aluno.carrieon.Controller;

import android.content.Context;
import android.util.Log;

import com.example.aluno.carrieon.BancoDeDados.BancoDados;
import com.example.aluno.carrieon.Model.Notacao;

import java.util.List;

public class NotacaoController {

    private BancoDados bd;
    private String m;

    public NotacaoController(Context context){
        bd = BancoDados.getBancoDados(context);
    }

    public boolean cadastrarNotacao(Notacao notacao){
        if (bd.notacaoDao().getNotacaoByNumero(notacao.getNumero()) != null) {
            this.m = "NNNNN.";
            return false;
        }else{
            bd.notacaoDao().inserirNotacao(notacao);
            this.m = "SALVA.";
            return true;
        }
    }

    public Notacao[] listarNotacao(){
        List<Notacao> notacaoList = bd.notacaoDao().getAllNotacao();
        Notacao[] notacaos = notacaoList.toArray(new Notacao[notacaoList.size()]);
        return notacaos;
    }

    public Notacao[] listarNotacaoData(int ano, int mes, int dia){
        String data = "%"+dia+"/"+mes+"/"+ano+"%";
        List<Notacao> notacaoList = bd.notacaoDao().getNotacaoByDate(data);
        Notacao[] notacaos = notacaoList.toArray(new Notacao[notacaoList.size()]);
        Log.e("seila", notacaoList.size()+"");
        return notacaos;
    }

    public Notacao[] listarNotacaoByEmail(String emailPessoa){
        if(bd.notacaoDao().getNotacaoByEmail(emailPessoa) != null){
            List<Notacao> notacaoList = bd.notacaoDao().getNotacaoByEmail(emailPessoa);
            Notacao[] notacaos = notacaoList.toArray(new Notacao[notacaoList.size()]);
            return notacaos;
        }else{
            m = "Sem registros!";
            return null;
        }
    }

    public Notacao buscarNotacaoByNumber(int numero_notacao){
        Notacao notacao = bd.notacaoDao().getNotacaoByNumero(numero_notacao);
        return notacao;
    }

    public boolean atualizarNotacao(Notacao notacao){
        if (bd.notacaoDao().getNotacaoByNumero(notacao.getNumero()) != null){
            bd.notacaoDao().atualizarNotacao(notacao);
            m = "Nota Atualizada!";
            return true;
        }else{
            m = "ERRO! Impossível atualizar agora!";
            return false;
        }
    }

    public boolean excluirNotacao(Notacao notacao){
        if(bd.notacaoDao().getNotacaoByNumero(notacao.getNumero()) != null){
            bd.notacaoDao().excluirNotacao(notacao);
            m = "Nota excluída!";
            return true;
        }else{
            m = "ERRO! Impossível excluir agora!";
            return false;
        }
    }


    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }
}
