package com.example.alzheimerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alzheimerapp.classifier.ImageClassifier;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class     UnsureActivity extends AppCompatActivity {

    EditText etSubject, etThoughts;
    Button btnAddPhoto, btnAddToFeed, btnSeeFeed, btnRetrospective;
    ImageView ivPhoto;

    final int REQUEST_CODE_GALLERY= 999;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsure);

        init();

        sqLiteHelper = new SQLiteHelper(this, "FeedDB.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS FEED (Id INTEGER PRIMARY KEY AUTOINCREMENT, subject VARCHAR, text VARCHAR, image BLOG ) ");

        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        UnsureActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY

                );
            }
        });

        btnAddToFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    byte[] byteArray2 = imageViewToByte(ivPhoto);
                    if(byteArray2.length > 0 ) {
                        sqLiteHelper.insertData(
                                etSubject.getText().toString().trim(),
                                etThoughts.getText().toString().trim(),
                                imageViewToByte(ivPhoto)

                        );
                    }
                    Toast.makeText(getApplicationContext(), "Added succsesfully!!", Toast.LENGTH_SHORT).show();
                    etSubject.setText("");
                    etThoughts.setText("");
                    ivPhoto.setImageResource(R.mipmap.ic_launcher);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnSeeFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnsureActivity.this, FeedList.class);
                startActivity(intent);
            }
        });

        btnRetrospective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnsureActivity.this, Retrospective.class);
                startActivity(intent);
            }
        });
    }

    private byte[] imageViewToByte(ImageView image) throws IOException {

        byte[] byteArray = new byte[0];
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, stream);
        byteArray = stream.toByteArray();
        return byteArray;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(getApplicationContext(), "You don't have permission!!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null ){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivPhoto.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){


        etSubject = (EditText) findViewById(R.id.etSubject);
        etThoughts = (EditText) findViewById(R.id.etThoughts);
        btnAddPhoto = (Button) findViewById(R.id.btnAddPhoto);
        btnAddToFeed = (Button) findViewById(R.id.btnAddFeed);
        btnSeeFeed = (Button) findViewById(R.id.btnSeeFeed);
        btnRetrospective = (Button) findViewById(R.id.btnRetrospective);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);

    }

  }