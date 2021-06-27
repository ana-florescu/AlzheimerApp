package com.example.alzheimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.alzheimerapp.classifier.ImageClassifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Retrospective extends AppCompatActivity {

    ImageView ivRetrospective;
    private ViewFlipper viewFlipper;
    ImageClassifier imageClassifier;
    List<ImageClassifier.Recognition> predicitons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrospective);
        setContentView(R.layout.activity_retrospective);
        viewFlipper = findViewById(R.id.view_flipper);
        try {
            imageClassifier = new ImageClassifier(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ivRetrospective = (ImageView)findViewById(R.id.ivRetrospective);

        Cursor cursor = UnsureActivity.sqLiteHelper.getData("SELECT * FROM FEED");

        while(cursor.moveToNext()){
            byte[] image = cursor.getBlob(3);
            Log.i("Image = " , image.toString());
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            Log.i("Bitmap = " , bitmap.toString());
            predicitons = imageClassifier.recognizeImage(
                    bitmap, 0);
            final List<String> predicitonsList = new ArrayList<>();
            for (ImageClassifier.Recognition recog : predicitons) {
                predicitonsList.add(recog.getName());
            }
            for(String predict : predicitonsList) {
                if (predict.equals("shoji")) {
                    flipperingImages(image);
                }
            }


        }

    }
    public void flipperingImages(byte[] image){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);

    }
}