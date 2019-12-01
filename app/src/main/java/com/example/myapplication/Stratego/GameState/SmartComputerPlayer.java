/**
 * class that shows how a smart computer player operates
 *
 * @author John Haas
 */
package com.example.myapplication.Stratego.GameState;

import android.util.Log;

import com.example.myapplication.Game.GameComputerPlayer;
import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.infoMsg.GameInfo;
import com.example.myapplication.Stratego.GameActions.StrategoComputerMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoPassAction;
import com.example.myapplication.Stratego.GameActions.StrategoSmartComputerSetupAction;
import com.example.myapplication.Stratego.GameActions.StrategoTransitionAction;

import java.util.ArrayList;

public class SmartComputerPlayer extends GameComputerPlayer {
    StrategoGameState gameStateCopy;

    public SmartComputerPlayer(String name) {
        super(name);
    }

    /**
     * mehtod that tells the computer what to happen upon receiving a new game state
     *
     * @param info the game state that the computer player receives
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        //makes sure the information is a game state
        if (info instanceof StrategoGameState) {
            gameStateCopy = (StrategoGameState) info;

            //makes sure its currently the computer player's turn
            if (gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() != this.playerNum) {
                return;
            }

            //if its setup phase activates the computers setup method
            if (gameStateCopy.getCurrentPhase() == Phase.SETUP_PHASE) {
                this.game.sendAction(new StrategoSmartComputerSetupAction(this));
            }
            //else it calculates the best play phase move and does that
            else {
                if(gameStateCopy.getLastKilledPiece() != null) {
                    Log.i("smrtmsg", "" + gameStateCopy.getLastKilledPiece());
                } else {
                    Log.i("smrtmsg", "noKilledPiece");
                }
                vendettaAttack();

                //attacks an enemy if its nearby
                ArrayList<MovablePiece> computerMovablePieces = new ArrayList<>();
                addMovablePieces(computerMovablePieces);
                generateLegalMove(computerMovablePieces);
            }
        }
    }

    /**
     * method that allows the computer to kill pieces that just killed one of its own
     */
    public void vendettaAttack() {
        //makes sure something just died
        if(gameStateCopy.getLastKilledPiece() == null) {
            return;
        }
        //default values
        int moveThisX = 100;
        int moveThisY = 100;

        //finds out where stuff got killed
        int lastKilledX = gameStateCopy.getLastKilledPiece().getX();
        int lastKilledY = gameStateCopy.getLastKilledPiece().getY();
        Rank lastKilledRank = gameStateCopy.getLastKilledPiece().getPieceRank();

        //looks next to the piece that just killed an enemy
        int x=1;
        int y=0;
        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                x*=-1;
                y*=-1;
                //makes sure the place its about to check is a valid location
                if(lastKilledX + x < gameStateCopy.getROWMIN()
                        || lastKilledX + x >= gameStateCopy.getROWMAX()
                        || lastKilledY + y < gameStateCopy.getCOLMIN()
                        || lastKilledY + y >= gameStateCopy.getCOLMAX()) {
                    continue;
                }

                //makes sure the place its about to check has a stratego piece
                if(gameStateCopy.getBoard()[lastKilledX + x][lastKilledY + y].getContainedPiece() == null) {
                    continue;
                }

                //finds the lowest piece number that will be able to kill the desired piece
                if(moveThisX == 100 && moveThisY == 100
                        && gameStateCopy.getBoard()[lastKilledX + x][lastKilledY + y].getContainedPiece().getPieceRank().ordinal()
                        <= gameStateCopy.getBoard()[lastKilledX][lastKilledY].getContainedPiece().getPieceRank().ordinal()) {
                    moveThisX = lastKilledX + x;
                    moveThisY = lastKilledY + y;
                }
                else if(moveThisX != 100 && moveThisY != 100 &&
                        gameStateCopy.getBoard()[lastKilledX + x][lastKilledY + y].getContainedPiece().getPieceRank().ordinal()
                        > gameStateCopy.getBoard()[moveThisX][moveThisY].getContainedPiece().getPieceRank().ordinal()
                        && gameStateCopy.getBoard()[lastKilledX +x][lastKilledY + y].getContainedPiece().getPieceRank().ordinal()
                        <= gameStateCopy.getBoard()[lastKilledX][lastKilledY].getContainedPiece().getPieceRank().ordinal()) {
                    moveThisX = lastKilledX + x;
                    moveThisY = lastKilledY + y;
                } else if(gameStateCopy.getBoard()[lastKilledX + x][lastKilledY + y].getContainedPiece().getPieceRank() == Rank.SPY
                        && lastKilledRank == Rank.ONE) {
                    this.game.sendAction(new StrategoComputerMoveAction
                            (this, lastKilledX+x, lastKilledY+y, lastKilledX,
                            lastKilledY));
                    return;
                }
            }
            int temp = x;
            x=y;
            y=temp;
        }
        //if the desired enemy can be killed, it is killed
        if(moveThisX == 100 || moveThisY == 100) {
            return;
        } else {
            this.game.sendAction(new StrategoComputerMoveAction(this, moveThisX, moveThisY,
                    lastKilledX, lastKilledY));
        }

    }

    /**
     * method that adds all the computers movable pieces to a list
     *
     * @param myPieces list that all the pieces will be added to
     */
    public void addMovablePieces(ArrayList<MovablePiece> myPieces) {
        //goes through all the pieces and adds the movable ones to the list
        for (int x = 0; x < gameStateCopy.getROWMAX(); x++) {
            for (int y = 0; y < gameStateCopy.getCOLMAX(); y++) {
                if (gameStateCopy.getBoard()[x][y].canOneMoveThis(this.playerNum)) {
                    myPieces.add(new MovablePiece(x, y,
                            gameStateCopy.getBoard()[x][y].getContainedPiece().getPieceRank()));
                }
            }
        }
    }

    /**
     * looks for a legal move
     *
     * @param myPieces contains all the movable pieces
     */
    public void generateLegalMove(ArrayList<MovablePiece> myPieces) {
        //looks through all the movable pieces
        while (!myPieces.isEmpty()) {
            //gets a random piece from the latter half of movable pieces
            int randomIndex = (int) (Math.random() *
                    (myPieces.size() - myPieces.size() / 2)) + myPieces.size() / 2;
            //tries to send a legal move
            boolean moveSent = findAndSendMove(myPieces.get(randomIndex));
            //if move is sent ends persons turn otherwise updates movable pieces left
            if (moveSent) {
                return;
            } else {
                myPieces.remove(myPieces.get(randomIndex));
            }
        }
        //if out of pieces passes the turn
        game.sendAction(new StrategoPassAction(this));
    }

    /**
     * looks through a piece for a valid move
     *
     * @param pieceToCheck piece that desires to be moved
     * @return true once move is sent
     *         false if no moves are sent
     */
    public boolean findAndSendMove(MovablePiece pieceToCheck) {
        //if in the enemy territory move around
        if(pieceToCheck.getY() > gameStateCopy.getEnemyTeam().getTOPBOUNDARYINDEX()
                || pieceToCheck.getY() <= gameStateCopy.getEnemyTeam().getBOTTOMBOUNDARYINDEX()) {
            return disperseRandomly(pieceToCheck);
        }

        // picks either up or down according to which is the aggressive play
        int randoRow = (int) Math.pow(-1, this.playerNum + 1);
        int randoCol = 0;

        //tries to move in the picked direction
        if (pieceToCheck.getX() + randoRow < gameStateCopy.getROWMAX()
                || pieceToCheck.getX() + randoRow > 0) {
            if (gameStateCopy.getBoard()[pieceToCheck.getX() + randoRow]
                    [pieceToCheck.getY() + randoCol].canOneMoveHere(this.playerNum)) {
                this.sleep(.25);
                game.sendAction(new StrategoComputerMoveAction(this,
                        pieceToCheck.getX(), pieceToCheck.getY(), pieceToCheck.getX() + randoRow,
                        pieceToCheck.getY() + randoCol));
                return true;
            }
        }

        //randomly picks left or right and tries to move there
        randoRow = 0;
        randoCol = (int) Math.pow(-1, (int) (Math.random() * 2 + 1));

        //if the first direction is an invalid move, tries moving the opposite direction
        for (int i = 0; i < 2; i++) {
            if (pieceToCheck.getY() + randoCol >= gameStateCopy.getCOLMAX()
                    || pieceToCheck.getY() + randoCol < 0) {
                continue;
            }
            if (gameStateCopy.getBoard()[pieceToCheck.getX() + randoRow]
                    [pieceToCheck.getY() + randoCol].canOneMoveHere(this.playerNum)) {
                this.sleep(.25);
                game.sendAction(new StrategoComputerMoveAction(this,
                        pieceToCheck.getX(), pieceToCheck.getY(), pieceToCheck.getX() + randoRow,
                        pieceToCheck.getY() + randoCol));
                return true;
            }
            randoCol *= -1;
        }

        //tries to go the opposite direction of where it first tried to move
        randoRow = (int) Math.pow(-1, this.playerNum);
        randoCol = 0;
        if (pieceToCheck.getX() + randoRow <= 9 && pieceToCheck.getX() + randoRow > 0) {
            if (gameStateCopy.getBoard()[pieceToCheck.getX() + randoRow]
                    [pieceToCheck.getY() + randoCol].canOneMoveHere(this.playerNum)) {
                this.sleep(.25);
                game.sendAction(new StrategoComputerMoveAction(this,
                        pieceToCheck.getX(), pieceToCheck.getY(), pieceToCheck.getX() + randoRow,
                        pieceToCheck.getY() + randoCol));
                return true;
            }
        }
        return false;
    }

    /**
     * method that tells units on the opponents side of the board to move randomly
     *
     * @param pieceToCheck
     * @return
     */
    public boolean disperseRandomly(MovablePiece pieceToCheck) {
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

}