package com.robomwm.moderatormode.state;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2/29/2020.
 *
 * @author RoboMWM
 */
public class StateTracker implements Listener
{
    private Map<Player, ModeratorModeContext> moderators = new HashMap<>();



    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        ModeratorModeContext context = moderators.get(event.getPlayer());

        if (context != null && context.getState())
        {
            context.setState(new ExitModeratorMode());
        }
    }
}
