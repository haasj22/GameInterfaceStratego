package com.example.myapplication.Game.actionMsg;

import com.example.myapplication.Game.util.GameTimer;

/**
 * An action generated by a time that tells the game that its
 * clock has 'ticked'.
 *
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class TimerAction extends GameAction{

    // to satisfy the Serializable interface
    private static final long serialVersionUID = -8093996755313861636L;

    // the timer that caused this action
    private GameTimer myTimer;

    /**
     * constructor
     *
     * @param timer
     * 		the timer that caused this action
     */
    public TimerAction(GameTimer timer) {
        // (there is no player associated with this action)
        super(null);
        myTimer = timer;
    }

    /** getter method for the timer
     *
     * @return
     * 		the timer that caused this action
     */
    public GameTimer getTimer() {
        return myTimer;
    }
}
