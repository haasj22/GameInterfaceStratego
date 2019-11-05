package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

import com.example.myapplication.R;

public class StandardGameBoard extends SurfaceView {
    enum piece {
        RED1, RED2, RED3, RED4, RED5, RED6, RED7, RED8, RED9, REDS, REDB, REDF, RED_,
        BLU1, BLU2, BLU3, BLU4, BLU5, BLU6, BLU7, BLU8, BLU9, BLUS, BLUB, BLUF, BLU_,
        WATR, EMPT;
    }

    piece[][] gameBoard= {
            {piece.EMPT, piece.EMPT, piece.REDB, piece.EMPT, piece.EMPT,
                    piece.EMPT, piece.EMPT, piece.EMPT, piece.RED9, piece.EMPT},
            {piece.EMPT, piece.REDB, piece.REDF, piece.REDB, piece.RED2,
                    piece.EMPT, piece.REDS, piece.EMPT, piece.RED8, piece.EMPT},
            {piece.EMPT, piece.EMPT, piece.REDB, piece.EMPT, piece.EMPT,
                    piece.RED5, piece.EMPT, piece.EMPT, piece.EMPT, piece.EMPT},
            {piece.EMPT,  piece.EMPT,  piece.EMPT,  piece.EMPT,  piece.EMPT,
                    piece.EMPT, piece.EMPT, piece.EMPT, piece.EMPT, piece.EMPT},
            {piece.WATR, piece.WATR, piece.EMPT, piece.RED1, piece.WATR,
                    piece.WATR, piece.EMPT, piece.EMPT, piece.WATR, piece.WATR},
            {piece.WATR, piece.WATR, piece.EMPT, piece.BLU3, piece.WATR,
                    piece.WATR, piece.EMPT, piece.EMPT, piece.WATR, piece.WATR},
            {piece.EMPT, piece.EMPT, piece.EMPT, piece.EMPT, piece.EMPT,
                    piece.BLU6, piece.EMPT, piece.BLU8, piece.EMPT, piece.EMPT},
            {piece.EMPT, piece.EMPT, piece.EMPT, piece.EMPT, piece.BLU4,
                    piece.EMPT, piece.EMPT, piece.EMPT, piece.EMPT, piece.EMPT},
            {piece.EMPT, piece.EMPT, piece.BLU1, piece.EMPT, piece.EMPT,
                    piece.EMPT, piece.BLU7, piece.EMPT, piece.EMPT, piece.BLUB},
            {piece.EMPT, piece.EMPT, piece.EMPT, piece.EMPT, piece.EMPT,
                    piece.EMPT, piece.EMPT, piece.EMPT, piece.BLUB, piece.BLUF}
    };

    public StandardGameBoard(Context context, AttributeSet attr) {
        super(context, attr);

        setWillNotDraw(false);
    }


    @Override
    public void onDraw(Canvas canvas) {
        Bitmap normalBoard =
                BitmapFactory.decodeResource(getResources(), R.drawable.base_board);
        Bitmap usableNormalBoard =
                Bitmap.createScaledBitmap(normalBoard, canvas.getWidth(), canvas.getHeight(), false);
        canvas.drawBitmap(usableNormalBoard, 0, 0, null);

        Bitmap gamePieceGraphics;
        Bitmap usableGamePieceGraphics;
        for(int x =0; x<gameBoard.length; x++) {
            for(int y = 0; y<gameBoard[x].length; y++) {
                switch(gameBoard[x][y]) {
                    case BLU1:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.base_piece1);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case BLU2:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.base_piece2);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case BLU3:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.base_piece3);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case BLU4:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.base_piece4);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case BLU5:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.base_piece5);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case BLU6:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.base_piece6);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case BLU7:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.base_piece7);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case BLU8:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.base_piece8);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case BLU9:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.base_piece9);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case BLUB:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.base_piece_bomb);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case BLUF:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.base_piecef);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case BLU_:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.base_piece);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case RED_: {
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    }
                    case RED1:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece1);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case RED2:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece2);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case RED3:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece3);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case RED4:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece4);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case RED5:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece5);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case RED6:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece6);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case RED7:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece7);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case RED8:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece8);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case RED9:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece9);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case REDB:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece_bomb);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case REDF:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiecef);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                    case REDS:
                        gamePieceGraphics =
                                BitmapFactory.decodeResource(getResources(), R.drawable.red_basepiece_spy);
                        usableGamePieceGraphics =
                                Bitmap.createScaledBitmap(gamePieceGraphics,
                                        canvas.getHeight() *4/50,
                                        canvas.getHeight()/10, false);
                        canvas.drawBitmap(usableGamePieceGraphics,
                                canvas.getWidth()*2/100 + canvas.getWidth() * y/10,
                                canvas.getHeight() * x/10, null);
                        break;
                }
            }
        }


    }


}
