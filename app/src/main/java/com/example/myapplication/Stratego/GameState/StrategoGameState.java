/**
 * class that contains all information the game will need to function
 *
 * @author John Haas
 * @author Jordan Ho
 * @author Kavya Mandla
 */

package com.example.myapplication.Stratego.GameState;

import android.util.Log;

import com.example.myapplication.Game.infoMsg.GameState;
import com.example.myapplication.Stratego.Player.StrategoHumanPlayer;

import java.io.Serializable;
import java.util.ArrayList;

public class StrategoGameState extends GameState implements Serializable {

    //to satisfy Serializable interface
    private static final long serialVersionUID = 2379343847L;

    private int redTeamSeconds;
    private int blueTeamSeconds;

    //max number of rows and cols in board
    private final int ROWMIN = 0;
    private final int COLMIN = 0;
    private final int COLMAX = 10;
    private final int ROWMAX = 10;
    private final int EMPTY = -1;

    //array that represents the state of the game board
    private Block[][] board = new Block[ROWMAX][COLMAX];

    //variables that will store what pieces player one has in play
    private ArrayList<Rank> redTeamPieces;
    //necessary for transitioning between phases
    private boolean redTeamHasFlag;

    //variables that will store what pieces player two has in play
    private ArrayList<Rank> blueTeamPieces;
    //necessary for transitioning between phases
    private boolean blueTeamHasFlag;

    //what phase the game is currently in
    private Phase currentPhase;

    //id of the player whose turn it is
    private Team currentTeamsTurn;

    //used for making moves and attacks
    private int lastTappedRow;
    private int lastTappedCol;
    private boolean didLastBlockContainPiece;

    //piece that is temporarily visible
    private Piece visiblePiece;

    //used for smartPlayerAI
    private MovablePiece lastKilledPiece;

    //last tapped button that is used for piece highlights
    private Rank lastTappedPieceButton;

    /**
     * Constructor for objects of class StrategoGameState
     */
    public StrategoGameState(){

        //base setup time for both teams
        redTeamSeconds = 150;
        blueTeamSeconds = 150;

        //player one starts with no pieces on the board
        redTeamPieces= new ArrayList<Rank>();
        redTeamHasFlag = true;

        //player one does not get to see where player two placed his pieces
        blueTeamPieces=new ArrayList<Rank>();
        blueTeamHasFlag = true;

        //starts the game out in setup phase
        currentPhase = Phase.SETUP_PHASE;

        //player one starts
        currentTeamsTurn = Team.RED_TEAM;

        //creates basic game board
        for(int i = 0; i < ROWMAX; i++){
            for(int j = 0; j < COLMAX; j++){
                if(i != 4 && i !=5) {
                    board[i][j] = new Block(Tile.GRASS);
                    continue;
                }
                if(j != 2 && j != 3 && j != 6 && j != 7) {
                    board[i][j] = new Block(Tile.BRIDGE);
                    continue;
                }
                board[i][j] = new Block(Tile.WATER);
            }
        }

        //sets default tapped row and column
        lastTappedRow = EMPTY;
        lastTappedCol = EMPTY;

        //move calculation variables
        didLastBlockContainPiece = false;
        lastTappedPieceButton = null;
        visiblePiece=null;
        lastKilledPiece=null;
    }

    /**
     * Deep Copy Constructor of the StrategoGameState
     *
     * @param trueState the one true state of the game that would be copied.
     */
    public StrategoGameState(StrategoGameState trueState){

        //copies player one's information
        redTeamSeconds=trueState.redTeamSeconds;
        blueTeamSeconds=trueState.blueTeamSeconds;

        //copies player one's pieces
        redTeamPieces= new ArrayList<Rank>();
        for(Rank r: trueState.redTeamPieces) {
            this.redTeamPieces.add(r);
        }

        //sees whether player one has won yet
        this.redTeamHasFlag = trueState.redTeamHasFlag;;

        //copies player two's pieces
        blueTeamPieces= new ArrayList<Rank>();
        for(Rank b: trueState.blueTeamPieces) {
            this.blueTeamPieces.add(b);
        }

        //sees whether player two has won yet
        this.blueTeamHasFlag = trueState.blueTeamHasFlag;

        //copies the phase of the game
        this.currentPhase = trueState.currentPhase;

        //finds who's turn it is
        this.currentTeamsTurn = trueState.currentTeamsTurn;

        //copies the game board
        for(int i = ROWMIN; i < ROWMAX; i++){
            for (int j = COLMIN; j < COLMAX; j++){
                this.board[i][j] = new Block(trueState.board[i][j]);
            }
        }

        //copies the last tapped position
        this.lastTappedRow = trueState.lastTappedRow;
        this.lastTappedCol = trueState.lastTappedCol;
        this.didLastBlockContainPiece = trueState.didLastBlockContainPiece;
        this.visiblePiece = trueState.visiblePiece;
        this.lastKilledPiece = trueState.lastKilledPiece;
        this.lastTappedPieceButton = trueState.lastTappedPieceButton;
    }
    /**-----------------------------------GETTER METHODS------------------------------------------*/

