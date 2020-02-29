package com.robomwm.moderatormode.state;

/**
 * Created on 2/29/2020.
 *
 * @author RoboMWM
 */
public class ExitModeratorMode implements ModeratorModeState
{
    @Override
    public void toggleModeratorMode(ModeratorModeContext context)
    {
        context.setState(this);
        //TODO: remove moderator attributes here
    }
}
