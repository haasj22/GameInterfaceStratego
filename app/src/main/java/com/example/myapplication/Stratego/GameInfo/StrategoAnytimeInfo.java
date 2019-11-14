/**
 * Informs of an anytime action
 *
 * @author Jordan HO
 */
package com.example.myapplication.Stratego.GameInfo;

import com.example.myapplication.Game.infoMsg.GameInfo;
import java.io.Serializable;

public class StrategoAnytimeInfo extends GameInfo implements Serializable {
    // creates a playerID to send data
    private int playerID;
    public StrategoAnytimeInfo(int playerNum){playerID = playerNum;}
    public int getPlayerID(){return playerID;}
}
