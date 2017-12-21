package com.myththewolf.MCC;

import com.myththewolf.BotServ.lib.API.invoke.BotPlugin;
import com.myththewolf.BotServ.lib.API.invoke.PluginAdapter;
import com.myththewolf.MCC.PacketHandlers.UserChatHandler;
import com.myththewolf.MCC.commands.SetChatChannel;
import com.myththewolf.MCC.commands.SetInfoChannel;
import com.myththewolf.MCC.commands.SetStaffRequetsChannel;
import com.myththewolf.MCC.commands.printconfig;
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
        plugin.getJDAInstance().addEventListener(new MessageReceivedListener(plugin, SS));
        try {
            plugin.registerCommand("$printconf", new printconfig());
            plugin.registerCommand("$setchatchan", new SetChatChannel());
            plugin.registerCommand("$setinfochan", new SetInfoChannel());
            plugin.registerCommand("$setappchan", new SetStaffRequetsChannel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
