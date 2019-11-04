package com.example.myapplication.Stratego.StrategoFrameworkClasses;

import com.example.myapplication.Game.GameHumanPlayer;
import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.infoMsg.GameInfo;

public class StrategoHumanPlayer extends GameHumanPlayer {
    public StrategoHumanPlayer(String name, int layoutId) {
        super(name);
    }

    public void setAsGui(GameMainActivity activity) {

    }

    @Override
    public void sendInfo(GameInfo info) {

    }

    @Override
    public boolean requiresGui() {
        return false;
    }

    @Override
    public boolean supportsGui() {
        return true;
    }
}