    public Block[][] getBoard() {
        return board;
    }
    public ArrayList<Rank> getRedTeamPieces() {
        return redTeamPieces;
    }
    public boolean getIsRedTeamHasFlag() {
        return redTeamHasFlag;
    }
    public ArrayList<Rank> getBlueTeamPieces() {
        return blueTeamPieces;
    }
    public boolean getIsBlueTeamHasFlag() {
        return blueTeamHasFlag;
    }
    public Phase getCurrentPhase() {
        return currentPhase;
    }
    public Team getCurrentTeamsTurn() { return currentTeamsTurn; }
    public Rank getLastTappedPieceButton() { return lastTappedPieceButton; }
    public int getCOLMIN() {return COLMIN; }
    public int getROWMIN() { return ROWMIN; }
    public int getLastTappedRow() { return lastTappedRow; }
    public int getLastTappedCol() { return lastTappedCol; }
    public int getCOLMAX() { return COLMAX; }
    public int getROWMAX() { return ROWMAX; }
    public boolean isDidLastBlockContainPiece() { return didLastBlockContainPiece; }
    public int getRedTeamSeconds() { return redTeamSeconds; }
    public int getBlueTeamSeconds() { return blueTeamSeconds; }
    public Piece getVisiblePiece() { return visiblePiece; }
    public Piece getPieceAt(int row, int col) {
        return board[row][col].getContainedPiece();
    }
    public MovablePiece getLastKilledPiece() { return lastKilledPiece; }
    public int getEMPTY() { return EMPTY; }

    /**
     * returns the team that is currently not taking their turn
     *
     * @return Team.RED_TEAM if it is the blue team's turn
     *         Team.BLUE_TEAM if it is the red team's turn
     */
    public Team getEnemyTeam() {
        if(currentTeamsTurn == Team.RED_TEAM) {
            return Team.BLUE_TEAM;
        } else {
            return Team.RED_TEAM;
        }
    }

    /**-----------------------------------SETTER METHODS------------------------------------------*/

    public void setRedTeamHasFlag(boolean RedTeamHasFlag) {
        this.redTeamHasFlag = RedTeamHasFlag;
    }

    public void setblueTeamHasFlag(boolean blueTeamHasFlag) {
        this.blueTeamHasFlag = blueTeamHasFlag;
    }

    public void setLastTappedPieceButton(Rank lastTappedPieceButton) {
        this.lastTappedPieceButton = lastTappedPieceButton;
    }

    public void setBlueTeamSeconds(int blueTeamSeconds) {
        if(blueTeamSeconds <= 0) {
            this.forfeitGame();
            return;
        }
        this.blueTeamSeconds = blueTeamSeconds;
    }
    public void setRedTeamSeconds(int redTeamSeconds) {
        if(redTeamSeconds <= 0) {
            this.forfeitGame();
            return;
        }
        this.redTeamSeconds = redTeamSeconds;
    }

    public void setLastKilledPiece(MovablePiece lastKilledPiece) { this.lastKilledPiece = lastKilledPiece; }
    public void setVisiblePiece(Piece visiblePiece) {
        this.visiblePiece = visiblePiece;
    }

    /**--------------------------------SETUP_PHASE METHODS----------------------------------------*/

