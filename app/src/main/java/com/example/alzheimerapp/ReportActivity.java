package com.example.alzheimerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.alzheimerapp.adapter.EventAdapter;
import com.example.alzheimerapp.databasePills.EntityClass;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    PieChart pieChart;
    List<EntityClass> dates;

    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        pieChart = findViewById(R.id.pieChart);
        dates = new ArrayList<EntityClass>();

        mFirestore = FirebaseFirestore.getInstance();
        setUpPieChart();
        readData(new FirestoreCallback() {
            @Override
            public void onCallBack(List<EntityClass> list) {
                loadPieChartData();
            }
        });


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


    private void readData(FirestoreCallback firestoreCallback){
        mFirestore.collection("pillTable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for(DocumentSnapshot doc : task.getResult()){
                    String name = doc.getString("pillName");
                    String date = doc.getString("pillDate");
                    String time = doc.getString("pillTime");
                    EntityClass entityClass = new EntityClass(name, date, time);
                    dates.add(entityClass);

                }
                firestoreCallback.onCallBack(dates);
            }
        });
    }

    private interface FirestoreCallback {
        void onCallBack(List<EntityClass> list);
    }
}