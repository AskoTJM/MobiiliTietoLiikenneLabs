package com.example.mobiilitietoliikennelabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utilities {
    // Used by MainActivity



    // Used by Lab1_3 and Lab2_1
    public static String fromStream(InputStream in) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }


    // Used by Lab1_3 and Lab2_1
    public static String loadFromWeb(String urlString) throws IOException
    {
        String htmlText = "Error. Screw this.";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection;
            connection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            htmlText = Utilities.fromStream(in);

        }
        catch (Exception e) {e.printStackTrace();}
    return htmlText;
    }


}
