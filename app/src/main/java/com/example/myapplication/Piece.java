package com.example.myapplication;

public class Piece {
    enum Team {
        RED_TEAM,
        BLUE_TEAM
    };

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

    Team pieceTeam;
    Rank pieceRank;
    boolean isVisible;

    public Piece(Team pTeam, Rank pRank) {
        pieceTeam = pTeam;
        pieceRank = pRank;
        isVisible = false;
    }
}
