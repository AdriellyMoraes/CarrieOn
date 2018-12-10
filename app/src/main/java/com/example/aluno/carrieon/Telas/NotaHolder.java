package com.example.aluno.carrieon.Telas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aluno.carrieon.R;

public class NotaHolder extends RecyclerView.ViewHolder {

    TextView txtNovo;
    ImageView imgNovo;

    public NotaHolder(View itemView) {
        super(itemView);
        txtNovo = (TextView)itemView.findViewById(R.id.txtNovo);
        imgNovo = (ImageView)itemView.findViewById(R.id.imgNovo);
    }
}
