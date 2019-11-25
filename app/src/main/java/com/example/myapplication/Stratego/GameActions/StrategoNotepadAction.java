/**
 * action that allows the user to enter notepad mode
 *
 * @author Kavya Mandla
 */
package com.example.myapplication.Stratego.GameActions;

import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.actionMsg.GameAction;

import java.io.Serializable;

/**
 * StrategoNotePadAction
 * @author Kavya Mandla
 */
public class StrategoNotepadAction extends GameAction implements Serializable {

    //to satisfy the serializable interface
    private static final long serialVersionUID = 23948734249L;

    public StrategoNotepadAction(GamePlayer player){
        super(player);
    }
}
