package com.example.mobiilitietoliikennelabs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;


public class lab4_2 extends AppCompatActivity{

    l42WebSocketClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4_2);



        TextView textViewForReceivedText = findViewById(R.id.l42receivedText);
        try {
            final l42WebSocketClient client = new l42WebSocketClient(new URI("wss://obscure-waters-98157.herokuapp.com"), textViewForReceivedText) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {

                }

            };
            client.connect();
            Button sendButton = findViewById(R.id.l42sendButton);
            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText whatAreWeGoingToSendString = findViewById(R.id.l42sentText);
                    String sentMessage = whatAreWeGoingToSendString.getText().toString();
                    client.sendMessage(sentMessage);
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
