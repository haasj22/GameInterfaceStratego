package com.example.myapplication.Stratego.GameInfo;

import com.example.myapplication.Game.infoMsg.GameInfo;
import java.io.Serializable;

/**
 * Informs of an attack action
 */

public class StrategoAttackPieceInfo extends GameInfo implements Serializable {
    private int playerID;

    public StrategoAttackPieceInfo(int playerNum){playerID = playerNum;}

    public int getPlayerID() {return playerID;}
}
