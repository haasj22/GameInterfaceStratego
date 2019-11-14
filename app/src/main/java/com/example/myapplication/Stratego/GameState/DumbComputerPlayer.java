/**
 * class that contains how the dumbComputerPlayer will work
 *
 * @author John Haas
 */
package com.example.myapplication.Stratego.GameState;

import android.util.Log;

import com.example.myapplication.Game.GameComputerPlayer;
import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.infoMsg.GameInfo;
import com.example.myapplication.Game.infoMsg.IllegalMoveInfo;
import com.example.myapplication.Game.infoMsg.NotYourTurnInfo;
import com.example.myapplication.Stratego.GameActions.StrategoMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoTransitionAction;
import com.example.myapplication.Stratego.GameState.Phase;
import com.example.myapplication.Stratego.GameState.StrategoGameState;

public class DumbComputerPlayer extends GameComputerPlayer {
    StrategoGameState gameStateCopy;

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
            Log.i("computermsg", "" + gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER());
            Log.i("computermsg", "" + this.playerNum);
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
                generateRandomMove();
            }
        }
    }

    /**
     * method that generates a random valid move
     */
    public void generateRandomMove() {
        //continuously looks for valid moves
        while(true) {
            //finds a random spot on the board
            int row = (int) (Math.random() * 10);
            int col = (int) (Math.random() * 10);
            //highlights the random square
            this.game.sendAction(new StrategoMoveAction(this, row, col));
            Log.i("computermsg", "Row" + row);
            Log.i("computermsg", "Col" + col);

            //moves the piece down if valid
            if (row + 1 < 10 && gameStateCopy.getBoard()[row + 1][col].isHighLighted()) {
                this.sleep(1.0);
                this.game.sendAction(new StrategoMoveAction(this, row + 1, col));
                Log.i("computermsg", "moved down");
                break;
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
                    this.game.sendAction(new StrategoMoveAction(this, row, col + leftOrRight));
                    Log.i("computermsg", "moved left or right");
                    break;
                }
            }
            //determines whether the piece can move up and tries to move there
            if (row - 1 > 0 && gameStateCopy.getBoard()[row - 1][col].isHighLighted()) {
                this.sleep(1.0);
                this.game.sendAction(new StrategoMoveAction(this, row - 1, col));
                Log.i("computermsg", "moved up");
                break;
            }
        }
        return;
    }


    @Override
    public boolean supportsGui() {
        return false;
    }
}
