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

    //max number of rows and cols in board
    static final int COLMAX = 10;
    static final int ROWMAX = 10;

    //amount of pieces
    final int numOfMarshalls = 1;
    final int numOfGenerals = 1;
    final int numOfColonels = 2;
    final int numOfMajors = 3;
    final int numOfCaptains = 4;
    final int numOfLietenants = 4;
    final int numOfSergeants = 4;
    final int numOfMiners = 5;
    final int numOfScouts = 8;
    final int numOfSpy = 1;
    final int numOfBombs = 6;

    //ranks of pieces
    final int rankOfMarshall = 10;

    private Board[][] board = new Board[10][10];


    private int playerOneID;
    private int playerTwoID;

    private int turn;
    private boolean gameWon;
    private boolean enoughPlayers;

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
    }
}
