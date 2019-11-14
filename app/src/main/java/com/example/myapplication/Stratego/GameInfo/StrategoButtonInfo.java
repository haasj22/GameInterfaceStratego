package com.example.myapplication.Stratego.GameInfo;

import com.example.myapplication.Game.Game;
import com.example.myapplication.Game.infoMsg.GameInfo;
import java.io.Serializable;

/**
 * Informs of a button action
 * @author Jordan Ho
 */
public class StrategoButtonInfo extends GameInfo implements Serializable {
    // creates a playerID to send data
    private int playerID;
    public StrategoButtonInfo(int playerNum){playerID = playerNum;}
    public int getPlayerID(){return playerID;}
}
