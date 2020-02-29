package com.robomwm.moderatormode.state;

/**
 * Created on 2/29/2020.
 *
 * @author RoboMWM
 */
public class EnterModeratorMode implements ModeratorModeState
{
    @Override
    public void toggleModeratorMode(ModeratorModeContext context)
    {
        context.setState(this);
        //TODO: add moderator attributes here
    }
}
