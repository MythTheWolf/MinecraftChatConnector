package com.myththewolf.MCC.lib.messageListeners;

import com.myththewolf.MCC.MCCMain;
import com.myththewolf.MCC.lib.MessageChannelListener;
import com.myththewolf.MCC.lib.TCPServer;
import org.json.JSONObject;

public class UserChatListener implements MessageChannelListener {
    @Override
    public void onEvent(String message, TCPServer server) {
        JSONObject conf = MCCMain.plugin.getJSONConfig();
        JSONObject parsed = new JSONObject(message);
        if (parsed.isNull("chat-channel")) {
            System.err.println("Recieved packet of type `user-chat` but it has no channel to go to! (Did you run ;setchatchan ?)");
            return;
        }
        String msg = "[" + parsed.getString("username") + "]" + parsed.getString("message");
        MCCMain.plugin.getJDAInstance().getTextChannelById(conf.getString("chat-channel")).sendMessage(msg).queue();
    }
}
