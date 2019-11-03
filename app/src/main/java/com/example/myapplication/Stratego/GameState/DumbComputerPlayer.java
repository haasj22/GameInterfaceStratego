package com.example.myapplication.Stratego.GameState;

import com.example.myapplication.Game.GameComputerPlayer;
import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.infoMsg.GameInfo;
import com.example.myapplication.Stratego.GameActions.StrategoMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoTransitionAction;
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

            if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() != this.playerNum) {
                return;
            }

            if(gameStateCopy.getCurrentPhase() == Phase.SETUP_PHASE) {
                this.game.sendAction(new StrategoTransitionAction(this));
            } else {
                int row = (int)(Math.random() * 10);
                int col = (int)(Math.random() * 10);
                this.game.sendAction(new StrategoMoveAction(this, row, col));
            }
        }
    }


    @Override
    public boolean supportsGui() {
        return false;
    }
}
