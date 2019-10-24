package com.example.myapplication.Stratego.Player;

import com.example.myapplication.Game.GameComputerPlayer;
import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.infoMsg.GameInfo;

public class DumbComputerPlayer extends GameComputerPlayer {

    public DumbComputerPlayer(String name){ super(name);}

    /**
    protected void receiveInfo(GameInfo info){
        // receives info from current sate of the game
    }
    **/

    public void chooseRandomAction(){
        // randomly chooses action
    }
    public void chooseRandomBoard(){
        // randomly chooses layout of pieces for board
    }
    public void attackPiece(){
        // attacks chosen adjacent enemy piece
    }
    public void captureFlag(){
        // attempts to capture suspected enemy flag
    }
    public void hitBomb(){
        // hits bomb upon discovery
    }
    @Override
    public void gameSetAsGui(GameMainActivity activity) {

    }

    @Override
    public void sendInfo(GameInfo info) {

    }

    @Override
    public void start() {

    }

    @Override
    public boolean requiresGui() {
        return false;
    }

    @Override
    public boolean supportsGui() {
        return false;
    }
}
