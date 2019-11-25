/**
 * allows the computers to store the movable pieces in arrays
 *
 * @author John Haas
 */
package com.example.myapplication.Stratego.GameState;

public class MovablePiece {

    private int x;
    private int y;
    private Rank pieceRank;

    /**
     * constructor for objects of the movable piece class
     *
     * @param knownX x location of the piece
     * @param knownY y location of the piece
     * @param knownRank rank of the piece
     */
    public MovablePiece(int knownX, int knownY, Rank knownRank) {
        x=knownX;
        y=knownY;
        pieceRank=knownRank;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rank getPieceRank() {
        return pieceRank;
    }

    public void setPieceRank(Rank pieceRank) {
        this.pieceRank = pieceRank;
    }
}
