package com.example.myapplication.Stratego.GameInfo;

import com.example.myapplication.Game.infoMsg.GameInfo;
import java.io.Serializable;

/**
 * Informs of a hit bomb action
 */
public class StrategoHitBombInfo extends GameInfo implements Serializable {
    private int playerID;
    public StrategoHitBombInfo(int playerNum){playerID = playerNum;}
    public int getPlayerID(){return playerID;}
}
