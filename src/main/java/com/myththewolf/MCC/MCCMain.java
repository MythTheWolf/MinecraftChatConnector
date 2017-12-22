package com.myththewolf.MCC;

import com.myththewolf.BotServ.lib.API.invoke.BotPlugin;
import com.myththewolf.BotServ.lib.API.invoke.PluginAdapter;
import com.myththewolf.MCC.PacketHandlers.GetDiscordIdFromSecret;
import com.myththewolf.MCC.PacketHandlers.UserChatHandler;
import com.myththewolf.MCC.commands.*;
import com.myththewolf.MCC.events.MessageReceivedListener;
import com.myththewolf.MCC.lib.SocketServer;


public class MCCMain implements PluginAdapter {
    public static BotPlugin plugin;


    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable(BotPlugin plugin) {
        MCCMain.plugin = plugin;
        SocketServer SS = new SocketServer(6789);
        SS.startServer();
        SS.registerPacketHandler("user-chat", new UserChatHandler());
        SS.registerPacketHandler("get-id-from-secret", new GetDiscordIdFromSecret());
        plugin.getJDAInstance().addEventListener(new MessageReceivedListener(plugin, SS));
        try {
            plugin.registerCommand("$printconf", new printconfig());
            plugin.registerCommand("$setchatchan", new SetChatChannel());
            plugin.registerCommand("$setinfochan", new SetInfoChannel());
            plugin.registerCommand("$setappchan", new SetStaffRequetsChannel());
            plugin.registerCommand("$linkaccount", new linkmc());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
