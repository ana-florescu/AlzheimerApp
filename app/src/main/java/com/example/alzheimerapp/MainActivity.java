package com.example.alzheimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMenuInteractive(View view){
        Intent intent = new Intent(this, MenuInteractive.class);
        startActivity(intent);
    }
    public void openMenuFeelings(View view){
        Intent intent = new Intent(this, MenuFeelings.class);
        startActivity(intent);
    }

    public void openMusic(View view){
        Intent intent = new Intent(this, MusicActivity.class);
        startActivity(intent);
    }

}