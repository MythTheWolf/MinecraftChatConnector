package com.myththewolf.MCC.lib;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import com.myththewolf.MCC.MCCMain;

public class TCPServer {
    private boolean run = false;
    private String clientSentence;
    private ServerSocket welcomeSocket;
    private Socket connectionSocket;
    private HashMap<String, MessageChannelListener> events = new HashMap<>();
    private JSONObject parse = null;

    public void startServer() {
        run = true;
        run();
    }

    private void run() {


        try {
            welcomeSocket = new ServerSocket(MCCMain.plugin.getJSONConfig().getInt("bind-port"));
        } catch (IOException e1) {

            e1.printStackTrace();
        }
        System.out.println("[MCC] Socket server running on /" + welcomeSocket.getLocalPort());
        while (run) {
            System.out.print("accept");
            try {
                connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));


                clientSentence = inFromClient.readLine();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            System.out.print("Imboud packet: " + clientSentence);
            JSONObject RAW = new JSONObject(clientSentence);
            try {

                parse = new JSONObject(RAW.getString("data"));

                if (parse == null || parse.isNull("packetType")) {

                    throw new JSONException("packetType is missing!");
                }

            } catch (JSONException e) {
                JSONObject resp = new JSONObject();
                resp.put("ID", RAW.getString("ID"));
                resp.put("status", "BADREQUST");
                writeToClient(resp.toString());
            }

            String type = parse.getString("packetType");
            JSONObject rep = new JSONObject();
            rep.put("status", "OK");
            rep.put("ID", RAW.getString("ID"));
            System.out.print("Responding w/:" + rep.toString());
            writeToClient(rep.toString());
            events.forEach((key, val) -> {
                if (key.equals(type)) {
                    parse.remove("packetType");
                    val.onEvent(parse.toString(), this);
                }
            });

        }

        try {
            welcomeSocket.close();
        } catch (IOException e) {

        }
        System.out.println("Closing server");
    }

    public void writeToClient(String buff) {
        try {
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            outToClient.writeBytes(buff + "\n");
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void registerPacketListener(String scope, MessageChannelListener list) {
        events.put(scope, list);
    }

    public void sayError(String messge) {
        JSONObject root = new JSONObject();
        root.put("status", "error");
        root.put("message", messge);
        writeToClient(root.toString());
    }

    public void sayOk(String message) {
        JSONObject root = new JSONObject();
        root.put("status", "OK");
        root.put("message", message);
        writeToClient(root.toString());
    }
}
