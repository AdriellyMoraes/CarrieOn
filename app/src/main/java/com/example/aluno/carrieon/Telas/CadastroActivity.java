package com.example.aluno.carrieon.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aluno.carrieon.BancoDeDados.BancoDados;
import com.example.aluno.carrieon.BancoDeDados.FirebaseConexao;
import com.example.aluno.carrieon.Controller.PessoaController;
import com.example.aluno.carrieon.Model.Pessoa;
import com.example.aluno.carrieon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class CadastroActivity extends AppCompatActivity {

    //Banco de dados
    private BancoDados bancoDados;
    PessoaController pc;

    Button btnCancelar;
    Button btnConfirmar;
    EditText txtNome;
    EditText txtEmail;
    EditText txtDataNasc;
    EditText txtSenha;
    EditText txtConfirmarSenha;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        pc = new PessoaController(getApplicationContext());

        //Inicializando botões
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        txtNome = (EditText) findViewById(R.id.txtNome);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtDataNasc = (EditText) findViewById(R.id.txtDataNasc);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        txtConfirmarSenha = (EditText) findViewById(R.id.txtConfirmarSenha);
        
        //Adicionando ação ao botão cancelar
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(CadastroActivity.this, EntradaActivity.class);
                startActivity(inte);
            }
        });

       btnConfirmar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               cadastrarPessoa();
           }
       });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseConexao.getFirebaseAuth();
    }

    private void cadastrarPessoa() {
        if(txtSenha.getText().toString().length() < 6){
            Toast.makeText(this, "A senha deve ter pelo menos seis caracteres.", Toast.LENGTH_SHORT).show();
        }else if(txtSenha.getText().toString().equals(txtConfirmarSenha.getText().toString())){
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(txtNome.getText().toString().trim());
            pessoa.setEmail(txtEmail.getText().toString().trim());
            pessoa.setDataNasc(txtDataNasc.getText().toString().trim());
            pessoa.setSenha(txtSenha.getText().toString().trim());

            boolean cadastrado = pc.cadastrarPessoa(pessoa);

            if(cadastrado){
                firebaseAuth.createUserWithEmailAndPassword(txtEmail.getText().toString().trim(),txtSenha.getText().toString().trim());
                Intent intent = new Intent(CadastroActivity.this, EntradaActivity.class);
                Toast.makeText(this,pc.getM(),Toast.LENGTH_SHORT).show();
                startActivity(intent);
                CadastroActivity.this.finish();
            }else{
                Toast.makeText(this, pc.getM(), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Senhas não são iguais!", Toast.LENGTH_SHORT).show();
        }
    }

}
