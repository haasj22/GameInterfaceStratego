package com.example.myapplication.Stratego.GameInfo;

import com.example.myapplication.Game.infoMsg.GameInfo;
import java.io.Serializable;

/**
 * Informs of a capture flag action
 */
public class StrategoCaptureFlagInfo extends GameInfo implements Serializable {
    private int playerID;
    public StrategoCaptureFlagInfo(int playerNum){playerID = playerNum;}
    public int getPlayerID(){return playerID;}
}
