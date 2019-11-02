package com.example.myapplication.Stratego.GameState;

import com.example.myapplication.Game.GameComputerPlayer;
import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.infoMsg.GameInfo;
import com.example.myapplication.Stratego.GameState.Phase;
import com.example.myapplication.Stratego.GameState.StrategoGameState;

public class DumbComputerPlayer extends GameComputerPlayer {
    StrategoGameState gameStateCopy;

    public DumbComputerPlayer(String name){ super(name);}

    /**
    protected void receiveInfo(GameInfo info){
        // receives info from current sate of the game
    }
    **/

    @Override
    protected void receiveInfo(GameInfo info) {
        if(info instanceof StrategoGameState) {
            gameStateCopy = (StrategoGameState)info;

            /*if(gameStateCopy.getCurrentTeamsTurn() != this.playerNum) {
                return;
            }*/

            if(gameStateCopy.getCurrentPhase() == Phase.SETUP_PHASE) {
                gameStateCopy.transitionPhases();
            } else {
                int row = (int)(Math.random() * 10);
                int col = (int)(Math.random() * 10);
                gameStateCopy.tapOnSquarePLAY(row, col);
            }
        }
    }


    @Override
    public boolean supportsGui() {
        return false;
    }
}
