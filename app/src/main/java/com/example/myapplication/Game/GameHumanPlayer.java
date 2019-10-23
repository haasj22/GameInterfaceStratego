package com.example.myapplication.Game;

import android.view.View;

import com.example.myapplication.Game.Game;
import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;
import com.example.myapplication.Game.util.GameTimer;
import com.example.myapplication.Game.util.Tickable;

import java.util.logging.Handler;

public abstract class GameHumanPlayer implements GamePlayer, Tickable {
    // instance variables
    protected Game game; // the game
    protected int playerNum; // my player ID
    protected String name; // my player's name
    protected String[] allPlayerNames; // the names of all the players
    private Boolean gameOver; // whether the game is over
    private Handler myHandler; // thread handler
    private GameMainActivity myActivity; // the current activity
    private GameTimer myTimer = new GameTimer(this);

    public GameHumanPlayer(String name){
        this.name = name;
        this.gameOver = false;
        //this.myHandler = new Handler();
    }

    protected final GameTimer getTimer() {
        return myTimer;
    }

    public final void tick(GameTimer timer) {
        //sendInfo(new TimerInfo(timer));
    }

    public View getTopView() {
        return null;
    };

    public void start(){

    }

    protected void initAfterReady() {

    }

    public void gameSetAsGui(GameMainActivity a) {
        myActivity = a;
        //setAsGui(a);
    }





}
