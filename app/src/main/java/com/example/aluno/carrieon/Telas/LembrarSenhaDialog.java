package com.example.aluno.carrieon.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aluno.carrieon.BancoDeDados.FirebaseConexao;
import com.example.aluno.carrieon.Controller.PessoaController;
import com.example.aluno.carrieon.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LembrarSenhaDialog extends AppCompatActivity {

    EditText edt_snh_email;
    Button btn_snh_enviar;
    Button btn_snh_cancelar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lembrar_senha_dialog);

        edt_snh_email = (EditText) findViewById(R.id.edt_snh_email);
        btn_snh_enviar = (Button) findViewById(R.id.btn_snh_enviar);
        btn_snh_cancelar = (Button) findViewById(R.id.btn_snh_cancelar);

        btn_snh_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth.sendPasswordResetEmail(edt_snh_email.getText().toString().trim());
                startActivity(new Intent(LembrarSenhaDialog.this,EntradaActivity.class));
                Toast.makeText(getBaseContext(),"E-mail de recuperação enviado!", Toast.LENGTH_LONG).show();

            }
        });

        btn_snh_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LembrarSenhaDialog.this,EntradaActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseConexao.getFirebaseAuth();
    }
}
