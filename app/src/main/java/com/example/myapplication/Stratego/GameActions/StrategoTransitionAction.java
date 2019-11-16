/**
 * action that tells the game to transition phases
 *
 * @author John Haas, Kavya Mandla, Jordan Ho
 */
package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

import java.io.Serializable;

public class StrategoTransitionAction extends GameAction implements Serializable {

    //to satisfy the serializable interface
    private static final long serialVersionUID = 3468418744343L;

    public StrategoTransitionAction(GamePlayer player) {
        super(player);
    }
}
