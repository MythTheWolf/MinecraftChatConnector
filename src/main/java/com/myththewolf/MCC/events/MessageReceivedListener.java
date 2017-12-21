package com.myththewolf.MCC.events;

import com.myththewolf.BotServ.lib.API.invoke.BotPlugin;
import com.myththewolf.MCC.lib.SocketServer;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import org.json.JSONObject;

public class MessageReceivedListener implements EventListener {
    private BotPlugin discordplugin;
    private SocketServer server;

    public MessageReceivedListener(BotPlugin pl, SocketServer SS) {
        discordplugin = pl;
        server = SS;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof MessageReceivedEvent) {
            String CHAT_CHANNEL = discordplugin.getJSONConfig().isNull("chat-channel-id") ? "NOP" : discordplugin.getJSONConfig().getString("chat-channel-id");
            if (((MessageReceivedEvent) event).getTextChannel().getId().equals(CHAT_CHANNEL)) {
                JSONObject packet = new JSONObject();
                packet.put("packetType", "user-chat");
                packet.put("discord-id", ((MessageReceivedEvent) event).getAuthor().getId());
                packet.put("content", ((MessageReceivedEvent) event).getMessage().getContent());
                server.writeBack(server.getConnectionSocket(), packet.toString());
            }
        }
    }
}
