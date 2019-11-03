package com.example.myapplication.Stratego.GameState;

import com.example.myapplication.Game.GameComputerPlayer;
import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.infoMsg.GameInfo;
import com.example.myapplication.Stratego.GameActions.StrategoSmartComputerSetupAction;
import com.example.myapplication.Stratego.GameActions.StrategoTransitionAction;

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
                this.game.sendAction(new StrategoSmartComputerSetupAction(this));
                this.game.sendAction(new StrategoTransitionAction(this));
            } else {
                /**
                 * check if opponent is on your side of the board if enemy is one away
                 * if so, attack
                 * if not, scan downward upward moving pieces around
                 *
                 */

            }
        }
    }
}
