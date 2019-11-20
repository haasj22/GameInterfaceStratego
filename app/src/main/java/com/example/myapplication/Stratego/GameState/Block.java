/**
 * class for objects of the block class
 *
 * @author John Haas
 */

package com.example.myapplication.Stratego.GameState;


public class Block {

    //instance variables
    private Tile blockType;
    private Piece containedPiece;
    private boolean isHighLighted;

    /**
     * constructor for objects of the Block class
     *
     * @param conPiece the piece that will be contained in the block
     * @param typeOfLand type of block this block will be
     */
    public Block(Piece conPiece, Tile typeOfLand) {
        blockType=typeOfLand;
        containedPiece=conPiece;
        isHighLighted=false; //all blocks start out unhighlighted
    }

    /**
     * secondary constructor for objects of the Block class that start out empty
     *
     * @param typeOfLand type of block this block will be
     */
    public Block(Tile typeOfLand) {
        blockType=typeOfLand;
        containedPiece=null; //empty block
        isHighLighted=false; //all blocks start unhighlighted
    }


    /**
     * copy constructor for objects of the block class
     *
     * @param trueBlock the block that  contains the true data
     */
    public Block(Block trueBlock) {
        //if block is empty creates empty block
        if(trueBlock == null) {
            this.blockType=null;
            this.containedPiece = null;
            this.isHighLighted = false;
        }
        //else copies everything over
        this.blockType=trueBlock.blockType;
        if(trueBlock.containedPiece != null) {
            this.containedPiece = new Piece(trueBlock.containedPiece);
        } else {
            this.containedPiece = null;
        }
        this.isHighLighted=trueBlock.isHighLighted;
    }

    /**------------------------------------Getter Methods-----------------------------------------*/

    public Tile getBlockType() { return blockType; }
    public Piece getContainedPiece() {
        return containedPiece;
    }
    public boolean isHighLighted() { return isHighLighted; }

    /**
     * method that checks if a block is empty or not
     *
     * @return true if block contains a piece
     *         false if block is empty
     */
    public boolean containsPiece() {
        return !(this.containedPiece == null);
    }

    /**-----------------------------------Setter Methods------------------------------------------*/

    public void setContainedPiece(Piece containedPiece) {
        this.containedPiece = containedPiece;
    }

    public void setHighLighted(boolean highLighted) {
        isHighLighted = highLighted;
    }

    /**-----------------------------------Generic Methods----------------------------------------*/

    /**
     * method that checks if a given block is highlightable
     *
     * @param currentTeamsTurn the team whos turn it currently is
     * @return true if block is highlightable
     *         false if block is not highlightable
     */
    public boolean isBlockHighlightable(Team currentTeamsTurn) {
        //pieces can't walk on water. Jesus rank not implemented yet
        if(this.getBlockType() == Tile.WATER) {
            return false;
        }
        //pieces can't move onto fellow teammates squares
        if (this.containsPiece() &&
                this.getContainedPiece().getPieceTeam() == currentTeamsTurn){
            return false;
        }
        //pieces can always move elsewhere
        return true;
    }

    /**
     * returns whether the given player could move to this block
     *
     * @param playNum the player that wants to move to this block
     * @return true if the player could move here
     *         false if the player can not
     */
    public boolean canOneMoveHere(int playNum) {
        return (this.getContainedPiece() == null && this.getBlockType() != Tile.WATER)
                || this.getContainedPiece() != null
                && this.getContainedPiece().getPieceTeam().getTEAMNUMBER() != playNum;
    }

    /**
     * returns whether the given player could move the piece contained in this block
     *
     * @param playNum the player that wants to move the piece
     * @return true if the player can move the piece
     *         false if the piece does not exist or is not of the desired team
     */
    public boolean canOneMoveThis(int playNum) {
        return this.getContainedPiece() != null
                && this.getContainedPiece().getPieceTeam().getTEAMNUMBER() == playNum
                && this.getContainedPiece().getPieceRank().isPieceMovable();
    }

    /**
     * returns whether if the block is occupied by an enemy piece
     *
     * @param playNum the player that is not the enemy
     * @return true if a player other than the current player owns the piece
     *         false if the block contains no piece or if the piece is not the enemies
     */
    public boolean doesEnemyOccupyThis(int playNum) {
        return this.getContainedPiece() != null
                && this.getContainedPiece().getPieceTeam().getTEAMNUMBER() != playNum;
    }

    /**
     * to String method for objects of the Block class
     *
     * @return toReturn: String that will
     * contain all the information about the block
     */
    public String toString() {
        String toReturn="Block Info\n";
        toReturn += "[Block Type: " + blockType + "]\n";
        toReturn += "----------------------\n";
        toReturn += containedPiece + "\n"; //prints the contained piece
        toReturn += "----------------------\n";
        toReturn += "Is Piece Highlighted: " + isHighLighted + "\n\n";

        return toReturn;
    }
}
