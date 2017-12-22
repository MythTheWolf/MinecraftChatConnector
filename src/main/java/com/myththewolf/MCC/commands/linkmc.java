package com.myththewolf.MCC.commands;

import com.myththewolf.BotServ.lib.API.command.CommandExecutor;
import com.myththewolf.BotServ.lib.API.command.DiscordCommand;
import net.dv8tion.jda.core.entities.ChannelType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

public class linkmc implements CommandExecutor {
    @Override
    public void onCommand(DiscordCommand discordCommand) {
        JSONObject conf = discordCommand.getPlugin().getJSONConfig();
        JSONObject putt = new JSONObject();
        String ID = UUID.randomUUID().toString();
        putt.put("discord-id", discordCommand.getSender().getId());
        putt.put("secret", ID);
        conf.getJSONArray("waiting_accounts").put(putt);
        discordCommand.getPlugin().saveConfig(conf);
        if (discordCommand.e.isFromType(ChannelType.PRIVATE)) {
            discordCommand.reply(":warning: Please check your DMs.");
        }
        discordCommand.getSender().openPrivateChannel().complete().sendMessage("Run this command in MC: `/mclink " + ID + "`").queue();
    }
}
