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

    public DumbComputerPlayer(String name){ super(name);}

    /**
    protected void receiveInfo(GameInfo info){
        // receives info from current sate of the game
    }
    **/

    @Override
    protected void receiveInfo(GameInfo info) {
        if(info == null) return;
        if(info instanceof NotYourTurnInfo || info instanceof IllegalMoveInfo) {
            return;
        }
        if(info instanceof StrategoGameState) {
            gameStateCopy = (StrategoGameState)info;

            Log.i("computermsg", "" + gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER());
            Log.i("computermsg", "" + this.playerNum);
            if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() != this.playerNum) {
                return;
            }
            Log.i("computermsg", "Computer sets up");
            if(gameStateCopy.getCurrentPhase() == Phase.SETUP_PHASE) {
                this.game.sendAction(new StrategoTransitionAction(this));
            } else {
                generateRandomMove();
            }
        }
    }

    public void generateRandomMove() {
        while(true) {
            int row = (int) (Math.random() * 10);
            int col = (int) (Math.random() * 10);
            this.game.sendAction(new StrategoMoveAction(this, row, col));
            Log.i("computermsg", "Row" + row);
            Log.i("computermsg", "Col" + col);
            if (row != 9) {
                Log.i("computermsg", "Can I move down: " + gameStateCopy.getBoard()[row + 1][col].isHighLighted());
            }
            if (row + 1 < 10 && gameStateCopy.getBoard()[row + 1][col].isHighLighted()) {
                this.sleep(1.0);
                this.game.sendAction(new StrategoMoveAction(this, row + 1, col));
                Log.i("computermsg", "moved down");
                break;
            }

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
