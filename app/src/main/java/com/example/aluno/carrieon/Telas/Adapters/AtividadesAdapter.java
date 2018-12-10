package com.example.aluno.carrieon.Telas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.carrieon.R;

public class AtividadesAdapter extends RecyclerView.Adapter<AtividadesAdapter.ImageViewHolder> {

    Context context;
    private int[] img_atividades;
    private String[] txt_atividades;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        int onClick(View view, int position);
    }

    public AtividadesAdapter(Context context, int[] img_atividades, String[] txt_atividades, OnItemClickListener listener){
        this.context = context;
        this.img_atividades = img_atividades;
        this.txt_atividades = txt_atividades;
        this.listener = listener;

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view,listener);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        int imgAtiv_id = img_atividades[position];
        String txtAtiv_id = txt_atividades[position];
        holder.txtNovo.setText(txtAtiv_id);
        holder.imgNovo.setImageResource(imgAtiv_id);
    }

    @Override
    public int getItemCount() {
        return img_atividades.length;
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

        TextView txtNovo;
        ImageView imgNovo;
        OnItemClickListener listener;

        public ImageViewHolder(View itemView, OnItemClickListener listener2) {
            super(itemView);
            txtNovo = (TextView)itemView.findViewById(R.id.txtNovo);
            imgNovo = (ImageView)itemView.findViewById(R.id.imgNovo);
            this.listener = listener2;
            itemView.setOnClickListener(this);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });*/
        }

        public int posicao(){
            return getAdapterPosition();
        }

        @Override
        public void onClick(View v) {
            ImageViewHolder.this.listener.onClick(v,getAdapterPosition());
            Toast.makeText(context,"AAAAA",Toast.LENGTH_LONG);
            posicao();
        }
    }

}
