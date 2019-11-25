/**
 * creates an action that allows the computer to move
 *
 * @author John Haas
 */
package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

public class StrategoComputerMoveAction extends GameAction {
    //variables that inform the game of where the computer wants to use
    int oldRow;
    int oldCol;
    int futureRow;
    int futureCol;

    /**
     * constructor for objects of the StrategoComputerMoveAction
     *
     * @param player player that wants to send the action
     * @param row1 initial row position
     * @param col1 intitial col position
     * @param row2 desired row position
     * @param col2 desired col position
     */
    public StrategoComputerMoveAction(GamePlayer player, int row1, int col1, int row2, int col2) {
        super(player);
        oldRow=row1;
        oldCol=col1;
        futureRow=row2;
        futureCol=col2;
    }

    public int getOldRow() { return oldRow; }
    public int getOldCol() { return oldCol; }
    public int getFutureRow() { return futureRow; }
    public int getFutureCol() { return futureCol; }
}
