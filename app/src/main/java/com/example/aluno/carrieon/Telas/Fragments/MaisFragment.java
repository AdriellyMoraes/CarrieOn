package com.example.aluno.carrieon.Telas.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.aluno.carrieon.BancoDeDados.FirebaseConexao;
import com.example.aluno.carrieon.R;
import com.example.aluno.carrieon.Telas.ContaActivity;
import com.example.aluno.carrieon.Telas.EntradaActivity;
import com.example.aluno.carrieon.Telas.LembreteActivity;
import com.example.aluno.carrieon.Telas.ListaAtividadesActivity;
import com.example.aluno.carrieon.Telas.ListaHumoresActivity;
import com.example.aluno.carrieon.Telas.SobreDialog;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class MaisFragment extends Fragment {

    private static final String TAG = "MaisFragment";

    Button btnConta;
    Button btnEdHumores;
    Button btnEdAtividades;
    Button btnLembrete;
    Button btnSobre;
    Button btnSair;

    private FragmentActivity myContext;

    public MaisFragment() {
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
        return inflater.inflate(R.layout.fragment_mais, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnConta = (Button) getView().findViewById(R.id.btnConta);
        btnEdHumores = (Button) getView().findViewById(R.id.btnEdHumores);
        btnEdAtividades = (Button) getView().findViewById(R.id.btnEdAtividades);
        btnLembrete = (Button) getView().findViewById(R.id.btnLembrete);
        btnSobre = (Button) getView().findViewById(R.id.btnSobre);
        btnSair = (Button) getView().findViewById(R.id.btnSair);


        btnEdHumores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity act = getActivity();
                if (act != null) {
                    startActivity(new Intent(act, ListaHumoresActivity.class));
                }
            }
        });

        btnEdAtividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity act = getActivity();
                if (act != null) {
                    startActivity(new Intent(act, ListaAtividadesActivity.class));
                }
            }
        });


        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentManager fragManager = myContext.getFragmentManager();
                SobreDialog dialog = new SobreDialog();
                dialog.show(fragManager,"g");
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ab = new AlertDialog.Builder(getContext());
                ab.setTitle("Saída");
                ab.setMessage("Você tem certeza que deseja sair?");

                ab.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            this.finalize();
                            Activity act = getActivity();
                            if (act != null) {
                                startActivity(new Intent(act, MaisFragment.class));
                                act.finish();
                            }
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                });

                ab.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentActivity act = getActivity();
                        if (act != null) {
                            FirebaseConexao.logOut();
                            Toast.makeText(getContext(), "Desconectado!", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("CarrieOn", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor;
                            editor = sharedPreferences.edit();
                            editor.putBoolean("Logado", false);
                            editor.putString("Email", "");
                            editor.commit();
                            startActivity(new Intent(act, EntradaActivity.class));
                            act.finish();
                        }
                    }

                });
                        ab.show();


            }
        });

        btnConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity act = getActivity();
                if (act != null) {
                    startActivity(new Intent(act, ContaActivity.class));
                }
            }
        });

        btnLembrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*AlertDialog.Builder a = new AlertDialog.Builder(myContext).setMessage("aaaaaaaaaaa");
                a.create();
                a.setView(R.layout.activity_lembrete);
                a.show();*/

                FragmentActivity act = getActivity();
                if (act != null) {
                    startActivity(new Intent(act, LembreteActivity.class));
                }
            }
        });
    }

}
