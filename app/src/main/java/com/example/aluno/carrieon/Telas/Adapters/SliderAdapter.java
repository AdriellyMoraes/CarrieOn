package com.example.aluno.carrieon.Telas.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aluno.carrieon.R;

/**
 * Created by Aluno on 08/08/2018.
 */

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {

        this.context = context;
    }

    //Arrays
    public int[] slide_images ={
            R.drawable.ic1,
            R.drawable.ic2,
            R.drawable.ic3
    };

    public String[] slide_headings ={
            "ORGANIZE SUA VIDA!", "LIVRE-SE DAS DESCULPAS!", "SEJA MAIS PRODUTIVO!"
    };

    public String[] slide_descs = {
            "CarrieOn funciona como um diário, onde você pode registrar seu dia a dia.",
            "Você pode consultar suas atividades e finalmente livrar-se de hábitos negativos.",
            "Através de estatísticas administre seus hábitos e tenha uma vida de sucesso. Vamos começar?"
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);


        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){

        container.removeView((RelativeLayout)object);

    }
}
