package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

import java.io.Serializable;

public class NoVendettaAction extends GameAction implements Serializable {
    //to satisfy the serializable interface
    private static final long serialVersionUID = 88654244343L;
    public NoVendettaAction(GamePlayer player) {super(player);}
}
