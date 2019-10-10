package com.example.myapplication;

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

    enum phase {
        SETUP_PHASE,
        PLAY_PHASE
    };

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
    final int rankOfMarshall = 10;

    private Block[][] board = new Block[10][10];

    //ids of the players
    private int playerOneID;
    private int playerOneTimer; //in milliseconds

    private int playerTwoID;
    private int playerTwoTimer; //in milliseconds

    private phase currentPhase;

    //id of the player whose turn it is
    private int turn;

    //checks if game is won
    private boolean gameWon;


    /**
     * Constructor for objects of class StrategoGameState
     */
    public StrategoGameState(){
        playerOneID = 1;
        playerOneTimer = 3000;

        playerTwoID = 2;
        playerTwoTimer = 0;

        currentPhase = phase.SETUP_PHASE;

        turn = 1;

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(i != 4 || i !=5) {
                    board[i][j] = new Block(Block.Tile.GRASS);
                    break;
                }
                if(j != 2 || j != 3 || j != 6 || j != 7) {
                    board[i][j] = new Block(Block.Tile.WATER);
                    break;
                }
                board[i][j] = new Block(Block.Tile.BRIDGE)
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


        return toReturn;
    }


}
