package com.example.myapplication.Stratego.GameInfo;

import com.example.myapplication.Game.infoMsg.GameInfo;
import java.io.Serializable;

/**
 * Informs of an anytime action
 */
public class StrategoAnytimeInfo extends GameInfo implements Serializable {
    private int playerID;

    public StrategoAnytimeInfo(int playerNum){playerID = playerNum;}
    public int getPlayerID(){return playerID;}
}
