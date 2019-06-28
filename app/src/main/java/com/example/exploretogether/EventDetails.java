package com.example.exploretogether;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDetails extends AppCompatActivity {
    TextView Title,Desc,Location,Date,Time,Orga,GAP;
    ImageView Image;
    Button booktickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Post Detail");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        Title = findViewById(R.id.Shwdetailtit);
        Desc = findViewById(R.id.Shwdetailtvmdesc);
        Location = findViewById(R.id.Shwdetailtvmloc);
        Date = findViewById(R.id.Shwdetailtvmdate);
        Time = findViewById(R.id.Shwdetailtvmtime);
        Image=findViewById(R.id.Shwdetailivimgvw);

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
