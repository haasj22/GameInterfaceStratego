package com.example.myapplication.Stratego.GameActions;

import android.widget.Button;

import com.example.myapplication.Game.Game;
import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Stratego.GameInfo.StrategoButtonInfo;
import com.example.myapplication.Game.actionMsg.GameAction;
import com.example.myapplication.Stratego.GameState.Rank;

import java.io.Serializable;

public class StrategoButtonPieceAction extends GameAction implements Serializable {

    private Rank heldRank;

    /**
     * constructor for StrategoButtonPieceAction
     * @param player
     * @param buttonRank
     */
    public StrategoButtonPieceAction(GamePlayer player, Rank buttonRank) {
        super(player);
        heldRank=buttonRank;
    }
    /**
    public StrategoButtonInfo getGameInfo(int playerID){
        return new StrategoButtonInfo(playerID);
    }
     **/
    public Rank getWhichButton() {
        return heldRank;
    }
}
