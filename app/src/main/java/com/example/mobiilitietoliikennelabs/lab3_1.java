package com.example.mobiilitietoliikennelabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class lab3_1 extends AppCompatActivity {

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Using layout from lab1_3
        setContentView(R.layout.activity_lab1_3);
        Intent intent = getIntent();
        TextView modeViewText = findViewById(R.id.modeView);
        modeViewText.setText(intent.getStringExtra("StrictMode"));

    }

    public void htmlPushButton(View view){

        EditText theUrl = findViewById(R.id.lab13Url);
        String webAdd = theUrl.getText().toString();
        final TextView urlResultText = findViewById(R.id.lab13Text);

        //RequestQueue
        queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, webAdd,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        urlResultText.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                urlResultText.setText("Something went horribly wrong!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}

