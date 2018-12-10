package com.example.aluno.carrieon.Controller;

import android.content.Context;

import com.example.aluno.carrieon.BancoDeDados.BancoDados;
import com.example.aluno.carrieon.Model.Humor;

import java.util.List;

public class HumorController {

    private BancoDados bd;
    private String m;

    public HumorController(Context context) {
        bd = BancoDados.getBancoDados(context);
    }

    public boolean cadastrarHumor(Humor humor) {
        if (bd.humorDao().getHumorByCodigo(humor.getCodigo()) != null){
            m = "Escolha um humor!";
            return false;
        }else{
            bd.humorDao().inserirHumor(humor);
            m = "Salva.";
            return true;
        }
    }

    public int buscarCodigoHumorByNome(String nomeHumor){
        int c = bd.humorDao().getHumorByNome(nomeHumor);
        return c;
    }

    public Humor buscarHumorByCodigo(int code){
        if(bd.humorDao().getHumorByCodigo(code) != null) {
            Humor humor = bd.humorDao().getHumorByCodigo(code);
            return humor;
        }else {
            m = "Sem humores";
            return null;
        }
    }

    public Humor[] listarHumores(){
        List<Humor> humorList = bd.humorDao().getAllHumor();
        Humor[] humores = humorList.toArray(new Humor[humorList.size()]);
        return humores;
    }

    public void atualizarHumor(int c, Humor humor){
        if(bd.humorDao().getHumorByCodigo(c) != null){
            bd.humorDao().atualizarHumor(humor);
            m = "Humor atualizado!";
        }else{
            m = "ERRO! Impossível atualizar no momento!";
        }
    }

    public void excluirHumor(Humor humor){
        if(bd.humorDao().getHumorByCodigo(humor.getCodigo()) != null){
            bd.humorDao().excluirHumor(humor);
            this.m = "Humor excluído!";
        }else{
            this.m = "ERRO! Impossível excluir humor!";
        }
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }
}
