package com.example.myapplication.Stratego.Player;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.GameHumanPlayer;
import com.example.myapplication.Game.infoMsg.GameInfo;
import com.example.myapplication.R;
import com.example.myapplication.StandardGameBoard;
import com.example.myapplication.Stratego.GameState.StrategoGameState;
import com.example.myapplication.Stratego.RulesHelp;
/**TODO: check to see if player can make valid move and if not skip turn, going to be implemented in on
**/
/*
public class HumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    private RulesHelp rulesHelpButton;
    private GameMainActivity activity;
    private StrategoGameState state;

    MediaPlayer mediaPlayer;


    private StandardGameBoard gameBoard;

    private Button startButton;
    private Button forfeitButton;

    *//**
     * HumanPlayer method
     * @param name
     *//*
    public HumanPlayer(String name) {
        super(name);
    }

    *//**
     * getTopView method
     * returns the GUI's top view
     * @return topGUI
     *//*
    public View getTopView() {
        return activity.findViewById(R.id.topGUI);
    }

    *//**
     * initAfterReady method
     *//*
    @Override
    protected void initAfterReady(){

    }



    public void receiveInfo(GameInfo info) {
        if (info instanceof StrategoGameState) {
            if (rulesHelpButton.getGame() == null) {


            }
        }
    }



    private void updateGui() {
        // updates gui after actions are played
    }

    private void setBoard() {
        // sets board according to players' layout
    }

    @Override
    public void onClick(View v) {
        if (v == rulesHelpButton){
            activity.startActivity(new Intent(activity,MenuButton.class));
        }
        if (v == startButton) {
            // start game
        }
        if (v == forfeitButton) {
            // forfeit game
        }

        //buttons won't work if it's not your turn
        if(state.playerTurn() != playerNum){
            return;
        }

        //TODO: initialize all buttons to move onto board.

        //update GUI to current piece user is choosing
        if(gameBoard.currentPiece() != null){
            gameBoard.invalidate();
        }

        //disappears from side list
        if( v == movePieceButton){
            //sendAction
            //gameBoard.currentPiece(null);
            //movePieceButton.setEnabled(false);
        }
    }

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
        activity.setContentView(R.layout.stratego_rules);

        mediaPlayer = MediaPlayer.create(activity.getApplicationContext(),R.raw.stratego);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        rulesHelpButton = new RulesHelp(this.activity.findViewById(R.id.menuButton),
                this, this.game, this.activity);



    }
    @Override
    public boolean onTouch(View v, MotionEvent event){
        //player to select move
        if(gameBoard == null)//currentpiece method) == null)
            {
                    return false;
            }
        return false;
        }

    *//**
     * updatePlayerInventory
     *//*
    private void updatePlayerInventory() {}
    private


    }

}*/

