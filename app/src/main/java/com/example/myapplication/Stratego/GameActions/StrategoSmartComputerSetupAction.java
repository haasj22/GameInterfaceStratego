/**
 * action that allows a smart computer to setup in one action
 *
 * @author John Haas
 */
package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

import java.io.Serializable;

public class StrategoSmartComputerSetupAction extends GameAction implements Serializable {

    //to satisfy the serializable interface
    private static final long serialVersionUID = 239422474343L;

    public StrategoSmartComputerSetupAction(GamePlayer player) {
        super(player);
    }
}
