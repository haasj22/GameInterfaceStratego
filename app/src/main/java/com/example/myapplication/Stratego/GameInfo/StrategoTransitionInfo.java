/**
 * Informs of a move piece action
 * @author Jordan Ho
 */
package com.example.myapplication.Stratego.GameInfo;

import com.example.myapplication.Game.infoMsg.GameInfo;
import java.io.Serializable;

public class StrategoTransitionInfo extends GameInfo implements Serializable {
    // creates a playerID to send data
    private int playerID;
    public StrategoTransitionInfo(int playerNum){playerID = playerNum;}
    public int getPlayerID(){return playerID;}
}
