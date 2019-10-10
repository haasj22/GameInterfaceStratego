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

    //ranks of pieces
    static final int rankOfMarshall = 1;
    static final int rankOfGeneral = 2;
    static final int rankOfColonel = 3;
    static final int rankOfMajor = 4;
    final int rankOfCaptain = 5;
    final int rankOfLieutenant = 6;
    final int rankOfSergeant = 7;
    final int rankOfMiner = 8;
    final int rankOfScout = 9;
    final int rankOfSpy = 0;

    private Block[][] board = new Block[10][10];

    //ids of the players
    private int playerOneID;
    private int playerOneTimer; //in milliseconds

    private int playerTwoID;
    private int playerTwoTimer; //in milliseconds

    private Phase currentPhase;

    //id of the player whose turn it is
    private int turn;

    //checks if game is won
    private boolean gameWon = false;


    /**
     * Constructor for objects of class StrategoGameState
     */
    public StrategoGameState(){
        playerOneID = 1;
        playerOneTimer = 3000;

        playerTwoID = 2;
        playerTwoTimer = 0;

        currentPhase = Phase.SETUP_PHASE;

        turn = 1;

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

        this.playerTwoID = state.playerTwoID;
        this.playerTwoTimer = state.playerTwoTimer;

        this.currentPhase = state.currentPhase;

        this.turn = state.turn;

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

        if(turn == 1){
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

    //allows players to set pieces on the board if its SET_UP phase
    public boolean setPiece(Piece pieceToPlace, int x, int y) {
        if(x >= ROWMAX || x < 0) {
            return false;
        }
        else if (y >= COLMAX || y < 0) {
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
    public boolean movePiece() {
        if(this.currentPhase != Phase.PLAY_PHASE) {
            return false;
        }
        return true;
    }

    //checks if the game is over
    public boolean isGameOver() {

        return false;
    }

    //How do I do this
    public boolean receiveUpdatedInfo(StrategoGameState newGameState) {
    }


}
