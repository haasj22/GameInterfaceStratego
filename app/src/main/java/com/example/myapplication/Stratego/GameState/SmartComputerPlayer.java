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

                gameStateCopy.setLastTappedPieceButton(Rank.FLAG);
                gameStateCopy.tapOnSquare(flagRow, flagCol);
                gameStateCopy.tapOnSquare(flagRow, flagCol);

                gameStateCopy.setLastTappedPieceButton(Rank.BOMB);
                if(flagRow != gameStateCopy.getCurrentTeamsTurn().getTOPBOUNDARYINDEX()) {
                    gameStateCopy.tapOnSquare(flagRow-1, flagCol);
                    gameStateCopy.tapOnSquare(flagRow-1, flagCol);
                } else if(flagRow != gameStateCopy.getCurrentTeamsTurn().getBOTTOMBOUNDARYINDEX()) {
                    gameStateCopy.tapOnSquare(flagRow + 1, flagCol);
                    gameStateCopy.tapOnSquare(flagRow + 1, flagCol);
                }


            } else {

            }
        }
    }
}
