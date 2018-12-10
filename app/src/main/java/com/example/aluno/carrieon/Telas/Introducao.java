package com.example.aluno.carrieon.Telas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.carrieon.Config;
import com.example.aluno.carrieon.Controller.HumorController;
import com.example.aluno.carrieon.Controller.NotacaoController;
import com.example.aluno.carrieon.Controller.PessoaController;
import com.example.aluno.carrieon.Model.Humor;
import com.example.aluno.carrieon.Model.Notacao;
import com.example.aluno.carrieon.R;
import com.example.aluno.carrieon.Telas.Adapters.SliderAdapter;

public class Introducao extends AppCompatActivity {

    private RelativeLayout mBackground;

    private ViewPager mSlideViewPager;

    private TextView[] mDots;

    private LinearLayout mDotLayout;

    private SliderAdapter sliderAdapter;

    private Button mNextBtn;
    private Button mBackBtn;

    private int mCurrentPage;

    SharedPreferences sharedPreferences;
    boolean logado;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        mNextBtn = (Button) findViewById(R.id.nextBtn);
        mBackBtn = (Button) findViewById(R.id.prevBtn);
        mBackground = (RelativeLayout) findViewById(R.id.slide_back);

        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener );

        sharedPreferences = getSharedPreferences("CarrieOn", Context.MODE_PRIVATE);
        logado = sharedPreferences.getBoolean("Logado", false);

        //Se usuário já estiver logado, carrega a página inicial, senão, carrega a tela de login
        if (logado == true) {
            intent = new Intent(Introducao.this, HomeActivity.class);
            startActivity(intent);
        }

        mNextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                int cont = mCurrentPage + 1;
                if(cont < 3) {
                    mSlideViewPager.setCurrentItem(cont);
                }else if(cont == 3){
                    Intent inte = new Intent(Introducao.this, EntradaActivity.class);
                    startActivity(inte);
                }



            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                mSlideViewPager.setCurrentItem(mCurrentPage - 1);

            }
        });

    }

    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for(int i = 0; i < mDots.length; i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);

        }

        if(mDots.length > 0){

            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));


        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);
            mCurrentPage = i;

            if(i == 0){

                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);
                mNextBtn.setText("Avançar");
                mBackBtn.setText("");
                mBackground.setBackgroundColor(getResources().getColor(R.color.colorGreen));

            } else if(i == mDots.length - 1){

                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Começar");
                mBackBtn.setText("Voltar");
                mBackground.setBackgroundColor(getResources().getColor(R.color.colorBlue));

            } else {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Avançar");
                mBackBtn.setText("Voltar");
                mBackground.setBackgroundColor(getResources().getColor(R.color.colorPurplee));
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
