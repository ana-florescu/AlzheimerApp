package com.example.alzheimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class FeedList extends AppCompatActivity {
    GridView gridView;
    ArrayList<Feed> list;
    FeedAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gridView = findViewById(R.id.gridview);
        list = new ArrayList<>();
        adapter = new FeedAdapter(this, R.layout.feed_items, list);
        gridView.setAdapter(adapter);

        Cursor cursor = UnsureActivity.sqLiteHelper.getData("SELECT * FROM FEED");
        list.clear();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String subject = cursor.getString(1);
            String thoughts = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Feed(id, subject, thoughts, image));
        }
        adapter.notifyDataSetChanged();
    }
}