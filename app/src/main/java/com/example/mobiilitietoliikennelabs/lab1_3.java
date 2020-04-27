package com.example.mobiilitietoliikennelabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class lab1_3 extends AppCompatActivity {

    String htmlChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1_3);
        Intent intent = getIntent();
        htmlChoice = intent.getStringExtra("mode");
        TextView modeViewText = findViewById(R.id.modeView);
        modeViewText.setText(intent.getStringExtra("StrictMode"));

    }

    public void htmlPushButton(View view){
        if(htmlChoice.equals("lab1_3")){
            getTheUrl();
        }
        if(htmlChoice.equals("lab2_1")){
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            myAsyncTasks.execute();
        }
    }



    public void getTheUrl(){
        EditText theUrl = findViewById(R.id.lab13Url);
        String webAdd = theUrl.getText().toString();
        try{
            String textBack =
                Utilities.loadFromWeb(webAdd);
            TextView textView = findViewById(R.id.lab13Text);
            textView.setText(textBack);
        }catch (Exception e){e.printStackTrace();}
    }

    public class MyAsyncTasks extends AsyncTask<String,String,String>{


        @Override
        protected String doInBackground(String... strings) {
            EditText theUrl = findViewById(R.id.lab13Url);
            String webAdd = theUrl.getText().toString();
            String htmlText = "";
            try{
                String textBack =
                        Utilities.loadFromWeb(webAdd);
                TextView textView = findViewById(R.id.lab13Text);
                textView.setText(textBack);
            }catch (Exception e){e.printStackTrace();}
            return htmlText;
        }
    }
}
