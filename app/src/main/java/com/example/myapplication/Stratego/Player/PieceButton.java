package com.example.myapplication.Stratego.Player;

import android.widget.Button;

import com.example.myapplication.Stratego.GameState.Rank;

public class PieceButton {

    private Button containedButton;
    private Rank buttonRank;

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
