/**
 * action that the user sends to forfeit the game
 *
 * @author John Haas, Kavya Mandla, Jordan Ho
 */
package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

/**
 * StrategoForfeitAction
 */
public class StrategoForfeitAction extends GameAction {
    public StrategoForfeitAction(GamePlayer player) {
        super(player);
    }
}
