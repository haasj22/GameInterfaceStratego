package com.example.myapplication.Stratego.Player;
/**
 *
 */

import android.content.Intent;
import android.graphics.Canvas;
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
import com.example.myapplication.Stratego.GameActions.StrategoButtonPieceAction;
import com.example.myapplication.Stratego.GameActions.StrategoForfeitAction;
import com.example.myapplication.Stratego.GameActions.StrategoMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoMuteAction;
import com.example.myapplication.Stratego.GameActions.StrategoTransitionAction;
import com.example.myapplication.Stratego.GameState.Phase;
import com.example.myapplication.Stratego.GameState.Piece;
import com.example.myapplication.Stratego.GameState.Rank;
import com.example.myapplication.Stratego.GameState.StrategoGameState;
import com.example.myapplication.Stratego.GameState.Team;
import com.example.myapplication.Stratego.StrategoFrameworkClasses.StrategoSurfaceView;
import com.example.myapplication.notepadSurfaceView;
import com.example.myapplication.notepadActivity;

/**
 * TODO: check to see if player can make valid move and if not skip turn, going to be implemented in on
 * handles GUI and all actions for humanPlayer
**/

public class StrategoHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener{

    //tag for logging
    private static final String TAG = "StrategoHumanPlayer";

    notepadSurfaceView notepadSurfaceView;
    HowToPlay howToPlay;

    //GUI text views
    private TextView whosTurnText;
    private TextView unitText;
    private TextView lastButtonText;
    private TextView helpScreenText;

    // state object
    private StrategoGameState state;


    //android current activity
    private GameMainActivity myActivity;

    //surface view
    private StrategoSurfaceView surfaceView;



    //music
    MediaPlayer mediaPlayer;


    //buttons and or image buttons for other actions
    private Button notepadButton;
    private Button startButton;
    private Button forfeitButton;
    private Button helpButton;
    private Button muteButton;
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

        //if move is illegal or if it's not your turn, flash red lights
        if(info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            surfaceView.flash(Color.RED, 50);
        }

