package com.example.mobiilitietoliikennelabs;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

public abstract class l42WebSocketClient extends WebSocketClient {

    TextView textViewForReceivedText;

    public l42WebSocketClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public l42WebSocketClient(URI serverUri, TextView prevContext) {
        super(serverUri);
            textViewForReceivedText = prevContext;

    }

    public l42WebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        //send("Hello");
        System.out.println("new connection opened");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(String message) {
        String receivedMessage = message + "\n";
        textViewForReceivedText.append(receivedMessage);
        System.out.println("received message: " + message);
    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }

    public void sendMessage(String sentMessage){
        send(sentMessage);
    }

    public static void main(String[] args) throws URISyntaxException {
        WebSocketClient client = new l42WebSocketClient(new URI("wss://obscure-waters-98157.herokuapp.com")) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {

            }
        };
        client.connect();
    }
}

