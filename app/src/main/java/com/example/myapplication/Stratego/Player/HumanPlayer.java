package com.example.myapplication.Stratego.Player;

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

public class HumanPlayer extends GameHumanPlayer {

    private RulesHelp rulesHelpButton;
    private GameMainActivity activity;
    private StrategoGameState state;


    private StandardGameBoard gameBoard;

    private Button startButton;
    private Button forfeitButton;

    public HumanPlayer(String name) {
        super(name);
    }
    public View getTopView(){
        return activity.findViewById(R.id.topGUI);
    }




    public void receiveInfo(GameInfo info){
        if (info instanceof StrategoGameState){
            if(rulesHelpButton.getGame() == null){


            }
        }
    }


    public void setAsGui(GameMainActivity activity){
        this.activity = activity;
        activity.setContentView(R.layout.stratego_rules);

        rulesHelpButton = new RulesHelp(this.activity.findViewById(R.id.menuButton),
                this, this.game, this.activity);


    }
    private void updateGui(){
        // updates gui after actions are played
    }
    private void setBoard(){
        // sets board according to players' layout
    }
    @Override
    public void onClick(View v) {
        if(v == startButton){
            // start game
        }
        if(v == forfeitButton){
            // forfeit game
        }
    }

    private void writeNotes(){
        // edits notes edit text for player's game notes
    }
    public void locateUnites(){
        // locates units of pieces
    }
    public void movePiece(){
        // moves game pieces
    }
    public void attackPiece(){
        // attacks chosen adjacent enemy piece
    }
    public void captureFlag(){
        // attempts to capture suspected enemy flag
    }
    public void hitBomb(){
        // hits bomb upon discovery
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void gameSetAsGui(GameMainActivity activity) {

    }

}
     **/
