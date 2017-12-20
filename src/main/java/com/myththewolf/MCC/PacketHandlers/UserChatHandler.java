package com.myththewolf.MCC.PacketHandlers;

import com.myththewolf.MCC.lib.SocketReceiver;
import com.myththewolf.MCC.lib.SocketServer;
import org.json.JSONObject;

public class UserChatHandler implements SocketReceiver {
    @Override
    public JSONObject onMessage(JSONObject data, SocketServer server) {
        System.out.println("Got chat packet:");
        JSONObject result = new JSONObject();
        return result;
    }
}
