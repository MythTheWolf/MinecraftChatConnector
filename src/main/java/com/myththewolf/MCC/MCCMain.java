package com.myththewolf.MCC;

import com.myththewolf.BotServ.lib.API.invoke.BotPlugin;
import com.myththewolf.BotServ.lib.API.invoke.PluginAdapter;


public class MCCMain implements PluginAdapter {
    public static BotPlugin plugin;


    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable(BotPlugin arg0) {
        plugin = arg0;

    }

}
