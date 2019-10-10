package com.example.myapplication.Stratego.GameState;

/**
 * @author Kavya Mandla
 * @author John Haas
 * @author Jordan Ho
 *
 * class for Block
 */

public class Block {

    private Tile blockType;
    private Piece containedPiece;
    private boolean isHighLighted;

    //TODO: make adjustments as necessary @JOHN
    public Block(){
        blockType = Tile.BRIDGE;
        containedPiece = ;
        isHighLighted = false;

    }

    public Block(Piece conPiece, Tile typeOfLand) {
        blockType=typeOfLand;
        containedPiece=conPiece;
        isHighLighted=false;
    }

    public Block(Tile typeOfLand) {
        blockType=typeOfLand;
        containedPiece=null;
        isHighLighted=false;
    }



    public String toString() {
        String toReturn="Block Info\n";
        toReturn += "[Block Type: " + blockType + "]\n";
        toReturn += "----------------------";
        toReturn += containedPiece;
        toReturn += "----------------------";
        toReturn += "Is Piece Highlighted: " + isHighLighted;

        return toReturn;
    }
}
