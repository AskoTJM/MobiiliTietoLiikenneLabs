package com.example.mobiilitietoliikennelabs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class lab2_2 extends AppCompatActivity implements View.OnClickListener {

   // String gotJson;
   // ProgressDialog pd;
    List<companyObject> myObjList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2_2);



        // Let's populate with data
            companyObject cO = new companyObject();
            cO.setNames("Apple", "AAPL");
            myObjList.add(cO);
            companyObject c1 = new companyObject();
            c1.setNames("Google","GOOGL");
            myObjList.add(c1);
            companyObject c2 = new companyObject();
            c2.setNames("Facebook","FB");
            myObjList.add(c2);
            companyObject c3 = new companyObject();
            c3.setNames("Nokia","NOK");
            myObjList.add(c3);
            companyObject c4 = new companyObject();
            c4.setNames("RedHat","RHT");
            myObjList.add(c4);
            companyObject c5 = new companyObject();
            c5.setNames("Intel","INTC");
            myObjList.add(c5);

        Intent intent = getIntent();
        String stockChoice = intent.getStringExtra("mode");
        if(stockChoice.equals("lab2_2"))
            hideButtons();
        if(stockChoice.equals("lab2_3")){
            EditText l22eCompanyName = findViewById(R.id.l22companyNameText);

            final EditText l22eCompanyStockId = findViewById(R.id.l22stockIDText);
            l22eCompanyName.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    // If the event is a key-down event on the "enter" button
                    if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        // Perform action on key press
                        l22eCompanyStockId.requestFocus();
                        return true;
                    }
                    return false;
                }

            });

            l22eCompanyStockId.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    // If the event is a key-down event on the "enter" button
                    if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        Button l22TempButton = findViewById(R.id.l22btn);
                        l22TempButton.callOnClick();
                        return true;
                    }
                    return false;
                }

            });
        }

        getData();


    }

    private void addStock(String stockText, String stockValue){
        LinearLayout l22hozScroll = findViewById(R.id.l22hozScroll);

        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setTextSize(18);
        textView.setPadding(32,2,0,0);
        textView.setForegroundGravity(Gravity.CENTER_HORIZONTAL);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        String setStockText = stockText + " : " + stockValue + " USD";
        textView.setText(setStockText);

        // Add TextView to LinearLayout
        if (l22hozScroll != null) {
            l22hozScroll.addView(textView);
        }
    }

    private void getData(){
        for(int i = 0; i < myObjList.size(); i++) {

            companyObject companyObjectJson = myObjList.get(i);
            String companyJsonStock = companyObjectJson.getCompanyStockName();
            String companyJsonName = companyObjectJson.getCompanyName();
            new JsonTask().execute("https://financialmodelingprep.com/api/company/price/"+companyJsonStock+"?datatype=json", companyJsonStock, companyJsonName);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.l22btn)){
            LinearLayout l22hozScroll = findViewById(R.id.l22hozScroll);
            EditText l22eCompanyName = findViewById(R.id.l22companyNameText);

            EditText l22eCompanyStockId = findViewById(R.id.l22stockIDText);

            companyObject cA = new companyObject();
            String l22eCompanyNameString = l22eCompanyName.getText().toString();
            String l22eCompanyStockIDString = l22eCompanyStockId.getText().toString();
            if(l22eCompanyNameString.isEmpty() || l22eCompanyStockIDString.isEmpty()) {
                Toast.makeText(this,"Error, please give name of the company and it's Stock ID." ,Toast.LENGTH_SHORT).show();
            }else{
                cA.setNames(l22eCompanyNameString,l22eCompanyStockIDString);
                myObjList.add(cA);
                l22hozScroll.removeAllViewsInLayout();
                getData();
            }
        }
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        String companyStockName = "error";
        String companyName = "error";

        protected void onPreExecute() {

        }

        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            companyStockName = params[1];
            companyName = params[2];

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";


                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
                }
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            /*if (pd.isShowing()){
                pd.dismiss();
            }
             */
            // JSONObject sys =  res

            String priceOfStock = "error";

            JSONObject reader2 = null;
            try {
                reader2 = new JSONObject(result);
                JSONObject sys  = reader2.getJSONObject(companyStockName);
                // Attempt reacting to Error, couldn't get to work in time.
                //if(sys.getString("Error").isEmpty()){
                //    l22Error();
                //}else{
                    priceOfStock = sys.getString("price");
                    addStock(companyName, priceOfStock);
                //}
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //addStock(company,priceOfStock);
        }
    }

    private void hideButtons(){
        EditText l22eCompanyName = findViewById(R.id.l22companyNameText);
        EditText l22eCompanyStockName = findViewById(R.id.l22stockIDText);
        Button l22eButton = findViewById(R.id.l22btn);
        l22eCompanyName.setVisibility(View.INVISIBLE);
        l22eCompanyStockName.setVisibility(View.INVISIBLE);
        l22eButton.setVisibility(View.INVISIBLE);
    }


    private void l22Error(){
        Toast.makeText(this,"Error, please give proper Stock ID." ,Toast.LENGTH_SHORT).show();
    }
}



