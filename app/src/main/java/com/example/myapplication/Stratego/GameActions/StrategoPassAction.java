package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

import java.io.Serializable;

public class StrategoPassAction extends GameAction implements Serializable {

    //to satisfy the serializable interface
    private static final long serialVersionUID = 8394342443L;

    public StrategoPassAction(GamePlayer player) {super(player);}
}
