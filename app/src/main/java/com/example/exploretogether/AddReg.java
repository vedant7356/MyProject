package com.example.exploretogether;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddReg extends AppCompatActivity {
    private Button AddEve;
    private Button AddShw;
    private TextView ask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reg);

    AddEve=(Button)findViewById(R.id.btaddeve);
    AddShw=(Button)findViewById(R.id.btaddshw);
    ask=(TextView)findViewById(R.id.tvask);

        AddEve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddReg.this, EventShowCreate.class));
            }
        });

        AddShw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddReg.this, ShowCreate.class));
            }
        });


    }
}
