package com.robomwm.moderatormode;

import com.robomwm.moderatormode.command.ModeratorModeCommand;
import com.robomwm.moderatormode.state.StateTracker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created on 2/17/2020.
 *
 * @author RoboMWM
 */
public class TestToggle
{
    StateTracker tracker = new StateTracker(mock(ModeratorMode.class));
    CommandExecutor executor = new ModeratorModeCommand(tracker);
    Command command = mock(Command.class);
    Player player = mock(Player.class, RETURNS_DEEP_STUBS);
    Player modPlayer = mock(Player.class, RETURNS_DEEP_STUBS);

    @Before
    public void setupCommand()
    {
        when(command.getName()).thenReturn("moderatormode");
        when(modPlayer.isOnGround()).thenReturn(true);
        when(modPlayer.getKiller()).thenReturn(null);
    }

    @Test
    public void testCommand()
    {
        assert !tracker.isInModeratorMode(player);
        assert !tracker.isInModeratorMode(modPlayer);

        //Permission tests don't work because those are handled by Bukkit (defined at plugin.yml)
        //assert !executor.onCommand(player, command, "moderatorMode", new String[0]);
        assert executor.onCommand(modPlayer, command, "moderatormode", new String[0]);

        //assert !tracker.isInModeratorMode(player);
        assert tracker.isInModeratorMode(modPlayer);

        //assert !executor.onCommand(player, command, "moderatorMode", new String[0]);
        assert executor.onCommand(modPlayer, command, "moderatormode", new String[0]);

        //assert !tracker.isInModeratorMode(player);
        assert !tracker.isInModeratorMode(modPlayer);
    }
}
