package com.myththewolf.MCC;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;

public class Utils {
  public static boolean isAdmin(Member mem) {
    return mem.hasPermission(Permission.MANAGE_SERVER);
  }
}
