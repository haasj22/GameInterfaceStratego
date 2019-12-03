/**
 * class that displays the updated gui on screen
 * @author John Haas
 * @author Jordan Ho
 * @author Kavya Mandla
 */
package com.example.myapplication.Stratego.Player;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.GameHumanPlayer;
import com.example.myapplication.Game.infoMsg.GameInfo;
import com.example.myapplication.Game.infoMsg.IllegalMoveInfo;
import com.example.myapplication.Game.infoMsg.NotYourTurnInfo;
import com.example.myapplication.HowToPlay;
import com.example.myapplication.R;
import com.example.myapplication.Stratego.GameActions.NoVendettaAction;
import com.example.myapplication.Stratego.GameActions.StrategoButtonPieceAction;
import com.example.myapplication.Stratego.GameActions.StrategoForfeitAction;
import com.example.myapplication.Stratego.GameActions.StrategoMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoMuteAction;
import com.example.myapplication.Stratego.GameActions.StrategoPassAction;
import com.example.myapplication.Stratego.GameActions.StrategoRemoveVisibilityAction;
import com.example.myapplication.Stratego.GameActions.StrategoTransitionAction;
import com.example.myapplication.Stratego.GameState.Phase;
import com.example.myapplication.Stratego.GameState.Rank;
import com.example.myapplication.Stratego.GameState.StrategoGameState;
import com.example.myapplication.Stratego.GameState.Team;
import com.example.myapplication.Stratego.StrategoFrameworkClasses.StrategoSurfaceView;
import com.example.myapplication.notepadSurfaceView;
import com.example.myapplication.notepadActivity;

import java.util.ArrayList;

public class StrategoHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener{

    //tag for logging
    private static final String TAG = "StrategoHumanPlayer";

    //GUI text views
    private TextView whosTurnText;
    private TextView unitText;
    private TextView lastButtonText;
    private TextView helpScreenText;
    private TextView currentTimerText;


    //android current activity
    private GameMainActivity myActivity;

    //surface view
    private StrategoSurfaceView surfaceView;

    //music
    private MediaPlayer mediaPlayer;

    //buttons that activate actions
    private Button notepadButton;
    private Button forfeitButton;
    private Button helpButton;
    private Button muteButton;
    private Button endTurnButton;

    ArrayList<Button> strategoPieceButtons = new ArrayList<Button>();

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
     * StrategoHumanPlayer method
     *
     * @param name name of the stratego player
     */
    public StrategoHumanPlayer(String name) {
        super(name);
    }

    public View getTopView() {
        return myActivity.findViewById(R.id.StrategoInGameLayout);
    }


