package com.example.myapplication.Stratego.GameActions;

import android.widget.Button;

import com.example.myapplication.Game.Game;
import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;
import com.example.myapplication.Stratego.GameState.Rank;

public class ButtonPieceAction extends GameAction {

    private Rank heldRank;

    public ButtonPieceAction(GamePlayer player, Rank buttonRank) {
        super(player);
        heldRank=buttonRank;
    }

    public Rank getWhichButton() {
        return heldRank;
    }
}
