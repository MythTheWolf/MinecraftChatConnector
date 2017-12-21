package com.myththewolf.MCC.lib;

import org.json.JSONObject;

public class QuickReplies {

    public static JSONObject EMPTY() {
        return new JSONObject();
    }

    public static JSONObject SUCCESS(String msg) {
        JSONObject succ = new JSONObject();
        succ.put("status", "OK");
        succ.put("message", msg);
        return succ;
    }
}
