package com.robomwm.moderatormode;

import com.robomwm.moderatormode.command.ModeratorModeCommand;
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
        getCommand("moderatormode").setExecutor(new ModeratorModeCommand());
    }
}
