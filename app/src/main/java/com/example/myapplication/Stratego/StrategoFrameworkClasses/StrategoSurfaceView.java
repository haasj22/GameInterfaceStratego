package com.example.myapplication.Stratego.StrategoFrameworkClasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import com.example.myapplication.Game.util.FlashSurfaceView;
import com.example.myapplication.Game.util.Logger;
import com.example.myapplication.R;
import com.example.myapplication.Stratego.GameState.Piece;
import com.example.myapplication.Stratego.GameState.Rank;
import com.example.myapplication.Stratego.GameState.StrategoGameState;
import com.example.myapplication.Game.Game;
import com.example.myapplication.Stratego.GameState.Team;

import java.util.ArrayList;

public class StrategoSurfaceView extends FlashSurfaceView {
    //tag for logging
    private static final String TAG = "StrategoSurfaceView";

    protected StrategoGameState state;
    //private Game game;

    Paint hightlightPaint;

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
    Bitmap scaledBaseBoard;
    Bitmap scaledBaseBluePiece;
    Bitmap scaledBaseBluePiece1;
    Bitmap scaledBaseBluePiece2;
    Bitmap scaledBaseBluePiece3;
    Bitmap scaledBaseBluePiece4;
    Bitmap scaledBaseBluePiece5;
    Bitmap scaledBaseBluePiece6;
    Bitmap scaledBaseBluePiece7;
    Bitmap scaledBaseBluePiece8;
    Bitmap scaledBaseBluePiece9;
    Bitmap scaledBaseBluePieceBomb;
    Bitmap scaledBaseBluePieceSpy;
    Bitmap scaledBaseBluePieceFlag;
    Bitmap scaledBaseRedPiece;
    Bitmap scaledBaseRedPiece1;
    Bitmap scaledBaseRedPiece2;
    Bitmap scaledBaseRedPiece3;
    Bitmap scaledBaseRedPiece4;
    Bitmap scaledBaseRedPiece5;
    Bitmap scaledBaseRedPiece6;
    Bitmap scaledBaseRedPiece7;
    Bitmap scaledBaseRedPiece8;
    Bitmap scaledBaseRedPiece9;
    Bitmap scaledBaseRedPieceBomb;
    Bitmap scaledBaseRedPieceSpy;
    Bitmap scaledBaseRedPieceFlag;

    ArrayList<Bitmap> strategoBitmaps;

    int width=0;
    int height=0;

    public StrategoSurfaceView(Context context) {
        super(context);
        setWillNotDraw(false);
        init();
    }

