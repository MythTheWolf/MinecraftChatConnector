package com.myththewolf.MCC.PacketHandlers;

import com.myththewolf.BotServ.lib.API.invoke.BotPlugin;
import com.myththewolf.MCC.MCCMain;
import com.myththewolf.MCC.lib.QuickReplies;
import com.myththewolf.MCC.lib.SocketReceiver;
import com.myththewolf.MCC.lib.SocketServer;
import org.json.JSONObject;

public class UserChatHandler implements SocketReceiver {
    @Override
    public JSONObject onMessage(JSONObject data, SocketServer server) {
        String USERNAME = data.getString("username");
        String CONTENT = data.getString("content");
        BotPlugin inst = MCCMain.plugin;
        if (inst.getJSONConfig().isNull("chat-channel-id")) {

        }
        return QuickReplies.SUCCESS("Sent message to the channel");
    }

}

