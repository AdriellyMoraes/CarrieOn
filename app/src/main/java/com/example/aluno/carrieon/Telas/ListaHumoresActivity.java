package com.example.aluno.carrieon.Telas;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aluno.carrieon.Controller.HumorController;
import com.example.aluno.carrieon.Model.Humor;
import com.example.aluno.carrieon.R;
import com.example.aluno.carrieon.Telas.Adapters.ListaAdapter;

public class ListaHumoresActivity extends Activity {

    ListaAdapter.OnItemClickListener listener;
    int posicaoHumor;
    HumorController hc;

    private Humor[] humores;
    private String[] nome;
    private String[] imagem;

    RecyclerView editarHumorRecyclerView;
    private RecyclerView.LayoutManager editarHumorLayoutManager;
    private ListaAdapter lAdapter;

    Button img_confAtu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_humores);

        final Context context = getApplicationContext();
        hc = new HumorController(context);
        humores = hc.listarHumores();
        lerAtividade();

        editarHumorLayoutManager = new LinearLayoutManager(context);
        editarHumorRecyclerView = (RecyclerView) findViewById(R.id.recycle_lista_humores);
        editarHumorRecyclerView.setLayoutManager(editarHumorLayoutManager);

        listener = new ListaAdapter.OnItemClickListener() {
            @Override
            public int onClick(View v,int position) {
                posicaoHumor = position+1;
                return 0;
            }
        };

        lAdapter = new ListaAdapter(context,nome,imagem,listener,2);
        editarHumorRecyclerView.setAdapter(lAdapter);
    }

    public void lerAtividade(){

        nome = new String[hc.listarHumores().length];
        imagem = new String[hc.listarHumores().length];

        for(int i=0; i<nome.length; i++){
            nome[i] = humores[i].getNome();
            imagem[i] = humores[i].getImg();
        }
    }

}
