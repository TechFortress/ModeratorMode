package com.robomwm.moderatormode.state;

import org.bukkit.ChatColor;
import org.bukkit.entity.Boss;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2/29/2020.
 *
 * @author RoboMWM
 */
public class StateTracker implements Listener
{
    private Plugin plugin;

    public StateTracker(Plugin plugin)
    {
        this.plugin = plugin;
    }

    private Map<Player, ModeratorModeContext> moderators = new HashMap<>();

    public void toggleModeratorMode(Player player)
    {
        if (!moderators.containsKey(player))
            moderators.put(player, new ModeratorModeContext(player, player.addAttachment(plugin)));
        ModeratorModeContext context = moderators.get(player);
        context.toggleState();
    }

    public boolean isInModeratorMode(Player player)
    {
        ModeratorModeContext context = moderators.get(player);
        return context != null && context.isInModeratorMode();
    }

    @EventHandler
    public void cleanupOnQuit(PlayerQuitEvent event)
    {
        ModeratorModeContext context = moderators.get(event.getPlayer());

        if (context != null && context.isInModeratorMode())
        {
            context.toggleState();
            moderators.remove(event.getPlayer());
        }
    }
}
