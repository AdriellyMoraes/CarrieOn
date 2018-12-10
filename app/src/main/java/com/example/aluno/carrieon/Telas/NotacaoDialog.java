package com.example.aluno.carrieon.Telas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.carrieon.Controller.AtividadeController;
import com.example.aluno.carrieon.Controller.HumorController;
import com.example.aluno.carrieon.Controller.NotacaoController;
import com.example.aluno.carrieon.Model.Atividade;
import com.example.aluno.carrieon.Model.Humor;
import com.example.aluno.carrieon.Model.Notacao;
import com.example.aluno.carrieon.R;

public class NotacaoDialog extends DialogFragment {

    //Banco de dado
    private AtividadeController ac;
    private NotacaoController nc;
    private HumorController hc;

    ImageView img_not_humor;
    ImageView img_not_atividade;
    ImageView img_not_fechar;
    TextInputEditText ed_not_descricao;
    TextView txt_not_atividade;
    TextView txt_not_humor;
    EditText ed_not_titulo;
    Button btn_not_excluir;
    Button btn_not_editar;
    Button btn_not_descartar;
    Button btn_not_salvar;

    Context myContext;

    int numero_notacao;

    SharedPreferences sharedPreferences;
    Intent intent;

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialog_notacao, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //Inicializando controllers
        ac = new AtividadeController(this.myContext);
        nc = new NotacaoController(this.myContext);
        hc = new HumorController(this.myContext);

        img_not_humor = (ImageView) getView().findViewById(R.id.img_not_humor);
        img_not_atividade = (ImageView) getView().findViewById(R.id.img_not_atividade);
        img_not_fechar = (ImageView) getView().findViewById(R.id.img_not_fechar);
        ed_not_descricao = (TextInputEditText) getView().findViewById(R.id.ed_not_Descricao);
        ed_not_titulo = (EditText) getView().findViewById(R.id.ed_not_Titulo);
        txt_not_atividade = (TextView) getView().findViewById(R.id.txt_not_atividade);
        txt_not_humor = (TextView) getView().findViewById(R.id.txt_not_humor);

        btn_not_descartar = (Button) getView().findViewById(R.id.btn_not_descartar);
        btn_not_salvar = (Button) getView().findViewById(R.id.btn_not_salvar);
        btn_not_editar = (Button) getView().findViewById(R.id.btn_not_editar);
        btn_not_excluir = (Button) getView().findViewById(R.id.btn_not_excluir);

        sharedPreferences = myContext.getSharedPreferences("CarrieOn", Context.MODE_PRIVATE);
        numero_notacao = sharedPreferences.getInt("numero_notacao", 0);

        final Notacao notacao = nc.buscarNotacaoByNumber(numero_notacao);
        final Atividade atividade = ac.buscarAtividadeByCodigo(notacao.getCodAtividade());
        final Humor humor = hc.buscarHumorByCodigo(notacao.getCodHumor());

        img_not_atividade.setImageResource(Integer.parseInt(atividade.getImg()));
        img_not_humor.setImageResource(Integer.parseInt(humor.getImg()));
        ed_not_descricao.setText(notacao.getDescricao());
        ed_not_titulo.setText(notacao.getTitulo());
        txt_not_atividade.setText(atividade.getNome());
        txt_not_humor.setText(humor.getNome());

        ed_not_titulo.setEnabled(false);
        ed_not_descricao.setEnabled(false);

        btn_not_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_not_editar.setVisibility(View.INVISIBLE);
                btn_not_excluir.setVisibility(View.INVISIBLE);
                btn_not_salvar.setVisibility(View.VISIBLE);
                btn_not_descartar.setVisibility(View.VISIBLE);
                ed_not_descricao.setEnabled(true);
                ed_not_titulo.setEnabled(true);
                ed_not_titulo.requestFocus();
            }
        });

        btn_not_descartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_not_editar.setVisibility(View.VISIBLE);
                btn_not_excluir.setVisibility(View.VISIBLE);
                btn_not_salvar.setVisibility(View.INVISIBLE);
                btn_not_descartar.setVisibility(View.INVISIBLE);
                ed_not_descricao.setEnabled(false);
                ed_not_titulo.setEnabled(false);
            }
        });

        btn_not_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = ed_not_titulo.getText().toString();
                String descricao = ed_not_descricao.getText().toString();
                String nome_atividade = txt_not_atividade.getText().toString();
                String nome_humor = txt_not_humor.getText().toString();

                Atividade a = ac.buscarAtividadeByCodigo(ac.buscarCodigoAtividadeByNome(nome_atividade));
                Humor h = hc.buscarHumorByCodigo(hc.buscarCodigoHumorByNome(nome_humor));

                notacao.setCodAtividade(a.getCodigo());
                notacao.setCodHumor(h.getCodigo());
                notacao.setDescricao(descricao);
                notacao.setTitulo(titulo);

                nc.atualizarNotacao(notacao);
                getDialog().dismiss();
                Toast.makeText(myContext,nc.getM(),Toast.LENGTH_SHORT).show();
            }
        });

        img_not_fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss(); }});

        btn_not_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nc.buscarNotacaoByNumber(notacao.getNumero()) != null) {
                    nc.excluirNotacao(notacao);

                    Notacao[] notacaos = nc.listarNotacao();
                    for(int i = 0; i<notacaos.length; i++){
                        if(notacaos[i].getNumero() > notacao.getNumero()){
                            Notacao n = notacaos[i];
                            n.setNumero(notacaos[i].getNumero()-1);
                            nc.atualizarNotacao(n);
                        }
                    }

                    getDialog().dismiss();
                    Toast.makeText(myContext,nc.getM(),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(myContext,nc.getM(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
