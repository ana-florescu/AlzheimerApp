package com.example.alzheimerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alzheimerapp.adapter.EventAdapter;
import com.example.alzheimerapp.databasePills.EntityClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PillsActivity extends AppCompatActivity {
    Button createEvent;
    EventAdapter eventAdapter;
    RecyclerView recyclerview;
     ArrayList<EntityClass> list;

    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills);
        createEvent = findViewById(R.id.btn_createEvent);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<EntityClass>();
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateEvent.class);
                startActivity(intent);
            }
        });
        mFirestore = FirebaseFirestore.getInstance();


        readData();
        eventAdapter = new EventAdapter(getApplicationContext(), list);
        recyclerview.setAdapter(eventAdapter);
        eventAdapter.notifyDataSetChanged();
    }



    private void readData() {
        mFirestore.collection("pillTable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for(DocumentSnapshot doc : task.getResult()){
                    String name = doc.getString("pillName");
                    String date = doc.getString("pillDate");
                    String time = doc.getString("pillTime");
                    EntityClass entityClass = new EntityClass(name, date, time);
                    list.add(entityClass);
                    eventAdapter = new EventAdapter(getApplicationContext(), list);
                    recyclerview.setAdapter(eventAdapter);
                   // eventAdapter.notifyDataSetChanged();
                }
            }
        });
    }


}