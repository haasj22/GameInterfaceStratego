package com.example.myapplication.Stratego.StrategoFrameworkClasses;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.LocalGame;
import com.example.myapplication.Game.actionMsg.GameAction;
import com.example.myapplication.Stratego.GameActions.StrategoForfeitAction;
import com.example.myapplication.Stratego.GameActions.StrategoMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoTransitionAction;
import com.example.myapplication.Stratego.GameState.StrategoGameState;

public class StrategoLocalGame extends LocalGame {
    //tag for logging
    private static final String TAG = "StrategoLocalGame";
    //the game state
    protected StrategoGameState state;

    /**
     * Constructor for StrategoLocalGame
     */
    public StrategoLocalGame() {

        //perform superclass initialization
        super();

        //create a new unfilled-in Stratego Game State
        state = new StrategoGameState();
    }

    @Override
    protected String checkIfGameOver() {
        if(state.isGameOver() == -1) {
            return "Blue team wins";
        } else if(state.isGameOver() == 1) {
            return "Red team wins";
        } else {
            return null;
        }
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new StrategoGameState(state));
    }


    protected boolean canMove(int playerIdx) { return state.getCurrentTeamsTurn().getTEAMNUMBER() == playerIdx;}

    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof StrategoMoveAction) {
            StrategoMoveAction sma = (StrategoMoveAction)action;
            state.tapOnSquare(sma.getRow(), sma.getCol());
        } else if (action instanceof StrategoForfeitAction) {
            StrategoForfeitAction sfa = (StrategoForfeitAction)action;
            state.forfeitGame();
        } else if (action instanceof StrategoTransitionAction) {
            StrategoTransitionAction sta = (StrategoTransitionAction)action;
            state.transitionPhases();
        }
        return true;
    }
}
