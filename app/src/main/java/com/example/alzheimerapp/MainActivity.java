package com.example.alzheimerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.meniuGallery) {
                    Intent intent = new Intent(MainActivity.this, UnsureActivity.class);
                    startActivity(intent);
                }
                else if (menuItem.getItemId() == R.id.meniuMap) {
                    Intent intent = new Intent(MainActivity.this, LostActivity.class);
                    startActivity(intent);
                }
                else if (menuItem.getItemId() == R.id.meniuPills) {
                    Intent intent = new Intent(MainActivity.this, PillsActivity.class);
                    startActivity(intent);
                }
                else if (menuItem.getItemId() == R.id.meniuReportPills) {
                    Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                    startActivity(intent);
                }
                else if (menuItem.getItemId() == R.id.meniuQuiz) {
                    Intent intent = new Intent(MainActivity.this, Quiz.class);
                    startActivity(intent);
                }
                else if (menuItem.getItemId() == R.id.meniuPaint) {
                    Intent intent = new Intent(MainActivity.this, Paint.class);
                    startActivity(intent);
                }

                return true;
            }
        });
    }

    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    public void openMenuInteractive(View view){
        Intent intent = new Intent(this, MenuInteractive.class);
        startActivity(intent);
    }
    public void openCamera(View view){
        Intent intent = new Intent(this, UnsureActivity.class);
        startActivity(intent);
    }

    public void openPills(View view){
        Intent intent = new Intent(this, PillsActivity.class);
        startActivity(intent);
    }

    public void openMap(View view){
        Intent intent = new Intent(this, LostActivity.class);
        startActivity(intent);
    }

}