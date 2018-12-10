package com.example.aluno.carrieon.Telas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class CalendarioAdapter extends RecyclerView.Adapter<CalendarioAdapter.ImageViewHolder> {

    Context context;
    private Atividade[] atividades;
    private Humor[] humores;
    private Notacao[] notacaos;
    private OnItemClickListener listener;

    //Banco de dado
    private AtividadeController ac;
    private NotacaoController nc;
    private HumorController hc;


    public interface OnItemClickListener{
        int onClick(View view, int position);
    }

    public CalendarioAdapter(Context context, Notacao[] notacaos, OnItemClickListener listener){
        this.context = context;
        this.notacaos = notacaos;
        this.listener = listener;

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendario_view_holder, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view,listener);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        lerNotacao();

        holder.icnHumor.setImageResource(Integer.parseInt(atividades[position].getImg()));
        holder.icnAtividade.setImageResource(Integer.parseInt(humores[position].getImg()));
        holder.txtoDescrip.setText(notacaos[position].getDescricao());
    }

    private void lerNotacao(){
        //Inicializando controllers
        ac = new AtividadeController(this.context);
        nc = new NotacaoController(this.context);
        hc = new HumorController(this.context);

        atividades = new Atividade[notacaos.length];
        humores = new Humor[notacaos.length];

        for(int i=0; i<notacaos.length; i++){
            atividades[i] = ac.buscarAtividadeByCodigo(notacaos[i].getCodAtividade());
            humores[i] = hc.buscarHumorByCodigo(notacaos[i].getCodHumor());
        }

    }

    public void mudarNotacoes(Notacao[] notacao){

        this.notacaos = notacao;
        //Inicializando controllers
        ac = new AtividadeController(this.context);
        nc = new NotacaoController(this.context);
        hc = new HumorController(this.context);

        atividades = new Atividade[notacaos.length];
        humores = new Humor[notacaos.length];

        for(int i=0; i<notacaos.length; i++){
            atividades[i] = ac.buscarAtividadeByCodigo(notacaos[i].getCodAtividade());
            humores[i] = hc.buscarHumorByCodigo(notacaos[i].getCodHumor());
        }

    }

    @Override
    public int getItemCount() {
        return notacaos.length;
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

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView icnHumor;
        ImageView icnAtividade;
        TextView txtoDescrip;
        OnItemClickListener listener;

        public ImageViewHolder(View itemView, OnItemClickListener listener2) {
            super(itemView);
            icnHumor = (ImageView) itemView.findViewById(R.id.icnHumor);
            icnAtividade = (ImageView)itemView.findViewById(R.id.icnAtividade);
            txtoDescrip = (TextView) itemView.findViewById(R.id.txtoDescrip);
            this.listener = listener2;
            itemView.setOnClickListener(this);
        }

        public int posicao(){
            return getAdapterPosition();
        }

        @Override
        public void onClick(View v) {
            ImageViewHolder.this.listener.onClick(v,getAdapterPosition());
            posicao();
        }
    }

}
