package com.example.aluno.carrieon.Telas.Adapters;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.carrieon.R;

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.ImageViewHolderR> {

    Context context;
    private String txt_Imagens_atividades[],txt_descricao[], txt_data_hora_criacao[], txt_Imagens_Humores[], txt_Titulo[];
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        int onClick(View view, int position);
    }

    public RegistroAdapter(Context context, String txt_Imagens_atividades[], String txt_descricao[], String txt_data_hora_criacao[], String txt_Imagens_Humores[], String txt_Titulo[], OnItemClickListener listener){
        this.context = context;
        this.txt_Imagens_atividades = txt_Imagens_atividades;
        this.txt_descricao = txt_descricao;
        this.txt_data_hora_criacao = txt_data_hora_criacao;
        this.txt_Imagens_Humores = txt_Imagens_Humores;
        this.txt_Titulo = txt_Titulo;
        this.listener = listener;
    }

    @Override
    public ImageViewHolderR onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.registro_view_holder, parent, false);
        ImageViewHolderR imageViewHolder = new ImageViewHolderR(view,listener);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolderR holder, int position) {
        holder.imgeAtiv.setImageResource(Integer.parseInt(txt_Imagens_atividades[position]));
        holder.editDescricao.setText(txt_descricao[position]);
        holder.txtDataCriacao.setText(txt_data_hora_criacao[position]);
        holder.imgeHumor.setImageResource(Integer.parseInt(txt_Imagens_Humores[position]));
        holder.txtTitulo.setText(txt_Titulo[position]);
    }

    @Override
    public int getItemCount() {
        return txt_Imagens_atividades.length;
    }

    /*public void updateList(Atividade atividade){
        insertItem(atividade);
    }

    public void insertItem(Atividade atividade){
        listAtividades.add(atividade);
        notifyItemInserted(getItemCount());
    }

    public void updateItem(int position, String nome){
        Atividade atividade = listAtividades.get(position);
        atividade.setNome(nome);
        notifyItemChanged(position);
    }

    private void removerItem(int position){
        listAtividades.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listAtividades.size());
    }*/

    public class ImageViewHolderR extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtDataCriacao;
        TextView editDescricao;
        TextView txtTitulo;
        ImageView imgeHumor;
        ImageView imgeAtiv;
        OnItemClickListener listener;

        public ImageViewHolderR(View itemView, OnItemClickListener listener2) {
            super(itemView);
            txtDataCriacao = (TextView) itemView.findViewById(R.id.txtDataCriacao);
            editDescricao = (TextView) itemView.findViewById(R.id.editDescricao);
            txtTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            imgeHumor = (ImageView) itemView.findViewById(R.id.imgeHumor);
            imgeAtiv = (ImageView) itemView.findViewById(R.id.imgeAtiv);
            itemView.setOnClickListener(this);
            this.listener = listener2;
        }

        public int posicao(){
            return getAdapterPosition();
        }

        @Override
        public void onClick(View v) {
            ImageViewHolderR.this.listener.onClick(v,getAdapterPosition());
            posicao();
        }
    }


}
