package com.example.myapplication.Stratego.Player;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.GameHumanPlayer;
import com.example.myapplication.Game.infoMsg.GameInfo;
import com.example.myapplication.Game.infoMsg.IllegalMoveInfo;
import com.example.myapplication.Game.infoMsg.NotYourTurnInfo;
import com.example.myapplication.R;
import com.example.myapplication.StandardGameBoard;
import com.example.myapplication.Stratego.GameActions.ButtonPieceAction;
import com.example.myapplication.Stratego.GameActions.StrategoMoveAction;
import com.example.myapplication.Stratego.GameState.Rank;
import com.example.myapplication.Stratego.GameState.StrategoGameState;
import com.example.myapplication.Stratego.RulesHelp;
import com.example.myapplication.Stratego.StrategoFrameworkClasses.StrategoSurfaceView;

/**
 * TODO: check to see if player can make valid move and if not skip turn, going to be implemented in on
 * handles GUI and all actions for humanPlayer
**/

public class StrategoHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener{

    //GUI
    private TextView whosTurn;


    //android activity
    private GameMainActivity activity;

    //most recent game state;
    //private StrategoGameState state;
    //StrategoGameState latestState = new StrategoGameState(); bad
    private StrategoSurfaceView surfaceView;


    MediaPlayer mediaPlayer;




    //buttons for other actions
    private RulesHelp rulesHelpButton;
    private Button menuButton;
    private Button notepadButton;
    private Button startButton;
    private Button forfeitButton;



    //buttons for pieces
    private Button marshallButton;
    private Button generalButton;
    private Button colonelButton;
    private Button majorButton;
    private Button captainButton;
    private Button lieutenantButton;
    private Button sergeantButton;
    private Button minerButton;
    private Button scoutButton;
    private Button spyButton;
    private Button bombButton;
    private Button flagButton;


    /**
     * HumanPlayer method
     *
     * @param name
     */
    public StrategoHumanPlayer(String name) {
        super(name);
    }

    /**
     * getTopView method
     * returns the
     * GUI's top view
     *
     * @return topGUI
     */

    public View getTopView() {
        return activity.findViewById(R.id.StrategoInGameLayout);
    }

