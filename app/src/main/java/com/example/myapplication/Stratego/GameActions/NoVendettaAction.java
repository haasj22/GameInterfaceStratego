package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

import java.io.Serializable;

public class NoVendettaAction extends GameAction implements Serializable {
    public NoVendettaAction(GamePlayer player) {super(player);}
}
