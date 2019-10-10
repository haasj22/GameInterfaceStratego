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
    final int rankOfMarshall = 1;
    final int rankOfGeneral = 2;
    final int rankOfColonel = 3;
    final int rankOfMajor = 4;
    final int rankOfCaptain = 5;
    final int rankOfLieutenant = 6;
    final int rankOfSergeant = 7;
    final int rankOfMiner = 8;
    final int rankOfScout = 9;
    final int rankOfSpy = 10;

    private Block[][] board = new Block[10][10];

    //ids of the players
    private int playerOneID = 0;
    private int playerOneTimer; //in milliseconds

    private int playerTwoID = 1;
    private int playerTwoTimer; //in milliseconds

    private phase currentPhase;

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
                board[i][j] = new Block(Block.Tile.BRIDGE);
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
        toReturn += "Current number of pieces on board:\n";
        toReturn += "Number of Marshals: " + numOfMarshalls + "\n";
        toReturn += "Number of Generals: " + numOfGenerals + "\n";
        toReturn += "Number of Colonels: " + numOfColonels + "\n";
        toReturn += "Number of Majors: " + numOfMajors + "\n";
        toReturn += "Number of Captains: " + numOfCaptains + "\n";
        toReturn += "Number of Lieutenants: " + numOfLietenants + "\n";
        toReturn += "Number of Sergeants: " + numOfSergeants + "\n";
        toReturn += "Number of Miners: " + numOfMiners + "\n";
        toReturn += "Number of Scouts: " + numOfScouts + "\n";
        toReturn += "Number of Spies: " + numOfSpy + "\n";
        toReturn += "Number of Bombs: " + numOfBombs + "\n";
        if(turn == 1){
            toReturn += "Player One's Turn\n";
        }
        else{
            toReturn += "Player Two's turn\n";
        }
        if(gameWon == true){
            toReturn += "Game is over\n";
        }
        else{
            toReturn += "Game is not over\n";
        }

        return toReturn;
    }


}
