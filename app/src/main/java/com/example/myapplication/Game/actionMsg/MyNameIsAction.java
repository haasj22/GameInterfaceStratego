package com.example.myapplication.Game.actionMsg;

import com.example.myapplication.Game.GamePlayer;

/**
 * An action by which the player tells the game its name
 * (typically the human's name, if it's a GameHumanPlayer).
 *
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class MyNameIsAction extends GameAction {

    // to satisfy the Serializable interface
    private static final long serialVersionUID = -4574617895412648866L;

    // the player's name
    private String name;

    /** constructor
     *
     * @param p
     * 		the player who sent the action
     * @param name
     * 		the player's name
     */
    public MyNameIsAction(GamePlayer p, String name) {
        // invoke superclass constructor
        super(p);
        // set the name
        this.name = name;
    }

    /**
     * getter-method for the name
     *
     * @return
     * 		the player's name
     */
    public String getName() {
        return name;
    }

}
