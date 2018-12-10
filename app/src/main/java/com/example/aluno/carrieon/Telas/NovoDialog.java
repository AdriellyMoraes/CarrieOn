package com.example.aluno.carrieon.Telas;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.carrieon.BancoDeDados.BancoDados;
import com.example.aluno.carrieon.Controller.AtividadeController;
import com.example.aluno.carrieon.Controller.HumorController;
import com.example.aluno.carrieon.Controller.NotacaoController;
import com.example.aluno.carrieon.Model.Atividade;
import com.example.aluno.carrieon.Model.Humor;
import com.example.aluno.carrieon.Model.Notacao;
import com.example.aluno.carrieon.R;
import com.example.aluno.carrieon.Telas.Adapters.AtividadesAdapter;
import com.example.aluno.carrieon.Telas.Adapters.HumoresAdapter;
import com.example.aluno.carrieon.Telas.Adapters.RegistroAdapter;
import com.example.aluno.carrieon.Telas.Fragments.RegistroFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NovoDialog extends DialogFragment {

    //Banco de dados
    private BancoDados bancoDados;
    private AtividadeController ac;
    private NotacaoController nc;
    private HumorController hc;

    EditText edTitulo;
    TextView txtNovo;
    ImageView imgNovo;
    TextInputEditText edDescricao;

    //Layout
    Button btnAdNova;
    Button btnDescartar;

    private FragmentActivity myContext;

    RecyclerView atividadeRecyclerView;
    RecyclerView humorRecyclerView;

    private RecyclerView.LayoutManager atividadeLayoutManager;
    private RecyclerView.LayoutManager humorLayoutManager;

    private AtividadesAdapter aAdapter;
    private HumoresAdapter hAdapter;
    FragmentActivity act;

    //Arrays relacionados às atividades
    private int[] images ={R.drawable.acampamento,R.drawable.aniversario,R.drawable.atraso,R.drawable.banheiro,R.drawable.banho,
                        R.drawable.boliche,R.drawable.cafe_da_manha,R.drawable.chuva,R.drawable.ciclismo,R.drawable.compras,
                        R.drawable.compromisso,R.drawable.computador,R.drawable.conserto,R.drawable.contas,
                        R.drawable.cozinhar,R.drawable.dente,R.drawable.descanso,R.drawable.festa,
                        R.drawable.foto,R.drawable.ginastica,R.drawable.jantar,R.drawable.jardinagem,
                        R.drawable.karaoke,R.drawable.lanche,R.drawable.leitura,R.drawable.ligacao,
                        R.drawable.limpeza,R.drawable.lista_compras,R.drawable.maquiagem,R.drawable.musica,
                        R.drawable.onibus,R.drawable.passeio_carro,R.drawable.passeio_moto,R.drawable.patinacao,
                        R.drawable.picnic,R.drawable.pipa,R.drawable.sorvete,R.drawable.tablet,
                        R.drawable.televisao,R.drawable.tocando,R.drawable.trabalho,R.drawable.viagem};

    private String[] texto ={"Acampamento","Aniversário","Atraso","Banheiro","Banho",
    "Boliche","Café da manhã","Chuva","Ciclismo","Compras",
    "Compromisso","Computador","Conserto","Contas",
    "Cozinhar","Dentista","Descanso","Festa",
    "Fotografia","Ginástica","Jantar","Jardinagem",
    "Karaokê","Lanche","Leitura","Ligação",
    "Limpeza","Lista de compras","Maquiagem","Música",
    "Passeio de ônibus","Passeio de carro","Passeio de moto","Patinação",
    "Piquenique","Brincadeiras","Tomando sorvete","Redes sociais",
    "Assistindo televisão","Instrumento musical","Trabalhando","Viagem"};

    //Arrays relacionados aos humores
    private int[] images_humores ={R.drawable.aborrecido,R.drawable.apaixonado,R.drawable.assustado,R.drawable.desconcertado,R.drawable.cansado,
            R.drawable.carinhoso,R.drawable.chateado,R.drawable.chocado,R.drawable.contente,
            R.drawable.curioso,R.drawable.deprimido,R.drawable.desapegado,
            R.drawable.doente,R.drawable.entediado,R.drawable.estupefato,R.drawable.faminto,
            R.drawable.feliz,R.drawable.fofo,R.drawable.indiferente,R.drawable.infeliz,
            R.drawable.irado,R.drawable.lastimoso,R.drawable.maravilhoso,R.drawable.nerd,
            R.drawable.sem_palavras,R.drawable.sensacional,R.drawable.serio,R.drawable.sonolento,
            R.drawable.sorridente,R.drawable.sortudo,R.drawable.surpreso,R.drawable.triste,
            R.drawable.uau};

    public String[] texto_humores = {"Aborrecido","Apaixonado","Assustado","Atrapalhado","Cansado",
    "Carinhoso","Chateado","Chocado","Contente",
    "Curioso","Deprimido","Desapegado",
    "Doente","Entediado","Estupefato","Faminto",
    "Feliz","Fofo","Indiferente","Infeliz",
    "Irado","Lastimoso","Maravilhoso","Estudioso",
    "Sem palavras", "Sensacional","Sério","Sonolento",
    "Sorridente","Sortudo","Surpreso","Triste",
    "Uau"};

    private int[] valor_humores ={1,3,2,2,3,2,2,3,2,1,2,2,1,2,2,2,3,3,2,1,1,1,3,2,2,3,2,2,3,3,2,1,2};

    int posicaoAtividade,posicaoHumor;
    AtividadesAdapter.OnItemClickListener listener;
    HumoresAdapter.OnItemClickListener listenerHumores;

    //
    //DatabaseReference databaseAtividade;

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialog_novo, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //Inicializando controllers
        ac = new AtividadeController(this.myContext);
        nc = new NotacaoController(this.myContext);
        hc = new HumorController(this.myContext);

        //Salvando atividades e humores no banco de dados
        //databaseAtividade = FirebaseDatabase.getInstance().getReference("Lcom/google/firebase/FirebaseApp");
        saveAllBD();


        //
        atualizarArrays();

        //Inicializando botões
        btnAdNova = (Button) getView().findViewById(R.id.btnAdNota);
        btnDescartar = (Button) getView().findViewById(R.id.btnDescartar);

        edTitulo = (EditText) getView().findViewById(R.id.edTitulo);
        txtNovo = (TextView) getView().findViewById(R.id.txtNovo);
        imgNovo = (ImageView) getView().findViewById(R.id.imgNovo);
        edDescricao = (TextInputEditText) getView().findViewById(R.id.edDescricao);

        ((InputMethodManager)myContext.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(edTitulo.getWindowToken(), 0);

        //
        atividadeLayoutManager = new GridLayoutManager(myContext,4);
        atividadeRecyclerView = (RecyclerView) getView().findViewById(R.id.recycle_atividades);
        atividadeRecyclerView.setHasFixedSize(true);
        atividadeRecyclerView.setLayoutManager(atividadeLayoutManager);

        //
        humorLayoutManager = new GridLayoutManager(myContext,4);
        humorRecyclerView = (RecyclerView) getView().findViewById(R.id.recycle_humores);
        humorRecyclerView.setHasFixedSize(true);
        humorRecyclerView.setLayoutManager(humorLayoutManager);

        //
        listener = new AtividadesAdapter.OnItemClickListener() {
            @Override
            public int onClick(View v,int position) {
                posicaoAtividade = position;
                return 0;
            }
        };
        aAdapter = new AtividadesAdapter(myContext,images,texto,listener);
        atividadeRecyclerView.setAdapter(aAdapter);

        //
        listenerHumores = new HumoresAdapter.OnItemClickListener() {
            @Override
            public int onClick(View v,int position) {
                posicaoHumor = position;
                return 0;
            }
        };
        hAdapter = new HumoresAdapter(myContext,images_humores,texto_humores,listenerHumores);
        humorRecyclerView.setAdapter(hAdapter);

        btnDescartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    this.finalize();

                    Activity act = getActivity();
                    if (act != null) {
                        startActivity(new Intent(act, HomeActivity.class));
                        act.finish();
                    }
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

        btnAdNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    cadastrarNota();
            }
        });


    }

    public void cadastrarNota(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        String currentDateandTime = sdf.format(new Date());

        String nomeAtividade = texto[posicaoAtividade];
        int codigoAtividade = ac.buscarCodigoAtividadeByNome(nomeAtividade);

        String nomeHumor = texto_humores[posicaoHumor];
        int codigoHumor = hc.buscarCodigoHumorByNome(nomeHumor);

        SharedPreferences sharedPreferences = myContext.getSharedPreferences("CarrieOn", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("Email", "w");

        Notacao notacao = new Notacao();
        notacao.setCodHumor(codigoHumor);
        notacao.setCodAtividade(codigoAtividade);
        notacao.setDescricao(edDescricao.getText().toString());
        notacao.setDataHoraCriacao(currentDateandTime);
        notacao.setTitulo(edTitulo.getText().toString());
        notacao.setEmailPessoa(email);

        boolean cadastrado = nc.cadastrarNotacao(notacao);

        if(cadastrado){
            Toast.makeText(myContext,ac.getM(),Toast.LENGTH_SHORT).show();
            act = (FragmentActivity) getActivity();
            try {
                this.finalize();
                startActivity(new Intent(act,HomeActivity.class));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        }else {
                Toast.makeText(myContext, ac.getM(), Toast.LENGTH_SHORT).show();
        }

    }

    public void saveAllBD(){
        Atividade atividade = new Atividade();
        Humor humor = new Humor();

        if(ac.buscarAtividadeByCodigo(1) == null && hc.buscarHumorByCodigo(1) == null) {
            for (int i = 0; i < images.length; i++) {
                atividade.setImg(Integer.toString(images[i]));
                atividade.setNome(texto[i]);
                ac.cadastrarAtividade(atividade);
                //databaseAtividade.child("atividades").push().setValue(atividade);
            }

            for(int i=0; i<images_humores.length; i++){
                humor.setImg(Integer.toString(images_humores[i]));
                humor.setNome(texto_humores[i]);
                humor.setValor(valor_humores[i]);
                hc.cadastrarHumor(humor);
            }
        }
    }

    public void atualizarArrays(){
        Atividade[] atividades  = ac.listarAtividades();
        Humor[] humores = hc.listarHumores();

        for(int i=0; i<images.length; i++){
            images[i] = Integer.parseInt(atividades[i].getImg());
            texto[i] = atividades[i].getNome();
        }

        for(int i=0; i<images_humores.length; i++){
            images_humores[i] = Integer.parseInt(humores[i].getImg());
            texto_humores[i] = humores[i].getNome();
        }

    }

}