    /**
     * receiveInfo method
     * @param info contains the new game state
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void receiveInfo(GameInfo info) {

        //makes sure something was send
        if(info == null) return;

        //if move is illegal or if it's not your turn, flash red lights
        if(info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            surfaceView.flash(Color.RED, 50);
        }

        //set game state onto surface view
        if(info instanceof StrategoGameState) {

            if (surfaceView == null) return;
            Log.i("statemsg", "state set");
            surfaceView.setState((StrategoGameState) info);
            surfaceView.setSurfaceViewOwner(this.playerNum);

            //sets the timer to the appropriate time
            if(surfaceView.getSurfaceViewOwner() == Team.RED_TEAM.getTEAMNUMBER()) {
                currentTimerText.setText("Time Left:" + (surfaceView.getState().getRedTeamSeconds() / 60)
                        + ":" + (surfaceView.getState().getRedTeamSeconds() % 60));
            } else {
                currentTimerText.setText("Time Left:" + (surfaceView.getState().getBlueTeamSeconds() / 60)
                        + ":" + (surfaceView.getState().getBlueTeamSeconds() % 60));
            }

            //show unit text during set up phase
            if(surfaceView.getState().getCurrentPhase() == Phase.SETUP_PHASE) {
                this.setUnitText(surfaceView.getState());
            }

            // show enemy list during play phase
            else {
                if(!hasMoveablePieceLeft()) {
                    game.sendAction(new StrategoPassAction(this));
                }
                if(surfaceView.getState().getCurrentTeamsTurn().getTEAMNUMBER() == this.playerNum) {
                    this.setEnemyLeft(surfaceView.getState());
                    if(this.surfaceView.getState().getLastKilledPiece() != null) {
                        Log.i("smrtmsg", "removing vendetta");
                        game.sendAction(new NoVendettaAction(this));
                    }
                }
            }

            //show last tapped button on surface view
            this.setLastTappedButtonText(surfaceView.getState());
            this.setHelpScreenText(surfaceView.getState());
            this.setCurrentTeamsTurnText(surfaceView.getState());
            Log.i("statemsg", "" + this.playerNum + " invalidated");
            surfaceView.invalidate();

            if(surfaceView.getState().getVisiblePiece() != null) {
                this.game.sendAction(new StrategoRemoveVisibilityAction(this));
            }
        }
    }

    /**
     * method that checks if a player has movable pieces left
     * @return
     */
    public boolean hasMoveablePieceLeft() {
        //goes through every block of the board
        for(int i = 0; i < surfaceView.getState().getROWMAX(); i++) {
            for(int j=0; j<surfaceView.getState().getCOLMAX(); j++) {
                //checks if the piece is movable
                if(surfaceView.getState().getBoard()[i][j].canOneMoveThis(this.playerNum)) {
                    //checks if that movable piece can move anywhere currently
                    if(i != 0 && surfaceView.getState().getBoard()[i-1][j].canOneMoveHere(this.playerNum)) {
                        return true;
                    }
                    if(i != 9 && surfaceView.getState().getBoard()[i+1][j].canOneMoveHere(this.playerNum)) {
                        return true;
                    }
                    if(j != 0 && surfaceView.getState().getBoard()[i][j-1].canOneMoveHere(this.playerNum)) {
                        return true;
                    }
                    if(j != 9 && surfaceView.getState().getBoard()[i][j+1].canOneMoveHere(this.playerNum)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * method that implements the enemy List on the GUI
     * @param gsc the current state of the game
     */
    private void setUnitText(StrategoGameState gsc) {
        //writes the current amount of pieces that the player has left to place
        String unitsLeftToPlace = "Pieces left to Setup:\n";
        unitsLeftToPlace += Rank.ONE.getMaxAmountOfPieces() - gsc.calculateNumberOfPieces(Rank.ONE)
                + " x Marshals (1)\n";
        unitsLeftToPlace += Rank.TWO.getMaxAmountOfPieces() - gsc.calculateNumberOfPieces(Rank.TWO)
                + " x Generals (2)\n";
        unitsLeftToPlace += Rank.THREE.getMaxAmountOfPieces() - gsc.calculateNumberOfPieces(Rank.THREE)
                + " x Colonels (3)\n";
        unitsLeftToPlace += Rank.FOUR.getMaxAmountOfPieces() - gsc.calculateNumberOfPieces(Rank.FOUR)
                + " x Majors (4)\n";
        unitsLeftToPlace += Rank.FIVE.getMaxAmountOfPieces() - gsc.calculateNumberOfPieces(Rank.FIVE)
                + " x Captains (5)\n";
        unitsLeftToPlace += Rank.SIX.getMaxAmountOfPieces() - gsc.calculateNumberOfPieces(Rank.SIX)
                + " x Lieutenants(6)\n";
        unitsLeftToPlace += Rank.SEVEN.getMaxAmountOfPieces() - gsc.calculateNumberOfPieces(Rank.SEVEN)
                + " x Sergeants (7)\n";
        unitsLeftToPlace += Rank.EIGHT.getMaxAmountOfPieces() - gsc.calculateNumberOfPieces(Rank.EIGHT)
                + " x Miners (8)\n";
        unitsLeftToPlace += Rank.NINE.getMaxAmountOfPieces() - gsc.calculateNumberOfPieces(Rank.NINE)
                + " x Scouts (9)\n";
        unitsLeftToPlace += Rank.SPY.getMaxAmountOfPieces() - gsc.calculateNumberOfPieces(Rank.SPY)
                + " x Spies (S)\n";
        unitsLeftToPlace += Rank.BOMB.getMaxAmountOfPieces() - gsc.calculateNumberOfPieces(Rank.BOMB)
                + " x Bomb\n";
        unitsLeftToPlace += Rank.FLAG.getMaxAmountOfPieces() - gsc.calculateNumberOfPieces(Rank.FLAG)
                + " x Flag\n";
        unitText.setText(unitsLeftToPlace);
    }


    /**
     * methods that displays the amount of pieces the enemy still has
     * @param gsc the current state of the game
     */
    private void setEnemyLeft(StrategoGameState gsc) {
        //displays enemy pieces.
        String unitsLeftToPlace = "Enemy Pieces Left\n";
        unitsLeftToPlace += gsc.calculateNumberOfEnemyPieces(Rank.ONE) + " x Marshals (1)\n";
        unitsLeftToPlace += gsc.calculateNumberOfEnemyPieces(Rank.TWO) + " x Generals (2)\n";
        unitsLeftToPlace += gsc.calculateNumberOfEnemyPieces(Rank.THREE) + " x Colonels (3)\n";
        unitsLeftToPlace += gsc.calculateNumberOfEnemyPieces(Rank.FOUR) + " x Majors (4)\n";
        unitsLeftToPlace += gsc.calculateNumberOfEnemyPieces(Rank.FIVE) + " x Captains (5)\n";
        unitsLeftToPlace += gsc.calculateNumberOfEnemyPieces(Rank.SIX) + " x Lieutenants (6)\n";
        unitsLeftToPlace += gsc.calculateNumberOfEnemyPieces(Rank.SEVEN) + " x Sergeants (7)\n";
        unitsLeftToPlace += gsc.calculateNumberOfEnemyPieces(Rank.EIGHT) + " x Miners (8)\n";
        unitsLeftToPlace += gsc.calculateNumberOfEnemyPieces(Rank.NINE) + " x Scouts (9)\n";
        unitsLeftToPlace += gsc.calculateNumberOfEnemyPieces(Rank.SPY) + " x Spies (S)\n";
        unitsLeftToPlace += gsc.calculateNumberOfEnemyPieces(Rank.BOMB) + " x Bomb\n";
        unitsLeftToPlace += gsc.calculateNumberOfEnemyPieces(Rank.FLAG) + " x Flag\n";
        unitText.setText(unitsLeftToPlace);
    }

    /**
     * method that shows the button piece that the player last tapped
     * @param gsc current state of the game
     */
    private void setLastTappedButtonText(StrategoGameState gsc) {
        String buttonText = "Last Tapped Button: " + gsc.getLastTappedPieceButton();
        lastButtonText.setText(buttonText);
    }

    /**
     * method that displays which team is currently playing
     * @param gsc the current state of the game
     */
    private void setCurrentTeamsTurnText(StrategoGameState gsc){
        //shows who's turn it currently is
        if(gsc.getCurrentTeamsTurn() == Team.BLUE_TEAM){
            String buttonText = "It's Blue Teams Turn!";
            whosTurnText.setText(buttonText);
        }
        else if(gsc.getCurrentTeamsTurn() == Team.RED_TEAM){
            String buttonText = "It's Red Teams Turn!";
            whosTurnText.setText(buttonText);
        }
    }

    /**
     * displays the text for the help screen
     * @param gsc the current state of the game
     */
    private void setHelpScreenText(StrategoGameState gsc){
        String buttonText="Help Screen";
        if (gsc.getLastTappedPieceButton() == Rank.ONE){
            buttonText = "Marshall Button:\n " +
                    "Rank: 1 (Highest) \n" +
                    "If 1 attacks S, the S loses\n" +
                    "If S attacks 1, the 1 loses.\n";
        }
        else if(gsc.getLastTappedPieceButton() == Rank.TWO){
            buttonText = "General Button:\n" +
                    "Rank: 2 \n" +
                    "2 defeats 3, 4, 5, 6, 7, 8, 9, S.\n";
        }
        else if(gsc.getLastTappedPieceButton() == Rank.THREE){
            buttonText = "Colonel Button:\n" +
                    "Rank: 3\n" +
                    "3 defeats 4, 5, 6, 7, 8, 9, S.\n";
        }
        else if(gsc.getLastTappedPieceButton() == Rank.FOUR){
            buttonText = "Major Button:\n" +
                    "Rank: 4\n" +
                    "4 defeats 5, 6, 7, 8, 9, S.\n";
        }
        else if(gsc.getLastTappedPieceButton() == Rank.FIVE){
            buttonText = "Captain Button:\n" +
                    "Rank: 5\n" +
                    "5 defeats 6, 7, 8, 9, S.\n";
        }
        else if(gsc.getLastTappedPieceButton() == Rank.SIX){
            buttonText = "Lieutenant Button:\n" +
                    "Rank: 6\n" +
                    "6 defeats 7, 8, 9, S.\n";
        }
        else if(gsc.getLastTappedPieceButton() == Rank.SEVEN){
            buttonText = "Sergeant Button:\n" +
                    "Rank: 7\n" +
                    "7 defeats 8, 9, S.\n";
        }
        else if(gsc.getLastTappedPieceButton() == Rank.EIGHT){
            buttonText = "Miner Button:\n" +
                    "Rank: 8\n" +
                    "8 defeats 9 and the bomb.\n";
        }
        else if(gsc.getLastTappedPieceButton() == Rank.NINE){
            buttonText = "Scout Button:\n" +
                    "Rank: 9 (Lowest)\n" +
                    "9 loses to every piece.\n";
        }
        else if(gsc.getLastTappedPieceButton() == Rank.SPY){
            buttonText = "Spy Button:\n" +
                    "Rank: S\n" +
                    "If 1 attacks S, the S loses\n" +
                    "If S attacks 1, the 1 loses.\n";
        }
        else if(gsc.getLastTappedPieceButton() == Rank.BOMB){
            buttonText = "Bomb Button:\n" +
                    "Rank: B\n" +
                    "B defeats every piece except 8.\n ";
        }
        else if(gsc.getLastTappedPieceButton() == Rank.FLAG){
            buttonText = "Flag Button:\n" +
                    "Rank: F\n" +
                    "Flag loses every other piece.\n" +
                    "Capture your opponent's flag!\n";
        }
        helpScreenText.setText(buttonText);
    }

    /**
     * method thats sends an appropriate action when button is clicked
     * @param v the view that was clicked on
     */
    @Override
    public void onClick(View v) {
        /*
        //TODO WORK ON GETTING THIS TO WORK
        for(int i = 0; i < strategoPieceButtons.size(); i++) {
            if(v.getId() == strategoPieceButtons.get(i).getId()) {
                setWhiteButtons(v);
                strategoPieceButtons.get(i).setBackgroundColor(Color.GREEN);
                this.game.sendAction(new StrategoButtonPieceAction(this, strategoPieceButtons.get(i)));
            }
        }
         */
        //action type - send action
        switch (v.getId()){
            case R.id.marshallButton:
                setWhiteButtons(v);
                marshallButton.setBackgroundColor(Color.GREEN);
                StrategoButtonPieceAction marshallButtonAction =
                        new StrategoButtonPieceAction(this, Rank.ONE);
                this.game.sendAction(marshallButtonAction);
                break;
            case R.id.generalButton:
                setWhiteButtons(v);
                generalButton.setBackgroundColor(Color.GREEN);
                StrategoButtonPieceAction generalButtonAction =
                        new StrategoButtonPieceAction(this, Rank.TWO);
                this.game.sendAction(generalButtonAction);
                break;
            case R.id.colonelButton:
                setWhiteButtons(v);
                colonelButton.setBackgroundColor(Color.GREEN);
                StrategoButtonPieceAction colonelButtonAction =
                        new StrategoButtonPieceAction(this, Rank.THREE);
                this.game.sendAction(colonelButtonAction);
                break;
            case R.id.majorButton:
                setWhiteButtons(v);
                majorButton.setBackgroundColor(Color.GREEN);
                StrategoButtonPieceAction majorButtonAction =
                        new StrategoButtonPieceAction(this, Rank.FOUR);
                this.game.sendAction(majorButtonAction);
                break;
            case R.id.captainButton:
                setWhiteButtons(v);
                captainButton.setBackgroundColor(Color.GREEN);
                StrategoButtonPieceAction captainButtonAction =
                        new StrategoButtonPieceAction(this, Rank.FIVE);
                this.game.sendAction(captainButtonAction);
                break;
            case R.id.lieutenantButton:
                setWhiteButtons(v);
                lieutenantButton.setBackgroundColor(Color.GREEN);
                StrategoButtonPieceAction lieutenantButtonAction =
                        new StrategoButtonPieceAction(this, Rank.SIX);
                this.game.sendAction(lieutenantButtonAction);
                break;
            case R.id.sergeantButton:
                setWhiteButtons(v);
                sergeantButton.setBackgroundColor(Color.GREEN);
                StrategoButtonPieceAction sergeantButtonAction =
                        new StrategoButtonPieceAction(this, Rank.SEVEN);
                this.game.sendAction(sergeantButtonAction);
                break;
            case R.id.minerButton:
                setWhiteButtons(v);
                minerButton.setBackgroundColor(Color.GREEN);
                StrategoButtonPieceAction minerButtonAction =
                        new StrategoButtonPieceAction(this, Rank.EIGHT);
                this.game.sendAction(minerButtonAction);
                break;
            case R.id.scoutButton:
                setWhiteButtons(v);
                scoutButton.setBackgroundColor(Color.GREEN);
                StrategoButtonPieceAction scoutButtonAction =
                        new StrategoButtonPieceAction(this, Rank.NINE);
                this.game.sendAction(scoutButtonAction);
                break;
            case R.id.spyButton:
                setWhiteButtons(v);
                spyButton.setBackgroundColor(Color.GREEN);
                StrategoButtonPieceAction spyButtonAction =
                        new StrategoButtonPieceAction(this, Rank.SPY);
                this.game.sendAction(spyButtonAction);
                break;
            case R.id.flagButton:
                setWhiteButtons(v);
                flagButton.setBackgroundColor(Color.GREEN);
                StrategoButtonPieceAction flagButtonAction =
                        new StrategoButtonPieceAction(this, Rank.FLAG);
                this.game.sendAction(flagButtonAction);
                break;
            case R.id.bombButton:
                setWhiteButtons(v);
                bombButton.setBackgroundColor(Color.GREEN);
                StrategoButtonPieceAction bombButtonAction =
                        new StrategoButtonPieceAction(this, Rank.BOMB);
                this.game.sendAction(bombButtonAction);
                break;
            case R.id.forfeitButton:
                StrategoForfeitAction forfeitAction = new StrategoForfeitAction(this);
                this.game.sendAction(forfeitAction);
                break;
            case R.id.muteButton:
                StrategoMuteAction muteAction = new StrategoMuteAction(this);
                this.game.sendAction(muteAction);
                //muteGame();
                break;
            case R.id.infoButton:
                Intent intent0 = new Intent(this.myActivity, HowToPlay.class);
                helpButton.getContext().startActivity(intent0);
                break;
            case R.id.notepadButton:
                Intent intent1 = new Intent(this.myActivity, notepadActivity.class);
                notepadButton.getContext().startActivity(intent1);
                break;
            case R.id.endTurnButton:
                if(this.playerNum == this.surfaceView.getState().getCurrentTeamsTurn().getTEAMNUMBER()) {
                    endTurnButton.setVisibility(View.INVISIBLE);

                    StrategoTransitionAction transitionAction =
                            new StrategoTransitionAction(this);
                    this.game.sendAction(transitionAction);
                }
                break;
                default:
                    setWhiteButtons(v);
                    break;
        }
        /**
         * External Citation
         * Problem: Could not fix mute button
         * Date: 3 December 2019
         * Source: https://stackoverflow.com/questions/9461056/android-media-player-play-pause-button
         * Solution: Added if statements using isPlaying()
         */
        if(v == muteButton){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            } else{
                mediaPlayer.start();
            }

        }
    }
        /**
         * changes color of all buttons to white when called
         * @author Jordan Ho
         * @param v
         */
    public void setWhiteButtons(View v){
        marshallButton.setBackgroundColor(Color.WHITE);
        generalButton.setBackgroundColor(Color.WHITE);
        colonelButton.setBackgroundColor(Color.WHITE);
        majorButton.setBackgroundColor(Color.WHITE);
        captainButton.setBackgroundColor(Color.WHITE);
        lieutenantButton.setBackgroundColor(Color.WHITE);
        sergeantButton.setBackgroundColor(Color.WHITE);
        minerButton.setBackgroundColor(Color.WHITE);
        scoutButton.setBackgroundColor(Color.WHITE);
        spyButton.setBackgroundColor(Color.WHITE);
        bombButton.setBackgroundColor(Color.WHITE);
        flagButton.setBackgroundColor(Color.WHITE);
    }

    /**
     * setAsGui sets the current player as the activity's GUI
     * @param activity the current activity being run
     */
    public void setAsGui(GameMainActivity activity) {

        //remember the activity
        myActivity = activity;

        //loads the layout for stratego GUI
        activity.setContentView(R.layout.stratego_board);


        //starts the music

        mediaPlayer = MediaPlayer.create(activity.getApplicationContext(), R.raw.stratego);
        mediaPlayer.start();


        //makes sure the surface view works correctly
        surfaceView = activity.findViewById(R.id.boardImageView);
        Log.i("playermsg", "" + this.playerNum);
        surfaceView.setSurfaceViewOwner(this.playerNum);
        surfaceView.setOnTouchListener(this);

        //sets the text views to their proper variables
        whosTurnText = (TextView)activity.findViewById(R.id.turnText);
        unitText = (TextView)activity.findViewById(R.id.unitTextView);
        lastButtonText = (TextView)activity.findViewById(R.id.lastTappedButtonText);
        helpScreenText = (TextView)activity.findViewById(R.id.helpScreenText);
        currentTimerText = (TextView)activity.findViewById(R.id.currentPlayerTimer);

        strategoPieceButtons.add((Button)activity.findViewById(R.id.marshallButton));
        strategoPieceButtons.add((Button)activity.findViewById(R.id.generalButton));
        strategoPieceButtons.add((Button)activity.findViewById(R.id.colonelButton));
        strategoPieceButtons.add((Button)activity.findViewById(R.id.majorButton));
        strategoPieceButtons.add((Button)activity.findViewById(R.id.captainButton));
        strategoPieceButtons.add((Button)activity.findViewById(R.id.lieutenantButton));
        strategoPieceButtons.add((Button)activity.findViewById(R.id.sergeantButton));
        strategoPieceButtons.add((Button)activity.findViewById(R.id.minerButton));
        strategoPieceButtons.add((Button)activity.findViewById(R.id.scoutButton));
        strategoPieceButtons.add((Button)activity.findViewById(R.id.spyButton));
        strategoPieceButtons.add((Button)activity.findViewById(R.id.bombButton));
        strategoPieceButtons.add((Button)activity.findViewById(R.id.flagButton));
        for(int i = 0; i < strategoPieceButtons.size(); i++) {
            strategoPieceButtons.get(i).setOnClickListener(this);
        }

        //piece buttons
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
        helpButton = (Button)activity.findViewById(R.id.infoButton);
        helpButton.setOnClickListener(this);
        muteButton = (Button)activity.findViewById(R.id.muteButton);
        muteButton.setOnClickListener(this);
        forfeitButton = (Button)activity.findViewById(R.id.forfeitButton);
        forfeitButton.setOnClickListener(this);
        notepadButton = (Button)activity.findViewById(R.id.notepadButton);
        notepadButton.setOnClickListener(this);
        endTurnButton = (Button)activity.findViewById(R.id.endTurnButton);
        endTurnButton.setOnClickListener(this);



    }


    /**
     * method that handles touch events
     * @param v the view that was touched
     * @param event the type of touch that happened
     * @return true when touch is handled fully
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //keeps single touches from being read again
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //finds out where on the board is being tapped
            int row = (int) (event.getY()) * 10 / v.getHeight();
            int col = (int) (event.getX()) * 10 / v.getWidth();

            //makes an appropriate action and sends it
            StrategoMoveAction moveCommand = new StrategoMoveAction(this, row, col);
            this.game.sendAction(moveCommand);
        }

        return true;
    }//onTouch

}



