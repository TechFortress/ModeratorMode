package com.robomwm.moderatormode.command;

import com.robomwm.moderatormode.state.StateTracker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created on 2/29/2020.
 *
 * @author RoboMWM
 */
public class ModeratorModeCommand implements CommandExecutor
{
    private StateTracker tracker;

    public ModeratorModeCommand(StateTracker tracker)
    {
        this.tracker = tracker;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player))
            return false;

        Player player = (Player)sender;

        tracker.toggleModeratorMode(player);
        return true;
    }
}
