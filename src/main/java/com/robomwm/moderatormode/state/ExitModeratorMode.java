package com.robomwm.moderatormode.state;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created on 2/29/2020.
 *
 * @author RoboMWM
 */
public class ExitModeratorMode implements ModeratorModeState
{
    @Override
    public void toggleModeratorMode(ModeratorModeContext context)
    {
        Player player = context.getPlayer();
        //TODO: remove moderator attributes here

        context.removePermissionAttachment();
        player.teleport(context.getLastSurvivalLocation());
        player.sendActionBar(ChatColor.GREEN + "Exited moderator mode.");
    }
}
