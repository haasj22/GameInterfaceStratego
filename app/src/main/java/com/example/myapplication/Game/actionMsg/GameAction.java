
package com.example.myapplication.Game.actionMsg;

import com.example.myapplication.Game.GamePlayer;

import java.io.Serializable;

public abstract class GameAction implements Serializable {

    // to support the Serializable interface
    private static final long serialVersionUID = 30672013L;

    // player who generated the request
    private GamePlayer player;

    /**
     *  constructor for GameAction
     * @param player
     */
    public GameAction(GamePlayer player){
        this.player = player;
    }

    /**
     * tells which player created the action
     * @return
     */
    public GamePlayer getPlayer(){
        return player;
    }

    public void setPlayer(GamePlayer p) {
        this.player = p;
    }
}
