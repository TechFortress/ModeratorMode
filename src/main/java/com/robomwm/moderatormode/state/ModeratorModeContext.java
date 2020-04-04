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
    private boolean isInModeratorMode = false;

    public ModeratorModeContext(Player player)
    {
        state = null;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void toggleState()
    {
        if (!isInModeratorMode)
        {
            //TODO: put checks here
            this.state = new EnterModeratorMode();
            isInModeratorMode = true;
        }
        else
        {
            this.state = new ExitModeratorMode();
            isInModeratorMode = false;
        }
    }

    public boolean isInModeratorMode()
    {
        return isInModeratorMode;
    }
}
