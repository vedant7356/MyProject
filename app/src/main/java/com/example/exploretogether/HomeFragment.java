package com.example.exploretogether;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class HomeFragment extends Fragment {
    ListView search;
    ArrayAdapter<String>adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);
        Button btevents= (Button) view.findViewById(R.id.bteve);
        Button btshows=(Button)view.findViewById(R.id.btshow);


        btevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),EventsActivity.class);
                startActivity(intent);
            }
        });

        btshows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getActivity(),ShowsActivity.class);
                startActivity(in);
            }
        });
        return view;
    }

}

