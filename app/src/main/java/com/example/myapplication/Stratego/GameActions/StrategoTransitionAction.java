/**
 * action that tells the game to transition phases
 *
 * @author John Haas
 */
package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

public class StrategoTransitionAction extends GameAction {
    public StrategoTransitionAction(GamePlayer player) {
        super(player);
    }
}
