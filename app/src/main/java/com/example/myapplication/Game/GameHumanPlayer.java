package com.example.myapplication.Game;

import com.example.myapplication.Game.Game;
import com.example.myapplication.Game.GamePlayer;

public abstract class GameHumanPlayer implements GamePlayer {
    protected Game game;
    protected String name;
    protected Boolean gameOver;

    public GameHumanPlayer(String name){
        this.name = name;
        this.gameOver = false;

    }

}
