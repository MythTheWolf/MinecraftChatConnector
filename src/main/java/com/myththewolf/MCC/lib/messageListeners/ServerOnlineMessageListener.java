package com.myththewolf.MCC.lib.messageListeners;

import java.util.Date;
import org.json.JSONObject;
import com.myththewolf.BotServ.lib.API.invoke.BotPlugin;
import com.myththewolf.MCC.MCCMain;
import com.myththewolf.MCC.lib.MessageChannelListener;
import com.myththewolf.MCC.lib.TCPServer;

public class ServerOnlineMessageListener implements MessageChannelListener {


  public void onEvent(String message, TCPServer server) {
    BotPlugin self = MCCMain.plugin;
    String ID = !self.getJSONConfig().isNull("server-info-channel")
        ? self.getJSONConfig().getString("server-info-channel")
        : self.getJDAInstance().getTextChannels().get(0).getId();
    JSONObject packet = new JSONObject(message);
    String finalmsg = "[" + new Date().toString() + "]" + "Server is online with "
        + packet.getString("pluginCount") + "plugins loaded.";
    self.getJDAInstance().getTextChannelById(ID).sendMessage(finalmsg).queue();
    JSONObject response = new JSONObject();
    response.put("status", "OK");
    server.writeToClient(response.toString());
  }
}
