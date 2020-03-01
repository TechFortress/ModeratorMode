package com.robomwm.moderatormode;

import com.robomwm.moderatormode.command.ModeratorModeCommand;
import com.robomwm.moderatormode.state.StateTracker;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created on 2/17/2020.
 *
 * @author RoboMWM
 */
public class ModeratorMode extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        StateTracker tracker = new StateTracker();

        getCommand("moderatormode").setExecutor(new ModeratorModeCommand(tracker));
    }
}
