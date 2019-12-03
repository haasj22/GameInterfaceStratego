package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

import java.io.Serializable;

public class StrategoRemoveVisibilityAction extends GameAction implements Serializable {

    //to satisfy the serializable interface
    private static final long serialVersionUID = 575384343L;
    public StrategoRemoveVisibilityAction(GamePlayer player) {
        super(player);
    }
}
