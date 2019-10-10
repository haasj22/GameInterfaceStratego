package com.example.myapplication.Stratego.GameState;

import java.util.ArrayList;

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
    private final int COLMAX = 10;
    private final int ROWMAX = 10;

    //amount of pieces
    private final int numOfMarshalls = 1;
    private final int numOfGenerals = 1;
    private final int numOfColonels = 2;
    private final int numOfMajors = 3;
    private final int numOfCaptains = 4;
    private final int numOfLietenants = 4;
    private final int numOfSergeants = 4;
    private final int numOfMiners = 5;
    private final int numOfScouts = 8;
    private final int numOfSpy = 1;
    private final int numOfBombs = 6;

    private Block[][] board = new Block[ROWMAX][COLMAX];

    //ids of the players
    private int playerOneID;
    private int playerOneTimer; //in milliseconds

    //variables that will store the amount of pieces player One has
    private ArrayList<Piece> playerOnePieces;
    private boolean playerOneHasFlag;

    private int playerTwoID;
    private int playerTwoTimer; //in milliseconds

    //variables that will store the amount of pieces player Two has
    private ArrayList<Piece> playerTwoPieces;
    private boolean playerTwoHasFlag;

    private Phase currentPhase;

    //id of the player whose turn it is
    private int currentPlayer;


    /**
     * Constructor for objects of class StrategoGameState
     */
    public StrategoGameState(){
        playerOneID = 1;
        playerOneTimer = 3000;

        playerOnePieces= new ArrayList<Piece>();
        playerOneHasFlag = false;

        playerTwoID = 2;
        playerTwoTimer = 0;

        playerTwoPieces=new ArrayList<Piece>();
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

    }

    /**
     * Deep Copy Constructor
     *
     * @param state the one true state of the game that would be copied.
     */
    public StrategoGameState(StrategoGameState state){

        this.playerOneID = state.playerOneID;
        this.playerOneTimer = state.playerOneTimer;

        for(Piece p: state.playerOnePieces) {
            this.playerOnePieces.add(new Piece(p));
        }
        //deep copy of arrayLists

        this.playerOneHasFlag = state.playerOneHasFlag;

        this.playerTwoID = state.playerTwoID;
        this.playerTwoTimer = state.playerTwoTimer;

        for(Piece p: state.playerTwoPieces) {
            this.playerTwoPieces.add(new Piece(p));
        }

        this.playerTwoHasFlag = state.playerTwoHasFlag;

        this.currentPhase = state.currentPhase;

        this.currentPlayer = state.currentPlayer;

        //put new blocks here
        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                this.board[i][j] = new Block(state.board[i][j]);
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

        return toReturn;
    }

    //set up variables?
    //allows players to set pieces on the board if its SET_UP phase
    public boolean setPiece(Rank pieceRank, int x, int y) {
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

    public boolean removePiece() {
        return false;
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
    private boolean attackPiece(Piece attacker, Piece defender) {
        return false;
    }

    public boolean forfeitGame() {
        return false;
    }

    //checks if the game is over
    public boolean isGameOver() {

        return false;
    }


    //timer maybe

}
