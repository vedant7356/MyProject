package com.example.exploretogether;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.example.exploretogether.RegisterActivity.PReqCode;
import static com.example.exploretogether.RegisterActivity.REQUESCODE;

public class ShowCreate extends AppCompatActivity {
    private static final String TAG = "You have selected";

    private EditText mDisplayDateshw;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText mTimeshw;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;
    Geocoder geocoder;
    List<Address> addresses;
    TimePickerDialog timePickerDialog;
    private StorageReference storageReference;
    private Button regshow;
    private EditText location;
    private EditText titleshw;
    private EditText descshw;
    private EditText organshw;
    private EditText appshw;
    String shwtitle, shwdesc, shwloc, shwdate, shwtime, shwog, shwguest;
    private ImageView picshw;
    int Place_Picker_Request = 1;
    Double latitude = 18.944620;
    Double longitude = 72.822278;
    Uri pickedImgUri ;
    DatabaseReference newShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_create);
        newShow=FirebaseDatabase.getInstance().getReference("Shows");
        mTimeshw = findViewById(R.id.ettimeshw);
        mDisplayDateshw = (EditText) findViewById(R.id.etdtshw);
        titleshw = (EditText) findViewById(R.id.ettitshw);
        descshw = (EditText) findViewById(R.id.etdescsh);
        organshw = (EditText) findViewById(R.id.etogshw);
        appshw = (EditText) findViewById(R.id.etgap);
        location = (EditText) findViewById(R.id.etlocshw);
        mTimeshw = findViewById(R.id.ettimeshw);
        geocoder = new Geocoder(this, Locale.getDefault());
        picshw = findViewById(R.id.ivpicshw);
        regshow=(Button)findViewById(R.id.btnewaddshw);
        location = (EditText) findViewById(R.id.etlocshw);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        picshw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 22) {
                    checkandrequestforpermission();
                } else {
                    openGallery();
                }
            }
        });
        regshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    /*String id= newShow.push().getKey();
                    AddingShows addingShows=new AddingShows(id,shwtitle,shwdesc,shwloc,shwdate,shwtime,shwog,shwguest);
                    newShow.child(id).setValue(addingShows);*/
                }
            }
        });

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String address = addresses.get(0).getAddressLine(0);
            String area = addresses.get(0).getLocality();
            String city = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalcode = addresses.get(0).getPostalCode();

            String fulladdress = address + "," + area + "" + city + "" + country + "" + postalcode + "";
            location.setText(fulladdress);


        } catch (IOException ex) {
        }        mTimeshw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(ShowCreate.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        mTimeshw.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });


        mDisplayDateshw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ShowCreate.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
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
                mDisplayDateshw.setText(date);
            }
        };
    }

    private void checkandrequestforpermission() {
        if (ContextCompat.checkSelfPermission(ShowCreate.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ShowCreate.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(ShowCreate.this, "Please accept for required permission", Toast.LENGTH_SHORT).show();

            } else {
                ActivityCompat.requestPermissions(ShowCreate.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }

        } else {
            openGallery();

        }

    }
    private void sendUserData() {
        final StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("/SHOWS REGISTERED");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // image uploaded succesfully
                // now we can get our image url

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        // uri contain user image url


                        UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                                .setPhotoUri(uri)
                                .build();
                    }
                });

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData();
            picshw.setImageURI(pickedImgUri);


        }


    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }
    private Boolean validate() {
        Boolean result = false;
        shwtitle = titleshw.getText().toString().trim();
        shwdesc = descshw.getText().toString().trim();
        shwloc = location.getText().toString().trim();
        shwdate = mDisplayDateshw.getText().toString().trim();
        shwtime= mTimeshw.getText().toString().trim();
        shwog= organshw.getText().toString().trim();
        shwguest = appshw.getText().toString().trim();
        if (shwtitle.isEmpty() || shwdesc.isEmpty() || shwloc.isEmpty() || shwdate.isEmpty() || shwtime.isEmpty() || shwog.isEmpty() || shwguest.isEmpty() ) {
            Toast.makeText(this, "Please enter the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}
