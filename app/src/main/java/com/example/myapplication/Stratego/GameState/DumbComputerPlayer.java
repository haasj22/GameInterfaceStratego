/**
 * class that contains how the dumbComputerPlayer will work
 *
 * @author John Haas
 */
package com.example.myapplication.Stratego.GameState;

import com.example.myapplication.Game.GameComputerPlayer;
import com.example.myapplication.Game.infoMsg.GameInfo;
import com.example.myapplication.Game.infoMsg.IllegalMoveInfo;
import com.example.myapplication.Game.infoMsg.NotYourTurnInfo;
import com.example.myapplication.Stratego.GameActions.StrategoComputerMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoPassAction;
import com.example.myapplication.Stratego.GameActions.StrategoTransitionAction;


import java.util.ArrayList;

public class DumbComputerPlayer extends GameComputerPlayer {
    //players game state
    private StrategoGameState gameStateCopy;

    //default constructor
    public DumbComputerPlayer(String name){ super(name);}

    /**
     * method that gives the computer an updated game state
     *
     * @param info contains info of the game state
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        //makes sure information exists
        if(info == null) return;
        //if the previous move was invalid does not accept new copy
        if(info instanceof NotYourTurnInfo || info instanceof IllegalMoveInfo) {
            return;
        }
        //else if information is valid
        if(info instanceof StrategoGameState) {
            //update player's game state
            gameStateCopy = (StrategoGameState)info;

            if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() != this.playerNum) {
                return;
            }

            //makes sure its the computer players turn
            if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() != this.playerNum) {
                return;
            }

            //if computer is in setup phase, generate a random board
            if(gameStateCopy.getCurrentPhase() == Phase.SETUP_PHASE) {
                this.game.sendAction(new StrategoTransitionAction(this));
            }
            //else take a random move
            else {
                ArrayList<MovablePiece> computerMovablePieces= new ArrayList<>();
                addMovablePieces(computerMovablePieces);
                generateLegalMove(computerMovablePieces);
                return;
            }
        }
    }

    /**
     * method that adds a computer players movable pieces to a list.
     * @param myPieces list to add the movable pieces to
     */
    public void addMovablePieces(ArrayList<MovablePiece> myPieces) {
        //goes through each piece on the board and if its movable adds it to a list
        for(int x=0; x<10; x++) {
            for(int y=0; y<10; y++) {
                if(gameStateCopy.getBoard()[x][y].canOneMoveThis(this.playerNum)) {
                    myPieces.add(new MovablePiece(x, y,
                            gameStateCopy.getBoard()[x][y].getContainedPiece().getPieceRank()));
                }
            }
        }
    }

    /**
     * method that generates a legal move from the movable pieces
     *
     * @param myPieces pieces that could possible be moved
     */
    public void generateLegalMove(ArrayList<MovablePiece> myPieces) {
        //while there are still pieces that could be moved tries to move them
        while(!myPieces.isEmpty()) {
            int randomIndex = (int)(Math.random() * myPieces.size());
            boolean moveSent = findAndSendMove(myPieces.get(randomIndex));
            if(moveSent) {
                return;
            } else {
                myPieces.remove(myPieces.get(randomIndex));
            }
        }
        //if there are no movable pieces left, the computer passes
        game.sendAction(new StrategoPassAction(this));
    }

    /**
     * method that checks a movable piece for a movable action and sends it if possible
     *
     * @param pieceToCheck piece that could possibly be moved
     * @return true once piece has been moved
     *         false if the piece cannot be moved
     */
    public boolean findAndSendMove(MovablePiece pieceToCheck) {
        //creates a coordinate mapping that a piece could be offset to
        int randoRow = (int)(Math.random() * 3 - 1);
        int randoCol;
        if (randoRow != 0) {
            randoCol = 0;
        } else {
            randoCol=(int)Math.pow(-1, (int)(Math.random() * 2 + 1));
        }

        //goes through each cardinal direction and tries to move there
        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                if(pieceToCheck.getX() + randoRow > 9 || pieceToCheck.getX() + randoRow < 0) {
                    continue;
                }
                if(pieceToCheck.getY() + randoCol > 9 || pieceToCheck.getY() + randoCol < 0) {
                    continue;
                }
                if(gameStateCopy.getBoard()[pieceToCheck.getX() + randoRow]
                        [pieceToCheck.getY() + randoCol].canOneMoveHere(this.playerNum)) {
                    this.sleep(.25);
                    game.sendAction(new StrategoComputerMoveAction(this,
                            pieceToCheck.getX(), pieceToCheck.getY(), pieceToCheck.getX() + randoRow,
                            pieceToCheck.getY() + randoCol));
                    return true;
                }
                randoCol *= -1;
                randoRow *= -1;
            }
            int temp = randoRow;
            randoRow = randoCol;
            randoCol = temp;
        }
        //returns false if there is no actual place to move
        return false;
    }


    @Override
    public boolean supportsGui() {
        return false;
    }
}
