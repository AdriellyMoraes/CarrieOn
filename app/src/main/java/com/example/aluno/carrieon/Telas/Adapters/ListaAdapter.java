package com.example.aluno.carrieon.Telas.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.carrieon.Controller.AtividadeController;
import com.example.aluno.carrieon.Controller.HumorController;
import com.example.aluno.carrieon.Model.Atividade;
import com.example.aluno.carrieon.Model.Humor;
import com.example.aluno.carrieon.R;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ImageViewAdaptador>{

    Context context;
    private AtividadeController ac;
    private HumorController hc;
    private String[] nome;
    private String[] imagem;
    private int ac_or_hc;
    private String old;
    private OnItemClickListener listener;


    public interface OnItemClickListener{
        int onClick(View view, int position);
    }

    public ListaAdapter(Context context, String[] nome, String[] imagem, OnItemClickListener listener, int ac_or_hc){
        this.context = context;
        this.nome = nome;
        this.imagem = imagem;
        this.ac_or_hc = ac_or_hc;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageViewAdaptador onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_editar, parent, false);
        ImageViewAdaptador imageViewHolder = new ImageViewAdaptador(view,listener);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewAdaptador holder, int position) {
        holder.img_opcoes.setImageResource(Integer.parseInt(imagem[position]));
        holder.txt_opcoes.setText(nome[position]);
        holder.img_edit.setImageResource(R.drawable.edit_icon);
        holder.img_delete.setImageResource(R.drawable.delete_icon);
        holder.img_confAtu.setImageResource(R.drawable.icon_confirmar);
        holder.img_cancel.setImageResource(R.drawable.ic_cancel);
    }

    @Override
    public int getItemCount() {
        return nome.length;
    }

    public class ImageViewAdaptador extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView img_edit;
        ImageView img_delete;
        ImageView img_confAtu;
        ImageView img_cancel;

        ImageView img_opcoes;
        EditText txt_opcoes;

        OnItemClickListener listener;

        public ImageViewAdaptador(View itemView, OnItemClickListener listener2) {
            super(itemView);

            //Inicializando componentes funcionais
            img_edit = (ImageView)itemView.findViewById(R.id.img_edit);
            img_delete = (ImageView)itemView.findViewById(R.id.img_delete);
            img_confAtu = (ImageView)itemView.findViewById(R.id.img_confAtu);
            img_cancel = (ImageView)itemView.findViewById(R.id.img_cancel);

            //Inicializando componentes visuais
            img_opcoes = (ImageView)itemView.findViewById(R.id.img_opcoes_humores);
            txt_opcoes = (EditText)itemView.findViewById(R.id.text_opcoes_humores);

            //Inicializando controlador
            ac = new AtividadeController(context);
            hc = new HumorController(context);

            this.listener = listener2;
            itemView.setOnClickListener(this);
        }

        public int posicao(){
            return getAdapterPosition();
        }

        @Override
        public void onClick(View v) {


            img_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    old = txt_opcoes.getText().toString();
                    img_delete.setVisibility(View.INVISIBLE);
                    img_confAtu.setVisibility(View.VISIBLE);
                    img_confAtu.setClickable(true);
                    img_cancel.setVisibility(View.VISIBLE);
                    img_cancel.setClickable(true);
                    img_edit.setVisibility(View.INVISIBLE);
                    txt_opcoes.setEnabled(true);
                    txt_opcoes.setSelection(txt_opcoes.getText().length());
                    txt_opcoes.requestFocus();
                }
            });

            img_confAtu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ac_or_hc == 1){
                        Atividade atividade = ac.buscarAtividadeByCodigo(getAdapterPosition()+1);
                        atividade.setNome(txt_opcoes.getText().toString());
                        ac.atualizarAtividade(atividade.getCodigo(),atividade);
                        txt_opcoes.setEnabled(false);
                        img_cancel.setVisibility(View.INVISIBLE);
                        img_confAtu.setVisibility(View.INVISIBLE);
                        img_edit.setVisibility(View.VISIBLE);
                        img_delete.setVisibility(View.VISIBLE);
                        ((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(txt_opcoes.getWindowToken(), 0);
                        Toast.makeText(context, ac.getM(), Toast.LENGTH_SHORT).show();
                    }else if(ac_or_hc == 2){
                        Humor humor = hc.buscarHumorByCodigo(getAdapterPosition()+1);
                        humor.setNome(txt_opcoes.getText().toString());
                        hc.atualizarHumor(humor.getCodigo(),humor);
                        txt_opcoes.setEnabled(false);
                        img_cancel.setVisibility(View.INVISIBLE);
                        img_confAtu.setVisibility(View.INVISIBLE);
                        img_edit.setVisibility(View.VISIBLE);
                        img_delete.setVisibility(View.VISIBLE);
                        ((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(txt_opcoes.getWindowToken(), 0);
                        Toast.makeText(context, hc.getM(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "ERRO! Impossível atualizar no momento!",Toast.LENGTH_LONG).show();
                    }
                }
            });

            img_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    img_delete.setVisibility(View.VISIBLE);
                    img_confAtu.setVisibility(View.INVISIBLE);
                    img_cancel.setVisibility(View.INVISIBLE);
                    img_edit.setVisibility(View.VISIBLE);
                    txt_opcoes.setText(old);
                    txt_opcoes.setEnabled(false);
                }
            });

            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*AlertDialog.Builder bc = new AlertDialog.Builder(context);
                    bc.setTitle("Exclusão");*/
                    if (ac_or_hc == 1){
                        /*bc.setMessage("Esta ação é irreversível! Você tem certeza que deseja excluir esta atividade?");
                        bc.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {*/
                                Atividade atividade = ac.buscarAtividadeByCodigo(getAdapterPosition()+1);
                                ac.excluirAtividade(atividade);
                                Toast.makeText(context, ac.getM(),Toast.LENGTH_SHORT).show();
                            /*}
                        });*/
                    }else if(ac_or_hc == 2){
                        /*bc.setMessage("Esta ação é irreversível! Você tem certeza que deseja excluir este humor?");
                        bc.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {*/
                                Humor humor = hc.buscarHumorByCodigo(getAdapterPosition()+1);
                                hc.excluirHumor(humor);
                                Toast.makeText(context, hc.getM(),Toast.LENGTH_SHORT).show();
                            /*}
                        });*/
                    }else{
                        Toast.makeText(context, "ERRO! Impossível excluir no momento!",Toast.LENGTH_LONG).show();
                    }

                    /*bc.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                this.finalize();
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                    });
                    bc.show();*/
                }
            });

            ImageViewAdaptador.this.listener.onClick(v,getAdapterPosition());
            posicao();
        }
    }
}
