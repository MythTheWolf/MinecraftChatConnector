package com.myththewolf.MCC.lib;

import org.json.JSONObject;

public interface PacketHandler {
    public JSONObject onMessage(JSONObject data, SocketServer server);

}
