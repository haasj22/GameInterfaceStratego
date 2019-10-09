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
        playerTwoID = 2;

        turn = 1;

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){

            }
        }

        gameWon = false;
        enoughPlayers = true;
    }

    /**
     * Deep Copy Constructor
     *
     * @param state the one true state of the game that would be copied.
     */
    public StrategoGameState(StrategoGameState state){
        playerOneID = state.playerOneID;
        playerTwoID = state.playerTwoID;

        turn = state.turn;

        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if(board == null){
                    return;
                }
                else{
                    board[i][j] = new Board(state.board[i][j]);
                }
            }
        }
    }


}
