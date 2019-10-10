package com.example.myapplication.Game;

import com.example.myapplication.Game.actionMsg.GameAction;

public interface Game {
    /**
     * starts the game
     *
     */
    public abstract void start(GamePlayer[] players);
    public abstract void sendAction(GameAction action);
}
