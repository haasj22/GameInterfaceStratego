package com.example.myapplication.Stratego.Player;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.Game.Game;
import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.GameHumanPlayer;
import com.example.myapplication.Game.infoMsg.GameInfo;
import com.example.myapplication.Game.infoMsg.IllegalMoveInfo;
import com.example.myapplication.Game.infoMsg.NotYourTurnInfo;
import com.example.myapplication.R;
import com.example.myapplication.StandardGameBoard;
import com.example.myapplication.Stratego.GameActions.StrategoButtonPieceAction;
import com.example.myapplication.Stratego.GameActions.StrategoForfeitAction;
import com.example.myapplication.Stratego.GameActions.StrategoHelpAction;
import com.example.myapplication.Stratego.GameActions.StrategoMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoMuteAction;
import com.example.myapplication.Stratego.GameActions.StrategoNotepadAction;
import com.example.myapplication.Stratego.GameActions.StrategoTransitionAction;
import com.example.myapplication.Stratego.GameState.Rank;
import com.example.myapplication.Stratego.GameState.StrategoGameState;
import com.example.myapplication.Stratego.GameState.Team;
import com.example.myapplication.Stratego.StrategoFrameworkClasses.StrategoSurfaceView;

/**
 * TODO: check to see if player can make valid move and if not skip turn, going to be implemented in on
 * handles GUI and all actions for humanPlayer
 *
 * NOTE: I think receiveInfo, setAsGui, and  is done a
**/

public class StrategoHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener{

    //tag for logging
    private static final String TAG = "StrategoHumanPlayer";
    //GUI

    private TextView whosTurn;


    //android current activity
    private GameMainActivity myActivity;

    //surface view
    private StrategoSurfaceView surfaceView;



    MediaPlayer mediaPlayer;


