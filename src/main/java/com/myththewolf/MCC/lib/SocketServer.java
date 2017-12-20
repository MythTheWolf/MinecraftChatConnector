package com.myththewolf.MCC.lib;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class SocketServer {
    private ServerSocket server;
    private boolean shouldRun = false;
    public HashMap<String, SocketReceiver> receivers = new HashMap<>();
    private Socket clientSocket;

    public SocketServer(int port) {
        try {
            server = new ServerSocket(port);
            server.setSoTimeout(10000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        this.shouldRun = true;
        Thread T = new Thread(() -> {
            run();
        });
        T.start();
    }

    public void run() {
        try {
            clientSocket = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (shouldRun) {
            try {
                System.out.println("Waiting for client on port " + server.getLocalPort() + "...");

                System.out.println("Connection from " + clientSocket.getRemoteSocketAddress() + "accepted.");
                BufferedReader fromClient =
                        new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream()));
                String line = fromClient.readLine();
                System.out.println("Got data from client: " + line);
                try {
                    JSONObject object = new JSONObject(line);
                    if (object.isNull("packetType")) {
                        throw new JSONException("packetType is not optional!");
                    }
                    if (!this.receivers.containsKey(object.getString("packetType"))) {
                        throw new JSONException("Unknown packet type: " + object.getString("packetType"));
                    }


                    object.remove("packetType");
                    JSONObject res = this.receivers.get(object.getString("packetType")).onMessage(object, this);
                    JSONObject theResult = new JSONObject();
                    String STATUS = !res.isNull("status") ? res.getString("status") : "OK";
                    String MESSAGE = !res.isNull("message") ? res.getString("message") : "The packet was received, but the packet handler did not supply a message";
                    theResult.put("status", STATUS);
                    theResult.put("message", MESSAGE);

                    writeBack(clientSocket, theResult.toString());

                } catch (JSONException e) {
                    JSONObject response = new JSONObject();
                    response.put("status", "BAD_REQUEST");
                    response.put("message", e.getMessage());
                    writeBack(clientSocket, response.toString());
                }
            } catch (IOException e) {
                System.out.println("Fatal exception in socket server: " + e.getMessage());
            }
        }
    }

    public void writeBack(Socket bridge, String message) {
        try {
            System.out.println("Sending packet to client: " + message);
            PrintWriter toClient =
                    new PrintWriter(bridge.getOutputStream(), true);
            toClient.println(message);
        } catch (IOException e) {
            System.out.println("A error occurred while writing a message to the client: `" + message + "`::" + e.getMessage());
        }
    }

    public void registerPacketHandler(String packetType, SocketReceiver receiver) {
        this.receivers.put(packetType, receiver);
    }
}
