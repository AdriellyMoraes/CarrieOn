package com.example.aluno.carrieon.Telas.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.aluno.carrieon.Controller.AtividadeController;
import com.example.aluno.carrieon.Controller.HumorController;
import com.example.aluno.carrieon.Controller.NotacaoController;
import com.example.aluno.carrieon.Model.Atividade;
import com.example.aluno.carrieon.Model.Humor;
import com.example.aluno.carrieon.Model.Notacao;
import com.example.aluno.carrieon.R;
import com.example.aluno.carrieon.Telas.Adapters.CalendarioAdapter;
import com.example.aluno.carrieon.Telas.LembreteActivity;
import com.example.aluno.carrieon.Telas.NotacaoDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarioFragment extends Fragment {

    CalendarView calendario;

    //Banco de dado
    private AtividadeController ac;
    private NotacaoController nc;
    private HumorController hc;

    private FragmentActivity myContext;

    RecyclerView calendarioRecyclerView;
    private RecyclerView.LayoutManager calendarioLayoutManager;
    private CalendarioAdapter cAdapter;

    private String[] img_atividades, img_humores, txt_descricao;
    Notacao[] notacaos;

    CalendarioAdapter.OnItemClickListener listener;

    public CalendarioFragment() {
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
        return inflater.inflate(R.layout.fragment_calendario, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String languageToLoad  = "pt-br"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getContext().getResources().updateConfiguration(config,
                getContext().getResources().getDisplayMetrics());
        /*view = setContentView(R.layout.main);
        return view;*/

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendario = (CalendarView) getView().findViewById(R.id.calendarView2);

        //Inicializando controllers
        ac = new AtividadeController(this.myContext);
        nc = new NotacaoController(this.myContext);
        hc = new HumorController(this.myContext);

        calendarioLayoutManager = new LinearLayoutManager(getContext());
        calendarioRecyclerView = (RecyclerView) getView().findViewById(R.id.recycle_calendario);
        calendarioRecyclerView.setLayoutManager(calendarioLayoutManager);
        calendarioRecyclerView.setVisibility(View.VISIBLE);
        calendarioRecyclerView.addItemDecoration(new DividerItemDecoration(myContext, DividerItemDecoration.VERTICAL));

        SimpleDateFormat dia = new SimpleDateFormat("dd");
        String currentDia = dia.format(new Date());
        SimpleDateFormat mes = new SimpleDateFormat("MM");
        String currentMes = mes.format(new Date());
        SimpleDateFormat ano = new SimpleDateFormat("yyyy");
        String currentAno = ano.format(new Date());

        //
        listener = new CalendarioAdapter.OnItemClickListener() {
            @Override
            public int onClick(View v,int position) {
                int posicaoAtividade = position+1;
                SharedPreferences sharedPreferences = myContext.getSharedPreferences("CarrieOn", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor;
                editor = sharedPreferences.edit();
                editor.putInt("numero_notacao",posicaoAtividade);
                editor.commit();

                NotacaoDialog dialog = new NotacaoDialog();
                dialog.setStyle(20,0);
                dialog.show(getFragmentManager(),"y");
                return 0;
            }
        };

        cAdapter = new CalendarioAdapter(myContext,notacaos, listener);
        calendarioRecyclerView.setAdapter(cAdapter);

        lerNotacao(Integer.parseInt(currentAno),Integer.parseInt(currentMes),Integer.parseInt(currentDia));

        //Toast.makeText(myContext,"A"+notacaos[0].getDescricao()+"AAA",Toast.LENGTH_SHORT);

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int ano, int mes, int dia) {
                lerNotacao(ano,mes+1,dia);
               // Toast.makeText(myContext,"A    AAA",Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void lerNotacao(int ano, int mes, int dia){
        if(nc.listarNotacao() != null) {
            notacaos = nc.listarNotacaoData(ano,mes,dia);
            //notacaos = nc.listarNotacao();
            cAdapter.mudarNotacoes(notacaos);
            cAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(myContext,"Sem registros!",Toast.LENGTH_SHORT).show();
        }
    }

}
