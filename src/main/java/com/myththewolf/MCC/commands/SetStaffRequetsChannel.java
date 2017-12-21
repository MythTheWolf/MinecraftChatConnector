package com.myththewolf.MCC.commands;

import com.myththewolf.BotServ.lib.API.command.CommandExecutor;
import com.myththewolf.BotServ.lib.API.command.DiscordCommand;
import com.myththewolf.MCC.Utils;
import org.json.JSONObject;

public class SetStaffRequetsChannel implements CommandExecutor {
    @Override
    public void onCommand(DiscordCommand discordCommand) {
        if (!Utils.isAdmin(discordCommand.e.getMember())) {
            discordCommand.failed("You must have the MANAGE_SERVER permission to execute this!");
            return;
        }
        if (discordCommand.e.getMessage().getMentionedChannels().size() < 1) {
            discordCommand.failed("Invalid number of args! Use !man `setappchan` for help");
            return;
        }
        String ID = discordCommand.e.getMessage().getMentionedChannels().get(0).getId();
        JSONObject conf = discordCommand.getPlugin().getJSONConfig();
        conf.put("staff-app-channel-id", ID);
        discordCommand.getPlugin().saveConfig(conf);
        discordCommand.reply(":ok_hand: Updated!");
    }
}
