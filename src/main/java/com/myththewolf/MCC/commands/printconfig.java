package com.myththewolf.MCC.commands;

import com.myththewolf.BotServ.lib.API.command.CommandExecutor;
import com.myththewolf.BotServ.lib.API.command.DiscordCommand;


public class printconfig implements CommandExecutor {
    @Override
    public void onCommand(DiscordCommand discordCommand) {
        discordCommand.reply(discordCommand.getPlugin().getJSONConfig().toString());
    }
}
