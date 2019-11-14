/**
 * action that the user sends to forfeit the game
 *
 * @author John Haas
 */
package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

public class StrategoForfeitAction extends GameAction {
    public StrategoForfeitAction(GamePlayer player) {
        super(player);
    }
}
