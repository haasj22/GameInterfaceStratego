package com.example.myapplication.Stratego.GameState;

import com.example.myapplication.Game.GameComputerPlayer;
import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.infoMsg.GameInfo;

public class SmartComputerPlayer extends GameComputerPlayer {
    StrategoGameState gameStateCopy;

    public SmartComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if(info instanceof  StrategoGameState) {
            gameStateCopy = (StrategoGameState)info;

            if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() != this.playerNum) {
                return;
            }

            if(gameStateCopy.getCurrentPhase() == Phase.SETUP_PHASE) {
                int flagRow;
                int flagCol;
                flagRow = (int)(Math.random() * 2);
                flagCol = (int)(Math.random() * 10);

                if(gameStateCopy.getCurrentTeamsTurn() == Team.RED_TEAM) {
                    flagRow+=8;
                }



            } else {

            }
        }
    }
}
