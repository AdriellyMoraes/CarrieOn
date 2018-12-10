package com.example.aluno.carrieon.Telas;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aluno.carrieon.BancoDeDados.FirebaseConexao;
import com.example.aluno.carrieon.Controller.NotacaoController;
import com.example.aluno.carrieon.Controller.PessoaController;
import com.example.aluno.carrieon.Model.Notacao;
import com.example.aluno.carrieon.Model.Pessoa;
import com.example.aluno.carrieon.R;
import com.example.aluno.carrieon.Telas.Fragments.MaisFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ContaActivity extends AppCompatActivity {

    Button btnDados;
    Button btnExcluirConta;
    EditText edNome;
    EditText edEmail;
    EditText edDataNasc;
    EditText edSenha;
    PessoaController pc;
    NotacaoController nc;

    SharedPreferences sharedPreferences;
    String email;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta);
        pc = new PessoaController(getApplicationContext());
        nc = new NotacaoController(getApplicationContext());

        btnDados = (Button) findViewById(R.id.btnDados);
        btnExcluirConta = (Button) findViewById(R.id.btnExcluirConta);
        edNome = (EditText) findViewById(R.id.edNome);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edDataNasc = (EditText) findViewById(R.id.edDataNasc);
        edSenha = (EditText) findViewById(R.id.edSenha);

        sharedPreferences = getSharedPreferences("CarrieOn", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("Email", "");

        final Pessoa pessoa = pc.pessoaLogada(email);

        edNome.setText(pessoa.getNome());
        edEmail.setText(pessoa.getEmail());
        edDataNasc.setText(pessoa.getDataNasc());
        edSenha.setText(pessoa.getSenha());

        edEmail.setInputType(InputType.TYPE_NULL);

        btnDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarDados(view);
            }
        });

        btnExcluirConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder bc = new AlertDialog.Builder(ContaActivity.this);
                bc.setTitle("Exclusão");
                bc.setMessage("Você tem certeza que deseja excluir sua conta?");
                bc.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluirPessoa(view);
                    }
                });

                bc.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            this.finalize();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                });
                bc.show();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseConexao.getFirebaseAuth();
        firebaseUser = FirebaseConexao.getFirebaseUser();
    }

    public void alterarDados(View view){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(edNome.getText().toString().trim());
        pessoa.setEmail(edEmail.getText().toString().trim());
        pessoa.setDataNasc(edDataNasc.getText().toString().trim());
        pessoa.setSenha(edSenha.getText().toString().trim());
        Log.e("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",edSenha.getText().toString().trim());
        Log.e("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",pessoa.getSenha());
        boolean c = pc.atualizarPessoa(pessoa);

        firebaseUser.updatePassword(pessoa.getSenha());

        if(c){
            Toast.makeText(this, pc.getM(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, pc.getM(), Toast.LENGTH_SHORT).show();
        }

    }

    public void excluirPessoa(View view){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(edNome.getText().toString());
        pessoa.setEmail(edEmail.getText().toString());
        pessoa.setDataNasc(edDataNasc.getText().toString());
        pessoa.setSenha(edSenha.getText().toString());
        pc.excluirPessoa(pessoa);

        Notacao notacao[] = nc.listarNotacaoByEmail(pessoa.getEmail());
        for(int i = 0; i<notacao.length; i++){
            nc.excluirNotacao(notacao[i]);
        }

        firebaseUser.delete();

        SharedPreferences sharedPreferences = getSharedPreferences("CarrieOn", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        editor.putBoolean("Logado", false);
        editor.putString("Email", "");
        editor.commit();

        Toast.makeText(this, pc.getM(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ContaActivity.this,EntradaActivity.class);
        startActivity(i);
    }



    /*public void fazerLogin(View view){

        Pessoa pessoa = pc.fazerLogin(emailText, senhaText);

        if(pessoa != null){
            Intent inte = new Intent(EntradaActivity.this, HomeActivity.class);
            startActivity(inte);
            EntradaActivity.this.finish();
        }else{
            Toast.makeText(this, pc.getM(), Toast.LENGTH_SHORT).show();
        }
    }*/
}
