package com.example.myapplication.Stratego.GameInfo;

import com.example.myapplication.Game.infoMsg.GameInfo;
import java.io.Serializable;

/**
 * Informs of a move piece action
 */

public class StrategoTransitionInfo extends GameInfo implements Serializable {
    private int playerID;
    public StrategoTransitionInfo(int playerNum){playerID = playerNum;}
    public int getPlayerID(){return playerID;}
}
