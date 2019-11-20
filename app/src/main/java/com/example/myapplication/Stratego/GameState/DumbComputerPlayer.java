/**
 * class that contains how the dumbComputerPlayer will work
 *
 * @author John Haas
 */
package com.example.myapplication.Stratego.GameState;

import android.text.method.MovementMethod;
import android.util.Log;

import com.example.myapplication.Game.GameComputerPlayer;
import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.infoMsg.GameInfo;
import com.example.myapplication.Game.infoMsg.IllegalMoveInfo;
import com.example.myapplication.Game.infoMsg.NotYourTurnInfo;
import com.example.myapplication.Stratego.GameActions.StrategoComputerMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoTransitionAction;
import com.example.myapplication.Stratego.GameState.Phase;
import com.example.myapplication.Stratego.GameState.StrategoGameState;

import java.util.ArrayList;

public class DumbComputerPlayer extends GameComputerPlayer {
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
        Log.i("computer1msg", "receiveInfo");
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

            Log.i("computer1msg", "enteredRecieve");

            if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() != this.playerNum) {
                return;
            }

            Log.i("computer1msg", "" + gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER());
            Log.i("computer1msg", "" + this.playerNum);
            //makes sure its the computer players turn
            if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() != this.playerNum) {
                return;
            }
            Log.i("computermsg", "Computer sets up");
            //if computer is in setup phase, generate a random board
            if(gameStateCopy.getCurrentPhase() == Phase.SETUP_PHASE) {
                this.game.sendAction(new StrategoTransitionAction(this));
            }
            //else take a random move
            else {
                ArrayList<MovablePiece> computerMovablePieces= new ArrayList<>();
                addMovablePieces(computerMovablePieces);
                generateLegalMove(computerMovablePieces);
                Log.i("computer4msg", "returning agian");
                return;
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
            int randomIndex = (int)(Math.random() * myPieces.size());
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
    }

    public boolean findAndSendMove(MovablePiece pieceToCheck) {
        int randoRow = (int)(Math.random() * 3 - 1);
        int randoCol;
        if (randoRow != 0) {
            randoCol = 0;
        } else {
            randoCol=(int)Math.pow(-1, (int)(Math.random() * 2 + 1));
        }

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
                    Log.i("computer4msg", "row: " + pieceToCheck.getX() + "col: "+ pieceToCheck.getY());
                    Log.i("computer4msg", "newRow " + randoRow
                            + "newCol" + randoCol);
                    Log.i("computer4msg", "sending action");
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
        return false;
    }

    /**
     * method that generates a random valid move
     */
    /*
    public void generateRandomMove() {
        Log.i("computer4msg", "generateRandomMove");
        //continuously looks for valid moves
        while(true) {
            if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() != this.playerNum) {
                return;
            }
            Log.i("repeatmsg", "entered");
            //finds a random spot on the board
            int row = (int) (Math.random() * 10);
            int col = (int) (Math.random() * 10);
            //highlights the random square
            Log.i("computer3msg", "sent action");
            this.game.sendAction(new StrategoMoveAction(this, row, col));
            Log.i("computer2msg", "Row" + row);
            Log.i("computer2msg", "Col" + col);

            //moves the piece down if valid
            if (row + 1 < 10 && gameStateCopy.getBoard()[row + 1][col].isHighLighted()) {
                this.sleep(1.0);
                Log.i("computer3msg", "sent action");
                this.game.sendAction(new StrategoMoveAction(this, row + 1, col));
                Log.i("computer3msg", "moved down");
                return;
            }

            //randomly decides whether to try to move left or right and tries the other if one failes
            int leftOrRight = (-1) * (int) (Math.random() * 2 + 1);
            for (int x = 1; x <= 2; x++) {
                leftOrRight *= -1;
                if (col + leftOrRight < 0 || col + leftOrRight > 9) {
                    continue;
                }
                if (gameStateCopy.getBoard()[row][col + leftOrRight].isHighLighted()) {
                    this.sleep(1.0);
                    Log.i("computer3msg", "sent action");
                    this.game.sendAction(new StrategoMoveAction(this, row, col + leftOrRight));
                    Log.i("computer3msg", "moved left or right");
                    return;
                }
            }
            //determines whether the piece can move up and tries to move there
            if (row - 1 > 0 && gameStateCopy.getBoard()[row - 1][col].isHighLighted()) {
                this.sleep(1.0);
                Log.i("computer3msg", "sent action");
                this.game.sendAction(new StrategoMoveAction(this, row - 1, col));
                Log.i("computer3msg", "moved up");
                return;
            }
        }
    }*/


    @Override
    public boolean supportsGui() {
        return false;
    }
}
