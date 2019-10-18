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

    //stores the proper min and max indexes for the columns
    private final int COLMININDEX=0;
    private final int COLMAXINDEX=0;

    //array that represents the state of the game board
    private Block[][] board = new Block[ROWMAX][COLMAX];

    //player one's information
    private int redTeamID;
    private int redTeamTimer; //in milliseconds

    //variables that will store what pieces player one has in play
    private ArrayList<Piece> redTeamPieces;
    //necessary for transitioning between phases
    private boolean redTeamHasFlag;

    //player two's information
    private int blueTeamID;
    private int blueTeamTimer; //in milliseconds
    //variables that will store what pieces player two has in play
    private ArrayList<Piece> blueTeamPieces;
    //necessary for transitioning between phases
    private boolean blueTeamHasFlag;

    //what phase the game is currently in
    private Phase currentPhase;

    //id of the player whose turn it is
    private Team currentTeamsTurn;

    /**
     * Constructor for objects of class StrategoGameState
     */
    public StrategoGameState(){
        //player one sets up first
        redTeamID = 1;
        redTeamTimer = 3000;

        //player one starts with no pieces on the board
        redTeamPieces= new ArrayList<Piece>();
        redTeamHasFlag = false;

        //player one does not get access to any of player two's information
        blueTeamID = 2;
        blueTeamTimer = 0;

        //player one does not get to see where player two placed his pieces
        blueTeamPieces=new ArrayList<Piece>();
        blueTeamHasFlag = false;

        //starts the game out in setup phase
        currentPhase = Phase.SETUP_PHASE;

        //player one starts
        currentTeamsTurn = Team.RED_TEAM;

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
     * Deep Copy Constructor of the StrategoGameState
     *
     * @param trueState the one true state of the game that would be copied.
     */
    public StrategoGameState(StrategoGameState trueState){

        //copies player one's information
        this.redTeamID = trueState.redTeamID;
        this.redTeamTimer = trueState.redTeamTimer;

        //copies player one's pieces
        for(Piece p: trueState.redTeamPieces) {
            this.redTeamPieces.add(new Piece(p));
        }

        //sees whether player one has won yet
        this.redTeamHasFlag = trueState.redTeamHasFlag;

        //copies player two's information
        this.blueTeamID = trueState.blueTeamID;
        this.blueTeamTimer = trueState.blueTeamTimer;

        //copies player two's pieces
        for(Piece p: trueState.blueTeamPieces) {
            this.blueTeamPieces.add(new Piece(p));
        }

        //sees whether player two has won yet
        this.blueTeamHasFlag = trueState.blueTeamHasFlag;

        //copies the phase of the game
        this.currentPhase = trueState.currentPhase;

        //finds who's turn it is
        this.currentTeamsTurn = trueState.currentTeamsTurn;

        //copies the game board
        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                this.board[i][j] = new Block(trueState.board[i][j]);
            }
        }

    }
    /**-----------------------------------GETTER METHODS------------------------------------------*/

    public Block[][] getBoard() {
        return board;
    }

    public ArrayList<Piece> getRedTeamPieces() {
        return redTeamPieces;
    }

    public boolean getIsRedTeamHasFlag() {
        return redTeamHasFlag;
    }

    public ArrayList<Piece> getBlueTeamPieces() {
        return blueTeamPieces;
    }

    public boolean getIsBlueTeamHasFlag() {
        return blueTeamHasFlag;
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    /**-----------------------------------SETTER METHODS------------------------------------------*/

    public void setBoard(Block[][] board) {
        this.board = board;
    }

    public void setRedTeamPieces(ArrayList<Piece> RedTeamPieces) {
        this.redTeamPieces = RedTeamPieces;
    }

    public void setRedTeamHasFlag(boolean RedTeamHasFlag) {
        this.redTeamHasFlag = RedTeamHasFlag;
    }

    public void setblueTeamPieces(ArrayList<Piece> blueTeamPieces) {
        this.blueTeamPieces = blueTeamPieces;
    }

    public void setblueTeamHasFlag(boolean blueTeamHasFlag) {
        this.blueTeamHasFlag = blueTeamHasFlag;
    }

    public void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
    }

    /**--------------------------------SETUP_PHASE METHODS----------------------------------------*/

    /**
     * addPieceToGame method
     * allows players to set pieces on the board if its SET_UP phase
     * @param placedPiece desired piece to be placed
     * @param x the desired x coordinate of the placed piece
     * @param y the desired y coordinate of the placed piece
     * @return true if piece is set at the desired location
     *         false if the piece cannot be placed at the desired location
     */
    public boolean addPieceToGame(Piece placedPiece, int x, int y) {
        //TODO finish adding logic to and implementing the method
        //makes sure x is a legal value
        if(x < COLMININDEX || x > COLMAXINDEX ) {
            return false;
        }
        //makes sure y is a legal value
        if ( y < placedPiece.getPieceTeam().getTOPBOUNDARYINDEX()
                || y > placedPiece.getPieceTeam().getBOTTOMBOUNDARYINDEX() ) {
            return false;
        }

        //makes sure there are not too many of the desired piece on the board
        int numOfDesiredPieceOnBoard=0;
        for(Piece piece : redTeamPieces) {
            if(piece.getPieceRank() == placedPiece.getPieceRank()) {
                numOfDesiredPieceOnBoard++;
            }
        }

        //makes sure the amount of pieces doesn't exceed the maz
        if(numOfDesiredPieceOnBoard >= placedPiece.getPieceRank().getMaxAmountOfPieces()) {
            return false;
        }

        //doesn't allow a piece to be placed
        if(board[y][x].containsPiece()) {
            return false;
        }

        //sets the piece to the desired place
        board[y][x].setContainedPiece(placedPiece);

        //sets the flag variable accordingly
        if(placedPiece.getPieceRank() == Rank.FLAG) {
            if(currentTeamsTurn == Team.RED_TEAM) {
                setRedTeamHasFlag(true);
            } else {
                setblueTeamHasFlag(true);
            }
        }

        return true;
    }

    /**
     * removePieceFromGame method
     * removes pieces from board during set up phase
     * @param x x variable of the piece to be removed
     * @param y y variable of the piece to be removed
     * @return true if piece has been removed
     *         false if placement lacks a piece or coordinates are invalid
     */
    public boolean removePieceFromGame(int x, int y) {
        //removes the piece from the board
        board[y][x].setContainedPiece(null);
        return true;
    }

    /**
     * movePieceFromGame method
     * allows the user to move his pieces around the game board during SETUP_PHASE
     * @param x1 x variable of the original location
     * @param x2 x variable of the desired location
     * @param y1 y variable of the original location
     * @param y2 y variable of the desired location
     * @return true if piece is properly moved from point 1 to 2
     *         false if the coordinates are invalid
     */
    public boolean movePieceDuringSetup(int x1, int x2, int y1, int y2) {
        //makes sure that the first spot contains a piece
        if(!(board[y1][x1].containsPiece())) {
            return false;
        }
        //switches the piece in the first block with the second block
        Piece temp=new Piece(board[y1][x1].getContainedPiece());
        board[y1][x1].setContainedPiece(new Piece(board[y2][x2].getContainedPiece()));
        board[y2][x2].setContainedPiece(temp);
        return true;
    }

    /**
     * method that randomizes the remaining pieces that are not placed yet
     *
     * @return true when all pieces are placed
     */
    public boolean randomizeRemainingPieces() {
        //iterates through all possible ranks
        for(Rank r: Rank.values()) {
            //for the rest of the pieces that are not placed
            for(int x = getAmountOfPieces(currentTeamsTurn, r);
                x < r.getMaxAmountOfPieces(); x++) {

                //randomizes possible x and y values
                int randomXValue = (int)(Math.random() * 10);
                int randomYValue = (int)(Math.random() * 4 +
                        currentTeamsTurn.getTOPBOUNDARYINDEX());


                //places piece if possible or adds another iteration to the loop
                if(!board[randomYValue][randomXValue].containsPiece()) {
                    boolean isPiecePlace = addPieceToGame(new Piece(currentTeamsTurn, r),
                            randomXValue, randomYValue);
                } else {
                    x--;
                }

            }
        }
        return true;
    }

    /**
     * helper method that checks if a given team's side of the board is full
     *
     * @param targetTeam team whos side of the board needs to be checked
     * @return true if the team's side of the board is full
     *         false if the team's side of the board has an empty spot
     */
    private boolean isBoardFull(Team targetTeam) {
        //searches through the appropriate side of the board
        for(int x=targetTeam.getTOPBOUNDARYINDEX(); x <= targetTeam.getBOTTOMBOUNDARYINDEX(); x++) {
            for(int y=0; y<10; y++) {
                //returns false if the block does not contain a piece
                if(!board[x][y].containsPiece()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**-------------------------------------------------------------------------------------------*/

    /**
     * transitionPhases method
     * transitions from SETUP_PHASE to PLAY_PHASE
     * @return true if conditions to transition phases are met
     *         false if conditions to transition phases are not met
     */
    public boolean transitionPhases() {
        //makes sure the game is in set up phase
        if(currentPhase != Phase.SETUP_PHASE) {
            return false;
        }
        //fills the current players side of the board if not full
        if(!isBoardFull(currentTeamsTurn)) {
            randomizeRemainingPieces();
        }
        //transitions current player
        transitionTurns();
        //if the other players boards not full, its their turn to set up
        if(!isBoardFull(currentTeamsTurn)) {
            return false;
        }
        //transitions phases
        this.currentPhase = Phase.PLAY_PHASE;
        return true;
    }

    /**
     * transitionTurns method
     * transitions form one player's turn to the other
     */
    public boolean transitionTurns() {
        //switches the current team
        if(currentTeamsTurn == Team.RED_TEAM) {
            currentTeamsTurn = Team.BLUE_TEAM;
        } else if(currentTeamsTurn == Team.BLUE_TEAM) {
            currentTeamsTurn = Team.RED_TEAM;
        }
        return true;
    }

    /**--------------------------------------PLAY_PHASE-------------------------------------------*/

    /**
     * movePiece method
     * allows players to move their piece during PLAY_PHASE
     * @param x1 original x-coordinate of piece
     * @param x2 x-coordinate piece wants to be moved to
     * @param y1 original y-coordinate of piece
     * @param y2 y-coordinate piece wants to be moved to
     * @return true once piece has been moved
     *         false if the coordinates are invalid
     */
    public boolean movePiece(int x1, int x2, int y1, int y2) {
        //TODO implement movePiece function
        if(this.currentPhase != Phase.PLAY_PHASE) {
            return false;
        }
        return true;
    }

    /**
     * helper method for movePiece that triggers
     * when two pieces collide on game board
     * @param attacker piece that is on the offense
     * @param defender piece that is defending
     * @return true if attacker wins
     *         false if defender wins
     */
    private boolean attackPiece(Piece attacker, Piece defender) {
        //TODO implment attackPiece method
        return false;
    }

    /**
     * isGameOver method
     * checks if the game is over
     * @return true if both players have their flags
     */
    public boolean isGameOver() {
        //TODO implement isGameOver method
        return false;
    }

    /**-------------------------------------------------------------------------------------------*/

    /**-----------------------------------General Methods-----------------------------------------*/

    /**
     * method that gets the amount of a given piece a given player has
     *
     * @param desiredPlayer player that will be searched
     * @param desiredRank rank that the player is searching for
     * @return amount: the amount of the given piece the given player has
     */
    private int getAmountOfPieces(Team desiredPlayer, Rank desiredRank) {
        //will store the total of the desired piece
        int amount=0;

        //will store the pieces of the team that is to be searched
        ArrayList<Piece> currentTeamsPieces;

        //sets the current teams pieces according to the current team
        if(desiredPlayer == Team.RED_TEAM) {
            currentTeamsPieces = this.getRedTeamPieces();
        } else {
            currentTeamsPieces = this.getBlueTeamPieces();
        }

        //counts the amount of pieces matching the desired rank
        for(Piece p: currentTeamsPieces) {
            if(p.getPieceRank() == desiredRank) {
                amount++;
            }
        }

        //returns the amount of pieces found
        return amount;
    }

    /**
     * method that allows the user to forfeit the game
     * @return true once game has been ended
     */
    public boolean forfeitGame() {
        //TODO Implement forfeitGame method
        return true;
    }

    /**
     * method that prints class information as a String
     * @return toReturn: the String that contains all the gameState information
     */
    @Override
    public String toString(){
        //prints all the game state information
        String toReturn = "\nStratego Game State:\n";

        toReturn += "[Player One ID: " + redTeamID + "]\n";
        toReturn += "[Player One Timer: " + redTeamTimer + "]\n";

        toReturn += "[Player Two ID: " + blueTeamID + "]\n";
        toReturn += "[Player Two Timer: " + blueTeamTimer + "]\n";

        toReturn += "[Current Phase: " + currentPhase + "]\n";

        //prints whose turn it is based on currentPlayer variable
        if(currentTeamsTurn == Team.RED_TEAM){
            toReturn += "Red Team's Turn\n";
        }
        else{
            toReturn += "Blue Team's turn\n";
        }

        //prints whats stored in each board block
        toReturn += "------------------------\n";
        for(int i=0; i<ROWMAX; i++) {
            for(int j=0; j<COLMAX; j++) {
                toReturn += "[Block " + (i+1) + ":" + (j+1) + "]";
                toReturn += board[i][j];
            }
            toReturn += "\n";
        }
        toReturn += "------------------------\n";

        //return information
        return toReturn;
    }

    /**-------------------------------------------------------------------------------------------*/

}
