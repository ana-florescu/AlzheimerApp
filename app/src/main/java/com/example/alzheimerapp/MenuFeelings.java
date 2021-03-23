package com.example.alzheimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuFeelings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_feelings);
    }
    public void openFeelingUnsure(View view){
        Intent intent = new Intent(this, UnsureActivity.class);
        startActivity(intent);
    }
    public void openFeelingDepressed(View view){
        Intent intent = new Intent(this, DepressedActivity.class);
        startActivity(intent);
    }
    public void openFeelingLost(View view){
        Intent intent = new Intent(this, LostActivity.class);
        startActivity(intent);
    }
}