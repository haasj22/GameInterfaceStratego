package com.example.myapplication.Stratego.Player;

import com.example.myapplication.Game.GameComputerPlayer;
import com.example.myapplication.Game.GameMainActivity;

public class SmartComputerPlayer extends GameComputerPlayer {
    public SmartComputerPlayer(String name) {
        super(name);
    }

    public void selectLayout(){
        // chooses between previously planned layouts
    }
    public void movePieceForward(){
        // randomly chooses a piece and moves it forward
    }
    public void checkEnemyLayout(){
        // checks enemy's layout and finds flag
    }
    @Override
    public void gameSetAsGui(GameMainActivity activity) {

    }
}
