package com.example.aluno.carrieon.Controller;

import android.content.Context;
import android.util.Log;

import com.example.aluno.carrieon.BancoDeDados.BancoDados;
import com.example.aluno.carrieon.Model.Pessoa;

public class PessoaController {

    private BancoDados bd;
    private String m;

    public PessoaController(Context context) {
        bd = BancoDados.getBancoDados(context);
    }

    public boolean cadastrarPessoa(Pessoa pessoa){
        if(bd.pessoaDao().getUserByEmail(pessoa.getEmail()) != null){
            this.m = "E-mail já cadastrado!";
            bd.close();
            return false;
        }else{
            bd.pessoaDao().inserirPessoa(pessoa);
            this.m = "Cadastrado";
            bd.close();
            return true;
        }
    }

    public Pessoa fazerLogin(String email, String senha){
        Pessoa pessoa = bd.pessoaDao().login(email,senha);
        Pessoa pessoa1 = bd.pessoaDao().getUserByEmail(email);
        if(pessoa != null){
            this.m = "Bem vindo!";
            bd.close();
        }else{
            if(pessoa1 == null){
                this.m = "E-mail não cadastrado!";
                bd.close();
            }else {
                this.m = "Senha incorreta!";
                bd.close();
            }
        }
        return pessoa;
    }

    public boolean atualizarPessoa(Pessoa pessoa){
        try {
            bd.pessoaDao().atualizarPessoa(pessoa);
            this.m = "Dados atualizados!";
            bd.close();
            return true;
        } catch (Exception e){
            this.m = "Erro! Tente novamente.";
            bd.close();
            return  false;
        }
    }

    public Pessoa pessoaLogada(String email){
        if(bd.pessoaDao().getUserByEmail(email) != null){
            Pessoa p = bd.pessoaDao().getUserByEmail(email);
            return p;
        } else {
            return null;
        }

    }

    public void excluirPessoa(Pessoa pessoa){
        try{
            bd.pessoaDao().excluirPessoa(pessoa);
            this.m = "Conta excluída";
            bd.close();
        } catch (Exception e) {
            this.m = "Erro!";
            bd.close();
        }
    }

    public void excluirTodasPessoas(){
        bd.pessoaDao().deleteAllUsers();
    }

    public String getM(){
        return m;
    }

}
