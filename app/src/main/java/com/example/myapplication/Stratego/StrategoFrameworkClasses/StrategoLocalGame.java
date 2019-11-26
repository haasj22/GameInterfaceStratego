package com.example.myapplication.Stratego.StrategoFrameworkClasses;

import android.util.Log;

import java.io.Serializable;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.LocalGame;
import com.example.myapplication.Game.actionMsg.GameAction;
import com.example.myapplication.Game.util.GameTimer;
import com.example.myapplication.Stratego.GameActions.StrategoButtonPieceAction;
import com.example.myapplication.Stratego.GameActions.StrategoComputerMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoForfeitAction;
import com.example.myapplication.Stratego.GameActions.StrategoMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoMuteAction;
import com.example.myapplication.Stratego.GameActions.StrategoPassAction;
import com.example.myapplication.Stratego.GameActions.StrategoRemoveVisibilityAction;
import com.example.myapplication.Stratego.GameActions.StrategoSmartComputerSetupAction;
import com.example.myapplication.Stratego.GameActions.StrategoTransitionAction;
import com.example.myapplication.Stratego.GameState.Rank;
import com.example.myapplication.Stratego.GameState.SmartComputerPlayer;
import com.example.myapplication.Stratego.GameState.StrategoGameState;
import com.example.myapplication.Stratego.GameState.Team;

/**
 * The local game to control the master StrategoGameState
 * @author John Haas
 * @author Jordan Ho
 */
public class StrategoLocalGame extends LocalGame implements Serializable {
    //tag for logging
    private static final String TAG = "StrategoLocalGame";
    //the game state
    private StrategoGameState state;
    private GameTimer redTimer;

    /**
     * Constructor for StrategoLocalGame
     */
    public StrategoLocalGame() {
        super();
        // create the state for the beginning of the game
        state = new StrategoGameState();
        //perform superclass initialization
        redTimer = new GameTimer(this);
        redTimer.setInterval(1000);
        redTimer.start();
    }

    /**
     * Sends an updated game state to the players
     *
     * @param p the player to send info to
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //makes sure player exists
        if(p == null){
            return;
        }
        // if there is no state to send, ignore
        if(state == null){
            return;
        }
        //creates a copy of the official state and sends it to the desired player
        StrategoGameState stateForPlayer = new StrategoGameState(state);
        p.sendInfo((stateForPlayer));
    }

    /**
     * Determines whether a player is allowed to move
     * @param playerIdx
     * 		the player's player-number (ID) of the player in question
     * @return true if the player can move
     *         false if the player cannot move
     */
    protected boolean canMove(int playerIdx) {
        int activePlayer = state.getCurrentTeamsTurn().getTEAMNUMBER();
        return (activePlayer == playerIdx);
    }

    /**
     * Checks whether the game is over; if so, returns a string giving the result
     * otherwise, return null
     * @return a message stating which team won
     *         null if the game is not over
     */
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

    /**
     * Makes a move on behalf of a player
     * @param action The move that the player has sent to the game
     * @return True if the move was legal; false otherwise
     */
    @Override
    protected boolean makeMove(GameAction action) {
        // uses the actions to check instances of player actions
        //handles a move action by tapping on a square
        if(action instanceof StrategoMoveAction) {
            StrategoMoveAction sma = (StrategoMoveAction)action;
            state.tapOnSquare(sma.getRow(), sma.getCol());
        }
        //handles a forfeit action by telling the game state to end the game
        else if (action instanceof StrategoForfeitAction) {
            StrategoForfeitAction sfa = (StrategoForfeitAction)action;
            state.forfeitGame();
        }
        //handles a transition action by telling the game to transition phases
        else if (action instanceof StrategoTransitionAction) {
            StrategoTransitionAction sta = (StrategoTransitionAction)action;
            state.transitionPhases();
        }
        //handles a button press by updating the proper game state variable
        else if (action instanceof StrategoButtonPieceAction) {
            StrategoButtonPieceAction bpa = (StrategoButtonPieceAction)action;
            state.setLastTappedPieceButton(bpa.getWhichButton());
        }
        else if (action instanceof StrategoMuteAction){
            StrategoMuteAction ma = (StrategoMuteAction)action;
            state.muteGame();

        }
        //handles a computers setup action by setting up the board
        else if (action instanceof StrategoSmartComputerSetupAction) {
            StrategoSmartComputerSetupAction ssca = (StrategoSmartComputerSetupAction)action;
            this.letSmartComputerSetup();
        }
        //allows computers to make two dependant moves with one action
        else if (action instanceof StrategoComputerMoveAction) {
            StrategoComputerMoveAction scma = (StrategoComputerMoveAction) action;
            state.tapOnSquare(scma.getOldRow(), scma.getOldCol());
            state.tapOnSquare(scma.getFutureRow(), scma.getFutureCol());
        }
        //allows a player to pass if they cannot move
        else if (action instanceof StrategoPassAction) {
            StrategoPassAction spa = (StrategoPassAction)action;
            state.transitionTurns();
        }
        //allows the game to remove the visibility of a temporarily visible piece
        else if (action instanceof StrategoRemoveVisibilityAction) {
            StrategoRemoveVisibilityAction srva = (StrategoRemoveVisibilityAction) action;
            try{
                Thread.sleep(500);
            } catch(InterruptedException e) {

            }
            state.removeTemporaryVisiblePiece();
        }
        return true;
    }

    /**
     * method that allows the smart player to setup their game board
     */
    public void letSmartComputerSetup() {
        //generates a random spot for the flag
        int randomRow = (int)(Math.random() * 2);
        int randomCol = (int)(Math.random() * 10);

        //adjusts the flag for the proper team
        if(state.getCurrentTeamsTurn().getTEAMNUMBER() == 0) {
            randomRow += 8;
        }

        //adds the flag to the board
        state.setLastTappedPieceButton(Rank.FLAG);
        state.tapOnSquare(randomRow, randomCol);
        state.tapOnSquare(randomRow, randomCol);

        //places bombs around the flag if possible
        state.setLastTappedPieceButton(Rank.BOMB);
        if(randomRow != 0) {
            state.tapOnSquare(randomRow - 1, randomCol);
            state.tapOnSquare(randomRow - 1, randomCol);
        }
        if(randomRow != 9) {
            state.tapOnSquare(randomRow + 1, randomCol);
            state.tapOnSquare(randomRow + 1, randomCol);
        }
        if(randomCol != 0) {
            state.tapOnSquare(randomRow, randomCol -1);
            state.tapOnSquare(randomRow, randomCol -1);
        }
        if(randomCol != 9) {
            state.tapOnSquare(randomRow, randomCol + 1);
            state.tapOnSquare(randomRow, randomCol + 1);
        }

        //transitions the player to play phase
        state.transitionPhases();
    }

    /**
     * method that tells the computer what to happen when it ticks
     */
    @Override
    public void timerTicked() {
        //subtracts one second from the timer of the current team
        if(state.getCurrentTeamsTurn() == Team.RED_TEAM) {
            state.setRedTeamSeconds(state.getRedTeamSeconds() - 1);
        } else {
            state.setBlueTeamSeconds(state.getBlueTeamSeconds() - 1);
        }
    }
}