        //set game state onto surface view
        if(info instanceof StrategoGameState) {
            if (surfaceView == null) return;
            surfaceView.setState((StrategoGameState) info);
            Log.i("setupmsg", "surface view invalidated");

            //show unit text during set up phase
            if(surfaceView.getState().getCurrentPhase() == Phase.SETUP_PHASE) {
                this.setUnitText(surfaceView.getState());
            }
            // show enemy list during play phase
            else {
                this.setEnemyLeft(surfaceView.getState());
            }
            //show last tapped button on surface view
            this.setLastTappedButtonText(surfaceView.getState());
            surfaceView.invalidate();

            this.setHelpScreenText(surfaceView.getState());
            surfaceView.invalidate();

            this.setCurrentTeamsTurnText(surfaceView.getState());
            surfaceView.invalidate();

        }
    }

    /**
     * setUnitText method
     * implementing the enemy List on the GUI
     * @param gsc
     */
    public void setUnitText(StrategoGameState gsc) {
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
     * setEnemyLeft method
     * set how many enemy pieces are left
     * @param gsc
     */
    public void setEnemyLeft(StrategoGameState gsc) {
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
     * setLastTappedButtonText method
     * shows player last tapped Button because game works on taps
     * @param gsc
     */
    public void setLastTappedButtonText(StrategoGameState gsc) {
        String buttonText = "Last Tapped Button: " + gsc.getLastTappedPieceButton();
        lastButtonText.setText(buttonText);
    }

    public void setCurrentTeamsTurnText(StrategoGameState gsc){
        if(gsc.getCurrentTeamsTurn() == Team.BLUE_TEAM){
            String buttonText = "It's Blue Teams Turn!";
            whosTurnText.setText(buttonText);
        }
        else if(gsc.getCurrentTeamsTurn() == Team.RED_TEAM){
            String buttonText = "It's Red Teams Turn!";
            whosTurnText.setText(buttonText);
        }
    }

    public void setHelpScreenText(StrategoGameState gsc){
        if (gsc.getLastTappedPieceButton() == Rank.ONE){
            String buttonText = "Marshall Button:\n " +
                    "Rank: 1 (Highest) \n" +
                    "If 1 attacks S, the S loses\n" +
                    "If S attacks 1, the 1 loses.\n";
            helpScreenText.setText(buttonText);
        }
        else if(gsc.getLastTappedPieceButton() == Rank.TWO){
            String buttonText = "General Button:\n" +
                    "Rank: 2 \n" +
                    "2 defeats 3, 4, 5, 6, 7, 8, 9.\n";
            helpScreenText.setText(buttonText);
        }
        else if(gsc.getLastTappedPieceButton() == Rank.THREE){
            String buttonText = "Colonel Button:\n" +
                    "Rank: 3\n" +
                    "3 defeats 4, 5, 6, 7, 8, 9.\n";
            helpScreenText.setText(buttonText);
        }
        else if(gsc.getLastTappedPieceButton() == Rank.FOUR){
            String buttonText = "Major Button:\n" +
                    "Rank: 4\n" +
                    "4 defeats 5, 6, 7, 8, 9.\n";
            helpScreenText.setText(buttonText);
        }
        else if(gsc.getLastTappedPieceButton() == Rank.FIVE){
            String buttonText = "Captain Button:\n" +
                    "Rank: 5\n" +
                    "5 defeats 6, 7, 8, 9.\n";
            helpScreenText.setText(buttonText);
        }
        else if(gsc.getLastTappedPieceButton() == Rank.SIX){
            String buttonText = "Lieutenant Button:\n" +
                    "Rank: 6\n" +
                    "6 defeats 7, 8, 9.\n";
            helpScreenText.setText(buttonText);
        }
        else if(gsc.getLastTappedPieceButton() == Rank.SEVEN){
            String buttonText = "Sergeant Button:\n" +
                    "Rank: 7\n" +
                    "7 defeats 8, 9.\n";
            helpScreenText.setText(buttonText);
        }
        else if(gsc.getLastTappedPieceButton() == Rank.EIGHT){
            String buttonText = "Miner Button:\n" +
                    "Rank: 8\n" +
                    "8 defeats 9 and the bomb.\n";
            helpScreenText.setText(buttonText);
        }
        else if(gsc.getLastTappedPieceButton() == Rank.NINE){
            String buttonText = "Scout Button:\n" +
                    "Rank: 9 (Lowest)\n" +
                    "9 loses to every piece.\n";
            helpScreenText.setText(buttonText);
        }
        else if(gsc.getLastTappedPieceButton() == Rank.SPY){
            String buttonText = "Spy Button:\n" +
                    "Rank: S\n" +
                    "If 1 attacks S, the S loses\n" +
                    "If S attacks 1, the 1 loses.\n";
            helpScreenText.setText(buttonText);
        }
        else if(gsc.getLastTappedPieceButton() == Rank.BOMB){
            String buttonText = "Bomb Button:\n" +
                    "Rank: B\n" +
                    "B defeats every piece except 8.\n ";
            helpScreenText.setText(buttonText);
        }
        else if(gsc.getLastTappedPieceButton() == Rank.FLAG){
            String buttonText = "Flag Button:\n" +
                    "Rank: F\n" +
                    "Flag loses every other piece.\n" +
                    "Capture your opponent's flag!\n";
            helpScreenText.setText(buttonText);
        }
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        int row;
        int col;

        //action type - send action
        switch (v.getId()){
            case R.id.marshallButton:
                StrategoButtonPieceAction marshallButtonAction = new StrategoButtonPieceAction(this, Rank.ONE);
                this.game.sendAction(marshallButtonAction);
                break;
            case R.id.generalButton:
                StrategoButtonPieceAction generalButtonAction = new StrategoButtonPieceAction(this, Rank.TWO);
                this.game.sendAction(generalButtonAction);
                break;
            case R.id.colonelButton:
                StrategoButtonPieceAction colonelButtonAction = new StrategoButtonPieceAction(this, Rank.THREE);
                this.game.sendAction(colonelButtonAction);
                break;
            case R.id.majorButton:
                StrategoButtonPieceAction majorButtonAction = new StrategoButtonPieceAction(this, Rank.FOUR);
                this.game.sendAction(majorButtonAction);
                break;
            case R.id.captainButton:
                StrategoButtonPieceAction captainButtonAction = new StrategoButtonPieceAction(this, Rank.FIVE);
                this.game.sendAction(captainButtonAction);
                break;
            case R.id.lieutenantButton:
                StrategoButtonPieceAction lieutenantButtonAction = new StrategoButtonPieceAction(this, Rank.SIX);
                this.game.sendAction(lieutenantButtonAction);
                break;
            case R.id.sergeantButton:
                StrategoButtonPieceAction sergeantButtonAction = new StrategoButtonPieceAction(this, Rank.SEVEN);
                this.game.sendAction(sergeantButtonAction);
                break;
            case R.id.minerButton:
                StrategoButtonPieceAction minerButtonAction = new StrategoButtonPieceAction(this, Rank.EIGHT);
                this.game.sendAction(minerButtonAction);
                break;
            case R.id.scoutButton:
                StrategoButtonPieceAction scoutButtonAction = new StrategoButtonPieceAction(this, Rank.NINE);
                this.game.sendAction(scoutButtonAction);
                break;
            case R.id.spyButton:
                StrategoButtonPieceAction spyButtonAction = new StrategoButtonPieceAction(this, Rank.SPY);
                this.game.sendAction(spyButtonAction);
                break;
            case R.id.flagButton:
                StrategoButtonPieceAction flagButtonAction = new StrategoButtonPieceAction(this, Rank.FLAG);
                this.game.sendAction(flagButtonAction);
                break;
            case R.id.bombButton:
                StrategoButtonPieceAction bombButtonAction = new StrategoButtonPieceAction(this, Rank.BOMB);
                this.game.sendAction(bombButtonAction);
                break;
            case R.id.forfeitButton:
                StrategoForfeitAction forfeitAction = new StrategoForfeitAction(this);
                this.game.sendAction(forfeitAction);
                break;
            case R.id.muteButton:
                StrategoMuteAction muteAction = new StrategoMuteAction(this);
                this.game.sendAction(muteAction);
                if(mediaPlayer.isPlaying()){
                mediaPlayer.start();
                }
                else {
                    mediaPlayer.stop();
                }
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
                endTurnButton.setVisibility(View.INVISIBLE);
                StrategoTransitionAction transitionAction =
                        new StrategoTransitionAction(this);
                this.game.sendAction(transitionAction);
                break;
                default:
                    break;
        }
        /**
         * the color of the buttons change depending what is tapped on
         */
        if(v == marshallButton){
            marshallButton.setBackgroundColor(Color.GREEN);
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
        else if(v == generalButton){
            marshallButton.setBackgroundColor(Color.WHITE);
            generalButton.setBackgroundColor(Color.GREEN);
            if(Rank.TWO.getMaxAmountOfPieces() == 0){
                generalButton.setBackgroundColor(Color.BLUE);
            }
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
        else if(v == colonelButton){
            marshallButton.setBackgroundColor(Color.WHITE);
            generalButton.setBackgroundColor(Color.WHITE);
            colonelButton.setBackgroundColor(Color.GREEN);
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
        else if(v == majorButton){
            marshallButton.setBackgroundColor(Color.WHITE);
            generalButton.setBackgroundColor(Color.WHITE);
            colonelButton.setBackgroundColor(Color.WHITE);
            majorButton.setBackgroundColor(Color.GREEN);
            captainButton.setBackgroundColor(Color.WHITE);
            lieutenantButton.setBackgroundColor(Color.WHITE);
            sergeantButton.setBackgroundColor(Color.WHITE);
            minerButton.setBackgroundColor(Color.WHITE);
            scoutButton.setBackgroundColor(Color.WHITE);
            spyButton.setBackgroundColor(Color.WHITE);
            bombButton.setBackgroundColor(Color.WHITE);
            flagButton.setBackgroundColor(Color.WHITE);
        }
        else if(v == captainButton){
            marshallButton.setBackgroundColor(Color.WHITE);
            generalButton.setBackgroundColor(Color.WHITE);
            colonelButton.setBackgroundColor(Color.WHITE);
            majorButton.setBackgroundColor(Color.WHITE);
            captainButton.setBackgroundColor(Color.GREEN);
            lieutenantButton.setBackgroundColor(Color.WHITE);
            sergeantButton.setBackgroundColor(Color.WHITE);
            minerButton.setBackgroundColor(Color.WHITE);
            scoutButton.setBackgroundColor(Color.WHITE);
            spyButton.setBackgroundColor(Color.WHITE);
            bombButton.setBackgroundColor(Color.WHITE);
            flagButton.setBackgroundColor(Color.WHITE);
        }
        else if(v == lieutenantButton){
            marshallButton.setBackgroundColor(Color.WHITE);
            generalButton.setBackgroundColor(Color.WHITE);
            colonelButton.setBackgroundColor(Color.WHITE);
            majorButton.setBackgroundColor(Color.WHITE);
            captainButton.setBackgroundColor(Color.WHITE);
            lieutenantButton.setBackgroundColor(Color.GREEN);
            sergeantButton.setBackgroundColor(Color.WHITE);
            minerButton.setBackgroundColor(Color.WHITE);
            scoutButton.setBackgroundColor(Color.WHITE);
            spyButton.setBackgroundColor(Color.WHITE);
            bombButton.setBackgroundColor(Color.WHITE);
            flagButton.setBackgroundColor(Color.WHITE);
        }
        else if(v == sergeantButton){
            marshallButton.setBackgroundColor(Color.WHITE);
            generalButton.setBackgroundColor(Color.WHITE);
            colonelButton.setBackgroundColor(Color.WHITE);
            majorButton.setBackgroundColor(Color.WHITE);
            captainButton.setBackgroundColor(Color.WHITE);
            lieutenantButton.setBackgroundColor(Color.WHITE);
            sergeantButton.setBackgroundColor(Color.GREEN);
            minerButton.setBackgroundColor(Color.WHITE);
            scoutButton.setBackgroundColor(Color.WHITE);
            spyButton.setBackgroundColor(Color.WHITE);
            bombButton.setBackgroundColor(Color.WHITE);
            flagButton.setBackgroundColor(Color.WHITE);
        }
        else if(v == minerButton){
            marshallButton.setBackgroundColor(Color.WHITE);
            generalButton.setBackgroundColor(Color.WHITE);
            colonelButton.setBackgroundColor(Color.WHITE);
            majorButton.setBackgroundColor(Color.WHITE);
            captainButton.setBackgroundColor(Color.WHITE);
            lieutenantButton.setBackgroundColor(Color.WHITE);
            sergeantButton.setBackgroundColor(Color.WHITE);
            minerButton.setBackgroundColor(Color.GREEN);
            scoutButton.setBackgroundColor(Color.WHITE);
            spyButton.setBackgroundColor(Color.WHITE);
            bombButton.setBackgroundColor(Color.WHITE);
            flagButton.setBackgroundColor(Color.WHITE);
        }
        else if(v == scoutButton){
            marshallButton.setBackgroundColor(Color.WHITE);
            generalButton.setBackgroundColor(Color.WHITE);
            colonelButton.setBackgroundColor(Color.WHITE);
            majorButton.setBackgroundColor(Color.WHITE);
            captainButton.setBackgroundColor(Color.WHITE);
            lieutenantButton.setBackgroundColor(Color.WHITE);
            sergeantButton.setBackgroundColor(Color.WHITE);
            minerButton.setBackgroundColor(Color.WHITE);
            scoutButton.setBackgroundColor(Color.GREEN);
            spyButton.setBackgroundColor(Color.WHITE);
            bombButton.setBackgroundColor(Color.WHITE);
            flagButton.setBackgroundColor(Color.WHITE);
        }
        else if(v == spyButton){
            marshallButton.setBackgroundColor(Color.WHITE);
            generalButton.setBackgroundColor(Color.WHITE);
            colonelButton.setBackgroundColor(Color.WHITE);
            majorButton.setBackgroundColor(Color.WHITE);
            captainButton.setBackgroundColor(Color.WHITE);
            lieutenantButton.setBackgroundColor(Color.WHITE);
            sergeantButton.setBackgroundColor(Color.WHITE);
            minerButton.setBackgroundColor(Color.WHITE);
            scoutButton.setBackgroundColor(Color.WHITE);
            spyButton.setBackgroundColor(Color.GREEN);
            bombButton.setBackgroundColor(Color.WHITE);
            flagButton.setBackgroundColor(Color.WHITE);
        }
        else if(v == bombButton){
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
            bombButton.setBackgroundColor(Color.GREEN);
            flagButton.setBackgroundColor(Color.WHITE);
        }
        else if(v == flagButton){
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
            flagButton.setBackgroundColor(Color.GREEN);
        }




    }


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

        surfaceView = activity.findViewById(R.id.boardImageView);
        surfaceView.setSurfaceViewOwner(this.playerNum);
        surfaceView.setOnTouchListener(this);

        whosTurnText = (TextView)activity.findViewById(R.id.turnText);

       unitText = (TextView)activity.findViewById(R.id.unitTextView);
       lastButtonText = (TextView)activity.findViewById(R.id.lastTappedButtonText);
       helpScreenText = (TextView)activity.findViewById(R.id.helpScreenText);

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
     * stopPlaying Method
     * stops music from playing when mute button is tapped
     */
    public void stopPlaying(){
        mediaPlayer.stop();
    }

    //TODO: mute Button must call stopPlaying()


    /**
     * onTouch method
     * @param v
     * @param event
     * @return
     */
    @Override
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



