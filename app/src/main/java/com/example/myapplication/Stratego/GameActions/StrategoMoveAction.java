/**
 * action that allows the user to move or send up a piece
 *
 * @author John Haas, Jordan Ho
 */
package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

/**
 * StrategoMoveAction
 */
public class StrategoMoveAction extends GameAction {
    //Tag for logging
    private static final String TAG = "StrategoMoveAction";

    //to satisfy the serializable interface
    private static final long serialVersionUID = -2242980258970485343L;

    //variables that store the row and column of the tap
    private int row;
    private int col;

    /**
     * constructor for StrategoMoveAction
     *
     * @param player the player making the move
     * @param row the row of the column selected
     * @param col the column of the column selected
     */
    public StrategoMoveAction(GamePlayer player, int row, int col) {
        //invoke superclass constructor to set the player
        super(player);

        //set the row and column as passed to us
        this.row = Math.max(0, Math.min(9, row));
        this.col = Math.max(0, Math.min(9, col));
    }

    /**
     * get the object's row
     *
     * @return the row selected
     */
    public int getRow() { return row; }

    /**
     * get the object's column
     *
     * @return the column selected
     */
    public int getCol() { return col; }
}
