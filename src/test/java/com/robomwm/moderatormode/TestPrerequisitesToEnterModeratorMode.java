package com.robomwm.moderatormode;

import com.robomwm.moderatormode.command.ModeratorModeCommand;
import com.robomwm.moderatormode.state.StateTracker;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created on 4/4/2020.
 *
 * @author RoboMWM
 */
public class TestPrerequisitesToEnterModeratorMode
{
    StateTracker tracker = new StateTracker(mock(ModeratorMode.class));
    CommandExecutor executor = new ModeratorModeCommand(tracker);
    Command command = mock(Command.class);
    Player player = mock(Player.class, RETURNS_DEEP_STUBS);

    @Before
    public void setupCommand()
    {
        when(command.getName()).thenReturn("moderatormode");
        when(player.getKiller()).thenReturn(null);
    }

    //This should be broken up into multiple tests. Oh well, too lazy rn
    @Test
    public void whatever()
    {
        when(player.isOnGround()).thenReturn(false);
        checkForDeny();
        when(player.isOnGround()).thenReturn(true);

        Zombie followingMob = mock(Zombie.class, RETURNS_DEEP_STUBS);
        when(followingMob.getTarget()).thenReturn(player);
        Skeleton notFollowingMob = mock(Skeleton.class, RETURNS_DEEP_STUBS);
        List<Mob> mobs = new ArrayList<>();
        mobs.add(followingMob);
        mobs.add(notFollowingMob);
        when(player.getLocation().getNearbyEntitiesByType(Mob.class, 32, 32, 32)).thenReturn(mobs);
        checkForDeny();
        mobs.remove(followingMob);

        Wither wither = mock(Wither.class, RETURNS_DEEP_STUBS);
        mobs.add(wither);
        checkForDeny();
        mobs.remove(wither);

        when(player.getKiller()).thenReturn(player);
        checkForDeny();
        when(player.getKiller()).thenReturn(null);
    }

    public void checkForDeny()
    {
        executor.onCommand(player, command, "moderatormode", new String[0]);
        assert !tracker.isInModeratorMode(player);
    }
}
