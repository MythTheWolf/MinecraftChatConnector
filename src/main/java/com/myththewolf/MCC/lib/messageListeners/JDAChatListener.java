package com.myththewolf.MCC.lib.messageListeners;

import com.myththewolf.MCC.MCCMain;
import com.myththewolf.MCC.lib.TCPServer;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.json.JSONObject;

public class JDAChatListener extends ListenerAdapter {
    private TCPServer server;

    public JDAChatListener(TCPServer x) {
        this.server = x;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        String expectID = MCCMain.plugin.getJSONConfig().isNull("chat-channel") ? null : MCCMain.plugin.getJSONConfig().getString("chat-channel");
        if (expectID == null || !event.getTextChannel().getId().equals(expectID))
            return;
        else {
            JSONObject packet = new JSONObject();
            packet.put("packetType", "message");
            packet.put("discord-id", event.getAuthor().getId());
            packet.put("message", event.getMessage().getContent());
            event.getMessage().delete().queue();
        }
    }
}