    public StrategoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        init();
    }


    public void init() {
        hightlightPaint = new Paint(Color.YELLOW);
    }

    public void setState(StrategoGameState sgt) {this.state = sgt;}

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        width=w;
        height=h;

        scaledBaseBoard=Bitmap.createScaledBitmap(baseBoard, w, h, false);
        scaledBaseBluePiece=
                Bitmap.createScaledBitmap(baseBluePiece, h * 4/50, h/10, false);
        scaledBaseBluePiece1=
                Bitmap.createScaledBitmap(baseBluePiece1, h * 4/50, h/10, false);
        scaledBaseBluePiece2=
                Bitmap.createScaledBitmap(baseBluePiece2, h * 4/50, h/10, false);
        scaledBaseBluePiece3=
                Bitmap.createScaledBitmap(baseBluePiece3, h * 4/50, h/10, false);
        scaledBaseBluePiece4=
                Bitmap.createScaledBitmap(baseBluePiece4, h * 4/50, h/10, false);
        scaledBaseBluePiece5=
                Bitmap.createScaledBitmap(baseBluePiece5, h * 4/50, h/10, false);
        scaledBaseBluePiece6=
                Bitmap.createScaledBitmap(baseBluePiece6, h * 4/50, h/10, false);
        scaledBaseBluePiece7=
                Bitmap.createScaledBitmap(baseBluePiece7, h * 4/50, h/10, false);
        scaledBaseBluePiece8=
                Bitmap.createScaledBitmap(baseBluePiece8, h * 4/50, h/10, false);
        scaledBaseBluePiece9=
                Bitmap.createScaledBitmap(baseBluePiece9, h * 4/50, h/10, false);
        scaledBaseBluePieceBomb=
                Bitmap.createScaledBitmap(baseBluePieceBomb, h * 4/50, h/10, false);
        scaledBaseBluePieceSpy=
                Bitmap.createScaledBitmap(baseBluePieceSpy, h * 4/50, h/10, false);
        scaledBaseBluePieceFlag=
                Bitmap.createScaledBitmap(baseBluePieceFlag, h * 4/50, h/10, false);
        scaledBaseRedPiece=
                Bitmap.createScaledBitmap(baseRedPiece, h * 4/50, h/10, false);
        scaledBaseRedPiece1=
                Bitmap.createScaledBitmap(baseRedPiece1, h * 4/50, h/10, false);
        scaledBaseRedPiece2=
                Bitmap.createScaledBitmap(baseRedPiece2, h * 4/50, h/10, false);
        scaledBaseRedPiece3=
                Bitmap.createScaledBitmap(baseRedPiece3, h * 4/50, h/10, false);
        scaledBaseRedPiece4=
                Bitmap.createScaledBitmap(baseRedPiece4, h * 4/50, h/10, false);
        scaledBaseRedPiece5=
                Bitmap.createScaledBitmap(baseRedPiece5, h * 4/50, h/10, false);
        scaledBaseRedPiece6=
                Bitmap.createScaledBitmap(baseRedPiece6, h * 4/50, h/10, false);
        scaledBaseRedPiece7=
                Bitmap.createScaledBitmap(baseRedPiece7, h * 4/50, h/10, false);
        scaledBaseRedPiece8=
                Bitmap.createScaledBitmap(baseRedPiece8, h * 4/50, h/10, false);
        scaledBaseRedPiece9=
                Bitmap.createScaledBitmap(baseRedPiece9, h * 4/50, h/10, false);
        scaledBaseRedPieceBomb=
                Bitmap.createScaledBitmap(baseRedPieceBomb, h * 4/50, h/10, false);
        scaledBaseRedPieceSpy=
                Bitmap.createScaledBitmap(baseRedPieceSpy, h * 4/50, h/10, false);
        scaledBaseRedPieceFlag=
                Bitmap.createScaledBitmap(baseRedPieceFlag, h * 4/50, h/10, false);
    }

    public void onDraw(Canvas g) {
        if(state == null)
        {
            Log.i("msg","trying to draw but have no state");
            return;
        }
        Log.i("msg", "drawing board now!");
        g.drawBitmap(scaledBaseBoard, 0, 0, null);
        for(int row=0; row<state.getROWMAX(); row++) {
            for(int col=0; col<state.getCOLMAX(); col++) {
                Log.i("msg", "Row: " + row);
                Log.i("msg", "Col: " + col);
                Log.i("msg", "" + state.getBoard()[row][col].containsPiece());
                    if(state.getBoard()[row][col].getContainedPiece() == null) {
                        continue;
                    }
                Piece piece = state.getBoard()[row][col].getContainedPiece();
                    if(state.getBoard()[row][col].isHighLighted()) {
                        g.drawRect((height*col)/10, (height*row)/10,
                                (height * row + 1)/10, (height*col)/10, hightlightPaint);
                    }

                drawPiece(g, state.getCurrentTeamsTurn(), piece, row, col);
                }

        }
    }

    public void drawPiece(Canvas canvas, Team currentTeam, Piece drawThisPiece, int row, int col) {
        Log.i("drawmsg", "Team: " + drawThisPiece.getPieceTeam());
        Log.i("drawmsg", "Row: " + row);
        Log.i("drawmsg", "Col: " + col);

        switch(drawThisPiece.getPieceTeam()) {

            case RED_TEAM:

                //TODO: remove this clause for dubugging
                if(currentTeam != Team.RED_TEAM && drawThisPiece.getIsVisible() == false) {
                    canvas.drawBitmap(scaledBaseRedPiece, canvas.getWidth() * 2 / 100 +
                            canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                    break;
                }

                switch(drawThisPiece.getPieceRank()) {
                    case ONE:
                        canvas.drawBitmap(scaledBaseRedPiece1, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case TWO:
                        canvas.drawBitmap(scaledBaseRedPiece2, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case THREE:
                        canvas.drawBitmap(scaledBaseRedPiece3, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case FOUR:
                        canvas.drawBitmap(scaledBaseRedPiece4, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case FIVE:
                        canvas.drawBitmap(scaledBaseRedPiece5, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case SIX:
                        canvas.drawBitmap(scaledBaseRedPiece6, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case SEVEN:
                        canvas.drawBitmap(scaledBaseRedPiece7, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case EIGHT:
                        canvas.drawBitmap(scaledBaseRedPiece8, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case NINE:
                        Log.i("drawmsg", "Drew a nine");
                        canvas.drawBitmap(scaledBaseRedPiece9, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case BOMB:
                        canvas.drawBitmap(scaledBaseRedPieceBomb, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case SPY:
                        canvas.drawBitmap(scaledBaseRedPieceSpy, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case FLAG:
                        canvas.drawBitmap(scaledBaseRedPieceFlag, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                }
                break;
            case BLUE_TEAM:

                //TODO: remove this clause for debugging
                if(currentTeam != Team.BLUE_TEAM && drawThisPiece.getIsVisible() == false) {
                    Log.i("drawmsg", "got here somehow");
                    canvas.drawBitmap(scaledBaseBluePiece, canvas.getWidth() * 2 / 100 +
                            canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                    break;
                }

                switch (drawThisPiece.getPieceRank()) {
                    case ONE:
                        canvas.drawBitmap(scaledBaseBluePiece1, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * row / 10, canvas.getHeight() * col / 10, null);
                        break;
                    case TWO:
                        canvas.drawBitmap(scaledBaseBluePiece2, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * row / 10, canvas.getHeight() * col / 10, null);
                        break;
                    case THREE:
                        canvas.drawBitmap(scaledBaseBluePiece3, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * row / 10, canvas.getHeight() * col / 10, null);
                        break;
                    case FOUR:
                        canvas.drawBitmap(scaledBaseBluePiece4, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * row / 10, canvas.getHeight() * col / 10, null);
                        break;
                    case FIVE:
                        canvas.drawBitmap(scaledBaseBluePiece5, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * row / 10, canvas.getHeight() * col / 10, null);
                        break;
                    case SIX:
                        canvas.drawBitmap(scaledBaseBluePiece6, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * row / 10, canvas.getHeight() * col / 10, null);
                        break;
                    case SEVEN:
                        canvas.drawBitmap(scaledBaseBluePiece7, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * row / 10, canvas.getHeight() * col / 10, null);
                        break;
                    case EIGHT:
                        canvas.drawBitmap(scaledBaseBluePiece8, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * row / 10, canvas.getHeight() * col / 10, null);
                        break;
                    case NINE:
                        canvas.drawBitmap(scaledBaseBluePiece9, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * row / 10, canvas.getHeight() * col / 10, null);
                        break;
                    case BOMB:
                        canvas.drawBitmap(scaledBaseBluePieceBomb, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * row / 10, canvas.getHeight() * col / 10, null);
                        break;
                    case SPY:
                        canvas.drawBitmap(scaledBaseBluePieceSpy, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * row / 10, canvas.getHeight() * col / 10, null);
                        break;
                    case FLAG:
                        canvas.drawBitmap(scaledBaseBluePieceFlag, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * row / 10, canvas.getHeight() * col / 10, null);
                        break;
                    }
                break;

        }
    }
}
