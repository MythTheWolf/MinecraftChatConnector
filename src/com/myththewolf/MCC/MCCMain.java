package com.myththewolf.MCC;

import com.myththewolf.BotServ.lib.API.invoke.BotPlugin;
import com.myththewolf.BotServ.lib.API.invoke.PluginAdapter;
import com.myththewolf.MCC.lib.TCPServer;
import com.myththewolf.MCC.lib.messageListeners.ServerOnlineMessageListener;

public class MCCMain implements PluginAdapter {
  public static BotPlugin plugin;

  @Override
  public void onDisable() {
    // TODO Auto-generated method stub

  }

  @Override
  public void onEnable(BotPlugin arg0) {
    plugin = arg0;
    Thread T = new Thread(() -> {
      TCPServer srv = new TCPServer();
      try {
        srv.startServer();
        srv.registerEventListener("user-join", new ServerOnlineMessageListener());
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });
    T.start();
  }

}
