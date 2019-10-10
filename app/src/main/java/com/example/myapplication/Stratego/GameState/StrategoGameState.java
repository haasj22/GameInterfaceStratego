package com.example.myapplication.Stratego.GameState;

/**
 * The State of the game.
 * Includes values for pieces,
 *
 * @author Kavya Mandla
 * @author John Haas
 * @author Jordan Ho
 */

public class StrategoGameState {

    //max number of rows and cols in board
    static final int COLMAX = 10;
    static final int ROWMAX = 10;

    //amount of pieces
    static final int numOfMarshalls = 1;
    static final int numOfGenerals = 1;
    static final int numOfColonels = 2;
    static final int numOfMajors = 3;
    static final int numOfCaptains = 4;
    static final int numOfLietenants = 4;
    static final int numOfSergeants = 4;
    static final int numOfMiners = 5;
    static final int numOfScouts = 8;
    static final int numOfSpy = 1;
    static final int numOfBombs = 6;
    static final int numOfFlags = 1;

    private Block[][] board = new Block[10][10];

    //ids of the players
    private int playerOneID;
    private int playerOneTimer; //in milliseconds

    //variables that will store the amount of pieces player One has
    private int playerOneMarshalls;
    private int playerOneGenerals;
    private int playerOneColonels;
    private int playerOneMajors;
    private int playerOneCaptains;
    private int playerOneLietenants;
    private int playerOneSergeants;
    private int playerOneMiners;
    private int playerOneScouts;
    private int playerOneSpy;
    private int playerOneBombs;
    private boolean playerOneHasFlag;

    private int playerTwoID;
    private int playerTwoTimer; //in milliseconds

    //variables that will store the amount of pieces player Two has
    private int playerTwoMarshalls;
    private int playerTwoGenerals;
    private int playerTwoColonels;
    private int playerTwoMajors;
    private int playerTwoCaptains;
    private int playerTwoLietenants;
    private int playerTwoSergeants;
    private int playerTwoMiners;
    private int playerTwoScouts;
    private int playerTwoSpy;
    private int playerTwoBombs;
    private boolean playerTwoHasFlag;

    private Phase currentPhase;

    //id of the player whose turn it is
    private int currentPlayer;

    //checks if game is won
    private boolean gameWon = false;


