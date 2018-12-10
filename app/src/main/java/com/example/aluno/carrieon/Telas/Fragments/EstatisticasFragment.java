package com.example.aluno.carrieon.Telas.Fragments;


import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aluno.carrieon.Controller.HumorController;
import com.example.aluno.carrieon.Controller.NotacaoController;
import com.example.aluno.carrieon.Model.Humor;
import com.example.aluno.carrieon.Model.Notacao;
import com.example.aluno.carrieon.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstatisticasFragment extends Fragment {

    private static final String TAG="EstatísticaFragment";

    private PieChart pieChart;
    private BarChart barChart;

    String[] values = new String[] {"Dom","Seg","Ter","Qua","Qui","Sex","Sab"};
    int[] valorHumor = new int[] {0,0,0,0,0,0,0};
    int[] cores = new int[] {Color.YELLOW,Color.BLUE,Color.MAGENTA,Color.GREEN,R.color.colorPurplee,Color.RED,Color.CYAN};

    NotacaoController nc;
    HumorController hc;
    Notacao[] notacao;

    public EstatisticasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estatisticas, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pieChart = (PieChart) getView().findViewById(R.id.grafPie);
        barChart = (BarChart) getView().findViewById(R.id.grafBar);

        nc = new NotacaoController(getContext());
        hc = new HumorController(getContext());

        lerNotacao();

        createCharts();
    }

    static int dayofweek(int d, int m, int y)
    {
        int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
        y -= (m < 3) ? 1 : 0;
        return ( y + y/4 - y/100 + y/400 + t[m-1] + d) % 7;
    }

    public void lerNotacao(){
        valorHumor = new int[] {0,0,0,0,0,0,0};

        SimpleDateFormat dia = new SimpleDateFormat("dd");
        String currentDia = dia.format(new Date());
        SimpleDateFormat mes = new SimpleDateFormat("MM");
        String currentMes = mes.format(new Date());
        SimpleDateFormat ano = new SimpleDateFormat("yyyy");
        String currentAno = ano.format(new Date());
        int day = dayofweek(Integer.parseInt(currentDia), Integer.parseInt(currentMes), Integer.parseInt(currentAno));

        if(day >= 0){
            for(int j=0; j<day+1; j++){
                int dayWeek = day;
                notacao = nc.listarNotacaoData(Integer.parseInt(currentAno), Integer.parseInt(currentMes), Integer.parseInt(currentDia)-j);

                if(notacao != null){
                    dayWeek -= j;
                    for(int i=0; i<notacao.length; i++){
                        Humor humor = hc.buscarHumorByCodigo(notacao[i].getCodHumor());
                        valorHumor[dayWeek] += humor.getValor();
                        valorHumor[dayWeek] = valorHumor[dayWeek]/notacao.length;
                    }
                }
            }
        }
    }

    private Chart getSameChart(Chart chart, int corBackground, int animacaoY){
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(corBackground);
        chart.animateY(animacaoY);
        legenda(chart);

        return chart;
    }

    private void legenda(Chart chart){
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry>entries=new ArrayList<>();

        for(int i=0; i<values.length; i++){
            LegendEntry entry = new LegendEntry();
            entry.formColor = cores[i];
            entry.label = values[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }

    private ArrayList<BarEntry>getBarEntries(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        for(int i=0; i < valorHumor.length; i++)
            entries.add(new BarEntry(i,valorHumor[i]));
        return entries;
    }

    private ArrayList<PieEntry>getPieEntries(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        for(int i=0; i < valorHumor.length; i++)
            if(valorHumor[i] != 0)
            entries.add(new PieEntry(i,valorHumor[i]));
        return entries;
    }

    private void axisX(XAxis xAxis){
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(values));
    }

    private void axisLeft(YAxis xAxis){
        xAxis.setSpaceTop(30);
        xAxis.setAxisMinimum(0);
    }

    private void axisRight(YAxis axis){
        axis.setEnabled(false);
    }

    public void createCharts(){
        barChart = (BarChart)getSameChart(barChart, R.color.colorGreenNew,3000);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(false);
        barChart.setData(getBarData());
        barChart.invalidate();

        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());

        pieChart = (PieChart)getSameChart(pieChart, R.color.colorGreenNew,3000);
        pieChart.setHoleRadius(9);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData());
        pieChart.invalidate();
    }

    private DataSet getData(DataSet dataSet){
        dataSet.setColors(cores);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(10);
        return dataSet;
    }

    private BarData getBarData(){
        BarDataSet barDataSet = (BarDataSet)getData(new BarDataSet(getBarEntries(),""));

        barDataSet.setBarShadowColor(Color.LTGRAY);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }

    private PieData getPieData(){
        PieDataSet pieDataSet = (PieDataSet) getData(new PieDataSet(getPieEntries(),""));

        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }
}
