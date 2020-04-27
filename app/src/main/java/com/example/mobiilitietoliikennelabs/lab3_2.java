package com.example.mobiilitietoliikennelabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebHistoryItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class lab3_2 extends AppCompatActivity implements View.OnClickListener {

    JSONObject jsonData = new JSONObject();
    final String API_TOKEN = "d650f2a98bdc40a2aaecdd6fad1f496b";
    String l32Mode = "null";
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_2);
        Intent intentl32 = getIntent();
        l32Mode = intentl32.getStringExtra("mode");
        String url = "null";


        if(l32Mode.equals("lab3_2")) {
            //url = "http://api.football-data.org/v2/competitions?areas=2072";
            url = "https://api.football-data.org/v2/competitions?areas="+intentl32.getStringExtra("countryCode");
        }
        if(l32Mode.equals("lab4_1")){
            url = "https://api.football-data.org/v2/areas";
        }

        //RequestQueue
        queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        jsonData = response;
                        parseData(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    }

                }){
                    @Override
                    public Map<String, String> getHeaders() {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("X-Auth-Token", API_TOKEN);
                        return params;
                    }
                };
        queue.add(jsonObjectRequest);

    }


    private void parseData(JSONObject resultData){


        try {
            int numberOfData = 0;
            JSONArray obj = new JSONArray();
            LinearLayout l32HozScroll = findViewById(R.id.l32layout);

            if(l32Mode.equals("lab3_2")) {
                numberOfData = resultData.getInt("count");
                obj = resultData.getJSONArray("competitions");
            }
            if(l32Mode.equals("lab4_1")){
                numberOfData = resultData.getInt("count");
                obj = resultData.getJSONArray("areas");
            }

            for(int i = 0; i < numberOfData; i++){

                TextView textView = new TextView(this);
                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setTextSize(24);
                textView.setPadding(0,5,0,5);
                textView.setForegroundGravity(Gravity.CENTER_HORIZONTAL);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setClickable(true);
                textView.setOnClickListener(this);
                textView.setId(i);


                JSONObject jsonObject = obj.getJSONObject(i);
                String setFootballText2 = jsonObject.getString("name");

                textView.setText(setFootballText2);
                if(l32HozScroll != null)
                    l32HozScroll.addView(textView);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        // To test if click are registered.
        //Toast.makeText(this, Integer.toString(v.getId()) ,Toast.LENGTH_SHORT).show();
        if(l32Mode.equals("lab4_1")){
            try {
                JSONArray jsonArray = jsonData.getJSONArray("areas");
                JSONObject l41countryData = jsonArray.getJSONObject(v.getId());
                String l41IDcode = l41countryData.getString("id");
                Intent intentNew = new Intent(this, lab3_2.class);
                intentNew.putExtra("mode","lab3_2");
                intentNew.putExtra("countryCode",l41IDcode);
                startActivity(intentNew);
                //Toast.makeText(this, l41IDcode ,Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
