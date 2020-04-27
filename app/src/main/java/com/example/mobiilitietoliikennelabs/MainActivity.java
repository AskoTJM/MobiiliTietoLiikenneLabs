package com.example.mobiilitietoliikennelabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LifecycleEventObserver {

    int choice = 0;
    private static final int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 111;
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 222;
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 333;
    StrictMode.ThreadPolicy defaultPolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions(); // Do we have permissions?
        // Is the GPS on?
        if(isLocationEnabled() != true){
            Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        defaultPolicy = StrictMode.getThreadPolicy();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
        Spinner spinner = findViewById(R.id.spinner);



        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Lab 1_1");
        arrayList.add("Lab 1_2");
        arrayList.add("Lab 1_3");
        arrayList.add("Lab 2_1");
        arrayList.add("Lab 2_2");
        arrayList.add("Lab 2_3");
        arrayList.add("Lab 3_1");
        arrayList.add("Lab 3_2");
        arrayList.add("Lab 3_3");
        arrayList.add("Lab 4_1");
        arrayList.add("Lab 4_2");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView desc = findViewById(R.id.assignmentText);
                TextView note = findViewById(R.id.noteText);
                String assignmentText;
                String noteText;
                // Let's save position for later use.
                choice = position;
                switch(position){
                    case 0:
                        assignmentText = getString(R.string.lab1_1);
                        noteText = getString(R.string.lab1_1_note);
                        note.setText(noteText);
                        desc.setText(assignmentText);
                        break;
                    case 1:
                        assignmentText = getString(R.string.lab1_2);
                        noteText = getString(R.string.lab1_2_note);
                        note.setText(noteText);
                        desc.setText(assignmentText);
                        break;
                    case 2:
                        assignmentText = getString(R.string.lab1_3);
                        noteText = getString(R.string.lab1_3_note);
                        note.setText(noteText);
                        desc.setText(assignmentText);
                        break;
                    case 3:
                        assignmentText = getString(R.string.lab2_1);
                        noteText = getString(R.string.lab2_1_note);
                        note.setText(noteText);
                        desc.setText(assignmentText);
                        break;
                    case 4:
                        assignmentText = getString(R.string.lab2_2);
                        noteText = getString(R.string.lab2_2_note);
                        note.setText(noteText);
                        desc.setText(assignmentText);
                        break;
                    case 5:
                        assignmentText = getString(R.string.lab2_3);
                        noteText = getString(R.string.lab2_3_note);
                        note.setText(noteText);
                        desc.setText(assignmentText);
                        break;
                    case 6:
                        assignmentText = getString(R.string.lab3_1);
                        noteText = getString(R.string.lab3_1_note);
                        note.setText(noteText);
                        desc.setText(assignmentText);
                        break;
                    case 7:
                        assignmentText = getString(R.string.lab3_2);
                        noteText = getString(R.string.lab3_2_note);
                        note.setText(noteText);
                        desc.setText(assignmentText);
                        break;
                    case 8:
                        assignmentText = getString(R.string.lab3_3);
                        noteText = getString(R.string.lab3_3_note);
                        note.setText(noteText);
                        desc.setText(assignmentText);
                        break;
                    case 9:
                        assignmentText = getString(R.string.lab4_1);
                        noteText = getString(R.string.lab4_1_note);
                        note.setText(noteText);
                        desc.setText(assignmentText);
                        break;
                    case 10:
                        assignmentText = getString(R.string.lab4_2);
                        noteText = getString(R.string.lab4_2_note);
                        note.setText(noteText);
                        desc.setText(assignmentText);
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {

            }
        });

        alertOneButton();
    }

    @Override
    public void onClick(View v) {

        // Better place would be OnResume(), but for now we switch back to default Policy, in case lab1_3 switched it to permitAll.
        StrictMode.setThreadPolicy(defaultPolicy);

        switch(choice){
            case 0:

                Intent intent0 = new Intent(this, lab1_1.class);
                intent0.putExtra("mode", "lab1_1");
                startActivity(intent0);
                break;
            case 1:
                Intent intent1 = new Intent(this, lab1_1.class);
                intent1.putExtra("mode", "lab1_2");
                startActivity(intent1);
                break;
            case 2:
                // Let's change policy for this lab
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Intent intent2 = new Intent(this, lab1_3.class);
                // Pass the info what Policy we have so we know it's right one for the lab.
                String threadPolicy = StrictMode.getThreadPolicy().toString();
                intent2.putExtra("StrictMode",threadPolicy);
                intent2.putExtra("mode","lab1_3");
                startActivity(intent2);
                break;
            case 3:

                Intent intent3 = new Intent(this,lab1_3.class);
                // Pass the info what Policy we have so we know it's right one for the lab.
                String threadPolicy2 = StrictMode.getThreadPolicy().toString();
                intent3.putExtra("StrictMode",threadPolicy2);
                intent3.putExtra("mode", "lab2_1");
                startActivity(intent3);
                break;
            case 4:
                Intent intent4 = new Intent(this, lab2_2.class);
                intent4.putExtra("mode", "lab2_2");
                startActivity(intent4);
                break;
            case 5:
                Intent intent5 = new Intent(this, lab2_2.class);
                intent5.putExtra("mode", "lab2_3");
                startActivity(intent5);
                break;
            case 6:
                Intent intent6 = new Intent(this, lab3_1.class);
                intent6.putExtra("StrictMode", "Using Volley");
                startActivity(intent6);
                break;
            case 7:
                Intent intent7 = new Intent(this, lab3_2.class);
                intent7.putExtra("mode", "lab3_2");
                intent7.putExtra("countryCode","2072");
                startActivity(intent7);
                break;
            case 8:

                break;
            case 9:
                Intent intent9 = new Intent(this, lab3_2.class);
                intent9.putExtra("mode","lab4_1");
                startActivity(intent9);
                break;
            case 10:
                Intent intent10 = new Intent(this, lab4_2.class);
                startActivity(intent10);
        }


    }

    public void checkPermissions(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            //Toast.makeText(this, "No PERMISSION for Fine Location", Toast.LENGTH_LONG).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);

            }

        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            //Toast.makeText(this, "No PERMISSION for Coarse Location", Toast.LENGTH_LONG).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_COARSE_LOCATION);

            }

        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(this, "No PERMISSION for Internet", Toast.LENGTH_LONG).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.INTERNET)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},
                        MY_PERMISSIONS_REQUEST_INTERNET);

            }
        }
    }
//*/
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request.

        }
    }

    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {

    }

    @Override
    public void onResume(){
        super.onResume();
        //StrictMode.setThreadPolicy(defaultPolicy);
    }

    public void alertOneButton() {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.notice_title)
                .setMessage(R.string.notice_text)
                .setPositiveButton("Very well.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
}
