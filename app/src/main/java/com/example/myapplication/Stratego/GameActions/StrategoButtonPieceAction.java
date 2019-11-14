/**
 * creates an action that changes the lastTappedButton
 *
 * @author John Haas
 */
package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.Game;
import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Stratego.GameInfo.StrategoButtonInfo;
import com.example.myapplication.Game.actionMsg.GameAction;
import com.example.myapplication.Stratego.GameState.Rank;

import java.io.Serializable;


public class StrategoButtonPieceAction extends GameAction implements Serializable {

    //to satisfy the serializable interface
    private static final long serialVersionUID = 37297362L;
    //rank that the user is sending
    private Rank heldRank;

    /**
     * constructor for StrategoButtonPieceAction
     * @param player player that sent the action
     * @param buttonRank rank of the button that the user wants to change lastTappedButton to
     */
    public StrategoButtonPieceAction(GamePlayer player, Rank buttonRank) {
        super(player);
        heldRank=buttonRank;
    }

    public Rank getWhichButton() {
        return heldRank;
    }
}
