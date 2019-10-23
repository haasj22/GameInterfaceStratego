package com.example.myapplication.Stratego.Player;

import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.GameHumanPlayer;
/**
public class HumanPlayer extends GameHumanPlayer implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener{

    private Button startButton;
    private Button forfeitButton;

    public HumanPlayer(String name){
        super(name);
    }

    /**
    public receiveInfo(GameInfo info){
        // receives info from game state
    }
     **/
    /**
    public void setAsGui(GameMainActivity activity){
        // sets gui with most current information
    }
    private void updateGui(){
        // updates gui after actions are played
    }
    private void setBoard(){
        // sets board according to players' layout
    }
    @Override
    public void onClick(View v) {
        if(v == startButton){
            // start game
        }
        if(v == forfeitButton){
            // forfeit game
        }
    }

    private void writeNotes(){
        // edits notes edit text for player's game notes
    }
    public void locateUnites(){
        // locates units of pieces
    }
    public void movePiece(){
        // moves game pieces
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
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void gameSetAsGui(GameMainActivity activity) {

    }

}
     **/
