package com.example.myapplication.Stratego.GameState;

public class Block {
    enum Tile{
        WATER,
        GRASS,
        BRIDGE
    }

    private Piece containedPiece;
    private boolean isHighLighted;
    Tile blockType;

    public Block(Piece conPiece, Tile typeOfLand) {
        containedPiece=conPiece;
        isHighLighted=false;
        blockType=typeOfLand;
    }

    public Block(Tile typeOfLand) {
        containedPiece=null;
        isHighLighted=false;
        blockType=typeOfLand;
    }
}
