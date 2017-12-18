package com.myththewolf.MCC.lib;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class TCPServer {
  private boolean run = false;
  private String clientSentence;
  private ServerSocket welcomeSocket;
  private Socket connectionSocket;
  private static HashMap<String, MessageChannelListener> events = new HashMap<>();
  private JSONObject parse = null;

  public void startServer() throws Exception {
    run = true;
    run();
  }
  
  private void run() throws IOException {


    welcomeSocket = new ServerSocket(6789);
    System.out.println("[MCC] Socket server running on /" + welcomeSocket.getLocalPort());
    while (run) {
      connectionSocket = welcomeSocket.accept();
      BufferedReader inFromClient =
          new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));


      clientSentence = inFromClient.readLine();


      try {
        parse = new JSONObject(clientSentence);

        if (parse == null || parse.isNull("packetType")) {

          throw new JSONException("ERR");
        }
      } catch (JSONException e) {
        sayError(e.getMessage());
        return;
      }
      
      String type = parse.getString("packetType");
      events.forEach((key, val) -> {
        if (key.equals(type)) {
          parse.remove("packetType");
          val.onEvent(parse.toString(), this);
        }
      });

    }
    welcomeSocket.close();
    System.out.println("Closing server");
  }

  public void writeToClient(String buff) {
    try {
      DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
      outToClient.writeBytes(buff + "\n");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void registerEventListener(String scope, MessageChannelListener list) {
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
