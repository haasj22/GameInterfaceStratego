/**
 * class that contains all information the game will need to function
 *
 * @author John Haas
 * @author Jordan Ho
 * @author Kavya Mandla
 * @version October 2019
 */

package com.example.myapplication.Stratego.GameState;

import java.util.ArrayList;

public class StrategoGameState {

    //max number of rows and cols in board
    private final int COLMAX = 10;
    private final int ROWMAX = 10;

    //maximum amount of pieces each game can have
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

    //array that represents the state of the game board
    private Block[][] board = new Block[ROWMAX][COLMAX];

    //player one's information
    private int playerOneID;
    private int playerOneTimer; //in milliseconds
    //variables that will store what pieces player one has in play
    private ArrayList<Piece> playerOnePieces;
    //necessary for transitioning between phases
    private boolean playerOneHasFlag;

    //player two's information
    private int playerTwoID;
    private int playerTwoTimer; //in milliseconds
    //variables that will store what pieces player two has in play
    private ArrayList<Piece> playerTwoPieces;
    //necessary for transitioning between phases
    private boolean playerTwoHasFlag;

    //what phase the game is currently in
    private Phase currentPhase;

    //id of the player whose turn it is
    private int currentPlayer;


    /**
     * Constructor for objects of class StrategoGameState
     */
    public StrategoGameState(){
        //player one sets up first
        playerOneID = 1;
        playerOneTimer = 3000;

        //player one starts with no pieces on the board
        playerOnePieces= new ArrayList<Piece>();
        playerOneHasFlag = false;

        //player one does not get access to any of player two's information
        playerTwoID = 2;
        playerTwoTimer = 0;

        //player one does not get to see where player two placed his pieces
        playerTwoPieces=new ArrayList<Piece>();
        playerTwoHasFlag = false;

        //starts the game out in setup phase
        currentPhase = Phase.SETUP_PHASE;

        //player one starts
        currentPlayer = 1;

        //creates basic game board
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

        //copies player one's information
        this.playerOneID = state.playerOneID;
        this.playerOneTimer = state.playerOneTimer;

        //copies player one's pieces
        for(Piece p: state.playerOnePieces) {
            this.playerOnePieces.add(new Piece(p));
        }

        //sees whether player one has won yet
        this.playerOneHasFlag = state.playerOneHasFlag;

        //copies player two's information
        this.playerTwoID = state.playerTwoID;
        this.playerTwoTimer = state.playerTwoTimer;

        //copies player two's pieces
        for(Piece p: state.playerTwoPieces) {
            this.playerTwoPieces.add(new Piece(p));
        }

        //sees whether player two has won yet
        this.playerTwoHasFlag = state.playerTwoHasFlag;

        //copies the phase of the game
        this.currentPhase = state.currentPhase;

        //finds who's turn it is
        this.currentPlayer = state.currentPlayer;

        //copies the game board
        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                this.board[i][j] = new Block(state.board[i][j]);
            }
        }

    }

    /**
     * setPiece method
     * allows players to set pieces on the board if its SET_UP phase
     * @param pieceRank
     * @param x
     * @param y
     * @return
     */
    //set up variables?
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

    /**
     * removePiece method
     * removes pieces from board during set up phase
     * @return
     */
    public boolean removePiece() {
        return false;
    }

    /**
     * transitionPhases method
     * transitions from set up to PLAY_PHASE
     * @return
     */
    public boolean transitionPhases() {
        if(this.currentPhase == Phase.SETUP_PHASE) {
            this.currentPhase = Phase.PLAY_PHASE;
            return true;
        }
        return false;
    }

    /**
     * movePiece method
     * allows players to move their piece
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @return
     */
    public boolean movePiece(int x1, int x2, int y1, int y2) {
        if(this.currentPhase != Phase.PLAY_PHASE) {
            return false;
        }
        return true;
    }

    /**
     * attackPiece method
     * helper method for movePiece
     * @param attacker
     * @param defender
     * @return
     */
    private boolean attackPiece(Piece attacker, Piece defender) {
        return false;
    }


    /**
     * forfeitGame method
     * @return
     */
    public boolean forfeitGame() {
        return false;
    }

    /**
     * isGameOver method
     * checks if the game is over
     * @return
     */
    public boolean isGameOver() {

        return false;
    }


    /**
     * toString method
     * @return
     */
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

}
