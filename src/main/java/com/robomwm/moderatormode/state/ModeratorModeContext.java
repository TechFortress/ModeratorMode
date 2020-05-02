package com.robomwm.moderatormode.state;

import org.bukkit.ChatColor;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

/**
 * Created on 2/29/2020.
 *
 * @author RoboMWM
 */
public class ModeratorModeContext
{
    private ModeratorModeState state;
    private Player player;
    private boolean isInModeratorMode = false;
    private PermissionAttachment permissionAttachment;

    public ModeratorModeContext(Player player, PermissionAttachment moderatorPermissions)
    {
        this.player = player;
        this.permissionAttachment = moderatorPermissions;
        state = null;
    }

    public Player getPlayer()
    {
        return player;
    }

    public PermissionAttachment getPermissionAttachment()
    {
        return permissionAttachment;
    }

    public void toggleState()
    {
        if (!isInModeratorMode)
        {
            if (!prerequisites(player))
            {
                player.sendMessage(ChatColor.RED + "Unable to enter moderator mode at this time, please try again later.");
                return;
            }
            this.state = new EnterModeratorMode();
            isInModeratorMode = true;
            player.sendMessage(ChatColor.DARK_GREEN + "Entered Moderator Mode");
        }
        else
        {
            this.state = new ExitModeratorMode();
            isInModeratorMode = false;
            player.sendMessage(ChatColor.DARK_GREEN + "Exited Moderator Mode");
        }
    }

    public boolean isInModeratorMode()
    {
        return isInModeratorMode;
    }

    public boolean prerequisites(Player player)
    {
        if (!player.isOnGround())
        {
            System.out.println(player.getName() + " tried to enter moderator mode but wasn't on the ground.");
            return false;
        }

        //Is being targeted by a monster?
        //Is a boss mob nearby?

        for (Mob entity : player.getLocation().getNearbyEntitiesByType(Mob.class,32, 32, 32))
        {
            if (entity instanceof Boss)
            {
                System.out.println(player.getName() + " tried to enter moderator mode but was near a boss.");
                return false;
            }
            if (entity.getTarget() == player && entity instanceof Monster)
            {
                System.out.println(player.getName() + " tried to enter moderator mode but was being targeted by a monster.");
                return false;
            }
        }

        if (player.getKiller() != null)
        {
            System.out.println(player.getName() + " tried to enter moderator mode but was recently damaged by a player.");
            return false;
        }

        return true;
    }
}
