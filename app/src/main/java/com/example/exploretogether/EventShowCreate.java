package com.example.exploretogether;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EventShowCreate extends AppCompatActivity {
    private static final String TAG = "You have selected";
    private EditText mDisplayDateeve;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText mTimeeve;
    Calendar calendareve;
    int currentHour;
    int currentMinute;
    String amPm;
    Geocoder geocodereve;
    List<Address> addresses;
    TimePickerDialog timePickerDialog;
    private Button regeve;
    private EditText locationeve;
    private EditText titleeve;
    private EditText desceve;
    private EditText organeve;
    private EditText appeve;
    String evetitle, evedesc, eveloc, evedate, evetime, eveog, eveguest;
    private ImageView piceve;

    Double latitude=18.944620;
    Double longitude=72.822278;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_show_create);
        mTimeeve = findViewById(R.id.ettimeeve);
        mDisplayDateeve = (EditText) findViewById(R.id.etdteve);
        titleeve = (EditText) findViewById(R.id.ettiteve);
        desceve = (EditText) findViewById(R.id.etdesceve);
        organeve = (EditText) findViewById(R.id.etogeve);
        appeve = (EditText) findViewById(R.id.etgapeve);
        locationeve = (EditText) findViewById(R.id.etloceve);
        mTimeeve = findViewById(R.id.ettimeeve);
        geocodereve = new Geocoder(this, Locale.getDefault());
        piceve = findViewById(R.id.ivpiceve);
        regeve=(Button)findViewById(R.id.btnewaddeve);
        try {
            addresses=geocodereve.getFromLocation(latitude,longitude,1);
            String address=addresses.get(0).getAddressLine(0);
            String area=addresses.get(0).getLocality();
            String city=addresses.get(0).getAdminArea();
            String country=addresses.get(0).getCountryName();
            String postalcode=addresses.get(0).getPostalCode();

            String fulladdress=address+","+area+""+city+""+country+""+postalcode+"";
            locationeve.setText(fulladdress);


        }catch(IOException ex) {
        }
        try {

        }catch (NullPointerException ignored){
        }



        mTimeeve.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         calendareve = Calendar.getInstance();
                                         currentHour = calendareve.get(Calendar.HOUR_OF_DAY);
                                         currentMinute = calendareve.get(Calendar.MINUTE);

                                         TimePickerDialog timePickerDialog = new TimePickerDialog(EventShowCreate.this, new TimePickerDialog.OnTimeSetListener() {
                                             @Override
                                             public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                                                 if (hourOfDay >= 12) {
                                                     amPm = "PM";
                                                 } else {
                                                     amPm = "AM";
                                                 }
                                                 mTimeeve.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                                             }
                                         },  currentHour, currentMinute, false);
                                         timePickerDialog.show();
                                     }
        });


        mDisplayDateeve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EventShowCreate.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDateeve.setText(date);
            }
        };

}
}

