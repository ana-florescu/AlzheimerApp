package com.example.alzheimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuInteractive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_interactive);
    }

    public void openQuiz(View view){
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }
    public void openPaint(View view){
        Intent intent = new Intent(this, Paint.class);
        startActivity(intent);
    }
}