    /**
     * method that acts as a hub of all the human players setup methods
     *
     * @param row the row of the previous tap
     * @param col the col of the previous tap
     * @return true when the tap has been handled
     */
    public boolean tapOnSquareSETUP(int row, int col) {

        if(row < this.getCurrentTeamsTurn().getTOPBOUNDARYINDEX() ||
                row > this.getCurrentTeamsTurn().getBOTTOMBOUNDARYINDEX()) {
            return true;
        }

        //if tapped piece is empty and highlighted adds a piece to the game
        if(board[row][col].getContainedPiece() == null && didLastBlockContainPiece == false
                && lastTappedRow == row && lastTappedCol == col) {
            addPieceToGame(new Piece(currentTeamsTurn, this.getLastTappedPieceButton()), row, col);
            lastTappedRow = EMPTY;
            lastTappedCol = EMPTY;
            this.removeHighlightedBlocks();
        }
        //if the player taps a spot containing a piece twice removes it from board
        else if(row == lastTappedRow && col == lastTappedCol && didLastBlockContainPiece==true) {
            removePieceFromGame(row, col);
            board[row][col].setHighLighted(false);
            lastTappedRow = EMPTY;
            lastTappedCol = EMPTY;
            didLastBlockContainPiece=false;
            this.removeHighlightedBlocks();
        }
        //if the player taps a placed piece then another piece moves it around
        else if(didLastBlockContainPiece){
            movePieceDuringSetup(lastTappedRow, lastTappedCol, row, col);
            board[lastTappedRow][lastTappedCol].setHighLighted(false);
            lastTappedRow = EMPTY;
            lastTappedCol = EMPTY;
            didLastBlockContainPiece=false;
            this.removeHighlightedBlocks();
        }
        //if piece contains piece but there is no previous tap readies the piece for deletion
        else if(board[row][col].containsPiece()) {
            board[row][col].setHighLighted(true);
            lastTappedRow=row;
            lastTappedCol=col;
            didLastBlockContainPiece=true;
        }
        //else primes the piece to be added
        else {
            if(lastTappedRow != EMPTY && lastTappedCol != EMPTY) {
                board[lastTappedRow][lastTappedCol].setHighLighted(false);
            }
            board[row][col].setHighLighted(true);
            lastTappedRow = row;
            lastTappedCol = col;
            didLastBlockContainPiece=false;
        }
        return true;
    }


