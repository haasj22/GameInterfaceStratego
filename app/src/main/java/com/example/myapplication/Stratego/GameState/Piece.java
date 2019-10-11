package com.example.myapplication.Stratego.GameState;

/**
 *  @author Kavya Mandla
 *  @author John Haas
 *  @author Jordan Ho
 *
 */

public class Piece {

    enum Rank {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIZ,
        SEVEN,
        EIGHT,
        NINE,
        SPY,
        BOMB,
        FLAG
    };

    private Team pieceTeam;
    private Rank pieceRank;
    private boolean isVisible;


    public Piece(Team pTeam, Rank pRank) {
        pieceTeam = pTeam;
        pieceRank = pRank;
        isVisible = false;
    }


    public String toString() {
        String toReturn = "Piece Information\n";
        toReturn += "[Pieces Team: " + pieceTeam + "]\n";
        toReturn += "[Pieces Rank: " + pieceRank + "]\n";
        toReturn += "[Is Piece Visible: " + isVisible + "]\n";

        return toReturn;
    }
}
