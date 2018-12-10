package com.example.aluno.carrieon.Telas;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aluno.carrieon.R;

public class SobreDialog extends DialogFragment {

    //Layout_sobre
    Button btnTermos;
    Button btnSobreNos;
    Button btnDuvidas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.layout_sobre, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnTermos = (Button) getView().findViewById(R.id.btnTermos);
        btnSobreNos = (Button) getView().findViewById(R.id.btnSobreNos);
        btnDuvidas = (Button) getView().findViewById(R.id.btnDuvidas);

        btnTermos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TermosDeUso.class);
                startActivity(intent);

            }
        });

        btnSobreNos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog d = new Dialog(getContext());
                d.setContentView(R.layout.sobre_nos_layout);
                d.show();
                /*Intent intent = new Intent(getActivity(), SobreNosDialog.class);
                startActivity(intent);*/
            }
        });

        btnDuvidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EnviarEmailActivity.class);
                startActivity(intent);
            }
        });
    }

}