    /**
     * Constructor for objects of class StrategoGameState
     */
    public StrategoGameState(){
        playerOneID = 1;
        playerOneTimer = 3000;

        playerOneMarshalls = 0;
        playerOneGenerals = 0;
        playerOneColonels = 0;
        playerOneMajors = 0;
        playerOneCaptains = 0;
        playerOneLietenants = 0;
        playerOneSergeants = 0;
        playerOneMiners = 0;
        playerOneScouts = 0;
        playerOneSpy = 0;
        playerOneBombs = 0;
        playerOneHasFlag = false;

        playerTwoID = 2;
        playerTwoTimer = 0;

        playerTwoMarshalls = 0;
        playerTwoGenerals = 0;
        playerTwoColonels = 0;
        playerTwoMajors = 0;
        playerTwoCaptains = 0;
        playerTwoLietenants = 0;
        playerTwoSergeants = 0;
        playerTwoMiners = 0;
        playerTwoScouts = 0;
        playerTwoSpy = 0;
        playerTwoBombs = 0;
        playerTwoHasFlag = false;


        currentPhase = Phase.SETUP_PHASE;

        currentPlayer = 1;

        //creates basic block
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(i != 4 || i !=5) {
                    board[i][j] = new Block(Tile.GRASS);
                    break;
                }
                if(j != 2 || j != 3 || j != 6 || j != 7) {
                    board[i][j] = new Block(Tile.WATER);
                    break;
                }
                board[i][j] = new Block(Tile.BRIDGE);
            }
        }

        gameWon = false;
    }

    /**
     * Deep Copy Constructor
     *
     * @param state the one true state of the game that would be copied.
     */
    public StrategoGameState(StrategoGameState state){

        this.playerOneID = state.playerOneID;
        this.playerOneTimer = state.playerOneTimer;

        this.playerOneMarshalls = state.playerOneMarshalls;
        this.playerOneGenerals = state.playerOneGenerals;
        this.playerOneColonels = state.playerOneColonels;
        this.playerOneMajors = state.playerOneMajors;
        this.playerOneCaptains = state.playerOneCaptains;
        this.playerOneLietenants = state.playerOneLietenants;
        this.playerOneSergeants = state.playerOneSergeants;
        this.playerOneMiners = state.playerOneMiners;
        this.playerOneScouts = state.playerOneScouts;
        this.playerOneSpy = state.playerOneSpy ;
        this.playerOneBombs = state.playerOneBombs;
        this.playerOneHasFlag = state.playerOneHasFlag;

        this.playerTwoID = state.playerTwoID;
        this.playerTwoTimer = state.playerTwoTimer;

       this.playerTwoMarshalls = state.playerTwoMarshalls;
       this.playerTwoGenerals = state.playerTwoGenerals;
       this.playerTwoColonels = state.playerTwoColonels;
       this.playerTwoMajors = state.playerTwoMajors;
       this.playerTwoCaptains = state.playerTwoCaptains;
       this.playerTwoLietenants = state.playerTwoLietenants;
       this.playerTwoSergeants = state.playerTwoSergeants;
       this.playerTwoMiners = state.playerTwoMiners;
       this.playerTwoScouts = state.playerTwoScouts;
       this.playerTwoSpy = state.playerTwoSpy;
       this.playerTwoBombs = state.playerTwoBombs;
       this.playerTwoHasFlag = state.playerTwoHasFlag;

        this.currentPhase = state.currentPhase;

        this.currentPlayer = state.currentPlayer;

        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                this.board[i][j] = state.board[i][j];
            }
        }

    }

    @Override
    public String toString(){
        String toReturn = "\nStratego Game State:\n";

        toReturn += "[Player One ID: " + playerOneID + "]\n";
        toReturn += "[Player One Timer: " + playerOneTimer + "]\n";

        toReturn += "[Player Two ID: " + playerTwoID + "]\n";
        toReturn += "[Player Two Timer: " + playerTwoTimer + "]\n";

        toReturn += "[Current Phase: " + currentPhase + "]\n";

        if(currentPlayer == 1){
            toReturn += "Player One's Turn\n";
        }
        else{
            toReturn += "Player Two's turn\n";
        }

        toReturn += "------------------------\n";
        for(int i=0; i<ROWMAX; i++) {
            for(int j=0; j<COLMAX; j++) {
                toReturn += "[Block " + (i+1) + ":" + (j+1) + "]\n";
                toReturn += board[i][j];
            }
        }
        toReturn += "------------------------\n";

        if(gameWon == true){
            toReturn += "Game is over\n";
        }
        else{
            toReturn += "Game is not over\n";
        }

        return toReturn;
    }

    //set up variables?
    //allows players to set pieces on the board if its SET_UP phase
    public boolean setPiece(int x, int y) {
        if(x >= ROWMAX || x < 0 ) {
            return false;
        }
        else if ( y >= COLMAX || y < 0 ) {
            return false;
        }
        //use own phase?
        else if (this.currentPhase != Phase.SETUP_PHASE) {
            return false;
        }


        return true;
    }

    //transitions from set up to PLAY_PHASE
    public boolean transitionPhases() {
        if(this.currentPhase == Phase.SETUP_PHASE) {
            this.currentPhase = Phase.PLAY_PHASE;
            return true;
        }
        return false;
    }

    //allows the player to move their piece
    public boolean movePiece(int x1, int x2, int y1, int y2) {
        if(this.currentPhase != Phase.PLAY_PHASE) {
            return false;
        }
        return true;
    }

    //helper method for movePiece
    public boolean attackPiece(Piece attacker, Piece defender) {
        return false;
    }

    //checks if the game is over
    public boolean isGameOver() {

        return false;
    }

    //How do I do this
    public boolean receiveUpdatedInfo(StrategoGameState newGameState) {
        return false;
    }

    //timer maybe

}