    /**
     * addPieceToGame method
     * allows players to set pieces on the board if its SET_UP phase
     * @param placedPiece desired piece to be placed
     * @param row the desired row of the placed piece
     * @param col the desired col of the placed piece
     * @return true if piece is set at the desired location
     *         false if the piece cannot be placed at the desired location
     */
    public boolean addPieceToGame(Piece placedPiece, int row, int col) {
        if(placedPiece.getPieceRank() == null) {
            return false;
        }
        //makes sure x is a legal value
        if(col < COLMIN || col >= COLMAX ) {
            return false;
        }
        //makes sure y is a legal value
        if ( row < placedPiece.getPieceTeam().getTOPBOUNDARYINDEX()
                || row > placedPiece.getPieceTeam().getBOTTOMBOUNDARYINDEX() ) {
            return false;
        }

        //makes sure there are not too many of the desired piece on the board
        int numOfDesiredPieceOnBoard = calculateNumberOfPieces(placedPiece.getPieceRank());

        //makes sure the amount of pieces doesn't exceed the maz
        if(numOfDesiredPieceOnBoard >= placedPiece.getPieceRank().getMaxAmountOfPieces()) {
            return false;
        }

        //doesn't allow a piece to be placed
        if(board[row][col].containsPiece()) {
            return false;
        }
        //sets the piece to the desired place
        board[row][col].setContainedPiece(placedPiece);
        this.addPieceToPlayer(currentTeamsTurn, placedPiece.getPieceRank());

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
     * method that calculates the given number of pieces of a given rank
     *
     * @param pieceRank the rank that one wishes to count the pieces of
     * @return the number of pieces of the desired rank on the board
     */
    public int calculateNumberOfPieces(Rank pieceRank) {
        int numOfDesiredPieceOnBoard=0;
        //looks through the current team's array of pieces and counts the ones of the desired rank
        if(currentTeamsTurn == Team.RED_TEAM) {
            for (Rank r : redTeamPieces) {
                if (r == pieceRank) {
                    numOfDesiredPieceOnBoard++;
                }
            }
        } else {
            for (Rank r : blueTeamPieces) {
                if (r == pieceRank) {
                    numOfDesiredPieceOnBoard++;
                }
            }
        }
        return numOfDesiredPieceOnBoard;
    }

    /**
     * method that calculates the number of enemy pieces of the given rank
     *
     * @param pieceRank rank to look for in the player arrays
     * @return
     */
    public int calculateNumberOfEnemyPieces(Rank pieceRank) {
        int numOfDesiredPieceOnBoard=0;
        //finds the enemy team array and counts the number of times the desired piece recurs
        if(currentTeamsTurn == Team.BLUE_TEAM) {
            for (Rank r : redTeamPieces) {
                if (r == pieceRank) {
                    numOfDesiredPieceOnBoard++;
                }
            }
        } else {
            for (Rank r : blueTeamPieces) {
                if (r == pieceRank) {
                    numOfDesiredPieceOnBoard++;
                }
            }
        }
        return numOfDesiredPieceOnBoard;
    }

    /**
     * removePieceFromGame method
     * removes pieces from board during set up phase
     * @param row x variable of the piece to be removed
     * @param col y variable of the piece to be removed
     * @return true if piece has been removed
     *         false if placement lacks a piece or coordinates are invalid
     */
    public boolean removePieceFromGame(int row, int col) {
        //removes the piece from the board
        this.removePieceFromPlayer(currentTeamsTurn, board[row][col].getContainedPiece().getPieceRank());
        board[row][col].setContainedPiece(null);

        return true;
    }

    /**
     * movePieceFromGame method
     * allows the user to move his pieces around the game board during SETUP_PHASE
     * @param row1 x variable of the original location
     * @param col1 x variable of the desired location
     * @param row2 y variable of the original location
     * @param col2 y variable of the desired location
     * @return true if piece is properly moved from point 1 to 2
     *         false if the coordinates are invalid
     */
    public boolean movePieceDuringSetup(int row1, int col1, int row2, int col2) {
        //makes sure that the first spot contains a piece
        if(col2 < COLMIN || col2 >= COLMAX) {
            return false;
        }
        //makes sure y is a legal value
        if ( row2 < board[row1][col1].getContainedPiece().getPieceTeam().getTOPBOUNDARYINDEX()
                || row2 >board[row1][col1].getContainedPiece().getPieceTeam().getBOTTOMBOUNDARYINDEX() ) {
            return false;
        }
        if(!(board[row1][col1].containsPiece())) {
            return false;
        }
        //switches the piece in the first block with the second block
        Piece temp=new Piece(board[row1][col1].getContainedPiece());
        if(board[row2][col2].containsPiece()) {
            board[row1][col1].setContainedPiece(new Piece(board[row2][col2].getContainedPiece()));
        } else {
            board[row1][col1].setContainedPiece(null);
        }
        board[row2][col2].setContainedPiece(temp);
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
                int randomColValue = (int)(Math.random() * COLMAX);
                int randomRowValue = (int)(Math.random() * 4 +
                        currentTeamsTurn.getTOPBOUNDARYINDEX());

                //places piece if possible or adds another iteration to the loop
                boolean isPiecePlace = addPieceToGame(new Piece(currentTeamsTurn, r),
                        randomRowValue, randomColValue);
                if(isPiecePlace == false) {
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
    public boolean isBoardFull(Team targetTeam) {
        //searches through the appropriate side of the board
        for(int x=targetTeam.getTOPBOUNDARYINDEX(); x <= targetTeam.getBOTTOMBOUNDARYINDEX(); x++) {
            for(int y=COLMIN; y<COLMAX; y++) {
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
        removeHighlightedBlocks();
        //fills the current players side of the board if not full
        if(!isBoardFull(currentTeamsTurn)) {
            randomizeRemainingPieces();
        }
        //transitions current player
        transitionTurns();
        //if the other players boards not full, its their turn to set up
        if(!isBoardFull(currentTeamsTurn)) {
            lastTappedRow = EMPTY;
            lastTappedCol = EMPTY;
            return false;
        }
        //transitions phases
        this.currentPhase = Phase.PLAY_PHASE;
        this.redTeamSeconds = 300;
        this.blueTeamSeconds = 300;
        this.lastTappedRow = EMPTY;
        this.lastTappedCol = EMPTY;
        return true;
    }

    /**
     * transitionTurns method
     * transitions form one player's turn to the other
     */
    public boolean transitionTurns() {
        //switches the current team
        if(currentTeamsTurn == Team.RED_TEAM) {
            this.redTeamSeconds += 5;
            currentTeamsTurn = Team.BLUE_TEAM;
        } else if(currentTeamsTurn == Team.BLUE_TEAM) {
            this.blueTeamSeconds += 5;
            currentTeamsTurn = Team.RED_TEAM;
        }
        return true;
    }

    /**--------------------------------------PLAY_PHASE-------------------------------------------*/

    /**
     * overall method that reacts to interaction with blocks
     *
     * @param row the row index of the board where the tap was registered
     * @param col the col index of the board where the tap was registered
     * @return true once information is handled appropriately
     *         false if the given information is unusable
     */
    public boolean tapOnSquarePLAY(int row, int col) {
        //makes sure the coordinates are valid
        if(col < COLMIN || col > COLMAX) {
            return false;
        } else if (row < ROWMIN || row > ROWMAX) {
            return false;
        }
        //procedure for attacking piece
        if(board[row][col].isHighLighted() && board[row][col].containsPiece()) {
            attackPiece(lastTappedRow, lastTappedCol, row, col);
            removeHighlightedBlocks();
            lastTappedRow = EMPTY;
            lastTappedCol = EMPTY;
            transitionTurns();
            return true;
        //procedure for moving piece
        } else if (board[row][col].isHighLighted()) {
            movePiece(lastTappedRow, lastTappedCol, row, col);
            //updates data
            removeHighlightedBlocks();
            lastTappedRow = EMPTY;
            lastTappedCol = EMPTY;
            //ends the currentPlayers turn
            transitionTurns();
            //procedure for highlighting pieces
            return true;
        } else if (board[row][col].containsPiece() &&
                board[row][col].getContainedPiece().getPieceTeam() == currentTeamsTurn) {
            //does not highlight movable spots for bomb and flag
            if(board[row][col].getContainedPiece().getPieceRank() == Rank.BOMB ||
                board[row][col].getContainedPiece().getPieceRank() == Rank.FLAG) {

                removeHighlightedBlocks();
                lastTappedRow = EMPTY;
                lastTappedCol = EMPTY;
                return true;
            //procedure for highlighting scouts movable squares
            } else if(board[row][col].getContainedPiece().getPieceRank() == Rank.NINE) {
                setScoutsHighlightedBlocks(row, col);
                lastTappedRow=row;
                lastTappedCol=col;
                return true;
            //procedure for highlighting normal units movable squares
            } else {
                setHighLightedBlocks(row, col);
                lastTappedRow=row;
                lastTappedCol=col;
                return true;
            }
        //removes highlights if tapping on an empty or enemy square
        } else {
            removeHighlightedBlocks();
            lastTappedRow = EMPTY;
            lastTappedCol = EMPTY;
            return true;
        }
    }

    /**
     * movePiece method
     * helper method for tapSquare
     * allows players to move their piece during PLAY_PHASE
     * @param row1 original row of piece
     * @param col1 original col of piece
     * @param row2 row piece will be moved to
     * @param col2 col piece wants to be moved to
     * @return true once piece has been moved
     *         false if the coordinates are invalid
     */
    private boolean movePiece(int row1, int col1, int row2, int col2) {
        //moves the pieces to their according places
        board[row2][col2].setContainedPiece(board[row1][col1].getContainedPiece());
        board[row1][col1].setContainedPiece(null);
        return true;
    }

    /**
     * helper method for tapSquare
     * activates when two pieces collide on game board
     * @param row1 original row of piece
     * @param col1 original col of piece
     * @param row2 row piece will be moved to
     * @param col2 col piece will be moved to
     * @return true if attacker wins
     *         false if defender wins
     */
    private boolean attackPiece(int row1, int col1, int row2, int col2) {
        //ends the game if a flag is attacked
        if(board[row2][col2].getContainedPiece().getPieceRank() == Rank.FLAG) {
            return attackFlag();
        }
        //runs appropriate procedure for hitting a bomb
        if(board[row2][col2].getContainedPiece().getPieceRank() == Rank.BOMB) {
            return attackBomb(row1, col1, row2, col2);
        }
        //runs appropriate procedure for when a spy attacks
        if(board[row1][col1].getContainedPiece().getPieceRank() == Rank.SPY) {
            return spyAttacks(row1, col1, row2, col2);
        }
        //allows scouts to make units visible
        if(board[row1][col1].getContainedPiece().getPieceRank() == Rank.NINE){
            return scoutAttacks(row1, col1, row2, col2);
        }
        //otherwise treats all other collisions as normal unit attacks
        return unitAttacks(row1, col1, row2, col2);
    }

    /**
     * helper method to attackPiece that handles general unit attacks
     *
     * @param row1 row of the attacker
     * @param col1 col of the attacker
     * @param row2 row of the defender
     * @param col2 col of the defender
     * @return true when pieces have been moved accordingly
     */
    private boolean unitAttacks(int row1, int col1, int row2, int col2) {
        //stores the attacking and defending pieces into variables
        Piece attacker = board[row1][col1].getContainedPiece();
        Piece defender = board[row2][col2].getContainedPiece();

        //if defender is a lower number/higher rank kill the attacker
        if(attacker.getPieceRank().ordinal() > defender.getPieceRank().ordinal()) {
            //removes piece from board and teams arraylist
            removePieceFromPlayer(currentTeamsTurn, attacker.getPieceRank());
            //sets the piece to be temporarily visible
            if(board[row1][col1].getContainedPiece().getPieceRank() != Rank.NINE) {
                board[row2][col2].getContainedPiece().setVisible(true);
                visiblePiece = board[row2][col2].getContainedPiece();
            }
            //removes attacker from the board
            board[row1][col1].setContainedPiece(null);
            return true;
        }
        //if defender is a higher number/lower rank kill the defender
        if(attacker.getPieceRank().ordinal() < defender.getPieceRank().ordinal()) {
            //removes defender from arraylist
            removePieceFromPlayer(getEnemyTeam(), defender.getPieceRank());
            //moves attacker to desired location
            lastKilledPiece = new MovablePiece(row2, col2, board[row1][col1].getContainedPiece().getPieceRank());
            return movePiece(row1, col1, row2, col2);
        }
        //if attacker and defender are equal rank kill both
        if(attacker.getPieceRank().ordinal() == defender.getPieceRank().ordinal()) {
            //kills attacker and removes from proper teams hand
            removePieceFromPlayer(currentTeamsTurn, attacker.getPieceRank());
            board[row1][col1].setContainedPiece(null);
            //kills defender and removes from proper teams hand
            removePieceFromPlayer(getEnemyTeam(), defender.getPieceRank());
            board[row2][col2].setContainedPiece(null);
            return true;
        }
        return true;
    }

    /**
     * method that ends the animation of a piece being temporarily being revealed
     */
    public void removeTemporaryVisiblePiece() {
        visiblePiece.setVisible(false);
        visiblePiece=null;
    }

    /**
     * helper method to attackPiece that handles scoutAttacks
     *
     * @param row1 row of the attacker
     * @param col1 col of the attacker
     * @param row2 row of the defender
     * @param col2 col of the defender
     * @return true once all pieces have been moved accordingly
     */
    private boolean scoutAttacks(int row1, int col1, int row2, int col2) {
        //sets defenders status to visible
        board[row2][col2].getContainedPiece().setVisible(true);
        //otherwise acts as normal piece
        return unitAttacks(row1, col1, row2, col2);
    }

    /**
     * helper method to attackPiece that handles spy attacks
     *
     * @param row1 row of the attacker
     * @param col1 col of the attacker
     * @param row2 row of the defender
     * @param col2 col of the defender
     * @return true once pieces have been moved accordingly
     */
    private boolean spyAttacks(int row1, int col1, int row2, int col2) {
        //if spy attacks Marshall, removes marshall from board
        if(board[row2][col2].getContainedPiece().getPieceRank() == Rank.ONE) {
            removePieceFromPlayer(getEnemyTeam(), board[row2][col2].getContainedPiece().getPieceRank());
            lastKilledPiece = new MovablePiece(row2, col2, Rank.SPY);
            return movePiece(row1, col1, row2, col2);
        }
        //else treat spy like normal piece
        return unitAttacks(row1, col1, row2, col2);
    }

    /**
     * helper method for attackPiece that handles attacks involving bombs
     *
     * @param row1 row of the attacker
     * @param col1 col of the attacker
     * @param row2 row of the defender
     * @param col2 col of the defender
     * @return true once pieces have been moved accordingly
     */
    private boolean attackBomb(int row1, int col1, int row2, int col2) {
        //removes the bomb from the battlefield
        removePieceFromPlayer(this.getEnemyTeam(), board[row2][col2].getContainedPiece().getPieceRank());
        //if attacker is a miner, moves it to its desired location
        if(board[row1][col1].getContainedPiece().getPieceRank() == Rank.EIGHT) {
            return movePiece(row1, col1, row2, col2);
        }
        // removes attacker from battlefield and updates board
        else {
            removePieceFromPlayer(currentTeamsTurn, board[row1][col1].getContainedPiece().getPieceRank());
            board[row1][col1].setContainedPiece(null);
            return movePiece(row1, col1, row2, col2);
        }
    }

    /**
     * helper method for attackPiece that shows what happens when a flag is attacked
     *
     * @return true once game has ended
     */
    private boolean attackFlag() {
        //sets the proper teams hasFlag method to false and ends the game
        if(currentTeamsTurn == Team.RED_TEAM) {
            blueTeamHasFlag = false;
        } else {
            redTeamHasFlag = false;
        }
        return true;
    }

    /**
     * isGameOver method
     * checks if the game is over
     * @return true if either player has lost their flags
     *         false if both players have their flags
     */
    public int isGameOver() {
        //if either team has lost their flag return true
        if(redTeamHasFlag == false) {
            return -1;
        } else if (blueTeamHasFlag == false) {
            return 1;
        }
        //else return false
        return 0;
    }

    /**
     * helper method for tapOnSquarePLAY
     * method that highlights the blocks a normal unit could move
     *
     * @param row the row of the tap
     * @param col the col of the tap
     * @return true once all possible blocks have been highlighted
     */
    private boolean setHighLightedBlocks(int row, int col) {
        this.removeHighlightedBlocks();
        //highlights the spot above the tap if possible
        if(row != ROWMIN && board[row-1][col].isBlockHighlightable(currentTeamsTurn)) {
            board[row-1][col].setHighLighted(true);
        }
        //highlights the spot below the tap if possible
        if(row != ROWMAX-1 && board[row+1][col].isBlockHighlightable(currentTeamsTurn)) {
            board[row+1][col].setHighLighted(true);
        }
        //highlights the spot to the left of the tap if possible
        if(col != COLMIN && board[row][col-1].isBlockHighlightable(currentTeamsTurn)) {
            board[row][col-1].setHighLighted(true);
        }
        //highlights the spot to the right of the tap if possible
        if(col != COLMAX-1 && board[row][col+1].isBlockHighlightable(currentTeamsTurn)) {
            board[row][col+1].setHighLighted(true);
        }
        return true;
    }

    /**
     * helper method for tapOnSquarePLAY
     * highlights all the possible spots a scout could move
     *
     * @param row the row of the tapped piece
     * @param col the column of the tapped piece
     * @return true once all proper pieces have been highlighted
     */
    private boolean setScoutsHighlightedBlocks(int row, int col) {
        this.removeHighlightedBlocks();
        //checks the spots above the tapped piece
        for(int rowToCheck = row-1; rowToCheck >= 0; rowToCheck--) {
            if(!board[rowToCheck][col].isBlockHighlightable(currentTeamsTurn)) {
                break;
            }
            board[rowToCheck][col].setHighLighted(true);
            if(board[rowToCheck][col].doesEnemyOccupyThis(currentTeamsTurn.getTEAMNUMBER())) {
                break;
            }
        }
        //checks the spots below a tapped piece
        for(int rowToCheck = row+1; rowToCheck < ROWMAX; rowToCheck++) {
            if(!board[rowToCheck][col].isBlockHighlightable(currentTeamsTurn)) {
                break;
            }
            board[rowToCheck][col].setHighLighted(true);
            if(board[rowToCheck][col].doesEnemyOccupyThis(currentTeamsTurn.getTEAMNUMBER())) {
                break;
            }
        }
        //checks the spots to the left of a tapped piece
        for(int colToCheck = col-1; colToCheck >= 0; colToCheck--) {
            if(!board[row][colToCheck].isBlockHighlightable(currentTeamsTurn)) {
                break;
            }
            board[row][colToCheck].setHighLighted(true);
            if(board[row][colToCheck].doesEnemyOccupyThis(currentTeamsTurn.getTEAMNUMBER())) {
                break;
            }
        }
        //checks the spots to the right of a tapped piece
        for(int colToCheck = col+1; colToCheck < COLMAX; colToCheck++) {
            if(!board[row][colToCheck].isBlockHighlightable(currentTeamsTurn)) {
                break;
            }
            board[row][colToCheck].setHighLighted(true);
            if(board[row][colToCheck].doesEnemyOccupyThis(currentTeamsTurn.getTEAMNUMBER())) {
                break;
            }
        }
        return true;
    }


    /**
     * helper method for tapOnSquarePLAY
     * method that removes all highlighted spots from the board
     *
     * @return true when all highlighted spots are removed
     */
    private boolean removeHighlightedBlocks() {
        //goes through every piece and sets highlighted to false
        for(int row = ROWMIN; row < ROWMAX; row++) {
            for(int col = COLMIN; col < COLMAX; col++) {
                if(board[row][col].isHighLighted()) {
                    board[row][col].setHighLighted(false);
                }
            }
        }
        return true;
    }

    /**-------------------------------------------------------------------------------------------*/

    /**-----------------------------------General Methods-----------------------------------------*/

    /**
     * hub mehthod for all square taps
     *
     * @param row row of the tap
     * @param col col of the tap
     * @return true once tap has been handled
     */
    public boolean tapOnSquare(int row, int col) {
        //if its setup phase redirect to setup helper method
        if(this.getCurrentPhase() == Phase.SETUP_PHASE) {
            tapOnSquareSETUP(row, col);
        }
        //otherwise redirect to the play phase helper method
        else {
            tapOnSquarePLAY(row, col);
        }
        return true;
    }

    /**
     * helper method for addPieceToGame
     * helper method that allows one to easily add pieces to players pieces
     *
     * @param targetTeam team that wishes to add a piece
     * @param targetRank rank that the player wishes to add
     * @return true once piece has been added
     */
    private boolean addPieceToPlayer(Team targetTeam, Rank targetRank) {
        //adds piece to the given teams's arraylist of pieces
        if(targetTeam == Team.RED_TEAM) {
            redTeamPieces.add(targetRank);
        } else {
            blueTeamPieces.add(targetRank);
        }
        return true;
    }

    /**
     * helperMethod for attackPiece
     * method that removes a desired piece from a players hand
     *
     * @param targetTeam team from which a piece will be removed
     * @param targetRank rank that they wish to be removed
     * @return true once piece has been removed
     */
    private boolean removePieceFromPlayer(Team targetTeam, Rank targetRank) {
        //removes piece from the given team's arraylist of pieces
        if(targetTeam == Team.RED_TEAM) {
            redTeamPieces.remove(targetRank);
        } else {
            blueTeamPieces.remove(targetRank);
        }
        return true;
    }

    /**
     * helper method for addPieceToGame
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
        ArrayList<Rank> currentTeamsPieces;

        //sets the current teams pieces according to the current team
        if(desiredPlayer == Team.RED_TEAM) {
            currentTeamsPieces = this.getRedTeamPieces();
        } else {
            currentTeamsPieces = this.getBlueTeamPieces();
        }

        //counts the amount of pieces matching the desired rank
        for(Rank r: currentTeamsPieces) {
            if(r == desiredRank) {
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
        //sets the appropriate teams hasFlag variable to false
        if(currentTeamsTurn == Team.RED_TEAM) {
            redTeamHasFlag = false;
        }
        else {
            blueTeamHasFlag = false;
        }
        //runs the procedure for ending the game
        isGameOver();
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

        //prints all Red Team's information
        //toReturn += "[Red Team's Timer: " + redTeamTimer + "]\n";
        toReturn += "Red Team's Pieces:\n";
        for(Rank r: redTeamPieces) {
            toReturn += r.toString() + "\n";
        }
        toReturn += "Red Team has Flag?: " + redTeamHasFlag + "\n";

        //prints all Blue Team's information
        //toReturn += "[Blue Team's Timer: " + blueTeamTimer + "]\n";
        toReturn += "Blue Team's Pieces:\n";
        for(Rank r: blueTeamPieces) {
            toReturn += r.toString() + "\n";
        }
        toReturn += "Blue Team has Flag?: " + blueTeamHasFlag + "\n";

        toReturn += "[Current Phase: " + currentPhase + "]\n";

        //prints whose turn it is based on currentPlayer variable
        if(currentTeamsTurn == Team.RED_TEAM){
            toReturn += "Red Team's Turn\n";
        }
        else{
            toReturn += "Blue Team's turn\n";
        }

        toReturn += lastTappedRow + "\n";
        toReturn += lastTappedCol + "\n";

        //prints whats stored in each board block
        toReturn += "------------------------\n";
        for(int i=ROWMIN; i<ROWMAX; i++) {
            for(int j=COLMIN; j<COLMAX; j++) {
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
