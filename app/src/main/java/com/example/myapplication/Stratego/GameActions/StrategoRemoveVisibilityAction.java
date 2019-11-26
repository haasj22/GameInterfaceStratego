package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

import java.io.Serializable;

public class StrategoRemoveVisibilityAction extends GameAction implements Serializable {
    public StrategoRemoveVisibilityAction(GamePlayer player) {
        super(player);
    }
}
