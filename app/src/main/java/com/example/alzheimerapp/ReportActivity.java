package com.example.alzheimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.alzheimerapp.databasePills.DatabaseClass;
import com.example.alzheimerapp.databasePills.EntityClass;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    PieChart pieChart;
List<EntityClass> dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        pieChart = findViewById(R.id.pieChart);
        DatabaseClass databaseClass = DatabaseClass.getDatabase(getApplicationContext());
        dates = databaseClass.EventDao().getAllData();
        setUpPieChart();
        loadPieChartData();
    }

    private void setUpPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setEntryLabelTextSize(11);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Trace Pills");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData(){
       ArrayList<PieEntry> entries = new ArrayList<>();
       for(int i=0; i<dates.size();i++) {

           String values = dates.get(i).getEventname() + "\n" + dates.get(i).getEventdate();
           entries.add(new PieEntry(1, values));
       }

       ArrayList<Integer> colors = new ArrayList<>();
       for(int color: ColorTemplate.MATERIAL_COLORS){
           colors.add(color);
       }
        for(int color: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Pill name and date");
        dataSet.setColors(colors);


        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);
        data.setDrawValues(false);


        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}