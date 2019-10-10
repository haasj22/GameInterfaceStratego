package com.example.myapplication.Stratego.GameState;

public class Block {

    Tile blockType;
    private Piece containedPiece;
    private boolean isHighLighted;


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
