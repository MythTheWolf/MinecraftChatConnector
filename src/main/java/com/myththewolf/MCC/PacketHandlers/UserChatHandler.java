package com.myththewolf.MCC.PacketHandlers;

import com.myththewolf.MCC.lib.QuickReplies;
import com.myththewolf.MCC.lib.SocketReceiver;
import com.myththewolf.MCC.lib.SocketServer;
import org.json.JSONObject;

public class UserChatHandler implements SocketReceiver {
    @Override
    public JSONObject onMessage(JSONObject data, SocketServer server) {
        return QuickReplies.SUCCESS("Sent message to the channel");
    }
}
