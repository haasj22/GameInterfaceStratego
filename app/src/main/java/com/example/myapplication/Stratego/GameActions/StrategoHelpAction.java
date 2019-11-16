/**
 * action that allows the user to read the rules
 *
 * @author Kavya Mandla
 */
package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

import java.io.Serializable;

/**
 * StrategoHelpAction
 */
public class StrategoHelpAction extends GameAction implements Serializable {

    //to satisfy the serializable interface
    private static final long serialVersionUID = 23874364928L;

    public StrategoHelpAction(GamePlayer player){
        super(player);
    }
}