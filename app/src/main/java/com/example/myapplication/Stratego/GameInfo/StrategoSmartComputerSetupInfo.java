package com.example.myapplication.Stratego.GameInfo;

import com.example.myapplication.Game.infoMsg.GameInfo;
import java.io.Serializable;

/**
 * Informs of a smart computer player setup action
 */
public class StrategoSmartComputerSetupInfo extends GameInfo implements Serializable {
    private int playerID;

    public StrategoSmartComputerSetupInfo(int playerNum){playerID = playerNum;}
    public int getPlayerID(){return playerID;}
}
