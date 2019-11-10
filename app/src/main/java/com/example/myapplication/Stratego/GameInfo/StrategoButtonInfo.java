package com.example.myapplication.Stratego.GameInfo;

import com.example.myapplication.Game.Game;
import com.example.myapplication.Game.infoMsg.GameInfo;
import java.io.Serializable;

/**
 * Informs of a button action
 */
public class StrategoButtonInfo extends GameInfo implements Serializable {
    private int playerID;

    public StrategoButtonInfo(int playerNum){playerID = playerNum;}
    public int getPlayerID(){return playerID;}
}
