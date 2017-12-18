package com.myththewolf.MCC.lib.messageListeners;

import org.json.JSONObject;
import com.myththewolf.BotServ.lib.API.invoke.BotPlugin;
import com.myththewolf.MCC.MCCMain;
import com.myththewolf.MCC.lib.MessageChannelListener;
import com.myththewolf.MCC.lib.TCPServer;

public class ServerOnlineMessageListener implements MessageChannelListener {

  @Override
  public void onEvent(String message, TCPServer server) {
    BotPlugin self = MCCMain.plugin;
    String ID = !self.getJSONConfig().isNull("user-join-channel")
        ? self.getJSONConfig().getString("user-join-channel")
        : self.getJDAInstance().getTextChannels().get(0).getId();
    JSONObject packet = new JSONObject(message);
    String finalmsg = "[UserJoin]" + packet.getString("username");
    self.getJDAInstance().getTextChannelById(ID).sendMessage(finalmsg).queue();
  }
}
