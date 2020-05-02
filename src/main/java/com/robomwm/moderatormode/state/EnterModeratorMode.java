package com.robomwm.moderatormode.state;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * Created on 2/29/2020.
 *
 * @author RoboMWM
 */
public class EnterModeratorMode implements ModeratorModeState
{
    @Override
    public void toggleModeratorMode(ModeratorModeContext context)
    {
        Player player = context.getPlayer();
        context.setLastSurvivalLocation(player.getLocation());
        context.createOrGetPermissionAttachment().setPermission("ticketmaster.helper.basic", true);
        context.createOrGetPermissionAttachment().setPermission("griefprevention.softmute", true);
        player.setGameMode(GameMode.SPECTATOR);

        player.sendActionBar(ChatColor.GREEN + "Entered moderator mode.");
    }
}
