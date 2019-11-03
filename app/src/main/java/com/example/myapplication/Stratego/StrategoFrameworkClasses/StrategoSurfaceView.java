package com.example.myapplication.Stratego.StrategoFrameworkClasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.myapplication.Game.util.FlashSurfaceView;
import com.example.myapplication.R;
import com.example.myapplication.Stratego.GameState.StrategoGameState;

public class StrategoSurfaceView extends FlashSurfaceView {
    //tag for logging
    private static final String TAG = "StrategoSurfaceView";

    protected StrategoGameState state;

    //bitmaps
    Bitmap baseBoard = BitmapFactory.decodeResource(getResources() , R.drawable.base_board);
    Bitmap baseBluePiece = BitmapFactory.decodeResource(getResources(), R.drawable.base_piece);
    Bitmap baseBluePiece1 = BitmapFactory.decodeResource(getResources(), R.drawable.base_piece1);
    Bitmap baseBluePiece2 = BitmapFactory.decodeResource(getResources(), R.drawable.base_piece2);
    Bitmap baseBluePiece3 = BitmapFactory.decodeResource(getResources(), R.drawable.base_piece3);
    Bitmap baseBluePiece4 = BitmapFactory.decodeResource(getResources(), R.drawable.base_piece4);
    Bitmap baseBluePiece5 = BitmapFactory.decodeResource(getResources(), R.drawable.base_piece5);
    Bitmap baseBluePiece6 = BitmapFactory.decodeResource(getResources(), R.drawable.base_piece6);
    Bitmap baseBluePiece7 = BitmapFactory.decodeResource(getResources(), R.drawable.base_piece7);
    Bitmap baseBluePiece8 = BitmapFactory.decodeResource(getResources(), R.drawable.base_piece8);
    Bitmap baseBluePiece9 = BitmapFactory.decodeResource(getResources(), R.drawable.base_piece9);
    Bitmap baseBluePieceBomb = BitmapFactory.decodeResource(getResources(), R.drawable.base_piece_bomb);
    Bitmap baseBluePieceSpy = BitmapFactory.decodeResource(getResources(), R.drawable.base_piece_spy);
    Bitmap baseBluePieceFlag = BitmapFactory.decodeResource(getResources(), R.drawable.base_piecef);
    Bitmap baseRedPiece = BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece);
    Bitmap baseRedPiece1 = BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece1);
    Bitmap baseRedPiece2 = BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece2);
    Bitmap baseRedPiece3 = BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece3);
    Bitmap baseRedPiece4 = BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece4);
    Bitmap baseRedPiece5 = BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece5);
    Bitmap baseRedPiece6 = BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece6);
    Bitmap baseRedPiece7 = BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece7);
    Bitmap baseRedPiece8 = BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece8);
    Bitmap baseRedPiece9 = BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece9);
    Bitmap baseRedPieceBomb = BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece_bomb);
    Bitmap baseRedPieceSpy = BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece_spy);
    Bitmap baseRedPieceFlag = BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiecef);

    public StrategoSurfaceView(Context context) {
        super(context);
    }

    public void setState() {this.state = state;}

    public void onDraw(Canvas g) {

    }
}
