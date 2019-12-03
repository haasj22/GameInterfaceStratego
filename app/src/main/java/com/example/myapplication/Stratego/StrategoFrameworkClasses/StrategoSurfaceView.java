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
    Paint visiblePaint;

    ArrayList<Bitmap> strategoBitmaps = new ArrayList<>();



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
        addBitmaps();
    }


    /**
     * method that initiates necessary variables
     * created for possible future expanision of paints
     */
    public void init() {
        highlightPaint=new Paint();
        visiblePaint = new Paint();
        highlightPaint.setColor(Color.BLACK);
        visiblePaint.setColor(Color.YELLOW);
    }

    /**
     * method that adds all the necessary bitmaps to the arrayList
     */
    public void addBitmaps() {
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_piece1));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_piece2));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_piece3));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_piece4));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_piece5));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_piece6));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_piece7));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_piece8));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_piece9));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_piece_spy));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_piece_bomb));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_piecef));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_piece));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece1));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece2));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece3));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece4));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece5));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece6));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece7));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece8));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece9));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece_spy));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece_bomb));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiecef));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece));
        strategoBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.base_stratego_board));
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

        for(int x = 0; x < strategoBitmaps.size() - 1; x++) {
            strategoBitmaps.set(x, Bitmap.createScaledBitmap
                    (strategoBitmaps.get(x),h * 4/50, h/10, false));
        }
        strategoBitmaps.set(strategoBitmaps.size() - 1,
                Bitmap.createScaledBitmap(strategoBitmaps.get(strategoBitmaps.size() -1), w, h, false));
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
        g.drawBitmap(strategoBitmaps.get(strategoBitmaps.size()-1), 0, 0, null);

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

                //shows pieces if they're visible
                /*if(state.getBoard()[row][col].getContainedPiece() != null
                && state.getBoard()[row][col].getContainedPiece().getIsVisible() == true
                && state.getBoard()[row][col].getContainedPiece().getPieceTeam() == state.getCurrentTeamsTurn()) {
                    g.drawRect((width*col)/10, (height*row)/10,
                            (width * (col + 1))/10,
                            (height*(row+1))/10, visiblePaint);
                }*/

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

        int teamOffset;
        if(drawThisPiece.getPieceTeam() == Team.RED_TEAM) {
            teamOffset=13;
        } else {
            teamOffset=0;
        }

        if(currentTeam.getTEAMNUMBER() == surfaceViewOwner
                && drawThisPiece.getPieceTeam().getTEAMNUMBER() != surfaceViewOwner
                && drawThisPiece.getIsVisible() == false
                || currentTeam.getTEAMNUMBER() != surfaceViewOwner
                && drawThisPiece.getPieceTeam().getTEAMNUMBER() != surfaceViewOwner
                && drawThisPiece.getIsVisible() == false) {

            canvas.drawBitmap(strategoBitmaps.get(12 + teamOffset), canvas.getWidth() * 2 / 100 +
                    canvas.getWidth() * col / 10, canvas.getHeight() * row / 10, null);
            return;
        }
        else {
            canvas.drawBitmap(strategoBitmaps.get(drawThisPiece.getPieceRank().ordinal() + teamOffset),
                    canvas.getWidth() * 2 / 100 + canvas.getWidth() * col / 10,
                    canvas.getHeight() * row / 10, null);
            return;
        }
    }
}