    //buttons and or image buttons for other actions
    private Button notepadButton;
    private Button startButton;
    private Button forfeitButton;
    private ImageButton helpButton;
    private ImageButton muteButton;
    private Button endTurnButton;



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
        return myActivity.findViewById(R.id.StrategoInGameLayout);
    }


    /**
     * receiveInfo method
     * @param info
     */
    @Override
    public void receiveInfo(GameInfo info) {
        if(info == null) return;

        if(info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            surfaceView.flash(Color.RED, 50);
        }

        if(info instanceof StrategoGameState) {
            if (surfaceView == null) return;
            surfaceView.setState((StrategoGameState) info);
            Log.i("setupmsg", "surface view invalidated");
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
        Button tappedButton = (Button)v;
        switch((String)tappedButton.getText()){
            case"1":
                this.game.sendAction(new StrategoButtonPieceAction(this, Rank.ONE));
                break;
            case"2":
                this.game.sendAction(new StrategoButtonPieceAction(this, Rank.TWO));
                break;
            case"3":
                this.game.sendAction(new StrategoButtonPieceAction(this, Rank.THREE));
                break;
            case"4":
                this.game.sendAction(new StrategoButtonPieceAction(this, Rank.FOUR));
                break;
            case"5":
                this.game.sendAction(new StrategoButtonPieceAction(this, Rank.FIVE));
                break;
            case"6":
                this.game.sendAction(new StrategoButtonPieceAction(this, Rank.SIX));
                break;
            case"7":
                this.game.sendAction(new StrategoButtonPieceAction(this, Rank.SEVEN));
                break;
            case"8":
                this.game.sendAction(new StrategoButtonPieceAction(this, Rank.EIGHT));
                break;
            case"9":
                this.game.sendAction(new StrategoButtonPieceAction(this, Rank.NINE));
                break;
            case"S":
                this.game.sendAction(new StrategoButtonPieceAction(this, Rank.SPY));
                break;
            case"BOMB":
                this.game.sendAction(new StrategoButtonPieceAction(this, Rank.BOMB));
                break;
            case"FLAG":
                this.game.sendAction(new StrategoButtonPieceAction(this, Rank.FLAG));
                break;
        }

        //action type - send action
        switch (v.getId()){
            case R.id.forfeitButton:
                StrategoForfeitAction forfeitAction = new StrategoForfeitAction(this);
                this.game.sendAction(forfeitAction);
                break;
            case R.id.muteButton:
                StrategoMuteAction muteAction = new StrategoMuteAction(this);
                this.game.sendAction(muteAction);
                break;
            case R.id.infoButton:
                StrategoHelpAction helpAction = new StrategoHelpAction(this);
                this.game.sendAction(helpAction);
                break;
            case R.id.notepadButton:
                StrategoNotepadAction notepadAction = new StrategoNotepadAction(this);
                this.game.sendAction(notepadAction);
                break;
            case R.id.endTurnButton:
                StrategoTransitionAction transitionAction =
                        new StrategoTransitionAction(this);
                this.game.sendAction(transitionAction);
                break;
                default:
                    break;


        }

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



        /**
        if (v == menuButton) {
            activity.startActivity(new Intent(activity, MenuButton.class));
        }
        if (v == startButton) {
            // start game
        }
        if (v == forfeitButton) {
            // forfeit game
        }

        //buttons won't work if it's not your turn
        if (state.playerTurn() != playerNum) {
            return;
        }

        //TODO: initialize all buttons to move onto board.

        //update GUI to current piece user is choosing
        if (gameBoard.currentPiece() != null) {
            gameBoard.invalidate();
        }

        //disappears from side list
        if (v == movePieceButton) {
            //sendAction
            //gameBoard.currentPiece(null);
            //movePieceButton.setEnabled(false);
        }
        **/
    }

    /**
     *
     */


    /**
     * setAsGui sets the current player as the activity's GUI
     * @param activity
     */
    public void setAsGui(GameMainActivity activity) {

        //remember the activity
        myActivity = activity;

        //loads the layout for stratego GUI

        activity.setContentView(R.layout.stratego_board);


        mediaPlayer = MediaPlayer.create(activity.getApplicationContext(), R.raw.stratego);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        //rulesHelpButton = new RulesHelp(this.activity.findViewById(R.id.menuButton),
                //this, this.game, this.activity);


        surfaceView = activity.findViewById(R.id.boardImageView);
        surfaceView.setOnTouchListener(this);


        whosTurn = (TextView)activity.findViewById(R.id.turnText);

        marshallButton = (Button)activity.findViewById(R.id.marshallButton);
        marshallButton.setOnClickListener(this);
        generalButton = (Button)activity.findViewById(R.id.generalButton);
        generalButton.setOnClickListener(this);
        colonelButton = (Button)activity.findViewById(R.id.colonelButton);
        colonelButton.setOnClickListener(this);
        majorButton = (Button)activity.findViewById(R.id.majorButton);
        majorButton.setOnClickListener(this);
        captainButton = (Button)activity.findViewById(R.id.captainButton);
        captainButton.setOnClickListener(this);
        lieutenantButton = (Button)activity.findViewById(R.id.lieutenantButton);
        lieutenantButton.setOnClickListener(this);
        sergeantButton = (Button)activity.findViewById(R.id.sergeantButton);
        sergeantButton.setOnClickListener(this);
        minerButton = (Button)activity.findViewById(R.id.minerButton);
        minerButton.setOnClickListener(this);
        scoutButton = (Button)activity.findViewById(R.id.scoutButton);
        scoutButton.setOnClickListener(this);
        spyButton = (Button)activity.findViewById(R.id.spyButton);
        spyButton.setOnClickListener(this);
        bombButton = (Button)activity.findViewById(R.id.bombButton);
        bombButton.setOnClickListener(this);
        flagButton = (Button)activity.findViewById(R.id.flagButton);
        flagButton.setOnClickListener(this);

        //action buttons on GUI
        helpButton = (ImageButton)activity.findViewById(R.id.infoButton);
        helpButton.setOnClickListener(this);
        muteButton = (ImageButton)activity.findViewById(R.id.muteButton);
        muteButton.setOnClickListener(this);
        forfeitButton = (Button)activity.findViewById(R.id.forfeitButton);
        forfeitButton.setOnClickListener(this);
        notepadButton = (Button)activity.findViewById(R.id.notepadButton);
        notepadButton.setOnClickListener(this);
        endTurnButton = (Button)activity.findViewById(R.id.endTurnButton);
        endTurnButton.setOnClickListener(this);


    }


    /**
     * onTouch method
     * @param v
     * @param event
     * @return
     */
    public boolean onTouch(View v, MotionEvent event) {
        //if empty then return true
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.i("setupmsg", "tapped");
            //finds out where on the board is being tapped
            int row = (int) (event.getY()) * 10 / v.getHeight();
            int col = (int) (event.getX()) * 10 / v.getWidth();
            Log.i("setupmsg", "Row: " + row);
            Log.i("setupmsg", "Col: " + col);
            StrategoMoveAction moveCommand = new StrategoMoveAction(this, row, col);


            //tell game we want to make move
            if (this.game == null) {
                Log.i("msg", "Game Not Working");
            } else
                this.game.sendAction(moveCommand);
            surfaceView.invalidate();
        }
        return true;
    }//onTouch

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

}



