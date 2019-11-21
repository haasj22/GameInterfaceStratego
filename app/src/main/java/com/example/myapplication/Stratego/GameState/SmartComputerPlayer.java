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
        if(info instanceof  StrategoGameState) {
            gameStateCopy = (StrategoGameState)info;

            //makes sure its currently the computer player's turn
            if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() != this.playerNum) {
                return;
            }

            //if its setup phase activates the computers setup method
            if(gameStateCopy.getCurrentPhase() == Phase.SETUP_PHASE) {
                this.game.sendAction(new StrategoSmartComputerSetupAction(this));
            }
            //else it calculates the best play phase move and does that
            else {
                //attacks an enemy if its nearby
                ArrayList<MovablePiece> computerMovablePieces= new ArrayList<>();
                addMovablePieces(computerMovablePieces);
                generateLegalMove(computerMovablePieces);
            }
        }
    }

    public void addMovablePieces(ArrayList<MovablePiece> myPieces) {
        int count=0;
        for(int x=0; x<10; x++) {
            for(int y=0; y<10; y++) {
                if(gameStateCopy.getBoard()[x][y].canOneMoveThis(this.playerNum)) {
                    myPieces.add(new MovablePiece(x, y,
                            gameStateCopy.getBoard()[x][y].getContainedPiece().getPieceRank()));
                }
            }
        }
    }

    public void generateLegalMove(ArrayList<MovablePiece> myPieces) {
        while(!myPieces.isEmpty()) {
            int randomIndex = (int)(Math.random() * (myPieces.size() - myPieces.size()/2)) + myPieces.size()/2;
            Log.i("computer4msg", "before");
            boolean moveSent = findAndSendMove(myPieces.get(randomIndex));
            Log.i("computer4msg", "after");
            if(moveSent) {
                Log.i("computer4msg", "returning");
                return;
            } else {
                myPieces.remove(myPieces.get(randomIndex));
            }
        }
        game.sendAction(new StrategoPassAction(this));
    }

    public boolean findAndSendMove(MovablePiece pieceToCheck) {
        int randoRow = (int)Math.pow(-1, this.playerNum+1);
        int randoCol = 0;

        if(pieceToCheck.getX() + randoRow <= 9 || pieceToCheck.getX() + randoRow > 0) {
            if(gameStateCopy.getBoard()[pieceToCheck.getX() + randoRow]
                    [pieceToCheck.getY() + randoCol].canOneMoveHere(this.playerNum)) {
                game.sendAction(new StrategoComputerMoveAction(this,
                        pieceToCheck.getX(), pieceToCheck.getY(), pieceToCheck.getX() + randoRow,
                        pieceToCheck.getY() + randoCol));
                return true;
            }
        }
        randoRow=0;
        randoCol=(int)Math.pow(-1, (int)(Math.random() * 2 + 1));

        for(int i = 0; i<2; i++) {
            if(pieceToCheck.getY() + randoCol > 9 || pieceToCheck.getY() + randoCol < 0) {
                continue;
            }
            if(gameStateCopy.getBoard()[pieceToCheck.getX() + randoRow]
                    [pieceToCheck.getY() + randoCol].canOneMoveHere(this.playerNum)) {
                game.sendAction(new StrategoComputerMoveAction(this,
                        pieceToCheck.getX(), pieceToCheck.getY(), pieceToCheck.getX() + randoRow,
                        pieceToCheck.getY() + randoCol));
                return true;
            }
            randoCol *= -1;
        }

        randoRow = (int)Math.pow(-1, this.playerNum);
        randoCol = 0;
        if(pieceToCheck.getX() + randoRow <= 9 || pieceToCheck.getX() + randoRow > 0) {
            if(gameStateCopy.getBoard()[pieceToCheck.getX() + randoRow]
                    [pieceToCheck.getY() + randoCol].canOneMoveHere(this.playerNum)) {
                game.sendAction(new StrategoComputerMoveAction(this,
                        pieceToCheck.getX(), pieceToCheck.getY(), pieceToCheck.getX() + randoRow,
                        pieceToCheck.getY() + randoCol));
                return true;
            }
        }
        return false;
    }

    /**
     * method that tries to find an enemy unit that can be attacked
     *
     * @return 1000 * row of piece to be moved +
     *         100 * col of piece to be moved +
     *         10 * row of where the piece should be moved +
     *         1 * col of where the piece should be moved or
     *         -1 if there are no attackable enemies
     */
    protected int findAttackableEnemy() {
        int startingRow=-1;
        int startingCol=5;
        int upOrDown=0;
        int alternateInc = 0;

        //sets the proper direction of search depending on which team the player is in
        if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() == 1) {
            startingRow=0;
            upOrDown=1;
        } else if (gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() == 2) {
            startingRow=9;
            upOrDown=-1;
        }

        //goes through the entire game board finding the unit nearest to the back middle of the board
        for(int row = startingRow; row >= gameStateCopy.getROWMININDEX()
                && row <= gameStateCopy.getROWMAXINDEX(); row+=upOrDown) {
            for(int col = startingCol; col >= gameStateCopy.getCOLMININDEX()
                    && col <= gameStateCopy.getROWMININDEX(); row += alternateInc) {

                //if the enemy occupies a square checks for adjacent movable pieces
                if(gameStateCopy.getBoard()[row][col].doesEnemyOccupyThis(this.playerNum)) {

                    if(row != gameStateCopy.getROWMININDEX() &&
                            gameStateCopy.getBoard()[row - 1][col].canOneMoveThis(this.playerNum)) {
                        return (row-1) * 1000 + col * 100 + row * 10 + col;
                    } else if(row != gameStateCopy.getROWMAXINDEX()
                            && gameStateCopy.getBoard()[row+1][col].canOneMoveThis(this.playerNum)) {
                        return (row+1) * 1000 + col * 100 + row * 10 + col;
                    } else if(col != gameStateCopy.getCOLMININDEX()
                            && gameStateCopy.getBoard()[row][col-1].canOneMoveThis(this.playerNum)) {
                        return row * 1000 + (col - 1) * 100 + row * 10 + col;
                    } else if(col != gameStateCopy.getCOLMAXINDEX()
                            && gameStateCopy.getBoard()[row][col + 1].canOneMoveThis(this.playerNum)) {
                        return row * 1000 + (col + 1) * 100 + row * 10 + col;
                    }
                }

                //allows the board to be searched middle outward
                if(alternateInc >= 0) {
                    alternateInc = (alternateInc + 1) * -1;
                } else {
                    alternateInc = (alternateInc - 1) * -1;
                }
            }
            alternateInc = 0;
        }
        return -1;
    }

    /**
     * method that finds the nearest movable piece
     *
     * @return 1000 * row of piece to be moved +
     *         100 * col of piece to be moved +
     *         10 * row of where the piece should be moved +
     *         1 * col of where the piece should be moved or
     *        -1 if there are no movable units
     */
    protected int findNearestMovableUnit() {
        int startingRow=-1;
        int startingCol=5;
        int upOrDown=0;
        int alternateInc = 0;

        //sets the proper direction of search depending on which team the player is in
        if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() == 1) {
            startingRow=0;
            upOrDown=1;
        } else if (gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() == 2) {
            startingRow=9;
            upOrDown=-1;
        }

        //looks through the entire board with priority on pieces in the back toward the middle
        for(int row = startingRow; row >= gameStateCopy.getROWMININDEX()
                && row <= gameStateCopy.getROWMAXINDEX(); row+=upOrDown) {
            for(int col = startingCol; col >= gameStateCopy.getCOLMININDEX()
                    && col <= gameStateCopy.getROWMININDEX(); row += alternateInc) {

                //allows pieces to be searched middle first
                if(alternateInc >= 0) {
                    alternateInc = (alternateInc + 1) * -1;
                } else {
                    alternateInc = (alternateInc - 1) * -1;
                }

                //looks where to move once a movable piece has been found
                if(gameStateCopy.getBoard()[row][col].canOneMoveThis(playerNum)) {
                    int whereToMove = whereToMovePiece(row, col);
                    if(whereToMove == -1) {
                        continue;
                    }
                    return whereToMove;
                }
            }
            alternateInc = 0;
        }
        return -1;
    }

    /**
     * method that determines where a given piece should move
     *
     * @param row row of the piece to move
     * @param col col of the piece to move
     * @return 1000 * row of piece to be moved +
     *         100 * col of piece to be moved +
     *         10 * row of where the piece should be moved +
     *         1 * col of where the piece should be moved or
     *        -1 if there are no movable units
     */
    public int whereToMovePiece(int row, int col) {
        //if in the top section of the board
        if(row < 4) {
            //if blue move nearest piece in random direction
            if(playerNum == 0) {
                moveOneRandomOver(row, col);
            }
            //else if read move piece toward the opponents side of the board
            else {
                if(gameStateCopy.getBoard()[row + 1][col].canOneMoveHere(playerNum)) {
                    return (row + 1) * 1000 + col * 100 + row * 10 + col;
                }
                else if(col > gameStateCopy.getCOLMININDEX() && col < 2 || col == 5) {
                    if(gameStateCopy.getBoard()[row][col + 1].canOneMoveHere(playerNum)) {
                        return row * 1000 + (col + 1) * 100 + row * 10 + col;
                    }
                }
                else if(col == 4 || col > 7 && col <= gameStateCopy.getCOLMAXINDEX()) {
                    if(gameStateCopy.getBoard()[row][col - 1].canOneMoveHere(playerNum)) {
                        return row * 1000 + (col -1) * 100 + row * 10 + col;
                    }
                }
                return -1;
            }
        }
        //if on the bridge move in the direction of the enemy team
        if(row == 4 || row == 5) {
            if(this.playerNum == 1) {
                return (row+1) * 1000 + col * 100 + row * 10 + col;
            } else {
                return (row-1) * 1000 + col * 100 + row * 10 + col;
            }
        }
        //else if in lowest section of the board
        else if(row > 5) {
            //if blue move a piece in an random direction
            if(this.playerNum == 1) {
                moveOneRandomOver(row, col);
            }
            //else if red move one piece up the board toward the enemy side
            else {
                if(gameStateCopy.getBoard()[row - 1][col].canOneMoveHere(playerNum)) {
                    return (row - 1) * 1000 + col * 100 + row * 10 + col;
                }
                else if(col > gameStateCopy.getCOLMININDEX() && col < 2 || col == 5) {
                    if(gameStateCopy.getBoard()[row][col + 1].canOneMoveHere(playerNum)) {
                        return row * 1000 + (col + 1) * 100 + row * 10 + col;
                    }
                }
                else if(col == 4 || col > 7 && col <= gameStateCopy.getCOLMAXINDEX()) {
                    if(gameStateCopy.getBoard()[row][col-1].canOneMoveHere(playerNum)) {
                        return row * 1000 + (col -1) * 100 + row * 10 + col;
                    }
                }
                return -1;
            }
        }
        return -1;
    }

    /**
     * moves the desired piece in an random direction
     *
     * @param row row of the piece to be moved
     * @param col col of the piece to be moved
     * @return 10 times the row where to move + the col where to move or
     *         -1 if there are no valid moves
     */
    public int moveOneRandomOver(int row, int col) {
        //variables for controlling randomness
        int randRow = 0;
        int randCol = 0;
        int count = 0;

        //tries to randomly move one over 25 times
        while (randRow == 0 && randCol == 0 && count < 25) {
            //generates numbers between -1 and 1
            randRow = (int) Math.random() * 3 - 1;
            randCol = (int) Math.random() * 3 - 1;
            //determines if those random numbers create a valid move
            if (randRow == randCol || randRow == randCol * -1) {
                randRow = 0;
                randCol = 0;
                continue;
            }
            //makes sure the coordinates are in the legal range
            if (row + randRow > gameStateCopy.getROWMAXINDEX()
                    || row - randRow < gameStateCopy.getROWMININDEX()) {
                randRow = 0;
                randCol = 0;
            } else if (col + randCol > gameStateCopy.getCOLMAXINDEX()
                    || col - randCol < gameStateCopy.getCOLMININDEX()) {
                randRow = 0;
                randCol = 0;
            }
            //makes sure computer can move there
            if (!(gameStateCopy.getBoard()[row + randRow][col + randCol].canOneMoveHere(this.playerNum))) {
                randRow = 0;
                randCol = 0;
            }

            count++;
        }
        //if the computer was unable to find a valid move returns -1
        if (randRow == 0 && randCol == 0) {
            return -1;
        }
        //otherwise returns the legal move
        else {
            return (row + randRow) * 10 + col + randCol;
        }
    }
}
