package com.robomwm.moderatormode.state;

import org.bukkit.entity.Player;

/**
 * Created on 2/29/2020.
 *
 * @author RoboMWM
 */
public class ModeratorModeContext
{
    private ModeratorModeState state;
    private Player player;

    public ModeratorModeContext(Player player)
    {
        state = null;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setState(ModeratorModeState state)
    {
        this.state = state;
    }

    public ModeratorModeState getState()
    {
        return state;
    }
}
