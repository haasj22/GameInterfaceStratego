package com.example.myapplication.Game;

import com.example.myapplication.Game.actionMsg.GameAction;
import com.example.myapplication.Stratego.GameState.StrategoGameState;

public interface Game {

    /**
     * starts the game
     *
     * @param players
     * the players who are in the game
     *
     */
    public abstract void start(GamePlayer[] players);

    /**
     * sends the given action to the Game object
     *
     * @param action
     * the action to send
     *
     */
    public abstract void sendAction(GameAction action);
}
