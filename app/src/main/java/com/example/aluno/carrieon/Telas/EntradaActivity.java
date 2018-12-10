package com.example.aluno.carrieon.Telas;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.carrieon.BancoDeDados.FirebaseConexao;
import com.example.aluno.carrieon.Controller.PessoaController;
import com.example.aluno.carrieon.Model.Pessoa;
import com.example.aluno.carrieon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EntradaActivity extends AppCompatActivity {

    Button btnLogin;
    TextView txtCadastro;
    TextView txtEsquecerSenha;
    EditText edEmail, edSenha;
    PessoaController pc;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtCadastro = (TextView) findViewById(R.id.txtCadastro);
        txtEsquecerSenha = (TextView) findViewById(R.id.txtEsquecerSenha);
        edEmail = (EditText) findViewById(R.id.editEmail);
        edSenha = (EditText) findViewById(R.id.editSenha);

        pc = new PessoaController(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fazerLogin(view);
            }
        });

        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(EntradaActivity.this, CadastroActivity.class);
                startActivity(inte);
            }
        });

        txtEsquecerSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntradaActivity.this, LembrarSenhaDialog.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseConexao.getFirebaseAuth();
    }

    public void fazerLogin(View view){
        final String emailText = edEmail.getText().toString().trim();
        final String senhaText = edSenha.getText().toString().trim();


        firebaseAuth.signInWithEmailAndPassword(emailText,senhaText)
            .addOnCompleteListener(EntradaActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){


                        Pessoa p = pc.pessoaLogada(emailText);
                        p.setSenha(senhaText);
                        pc.atualizarPessoa(p);
                        Pessoa pessoa = pc.fazerLogin(emailText, p.getSenha());

                        SharedPreferences sharedPreferences = getSharedPreferences("CarrieOn", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor;
                        editor = sharedPreferences.edit();
                        editor.putBoolean("Logado", true);
                        editor.putString("Email", emailText);
                        editor.commit();
                        Intent inte = new Intent(EntradaActivity.this, HomeActivity.class);
                        startActivity(inte);
                        Toast.makeText(getBaseContext(), pc.getM(), Toast.LENGTH_SHORT).show();
                        EntradaActivity.this.finish();


                    }else{
                        Toast.makeText(getBaseContext(), "Não foi possível realizar o login!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}
