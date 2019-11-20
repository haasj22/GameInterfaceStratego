package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

public class StrategoComputerMoveAction extends GameAction {

    int oldRow;
    int oldCol;
    int futureRow;
    int futureCol;

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
