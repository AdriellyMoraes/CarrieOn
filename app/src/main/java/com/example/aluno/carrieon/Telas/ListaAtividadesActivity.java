package com.example.aluno.carrieon.Telas;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aluno.carrieon.Controller.AtividadeController;
import com.example.aluno.carrieon.Model.Atividade;
import com.example.aluno.carrieon.R;
import com.example.aluno.carrieon.Telas.Adapters.ListaAdapter;

public class ListaAtividadesActivity extends Activity {

    ListaAdapter.OnItemClickListener listener;
    int posicaoAtividade;
    AtividadeController ac;

    private Atividade[] atividades;
    private String[] nome;
    private String[] imagem;

    RecyclerView editarAtividadeRecyclerView;
    private RecyclerView.LayoutManager editarAtividadeLayoutManager;
    private ListaAdapter lAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividades);

        final Context context = getApplicationContext();
        ac = new AtividadeController(context);
        atividades = ac.listarAtividades();
        lerAtividade();

        editarAtividadeLayoutManager = new LinearLayoutManager(context);
        editarAtividadeRecyclerView = (RecyclerView) findViewById(R.id.recycle_lista_atividades);
        editarAtividadeRecyclerView.setLayoutManager(editarAtividadeLayoutManager);

        listener = new ListaAdapter.OnItemClickListener() {
            @Override
            public int onClick(View v,int position) {
                posicaoAtividade = position+1;
                return 0;
            }
        };

        //ListaAdapter recebe 1 se for referente à Atividade e recebe 2 se for referente ao Humor
        lAdapter = new ListaAdapter(context,nome,imagem,listener,1);
        editarAtividadeRecyclerView.setAdapter(lAdapter);

    }

    public void lerAtividade(){

        nome = new String[ac.listarAtividades().length];
        imagem = new String[ac.listarAtividades().length];

        for(int i=0; i<nome.length; i++){
            nome[i] = atividades[i].getNome();
            imagem[i] = atividades[i].getImg();
        }
    }

}
