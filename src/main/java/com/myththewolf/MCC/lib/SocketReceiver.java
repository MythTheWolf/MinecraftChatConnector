package com.myththewolf.MCC.lib;

import org.json.JSONObject;

public interface SocketReceiver {
    public JSONObject onMessage(JSONObject data, SocketServer server);

}
