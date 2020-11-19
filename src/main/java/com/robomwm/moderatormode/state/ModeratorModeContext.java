package com.robomwm.moderatormode.state;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;

/**
 * Created on 2/29/2020.
 *
 * @author RoboMWM
 */
public class ModeratorModeContext
{
    private ModeratorModeState state;
    private Player player;
    private PermissionAttachment permissionAttachment;
    private Location lastSurvivalLocation;
    private Plugin plugin;

    public ModeratorModeContext(Player player, Plugin plugin)
    {
        this.player = player;
        this.plugin = plugin;
        state = null;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Location getLastSurvivalLocation()
    {
        return lastSurvivalLocation;
    }

    public void setLastSurvivalLocation(Location lastSurvivalLocation)
    {
        this.lastSurvivalLocation = lastSurvivalLocation;
    }

    public PermissionAttachment createOrGetPermissionAttachment()
    {
        if (permissionAttachment == null)
            permissionAttachment = player.addAttachment(plugin);
        return permissionAttachment;
    }

    public boolean removePermissionAttachment()
    {
        if (permissionAttachment != null)
        {
            try
            {
                player.removeAttachment(permissionAttachment);
            }
            catch (IllegalArgumentException e)
            {
                permissionAttachment = null;
                return false;
            }
        }
        permissionAttachment = null;
        return true;
    }

    public void toggleState()
    {
        if (!isInModeratorMode())
        {
            if (!prerequisites(player))
            {
                player.sendMessage(ChatColor.RED + "Unable to enter moderator mode due to issue printed above, please try again later.");
                return;
            }
            new EnterModeratorMode().toggleModeratorMode(this);
        }
        else
        {
            new ExitModeratorMode().toggleModeratorMode(this);
        }
    }

    public boolean isInModeratorMode()
    {
        return state instanceof EnterModeratorMode;
    }

    public boolean prerequisites(Player player)
    {
        if (!player.isOnGround())
        {
            System.out.println(player.getName() + " tried to enter moderator mode but wasn't on the ground.");
            player.sendMessage(ChatColor.RED + "You are not on the ground.");
            return false;
        }

        //Is being targeted by a monster?
        //Is a boss mob nearby?

        for (Mob entity : player.getLocation().getNearbyEntitiesByType(Mob.class,32, 32, 32))
        {
            if (entity instanceof Boss)
            {
                System.out.println(player.getName() + " tried to enter moderator mode but was near a boss.");
                player.sendMessage(ChatColor.RED + "A boss mob is nearby.");
                return false;
            }
            if (entity.getTarget() == player && entity instanceof Monster)
            {
                System.out.println(player.getName() + " tried to enter moderator mode but was being targeted by a monster.");
                player.sendMessage(ChatColor.RED + "A monster is targeting you: " + entity.getName());
                return false;
            }
        }

        if (player.getKiller() != null)
        {
            System.out.println(player.getName() + " tried to enter moderator mode but was recently damaged by a player.");
            player.sendMessage(ChatColor.RED + "You were recently pvp'ing with a player: " + player.getKiller().getName());
            return false;
        }

        return true;
    }

    public void setState(ModeratorModeState state)
    {
        this.state = state;
    }
}
