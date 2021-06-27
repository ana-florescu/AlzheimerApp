package com.example.alzheimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.alzheimerapp.Display.colorList;
import static com.example.alzheimerapp.Display.current_brush;
import static com.example.alzheimerapp.Display.pathList;

public class Paint extends AppCompatActivity {
    public static Path path = new Path();
    public static android.graphics.Paint paint_brush = new android.graphics.Paint();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
    }
    public void eraser(View view){
        pathList.clear();
        colorList.clear();
        path.reset();
    }
    public void pencil(View view){
        paint_brush.setColor(Color.BLACK);
        currentColor(paint_brush.getColor());
    }

    public void currentColor(int c){
        current_brush = c;
        path = new Path();
    }
}