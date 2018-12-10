package com.example.aluno.carrieon.Telas.Fragments;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aluno.carrieon.BancoDeDados.BancoDados;
import com.example.aluno.carrieon.Controller.AtividadeController;
import com.example.aluno.carrieon.Controller.HumorController;
import com.example.aluno.carrieon.Controller.NotacaoController;
import com.example.aluno.carrieon.Controller.PessoaController;
import com.example.aluno.carrieon.Model.Atividade;
import com.example.aluno.carrieon.Model.Humor;
import com.example.aluno.carrieon.Model.Notacao;
import com.example.aluno.carrieon.R;
import com.example.aluno.carrieon.Telas.Adapters.RegistroAdapter;
import com.example.aluno.carrieon.Telas.EntradaActivity;
import com.example.aluno.carrieon.Telas.NotacaoDialog;
import com.example.aluno.carrieon.Telas.NovoDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroFragment extends Fragment {

    //Banco de dado
    private AtividadeController ac;
    private NotacaoController nc;
    private HumorController hc;
    private PessoaController pc;

    FloatingActionButton btnRegistro;

    NotacaoDialog dialog;

    private FragmentActivity myContext;

    RecyclerView notacaoRecyclerView;
    private RecyclerView.LayoutManager notacaoLayoutManager;
    private RegistroAdapter rAdapter;

    private String txt_Imagens_Atividades[], edit_Descricao[], txt_DataHoraCriacao[], txt_Imagens_Humores[], txt_Titulo[];

    Notacao[] notacaos;

    int posicaoAtividade;

    RegistroAdapter.OnItemClickListener listener;

    SharedPreferences sharedPreferences;
    String email;

    public RegistroFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //Inicializando controllers
        ac = new AtividadeController(this.myContext);
        nc = new NotacaoController(this.myContext);
        hc = new HumorController(this.myContext);
        pc = new PessoaController(this.myContext);

        lerNotacao();

        notacaoLayoutManager = new GridLayoutManager(myContext,2);
        notacaoRecyclerView = (RecyclerView) getView().findViewById(R.id.recycle_notacao);
        notacaoRecyclerView.setHasFixedSize(true);
        notacaoRecyclerView.setLayoutManager(notacaoLayoutManager);

        listener = new RegistroAdapter.OnItemClickListener() {
            @Override
            public int onClick(View v,int position) {
                posicaoAtividade = position+1;
                SharedPreferences sharedPreferences = myContext.getSharedPreferences("CarrieOn", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor;
                editor = sharedPreferences.edit();
                editor.putInt("numero_notacao",posicaoAtividade);
                editor.commit();

                //FragmentManager fragManager = myContext.getFragmentManager();
                NotacaoDialog dialog = new NotacaoDialog();
                dialog.setStyle(20,0);
                dialog.show(getFragmentManager(),"y");

                return 0;
            }
        };
        rAdapter = new RegistroAdapter(myContext,txt_Imagens_Atividades,edit_Descricao,txt_DataHoraCriacao,txt_Imagens_Humores,txt_Titulo, listener);
        notacaoRecyclerView.setAdapter(rAdapter);

        btnRegistro = getView().findViewById(R.id.btnRegis);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragManager = myContext.getFragmentManager();
                NovoDialog dialog = new NovoDialog();
                dialog.setStyle(20,20);
                dialog.show(fragManager,"g");
            }
        });
    }

    public void lerNotacao(){

        sharedPreferences = myContext.getSharedPreferences("CarrieOn", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("Email", "w");

        //if(pc.pessoaLogada(email) != null){
            if(nc.listarNotacaoByEmail(email) != null) {
                notacaos = nc.listarNotacaoByEmail(email);
                //notacaos = nc.listarNotacao();
                edit_Descricao = new String[nc.listarNotacaoByEmail(email).length];
                txt_Imagens_Atividades = new String[nc.listarNotacaoByEmail(email).length];
                txt_DataHoraCriacao = new String[nc.listarNotacaoByEmail(email).length];
                txt_Imagens_Humores = new String[nc.listarNotacaoByEmail(email).length];
                txt_Titulo = new String[nc.listarNotacaoByEmail(email).length];

                Atividade a = new Atividade();
                Humor h = new Humor();

                for (int i = 0; i < notacaos.length; i++) {
                    Notacao notacao = notacaos[i];
                    int codAtividade = notacaos[i].getCodAtividade();
                    int codHumor = notacaos[i].getCodHumor();

                    a = ac.buscarAtividadeByCodigo(codAtividade);
                    h = hc.buscarHumorByCodigo(codHumor);

                    txt_DataHoraCriacao[i] = notacao.getDataHoraCriacao();
                    txt_Imagens_Atividades[i] = a.getImg();
                    txt_Imagens_Humores[i] = h.getImg();
                    edit_Descricao[i] = notacao.getDescricao();
                    txt_Titulo[i] = notacao.getTitulo();
                }
            }else{
                Toast.makeText(myContext,"Sem registros!",Toast.LENGTH_SHORT).show();
            }
        /*} else {
            startActivity(new Intent(getActivity(),EntradaActivity.class));
        }*/
    }

}
