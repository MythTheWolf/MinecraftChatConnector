package com.myththewolf.MCC;

import com.myththewolf.BotServ.lib.API.invoke.BotPlugin;
import com.myththewolf.BotServ.lib.API.invoke.PluginAdapter;
import com.myththewolf.MCC.PacketHandlers.UserChatHandler;
import com.myththewolf.MCC.lib.SocketServer;


public class MCCMain implements PluginAdapter {
    public static BotPlugin plugin;


    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable(BotPlugin arg0) {
        plugin = arg0;
        SocketServer SS = new SocketServer(6789);
        SS.startServer();
        SS.registerPacketHandler("user-chat", new UserChatHandler());
    }

}
