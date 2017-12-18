package com.myththewolf.MCC;

import com.myththewolf.BotServ.lib.API.invoke.BotPlugin;
import com.myththewolf.BotServ.lib.API.invoke.PluginAdapter;
import com.myththewolf.MCC.lib.TCPServer;
import com.myththewolf.MCC.lib.messageListeners.ServerOnlineMessageListener;

public class MCCMain implements PluginAdapter {
  public static BotPlugin plugin;
  private TCPServer srv;

  @Override
  public void onDisable() {
    // TODO Auto-generated method stub

  }

  @Override
  public void onEnable(BotPlugin arg0) {
    plugin = arg0;
    srv = new TCPServer();
    Thread T = new Thread(() -> {
      try {
        srv.startServer();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });
    T.start();
    srv.registerEventListener("user-join", new ServerOnlineMessageListener());
  }

}
