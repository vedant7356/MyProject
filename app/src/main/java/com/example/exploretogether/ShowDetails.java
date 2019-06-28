package com.example.exploretogether;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowDetails extends AppCompatActivity {
    TextView Title,Desc,Location,Date,Time,Orga,GAP;
    ImageView Image;
    Button booktickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Show Details");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        Title = findViewById(R.id.Shwdetailtitsh);
        Desc = findViewById(R.id.Shwdetailtvmdescsh);
        Location = findViewById(R.id.Shwdetailtvmlocsh);
        Date = findViewById(R.id.Shwdetailtvmdatesh);
        Time = findViewById(R.id.Shwdetailtvmtimesh);
        Image=findViewById(R.id.Shwdetailivimgvwsh);

        byte[]bytes=getIntent().getByteArrayExtra("image");
        String title=getIntent().getStringExtra("title");
        String desc=getIntent().getStringExtra("desc");
        String loc=getIntent().getStringExtra("loc");
        String date=getIntent().getStringExtra("date");
        String time=getIntent().getStringExtra("time");
        Bitmap bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);

        Title.setText(title);
        Desc.setText(desc);
        Location.setText(loc);
        Date.setText(date);
        Time.setText(time);
        Image.setImageBitmap(bmp);



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
