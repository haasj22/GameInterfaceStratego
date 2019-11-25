/**
 * action that allows the user to mute the tablet
 *
 * @author Kavya Mandla
 */
package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

import java.io.Serializable;

public class StrategoMuteAction extends GameAction implements Serializable {

    //to satisfy the serializable interface
    private static final long serialVersionUID = 239434244343L;

    public StrategoMuteAction(GamePlayer player){
        super(player);
    }

}
