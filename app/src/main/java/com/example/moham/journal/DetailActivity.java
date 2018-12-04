package com.example.moham.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String title = intent.getStringExtra("titleEntry");
        String content = intent.getStringExtra("contentEntry");
        String mood = intent.getStringExtra("moodEntry");
        String timestamp = intent.getStringExtra("timeStampEntry");

        TextView titleView = findViewById(R.id.textViewTitle);
        TextView contentView = findViewById(R.id.textViewContent);
        ImageView moodImage = findViewById(R.id.imageViewMood);
        TextView timestampView = findViewById(R.id.textViewTimeStamp);
        int resId = this.getResources().getIdentifier(mood, "drawable", this.getPackageName());

        titleView.setText(title);
        contentView.setText(content);
        moodImage.setImageResource(resId);
        timestampView.setText(timestamp);

    }
}