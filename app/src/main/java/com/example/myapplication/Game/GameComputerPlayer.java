package com.example.myapplication.Game;

public abstract class GameComputerPlayer implements GamePlayer {
    protected Game game;
    protected String name;
    private boolean gameOver = false;
    private GameMainActivity myActivity;

    public GameComputerPlayer(String name){
        this.name = name;
    }
    public void gameSetAsGui(GameMainActivity activity){
        myActivity = activity;
        setAsGui(activity);
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

    public void setAsGui(GameMainActivity activity){
        // default behavior is to do nothing
    }
}
