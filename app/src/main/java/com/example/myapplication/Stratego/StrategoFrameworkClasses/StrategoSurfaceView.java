package com.example.myapplication.Stratego.StrategoFrameworkClasses;

import android.content.Context;

import com.example.myapplication.Game.util.FlashSurfaceView;
import com.example.myapplication.Stratego.GameState.StrategoGameState;

public class StrategoSurfaceView extends FlashSurfaceView {
    //tag for logging
    private static final String TAG = "StrategoSurfaceView";

    protected StrategoGameState state;

    public StrategoSurfaceView(Context context) {
        super(context);
    }

    public void setState() {this.state = state;}
}
