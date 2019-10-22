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
    private final int COLMAXINDEX=9;

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

    //used for making moves and attacks
    private int lastTappedPieceX;
    private int lastTappedPieceY;

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
                if(i != 4 && i !=5) {
                    board[i][j] = new Block(Tile.GRASS);
                    continue;
                }
                if(j != 2 && j != 3 && j != 6 && j != 7) {
                    board[i][j] = new Block(Tile.WATER);
                    continue;
                }
                board[i][j] = new Block(Tile.BRIDGE);
            }
        }

        lastTappedPieceX = -1;
        lastTappedPieceY = -1;
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
        redTeamPieces= new ArrayList<Piece>();
        for(Piece p: trueState.redTeamPieces) {
            this.redTeamPieces.add(new Piece(p));
        }

        //sees whether player one has won yet
        this.redTeamHasFlag = trueState.redTeamHasFlag;

        //copies player two's information
        this.blueTeamID = trueState.blueTeamID;
        this.blueTeamTimer = trueState.blueTeamTimer;

        //copies player two's pieces
        blueTeamPieces= new ArrayList<Piece>();
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

        this.lastTappedPieceX = trueState.lastTappedPieceX;
        this.lastTappedPieceY = trueState.lastTappedPieceY;
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
    public Team getCurrentTeamsTurn() { return currentTeamsTurn; }

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
     * @param row the desired row of the placed piece
     * @param col the desired col of the placed piece
     * @return true if piece is set at the desired location
     *         false if the piece cannot be placed at the desired location
     */
    public boolean addPieceToGame(Piece placedPiece, int row, int col) {
        //makes sure x is a legal value
        if(col < COLMININDEX || col > COLMAXINDEX ) {
            return false;
        }
        //makes sure y is a legal value
        if ( row < placedPiece.getPieceTeam().getTOPBOUNDARYINDEX()
                || row > placedPiece.getPieceTeam().getBOTTOMBOUNDARYINDEX() ) {
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
        if(board[row][col].containsPiece()) {
            return false;
        }

        //sets the piece to the desired place
        board[row][col].setContainedPiece(placedPiece);
        this.addPieceToPlayer(currentTeamsTurn, placedPiece);

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
        this.removePieceFromPlayer(currentTeamsTurn, board[y][x].getContainedPiece());
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
     * overall method that reacts to interaction with blocks
     *
     * @param x the row index of the board where the tap was registered
     * @param y the col index of the board where the tap was registered
     * @return true once information is handled appropriately
     *         false if the given information is unusable
     */
    public boolean tapOnSquare(int x, int y) {
        //makes sure the coordinates are valid
        if(x < 0 || x > COLMAX) {
            return false;
        } else if (y < 0 || y > ROWMAX) {
            return false;
        }

        //procedure for attacking piece
        if(board[y][x].isHighLighted() && board[y][x].containsPiece()) {
            attackPiece(lastTappedPieceX, x, lastTappedPieceY, y);
            removeHighlightedBlocks();
            lastTappedPieceX= -1;
            lastTappedPieceY= -1;
            transitionTurns();
        //procedure for moving piece
        } else if (board[y][x].isHighLighted()) {
            movePiece(lastTappedPieceX, x, lastTappedPieceY, y);
            //updates data
            removeHighlightedBlocks();
            lastTappedPieceX= -1;
            lastTappedPieceY= -1;
            //ends the currentPlayers turn
            transitionTurns();
        //procedure for highlighting pieces
        } else if (board[y][x].containsPiece() &&
                board[y][x].getContainedPiece().getPieceTeam() == currentTeamsTurn) {
            //does not highlight movable spots for bomb and flag
            if(board[y][x].getContainedPiece().getPieceRank() == Rank.BOMB ||
                board[y][x].getContainedPiece().getPieceRank() == Rank.FLAG) {

                removeHighlightedBlocks();
                lastTappedPieceX= -1;
                lastTappedPieceY= -1;
            //procedure for highlighting scouts movable squares
            } else if(board[y][x].getContainedPiece().getPieceRank() == Rank.NINE) {
                setScoutsHighlightedBlocks(x, y);
                lastTappedPieceX=x;
                lastTappedPieceY=y;
            //procedure for highlighting normal units movable squares
            } else {
                setHighLightedBlocks(x, y);
                lastTappedPieceX=x;
                lastTappedPieceY=y;
            }
        //removes highlights if tapping on an empty or enemy square
        } else {
            removeHighlightedBlocks();
            lastTappedPieceX= -1;
            lastTappedPieceY= -1;
        }
        return true;
    }

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
        //moves the pieces to their according places
        board[y2][x2].setContainedPiece(board[y1][x1].getContainedPiece());
        board[y1][x1].setContainedPiece(null);
        return true;
    }

    /**
     * helper method for movePiece that triggers
     * when two pieces collide on game board
     * @param x1 original x-coordinate of piece
     * @param x2 x-coordinate piece wants to be moved to
     * @param y1 original y-coordinate of piece
     * @param y2 y-coordinate piece wants to be moved to
     * @return true if attacker wins
     *         false if defender wins
     */
    private boolean attackPiece(int x1, int x2, int y1, int y2) {
        if(board[y2][x2].getContainedPiece().getPieceRank() == Rank.FLAG) {
            return attackFlag();
        }
        if(board[y2][x2].getContainedPiece().getPieceRank() == Rank.BOMB) {
            return attackBomb(x1, x2, y1, y2);
        }
        if(board[y1][x1].getContainedPiece().getPieceRank() == Rank.SPY) {
            return spyAttacks(x1, x2, y1, y2);
        }
        if(board[y1][x1].getContainedPiece().getPieceRank() == Rank.NINE){
            return scoutAttacks(x1, x2, y1, y2);
        }
        return unitAttacks(x1, x2, y1, y2);
    }

    /**
     * helper method to attackPiece that handles general unit attacks
     *
     * @param x1 x coordinate of the attacker
     * @param x2 x coordinate of the defender
     * @param y1 y coordinate of the attacker
     * @param y2 y coordiante of the defender
     * @return true when pieces have been moved accordingly
     */
    private boolean unitAttacks(int x1, int x2, int y1, int y2) {
        //stores the attacking and defending pieces into variables
        Piece attacker = board[y1][x1].getContainedPiece();
        Piece defender = board[y2][x2].getContainedPiece();
        //if defender is a lower number/higher rank kill the attacker
        if(attacker.getPieceRank().ordinal() > defender.getPieceRank().ordinal()) {
            //removes piece from board and teams arraylist
            removePieceFromPlayer(currentTeamsTurn, attacker);
            board[y1][x1].setContainedPiece(null);
            return true;
        }
        //if defender is a higher number/lower rank kill the defender
        if(attacker.getPieceRank().ordinal() < defender.getPieceRank().ordinal()) {
            //removes defender from arraylist
            removePieceFromPlayer(getEnemyTeam(), defender);
            //moves attacker to desired location
            return movePiece(x1, x2, y1, y2);
        }
        //if attacker and defender are equal rank kill both
        if(attacker.getPieceRank().ordinal() == defender.getPieceRank().ordinal()) {
            //kills attacker and removes from proper teams hand
            removePieceFromPlayer(currentTeamsTurn, attacker);
            board[y1][x1].setContainedPiece(null);
            //kills defender and removes from proper teams hand
            removePieceFromPlayer(getEnemyTeam(), defender);
            board[y2][x2].setContainedPiece(null);
            return true;
        }
        return true;
    }

    /**
     * helper method to attackPiece that handles scoutAttacks
     *
     * @param x1 x coordinate of the attacker
     * @param x2 x coordinate of the defender
     * @param y1 y coordinate of the attacker
     * @param y2 y coordinate of the defender
     * @return true once all pieces have been moved accordingly
     */
    private boolean scoutAttacks(int x1, int x2, int y1, int y2) {
        //sets defenders status to visible
        board[y2][x2].getContainedPiece().setVisible(true);
        //otherwise acts as normal piece
        return unitAttacks(x1, x2, y1, y2);
    }

    /**
     * helper method to attackPiece that handles spy attacks
     *
     * @param x1 x coordinate of the attacker
     * @param x2 x coordinate of the defender
     * @param y1 y coordinate of the attacker
     * @param y2 y coordinate of the defender
     * @return true once pieces have been moved accordingly
     */
    private boolean spyAttacks(int x1, int x2, int y1, int y2) {
        //if spy attacks Marshall, removes marshall from board
        if(board[y2][x2].getContainedPiece().getPieceRank() == Rank.ONE) {
            removePieceFromPlayer(getEnemyTeam(), board[y2][x2].getContainedPiece());
            return movePiece(x1, x2, y1, y2);
        }
        //else treat spy like normal piece
        return unitAttacks(x1, x2, y1, y2);
    }

    /**
     * helper method for attackPiece that handles attacks involving bombs
     *
     * @param x1 x coordinate of the attacker
     * @param x2 x coordinate of the defender
     * @param y1 y coordinate of the attacker
     * @param y2 y coordinate of the defender
     * @return true once pieces have been moved accordingly
     */
    private boolean attackBomb(int x1, int x2, int y1, int y2) {
        //removes the bomb from the battlefield
        removePieceFromPlayer(this.getEnemyTeam(), board[y2][x2].getContainedPiece());
        //if attacker is a miner, moves it to its desired location
        if(board[y1][x1].getContainedPiece().getPieceRank() == Rank.EIGHT) {
            return movePiece(x1, x2, y1, y2);
        }
        // removes attacker from battlefield and updates board
        else {
            removePieceFromPlayer(currentTeamsTurn, board[y1][x1].getContainedPiece());
            board[y1][x1].setContainedPiece(null);
            return movePiece(x1, x2, y1, y2);
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
        return isGameOver();
    }

    /**
     * isGameOver method
     * checks if the game is over
     * @return true if either player has lost their flags
     *         false if both players have their flags
     */
    public boolean isGameOver() {
        //if either team has lost their flag return true
        if(redTeamHasFlag == false || blueTeamHasFlag == false) {
            return true;
        }
        //else return false
        return false;
    }

    /**
     * method that highlights the blocks a normal unit could move
     *
     * @param x the x coordinate of the tap
     * @param y the y coordinate of the tap
     * @return true once all possible blocks have been highlighted
     */
    private boolean setHighLightedBlocks(int x, int y) {
        //highlights the spot above the tap if possible
        if(y != 0 && board[y-1][x].isBlockHighlightable(currentTeamsTurn)) {
            board[y-1][x].setHighLighted(true);
        }
        //highlights the spot below the tap if possible
        if(y != ROWMAX-1 && board[y+1][x].isBlockHighlightable(currentTeamsTurn)) {
            board[y+1][x].setHighLighted(true);
        }
        //highlights the spot to the left of the tap if possible
        if(x != 0 && board[y][x-1].isBlockHighlightable(currentTeamsTurn)) {
            board[y][x-1].setHighLighted(true);
        }
        //highlights the spot to the right of the tap if possible
        if(x != COLMAX-1 && board[y][x+1].isBlockHighlightable(currentTeamsTurn)) {
            board[y][x+1].setHighLighted(true);
        }
        return true;
    }

    /**
     * highlights all the possible spots a scout could move
     *
     * @param x the x coordinate of the tapped piece
     * @param y the y coordinate of the tapped piece
     * @return true once all proper pieces have been highlighted
     */
    private boolean setScoutsHighlightedBlocks(int x, int y) {
        //checks the spots above the tapped piece
        for(int row = y-1; row >= 0; row--) {
            if(!board[row][x].isBlockHighlightable(currentTeamsTurn)) {
                break;
            }
            board[row][x].setHighLighted(true);
        }
        //checks the spots below a tapped piece
        for(int row = y+1; row < ROWMAX; row++) {
            if(!board[row][x].isBlockHighlightable(currentTeamsTurn)) {
                break;
            }
            board[row][x].setHighLighted(true);
        }
        //checks the spots to the left of a tapped piece
        for(int col = x-1; col >= 0; col--) {
            if(!board[y][col].isBlockHighlightable(currentTeamsTurn)) {
                break;
            }
            board[y][col].setHighLighted(true);
        }
        //checks the spots to the right of a tapped piece
        for(int col = x+1; col < COLMAX; col++) {
            if(!board[y][col].isBlockHighlightable(currentTeamsTurn)) {
                break;
            }
            board[y][col].setHighLighted(true);
        }
        return true;
    }


    /**
     * method that removes all highlighted spots from the board
     *
     * @return true when all highlighted spots are removed
     */
    private boolean removeHighlightedBlocks() {
        //goes through every piece and sets highlighted to false
        for(int row = 0; row < ROWMAX; row++) {
            for(int col = 0; col < COLMAX; col++) {
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
     * helper method that allows one to easily add pieces to players pieces
     *
     * @param targetTeam team that wishes to add a piece
     * @param targetPiece piece that the player wishes to add
     * @return true once piece has been added
     */
    private boolean addPieceToPlayer(Team targetTeam, Piece targetPiece) {
        //adds piece to the given teams's arraylist of pieces
        if(targetTeam == Team.RED_TEAM) {
            redTeamPieces.add(targetPiece);
        } else {
            blueTeamPieces.add(targetPiece);
        }
        return true;
    }

    /**
     * method that removes a desired piece from a players hand
     *
     * @param targetTeam team from which a piece will be removed
     * @param targetPiece piece that they wish to be removed
     * @return true once piece has been removed
     */
    private boolean removePieceFromPlayer(Team targetTeam, Piece targetPiece) {
        //removes piece from the given team's arraylist of pieces
        if(targetTeam == Team.RED_TEAM) {
            redTeamPieces.remove(targetPiece);
        } else {
            blueTeamPieces.remove(targetPiece);
        }
        return true;
    }

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
        if(currentTeamsTurn == Team.RED_TEAM) {
            redTeamHasFlag = false;
        }
        else {
            blueTeamHasFlag = false;
        }
        return isGameOver();
    }

    /**
     * method that prints class information as a String
     * @return toReturn: the String that contains all the gameState information
     */
    @Override
    public String toString(){
        //prints all the game state information
        String toReturn = "\nStratego Game State:\n";

        toReturn += "[Red Team's id: " + redTeamID + "]\n";
        toReturn += "[Red Team's Timer: " + redTeamTimer + "]\n";
        toReturn += "Red Team's Pieces:\n";
        for(Piece p: redTeamPieces) {
            toReturn += p.toString();
        }

        toReturn += "[Blue Team's ID: " + blueTeamID + "]\n";
        toReturn += "[Blue Team's Timer: " + blueTeamTimer + "]\n";
        toReturn += "Red Team's Pieces:\n";
        for(Piece p: blueTeamPieces) {
            toReturn += p.toString();
        }

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
