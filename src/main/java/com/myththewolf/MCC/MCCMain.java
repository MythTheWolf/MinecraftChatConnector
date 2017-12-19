package com.myththewolf.MCC;

import com.myththewolf.BotServ.lib.API.invoke.BotPlugin;
import com.myththewolf.BotServ.lib.API.invoke.PluginAdapter;
import com.myththewolf.MCC.commands.SetChatChan;
import com.myththewolf.MCC.commands.setInfoChannel;
import com.myththewolf.MCC.lib.TCPServer;
import com.myththewolf.MCC.lib.messageListeners.JDAChatListener;
import com.myththewolf.MCC.lib.messageListeners.ServerOfflineListener;
import com.myththewolf.MCC.lib.messageListeners.ServerOnlineMessageListener;
import com.myththewolf.MCC.lib.messageListeners.UserJoinListener;

public class MCCMain implements PluginAdapter {
    public static BotPlugin plugin;
    private TCPServer srv;

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable(BotPlugin arg0) {
        plugin = arg0;
        srv = new TCPServer();

        Thread T = new Thread(() -> {
            try {
                srv.startServer();
            } catch (Exception e) {

                e.printStackTrace();
            }
        });
        T.start();
        srv.registerPacketListener("server-online", new ServerOnlineMessageListener());
        srv.registerPacketListener("server", new ServerOfflineListener());
        srv.registerPacketListener("user-join", new UserJoinListener());
        try {
            arg0.registerCommand(";setinfochan", new setInfoChannel());
            arg0.registerCommand(";setchatchan", new SetChatChan());
            arg0.getJDAInstance().addEventListener(new JDAChatListener(srv));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
