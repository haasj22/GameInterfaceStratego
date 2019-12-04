/**
 * class that allows buttons to be linked to a rank
 *
 * @author John Haas
 */
package com.example.myapplication.Stratego.Player;

import android.widget.Button;

import com.example.myapplication.Stratego.GameState.Rank;

public class PieceButton {
    //button and rank to be linked
    private Button containedButton;
    private Rank buttonRank;

    /**
     * constructor for objects of the pieceButton class
     *
     * @param desiredButton button to be linked
     * @param linkedRank rank to be linked
     */
    public PieceButton(Button desiredButton, Rank linkedRank) {
        containedButton=desiredButton;
        buttonRank=linkedRank;
    }

    public Button getContainedButton() {
        return containedButton;
    }

    public Rank getButtonRank() {
        return buttonRank;
    }
}
