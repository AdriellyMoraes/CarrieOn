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

public class HumoresAdapter extends RecyclerView.Adapter<HumoresAdapter.ImageViewHolderH> {

    Context context;
    private int[] img_humores;
    private String[] txt_humores;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        int onClick(View view, int position);
    }

    public HumoresAdapter(Context context, int[] img_humores, String[] txt_humores, OnItemClickListener listener){
        this.context = context;
        this.img_humores = img_humores;
        this.txt_humores = txt_humores;
        this.listener = listener;
    }

    @Override
    public ImageViewHolderH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);
        ImageViewHolderH imageViewHolder = new ImageViewHolderH(view,listener);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolderH holder, int position) {
        int imgAtiv_id = img_humores[position];
        String txtAtiv_id = txt_humores[position];
        holder.txtNovo.setText(txtAtiv_id);
        holder.imgNovo.setImageResource(imgAtiv_id);
    }

    @Override
    public int getItemCount() {
        return img_humores.length;
    }

    public class ImageViewHolderH extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtNovo;
        ImageView imgNovo;
        OnItemClickListener listener;

        public ImageViewHolderH(View itemView, OnItemClickListener listener2) {
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
            ImageViewHolderH.this.listener.onClick(v,getAdapterPosition());
            Toast.makeText(context,"AAAAA",Toast.LENGTH_LONG);
            posicao();
        }
    }

}
