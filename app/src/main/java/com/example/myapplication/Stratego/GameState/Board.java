package com.example.myapplication.Stratego.GameState;

/**
 * Holds information on pieces and their values
 *
 * @author  Kavya Mandla
 * @author John Haas
 * @author Jordan Ho
 * @version October 2019
 */

public class Board {
    int pieceRank;
    int strategoPieces ;
    int androidId;
    boolean piecePlayed;

    /**
     * Generic constructor for Tile class
     */
    public Board()
    {
        pieceRank = -1; //to avoid confusion with blank tiles, which have 0 value
        strategoPieces = -1; //to avoid confusion with blank tiles, which have ' ' value
        androidId = 0;
        piecePlayed = false;
    }

    /**
     * Constructor for Tile class
     */
    public Board(int rank, char pieceName, int id, boolean played)
    {
        pieceRank = rank;
        strategoPieces = pieceName;
        androidId = id;
        piecePlayed = played;


    }

    /**
     * Copy Constructor
     */
    public Board(Board gamePiece) {
        pieceRank = gamePiece.pieceRank;
        strategoPieces = gamePiece.strategoPieces;
        androidId = gamePiece.androidId;
        piecePlayed = gamePiece.piecePlayed;
    }

}





