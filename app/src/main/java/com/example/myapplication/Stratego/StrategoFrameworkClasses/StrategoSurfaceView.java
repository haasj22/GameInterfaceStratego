/**
 * displays the game board
 * @author John Haas
 * @author Kavya Mandla
 */
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

    private int surfaceViewOwner;
    protected StrategoGameState state;
    //private Game game;

    //the paint that appears as a highight
    Paint highlightPaint;

    //bitmaps for all game pieces
    Bitmap baseBoard = BitmapFactory.decodeResource(getResources() ,
            R.drawable.base_board);
    Bitmap scaledBaseBoard;
    Bitmap baseBluePiece = BitmapFactory.decodeResource(getResources(),
            R.drawable.base_piece);
    Bitmap scaledBaseBluePiece;
    Bitmap baseBluePiece1 = BitmapFactory.decodeResource(getResources(),
            R.drawable.base_piece1);
    Bitmap scaledBaseBluePiece1;
    Bitmap baseBluePiece2 = BitmapFactory.decodeResource(getResources(),
            R.drawable.base_piece2);
    Bitmap scaledBaseBluePiece2;
    Bitmap baseBluePiece3 = BitmapFactory.decodeResource(getResources(),
            R.drawable.base_piece3);
    Bitmap scaledBaseBluePiece3;
    Bitmap baseBluePiece4 = BitmapFactory.decodeResource(getResources(),
            R.drawable.base_piece4);
    Bitmap scaledBaseBluePiece4;
    Bitmap baseBluePiece5 = BitmapFactory.decodeResource(getResources(),
            R.drawable.base_piece5);
    Bitmap scaledBaseBluePiece5;
    Bitmap baseBluePiece6 = BitmapFactory.decodeResource(getResources(),
            R.drawable.base_piece6);
    Bitmap scaledBaseBluePiece6;
    Bitmap baseBluePiece7 = BitmapFactory.decodeResource(getResources(),
            R.drawable.base_piece7);
    Bitmap scaledBaseBluePiece7;
    Bitmap baseBluePiece8 = BitmapFactory.decodeResource(getResources(),
            R.drawable.base_piece8);
    Bitmap scaledBaseBluePiece8;
    Bitmap baseBluePiece9 = BitmapFactory.decodeResource(getResources(),
            R.drawable.base_piece9);
    Bitmap scaledBaseBluePiece9;
    Bitmap baseBluePieceBomb = BitmapFactory.decodeResource(getResources(),
            R.drawable.base_piece_bomb);
    Bitmap scaledBaseBluePieceBomb;
    Bitmap baseBluePieceSpy = BitmapFactory.decodeResource(getResources(),
            R.drawable.base_piece_spy);
    Bitmap scaledBaseBluePieceSpy;
    Bitmap baseBluePieceFlag = BitmapFactory.decodeResource(getResources(),
            R.drawable.base_piecef);
    Bitmap scaledBaseBluePieceFlag;
    Bitmap baseRedPiece = BitmapFactory.decodeResource(getResources(),
            R.drawable.red_basepiece);
    Bitmap scaledBaseRedPiece;
    Bitmap baseRedPiece1 = BitmapFactory.decodeResource(getResources(),
            R.drawable.red_basepiece1);
    Bitmap scaledBaseRedPiece1;
    Bitmap baseRedPiece2 = BitmapFactory.decodeResource(getResources(),
            R.drawable.red_basepiece2);
    Bitmap scaledBaseRedPiece2;
    Bitmap baseRedPiece3 = BitmapFactory.decodeResource(getResources(),
            R.drawable.red_basepiece3);
    Bitmap scaledBaseRedPiece3;
    Bitmap baseRedPiece4 = BitmapFactory.decodeResource(getResources(),
            R.drawable.red_basepiece4);
    Bitmap scaledBaseRedPiece4;
    Bitmap baseRedPiece5 = BitmapFactory.decodeResource(getResources(),
            R.drawable.red_basepiece5);
    Bitmap scaledBaseRedPiece5;
    Bitmap baseRedPiece6 = BitmapFactory.decodeResource(getResources(),
            R.drawable.red_basepiece6);
    Bitmap scaledBaseRedPiece6;
    Bitmap baseRedPiece7 = BitmapFactory.decodeResource(getResources(),
            R.drawable.red_basepiece7);
    Bitmap scaledBaseRedPiece7;
    Bitmap baseRedPiece8 = BitmapFactory.decodeResource(getResources(),
            R.drawable.red_basepiece8);
    Bitmap scaledBaseRedPiece8;
    Bitmap baseRedPiece9 = BitmapFactory.decodeResource(getResources(),
            R.drawable.red_basepiece9);
    Bitmap scaledBaseRedPiece9;
    Bitmap baseRedPieceBomb = BitmapFactory.decodeResource(getResources(),
            R.drawable.red_basepiece_bomb);
    Bitmap scaledBaseRedPieceBomb;
    Bitmap baseRedPieceSpy = BitmapFactory.decodeResource(getResources(),
            R.drawable.red_basepiece_spy);
    Bitmap scaledBaseRedPieceSpy;
    Bitmap baseRedPieceFlag = BitmapFactory.decodeResource(getResources(),
            R.drawable.red_basepiecef);
    Bitmap scaledBaseRedPieceFlag;

    //dimensions of the board
    int width = 0;
    int height = 0;

    /**
     * constructor for StrategoSurfaceView
     * @param context state of the game upon creation
     */
    public StrategoSurfaceView(Context context) {
        super(context);
        setWillNotDraw(false);
        init();
    }

    /**
     * secondary constructor for StrategoSurfaceView
     * @param context state of the game upon creation
     * @param attrs attributes of the surface view
     */
    public StrategoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        init();
    }


    /**
     * method that initiates necessary variables
     * created for possible future expanision of paints
     */
    public void init() {
        highlightPaint = new Paint(Color.YELLOW);
    }

    public int getSurfaceViewOwner() {
        return surfaceViewOwner;
    }

    public StrategoGameState getState() {
        return state;
    }

    public void setState(StrategoGameState sgt) {
        this.state = sgt;
    }

    public void setSurfaceViewOwner(int surfaceViewOwner) {
        this.surfaceViewOwner = surfaceViewOwner;
    }

    /**
     * method that explains what happens when the surface view's size is suddenly changed
     * @param w new width of the surface view
     * @param h new height of the surface view
     * @param oldw old width of the surface view
     * @param oldh old height of the surface view
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        //adjusts the dimension variables accordingly
        width = w;
        height = h;

        //scales all the bitmaps
        scaledBaseBoard=Bitmap.createScaledBitmap(baseBoard, w, h, false);
        scaledBaseBluePiece=
                Bitmap.createScaledBitmap(baseBluePiece,
                        h * 4/50, h/10, false);
        scaledBaseBluePiece1=
                Bitmap.createScaledBitmap(baseBluePiece1,
                        h * 4/50, h/10, false);
        scaledBaseBluePiece2=
                Bitmap.createScaledBitmap(baseBluePiece2,
                        h * 4/50, h/10, false);
        scaledBaseBluePiece3=
                Bitmap.createScaledBitmap(baseBluePiece3,
                        h * 4/50, h/10, false);
        scaledBaseBluePiece4=
                Bitmap.createScaledBitmap(baseBluePiece4,
                        h * 4/50, h/10, false);
        scaledBaseBluePiece5=
                Bitmap.createScaledBitmap(baseBluePiece5,
                        h * 4/50, h/10, false);
        scaledBaseBluePiece6=
                Bitmap.createScaledBitmap(baseBluePiece6,
                        h * 4/50, h/10, false);
        scaledBaseBluePiece7=
                Bitmap.createScaledBitmap(baseBluePiece7,
                        h * 4/50, h/10, false);
        scaledBaseBluePiece8=
                Bitmap.createScaledBitmap(baseBluePiece8,
                        h * 4/50, h/10, false);
        scaledBaseBluePiece9=
                Bitmap.createScaledBitmap(baseBluePiece9,
                        h * 4/50, h/10, false);
        scaledBaseBluePieceBomb=
                Bitmap.createScaledBitmap(baseBluePieceBomb,
                        h * 4/50, h/10, false);
        scaledBaseBluePieceSpy=
                Bitmap.createScaledBitmap(baseBluePieceSpy,
                        h * 4/50, h/10, false);
        scaledBaseBluePieceFlag=
                Bitmap.createScaledBitmap(baseBluePieceFlag,
                        h * 4/50, h/10, false);
        scaledBaseRedPiece=
                Bitmap.createScaledBitmap(baseRedPiece,
                        h * 4/50, h/10, false);
        scaledBaseRedPiece1=
                Bitmap.createScaledBitmap(baseRedPiece1,
                        h * 4/50, h/10, false);
        scaledBaseRedPiece2=
                Bitmap.createScaledBitmap(baseRedPiece2,
                        h * 4/50, h/10, false);
        scaledBaseRedPiece3=
                Bitmap.createScaledBitmap(baseRedPiece3,
                        h * 4/50, h/10, false);
        scaledBaseRedPiece4=
                Bitmap.createScaledBitmap(baseRedPiece4,
                        h * 4/50, h/10, false);
        scaledBaseRedPiece5=
                Bitmap.createScaledBitmap(baseRedPiece5,
                        h * 4/50, h/10, false);
        scaledBaseRedPiece6=
                Bitmap.createScaledBitmap(baseRedPiece6,
                        h * 4/50, h/10, false);
        scaledBaseRedPiece7=
                Bitmap.createScaledBitmap(baseRedPiece7,
                        h * 4/50, h/10, false);
        scaledBaseRedPiece8=
                Bitmap.createScaledBitmap(baseRedPiece8,
                        h * 4/50, h/10, false);
        scaledBaseRedPiece9=
                Bitmap.createScaledBitmap(baseRedPiece9,
                        h * 4/50, h/10, false);
        scaledBaseRedPieceBomb=
                Bitmap.createScaledBitmap(baseRedPieceBomb,
                        h * 4/50, h/10, false);
        scaledBaseRedPieceSpy=
                Bitmap.createScaledBitmap(baseRedPieceSpy,
                        h * 4/50, h/10, false);
        scaledBaseRedPieceFlag=
                Bitmap.createScaledBitmap(baseRedPieceFlag,
                        h * 4/50, h/10, false);
    }

    /**
     * method that draws the surface view
     * @param g canvas for the surface view to draw on
     */
    public void onDraw(Canvas g) {
        //makes sure gamestate exists
        if(state == null)
        {
            return;
        }

        //draws the base board
        g.drawBitmap(scaledBaseBoard, 0, 0, null);

        //goes through each position on the board
        for(int row=0; row<state.getROWMAX(); row++) {
            for(int col=0; col<state.getCOLMAX(); col++) {
                //draws highlights where they need to be
                if(state.getBoard()[row][col].isHighLighted()
                        && state.getCurrentTeamsTurn().getTEAMNUMBER() == surfaceViewOwner) {
                    g.drawRect((width*col)/10, (height*row)/10,
                            (width * (col + 1))/10,
                            (height*(row+1))/10, highlightPaint);
                }

                //only draws pieces on spaces that contain them
                if(state.getBoard()[row][col].getContainedPiece() == null) {
                    continue;
                }

                //get the piece at the desired board location and draws it
                Piece piece = state.getBoard()[row][col].getContainedPiece();
                drawPiece(g, state.getCurrentTeamsTurn(), piece, row, col);
            }
        }
    }

    /**
     * method that draws a piece onto the board
     *
     * @param canvas canvas that the piece will be drawn on
     * @param currentTeam team whose turn it currently is
     * @param drawThisPiece piece to be drawn
     * @param row row where the piece should be drawn
     * @param col col where the piece should be drawn
     */
    public void drawPiece(Canvas canvas, Team currentTeam, Piece drawThisPiece, int row, int col) {

        //checks which team the piece is in
        switch(drawThisPiece.getPieceTeam()) {

            case RED_TEAM:
                //draws a blank piece if its not suppose to be visible
                if(currentTeam.getTEAMNUMBER() == surfaceViewOwner
                        && drawThisPiece.getPieceTeam().getTEAMNUMBER() != surfaceViewOwner
                        && drawThisPiece.getIsVisible() == false
                        || currentTeam.getTEAMNUMBER() != surfaceViewOwner
                        && drawThisPiece.getPieceTeam().getTEAMNUMBER() != surfaceViewOwner
                        && drawThisPiece.getIsVisible() == false) {

                    canvas.drawBitmap(scaledBaseRedPiece, canvas.getWidth() * 2 / 100 +
                            canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                    break;
                }

                //draws the appropriate piece based on rank
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
                //draws a blank piece if its not suppose to be visible
                if(currentTeam.getTEAMNUMBER() == surfaceViewOwner
                        && drawThisPiece.getPieceTeam().getTEAMNUMBER() != surfaceViewOwner
                        && drawThisPiece.getIsVisible() == false
                        || currentTeam.getTEAMNUMBER() != surfaceViewOwner
                        && drawThisPiece.getPieceTeam().getTEAMNUMBER() != surfaceViewOwner
                        && drawThisPiece.getIsVisible() == false) {
                    canvas.drawBitmap(scaledBaseBluePiece, canvas.getWidth() * 2 / 100 +
                            canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                    break;
                }

                //draws the appropriate piece based on rank
                switch (drawThisPiece.getPieceRank()) {
                    case ONE:
                        canvas.drawBitmap(scaledBaseBluePiece1, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case TWO:
                        canvas.drawBitmap(scaledBaseBluePiece2, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case THREE:
                        canvas.drawBitmap(scaledBaseBluePiece3, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case FOUR:
                        canvas.drawBitmap(scaledBaseBluePiece4, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case FIVE:
                        canvas.drawBitmap(scaledBaseBluePiece5, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case SIX:
                        canvas.drawBitmap(scaledBaseBluePiece6, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case SEVEN:
                        canvas.drawBitmap(scaledBaseBluePiece7, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case EIGHT:
                        canvas.drawBitmap(scaledBaseBluePiece8, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case NINE:
                        canvas.drawBitmap(scaledBaseBluePiece9, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case BOMB:
                        canvas.drawBitmap(scaledBaseBluePieceBomb, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case SPY:
                        canvas.drawBitmap(scaledBaseBluePieceSpy, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    case FLAG:
                        canvas.drawBitmap(scaledBaseBluePieceFlag, canvas.getWidth() * 2 / 100 +
                                canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
                        break;
                    }
                break;
        }
    }
}
