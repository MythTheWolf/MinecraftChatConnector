package com.myththewolf.MCC.commands;

import org.json.JSONObject;
import com.myththewolf.BotServ.lib.API.command.CommandExecutor;
import com.myththewolf.BotServ.lib.API.command.DiscordCommand;
import com.myththewolf.MCC.Utils;

public class setInfoChannel implements CommandExecutor {

  @Override
  public void onCommand(DiscordCommand command) {
    if (!Utils.isAdmin(command.e.getMember())) {
      command.failed("You must have the MANAGE_SERVER permission!");
      return;
    }
    if (command.e.getMessage().getMentionedChannels().size() < 1) {
      command.failed("You must supply a text channel! Use `!man setInfo` for help");
      return;
    }
    String chanID = command.e.getMessage().getMentionedChannels().get(0).getId();
    JSONObject config = command.getPlugin().getJSONConfig();
    config.put("user-join-channel", chanID);
    command.getPlugin().saveConfig(config);
    command.reply(":ok_hand: Updated.");
  }

}