    /**
     * receiveInfo method
     * @param info
     */
    @Override
    public void receiveInfo(GameInfo info) {
        if (info == null) return;

        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            // if the move was out of turn or otherwise illegal, flash the screen
            surfaceView.flash(Color.RED, 50);
        }
        if (info instanceof StrategoGameState) {
            if (surfaceView == null)
                return;
           surfaceView.setState((StrategoGameState)info);
           surfaceView.invalidate();
        }
    }

    /**
     * setBoard method
     */
    private void setBoard() {
        // sets board according to players' layout
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        //if player is in set up phase then show pieces while setting up
        //TODO: call tapOnSquareSETUP to be able to add pieces to board
        //TODO: then call addPieceToGame to place pieces on board
        //TODO: if player wants to remove piece, call removePieceFromGame
        //TODO: if player wants to move piece, call movePieceDuringSetup
        //TODO: if player doesn't want to set up board, call randomizeRemainingPieces
        //TODO: then do a final check to see if the player's side is full with isBoardFull

        //if player is in play phase then don't show pieces of enemy (hide your pieces as well)
        //TODO: call tapOnSquarePLAY to be able to add piece to board
        //TODO: if player wants to move piece, call movePiece
        //TODO: if piece clashes with another, call attackPiece and it will ...
        //TODO: call unitAttacks, scoutAttacks, spyAttacks, attackBomb, or attackFlag depending

        Button tappedButton = (Button)v;

        switch((String)tappedButton.getText()) {
            case "1":
                this.game.sendAction(new ButtonPieceAction(this, Rank.ONE));
                break;
            case "2":
                this.game.sendAction(new ButtonPieceAction(this, Rank.TWO));
                break;
            case "3":
                this.game.sendAction(new ButtonPieceAction(this, Rank.THREE));
                break;
            case "4":
                this.game.sendAction(new ButtonPieceAction(this,Rank.FOUR));
                break;
            case "5":
                this.game.sendAction(new ButtonPieceAction(this, Rank.FIVE));
                break;
            case "6":
                this.game.sendAction(new ButtonPieceAction(this, Rank.SIX));
                break;
            case "7":
                this.game.sendAction(new ButtonPieceAction(this, Rank.SEVEN));
                break;
            case "8":
                this.game.sendAction(new ButtonPieceAction(this, Rank.EIGHT));
                break;
            case "9":
                this.game.sendAction(new ButtonPieceAction(this, Rank.NINE));
                break;
            case "S":
                this.game.sendAction(new ButtonPieceAction(this, Rank.SPY));
                break;
            case "BOMB":
                this.game.sendAction(new ButtonPieceAction(this, Rank.BOMB));
                break;
            case "FLAG":
                this.game.sendAction(new ButtonPieceAction(this, Rank.FLAG));
                break;
        }
    }

    /**
     *
     */
    private void writeNotes() {
        // edits notes edit text for player's game notes
    }

    public void locateUnites() {
        // locates units of pieces
    }

    public void movePiece() {
        // moves game pieces
    }

    public void attackPiece() {
        // attacks chosen adjacent enemy piece
    }

    public void captureFlag() {
        // attempts to capture suspected enemy flag
    }

    public void hitBomb() {
        // hits bomb upon discovery
    }

    public void setAsGui(GameMainActivity activity) {
        this.activity = activity;
        activity.setContentView(R.layout.stratego_board);

        mediaPlayer = MediaPlayer.create(activity.getApplicationContext(), R.raw.stratego);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        //rulesHelpButton = new RulesHelp(this.activity.findViewById(R.id.menuButton),
                //this, this.game, this.activity);

        surfaceView=(StrategoSurfaceView)activity.findViewById(R.id.boardImageView);
        surfaceView.setOnTouchListener(this);

        marshallButton=(Button)activity.findViewById(R.id.marshallButton);
        generalButton=(Button)activity.findViewById(R.id.generalButton);
        colonelButton=(Button)activity.findViewById(R.id.colonelButton);
        majorButton=(Button)activity.findViewById(R.id.majorButton);
        captainButton=(Button)activity.findViewById(R.id.captainButton);
        lieutenantButton=(Button)activity.findViewById(R.id.lieutenantButton);
        sergeantButton=(Button)activity.findViewById(R.id.sergeantButton);
        minerButton=(Button)activity.findViewById(R.id.minerButton);
        scoutButton=(Button)activity.findViewById(R.id.scoutButton);
        spyButton=(Button)activity.findViewById(R.id.spyButton);
        bombButton=(Button)activity.findViewById(R.id.bombButton);
        flagButton=(Button)activity.findViewById(R.id.flagButton);

        marshallButton.setOnClickListener(this);
        generalButton.setOnClickListener(this);
        colonelButton.setOnClickListener(this);
        majorButton.setOnClickListener(this);
        captainButton.setOnClickListener(this);
        lieutenantButton.setOnClickListener(this);
        sergeantButton.setOnClickListener(this);
        minerButton.setOnClickListener(this);
        minerButton.setOnClickListener(this);
        scoutButton.setOnClickListener(this);
        spyButton.setOnClickListener(this);
        bombButton.setOnClickListener(this);
        flagButton.setOnClickListener(this);

    }



    public boolean onTouch(View v, MotionEvent event) {
        Log.i("msg", "tapped");
        int row = (int)(event.getX())*10/v.getWidth();
        int col = (int)(event.getY())*10/v.getHeight();
        Log.i("msg", "Row: " + row);
        Log.i("msg", "Col: " + col);
        StrategoMoveAction moveCommand = new StrategoMoveAction(this, row, col);
        if(this.game == null) {
            Log.i("msg", "Game Not Working");
        }
        else
            this.game.sendAction(moveCommand);
        surfaceView.invalidate();
        return true;
    }

}



