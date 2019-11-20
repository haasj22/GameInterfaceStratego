package com.example.myapplication.Stratego.StrategoFrameworkClasses;

import android.util.Log;

import java.io.Serializable;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.LocalGame;
import com.example.myapplication.Game.actionMsg.GameAction;
import com.example.myapplication.Stratego.GameActions.StrategoButtonPieceAction;
import com.example.myapplication.Stratego.GameActions.StrategoComputerMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoForfeitAction;
import com.example.myapplication.Stratego.GameActions.StrategoMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoMuteAction;
import com.example.myapplication.Stratego.GameActions.StrategoSmartComputerSetupAction;
import com.example.myapplication.Stratego.GameActions.StrategoTransitionAction;
import com.example.myapplication.Stratego.GameState.Rank;
import com.example.myapplication.Stratego.GameState.SmartComputerPlayer;
import com.example.myapplication.Stratego.GameState.StrategoGameState;

/**
 * The local game to control the master StrategoGameState
 * @author Jordan Ho
 */
public class StrategoLocalGame extends LocalGame implements Serializable {
    //tag for logging
    private static final String TAG = "StrategoLocalGame";
    //the game state
    private StrategoGameState state;

    /**
     * Constructor for StrategoLocalGame
     */
    public StrategoLocalGame() {
        super();
        Log.i("SJLocalGame", "creating game");
        // create the state for the beginning of the game
        state = new StrategoGameState();
        //perform superclass initialization
        //super();

    }

    /**
     * Sends an updated game state to the players
     *
     * @param p the player to send info to
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        if(p == null){
            Log.i("StrategoLocalGame.java","GamePlayer object is null");
            return;
        }
        // if there is no state to send, ignore
        if(state == null){
            return;
        }
        StrategoGameState stateForPlayer;
        stateForPlayer = new StrategoGameState(state);

        p.sendInfo((stateForPlayer));
    }

    /**
     * Determines whether a player is allowed to move
     * @param playerIdx
     * 		the player's player-number (ID) of the player in question
     * @return
     */
    protected boolean canMove(int playerIdx) {
        int activePlayer = state.getCurrentTeamsTurn().getTEAMNUMBER();
        return (activePlayer == playerIdx);
    }

    /**
     * Checks whether the game is over; if so, returns a string giving the result
     * otherwise, return null
     * @return
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
     * @param action
     * 			The move that the player has sent to the game
     * @return True if the move was legal; false otherwise
     */
    @Override
    protected boolean makeMove(GameAction action) {
        // uses the actions to check instances of player actions
        //handles a move action by tapping on a square
        if(action instanceof StrategoMoveAction) {
            Log.i("computermsg", "action received");
            StrategoMoveAction sma = (StrategoMoveAction)action;
            Log.i("setupmsg", "" + sma.getRow());
            Log.i("setupmsg", "" + sma.getCol());
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

            //generates a random spot for the flag
            int randomRow = (int)(Math.random() * 2);
            int randomCol = (int)(Math.random() * 10);

            //adjusts the flag for the proper team
            if(state.getCurrentTeamsTurn().getTEAMNUMBER() == 0) {
                randomRow += 8;
            }

            //adds the flag to the board
            state.setLastTappedPieceButton(Rank.FLAG);
            Log.i("computersetupmsg", "" + randomRow);
            Log.i("computersetupmsg", "" + randomCol);
            state.tapOnSquare(randomRow, randomCol);
            state.tapOnSquare(randomRow, randomCol);

            //places bombs around the flag if possible
            state.setLastTappedPieceButton(Rank.BOMB);
            if(randomRow != 0) {
                Log.i("computersetupmsg", "enteredUp");
                state.tapOnSquare(randomRow - 1, randomCol);
                state.tapOnSquare(randomRow - 1, randomCol);
            }
            if(randomRow != 9) {
                Log.i("computersetupmsg", "enteredDown");
                state.tapOnSquare(randomRow + 1, randomCol);
                state.tapOnSquare(randomRow + 1, randomCol);
            }
            if(randomCol != 0) {
                Log.i("computersetupmsg", "enteredLeft");
                state.tapOnSquare(randomRow, randomCol -1);
                state.tapOnSquare(randomRow, randomCol -1);
            }
            if(randomCol != 9) {
                Log.i("computersetupmsg", "enteredRight");
                state.tapOnSquare(randomRow, randomCol + 1);
                state.tapOnSquare(randomRow, randomCol + 1);
            }

            //transitions the player to play phase
            state.transitionPhases();
        }
        else if (action instanceof StrategoComputerMoveAction) {
            StrategoComputerMoveAction scma = (StrategoComputerMoveAction) action;
            state.tapOnSquare(scma.getOldRow(), scma.getOldCol());
            state.tapOnSquare(scma.getFutureRow(), scma.getFutureCol());
        }
        return true;
    }


}
