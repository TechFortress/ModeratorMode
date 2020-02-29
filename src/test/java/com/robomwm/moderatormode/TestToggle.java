package com.robomwm.moderatormode;

import com.robomwm.moderatormode.command.ModeratorModeCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created on 2/17/2020.
 *
 * @author RoboMWM
 */
public class TestToggle
{
    CommandExecutor executor = new ModeratorModeCommand();
    Command command = mock(Command.class);
    Player player = mock(Player.class);
    Player modPlayer = mock(Player.class);

    @Before
    public void setupCommand()
    {
        when(command.getName()).thenReturn("moderatormode");
    }

    //TODO: update to check moderator status
    @Test
    public void test()
    {
        assert !executor.onCommand(player, command, "moderatorMode", new String[0]);
        assert executor.onCommand(modPlayer, command, "moderatormode", new String[0]);
    }
}
