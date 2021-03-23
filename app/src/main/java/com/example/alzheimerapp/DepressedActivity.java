package com.example.alzheimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class DepressedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depressed);
    }

    public void findPage (View view) {
        goToUrl ( "https://www.betterhelp.com/start/?help_with=Depression&utm_source=AdWords&utm_medium=Search_PPC_c&utm_term=depression+online+counseling_b&utm_content=117153808547&network=g&placement=&target=&matchtype=b&utm_campaign=11760441006&ad_type=text&adposition=&gclid=CjwKCAiAkJKCBhAyEiwAKQBCktHYwEwh6iRltYos2NofaoFdkZW6As9n7coSxXbi5ygkdCIgliCL8hoCp5UQAvD_BwE&not_found=1&gor=rd_start");
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}