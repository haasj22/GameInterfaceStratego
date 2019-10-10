package com.example.myapplication.Game;

import com.example.myapplication.Game.actionMsg.GameAction;
import com.example.myapplication.Stratego.GameState.StrategoGameState;

public interface Game {

    /**
     * starts the game
     *
     */
    public abstract void start(GamePlayer[] players);
    public abstract void sendAction(GameAction action);
}
