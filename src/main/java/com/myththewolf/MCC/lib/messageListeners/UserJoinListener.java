package com.myththewolf.MCC.lib.messageListeners;

import com.myththewolf.BotServ.lib.API.invoke.BotPlugin;
import com.myththewolf.MCC.MCCMain;
import com.myththewolf.MCC.lib.MessageChannelListener;
import com.myththewolf.MCC.lib.TCPServer;
import org.json.JSONObject;

public class UserJoinListener implements MessageChannelListener {
    @Override
    public void onEvent(String message, TCPServer server) {
        JSONObject parsed = new JSONObject(message);
        BotPlugin pl = MCCMain.plugin;
        JSONObject conf = pl.getJSONConfig();
        if (conf.isNull("info-channel")) {
            System.err.println("Recieved packet of type USER-JOIN but it has no channel to route to!");
            return;
        } else {
            String msg = "[UserJoin]" + parsed.getString("username");
            pl.getJDAInstance().getTextChannelById(conf.getString("info-channel")).sendMessage(msg).queue();
        }
    }
}
