package com.myththewolf.MCC.PacketHandlers;

import com.myththewolf.MCC.MCCMain;
import com.myththewolf.MCC.lib.PacketHandler;
import com.myththewolf.MCC.lib.SocketServer;
import org.json.JSONObject;

public class GetDiscordIdFromSecret implements PacketHandler {

    boolean found;
    int index = 0;

    @Override
    public JSONObject onMessage(JSONObject data, SocketServer server) {
        String reqSecret = data.getString("secret");
        found = false;
        index = 0;
        MCCMain.plugin.getJSONConfig().getJSONArray("waiting_accounts").forEach(cons -> {
            if (cons instanceof JSONObject) {
                JSONObject x = (JSONObject) cons;
                if (x.getString("secret").equals(reqSecret)) {
                    found = true;
                    index++;
                }
            }
        });
        if (found) {
            JSONObject sel = MCCMain.plugin.getJSONConfig().getJSONArray("waiting_accounts").getJSONObject(index);
            JSONObject packetReply = new JSONObject();
            packetReply.put("status", "OK");
            packetReply.put("discord-id", sel.getString("discord-id"));
            packetReply.put("message","Successfully located secret/ID pair");
            return packetReply;
        }else {
            JSONObject err = new JSONObject();
            err.put("status","NOT_FOUND");
            err.put("message","The requested discord secret could not be found");
            return err;
        }

    }
}
