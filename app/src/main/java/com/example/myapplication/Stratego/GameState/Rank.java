/**
 * Enum that will store a piece's rank
 *
 * @author John Haas
 */

package com.example.myapplication.Stratego.GameState;
import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * External Citation
 * Date Writing: 13 October 2019
 * Person Writing: John Haas
 * Problem: I was confused as how to add variables and methods to enums
 *
 * Resource: https://www.geeksforgeeks.org/enum-in-java/
 * Solution: I referenced a lot of the starter code in this webpage
 */
public enum Rank implements Serializable {
    ONE(1) { //0
        @Override
        public String toString() { return "One"; }
    },
    TWO(1) { //1
        @Override
        public String toString() {
            return "Two";
        }
    },
    THREE(2) { //2
        @Override
        public String toString() {
            return "Three";
        }
    },
    FOUR(3) { //3
        @Override
        public String toString() {
            return "Four";
        }
    },
    FIVE(4) { //4
        @Override
        public String toString() {
            return "Five";
        }
    },
    SIX(4) { //5
        @Override
        public String toString() {
            return "Six";
        }
    },
    SEVEN(4) { //6
        public String toString() {
            return "Seven";
        }
    },
    EIGHT(5) { //7
        @Override
        public String toString() {
            return "Eight";
        }
    },
    NINE(8) { //8
        @Override
        public String toString() {
            return "Nine";
        }
    },
    SPY(1) { //9
        @Override
        public String toString() {
            return "Spy";
        }
    },
    BOMB(6) { //19
        @Override
        public String toString() {
            return "Bomb";
        }
    },
    FLAG(1) {
        @Override
        public String toString() {
            return "Flag";
        }
    };

    private final int maxAmountOfPieces;
    private Rank(int maxAmount) {
        maxAmountOfPieces=maxAmount;
    }

    public int getMaxAmountOfPieces() {
        return maxAmountOfPieces;
    }

    public boolean isPieceMovable() {return !(this.toString().equals("Bomb") || this.toString().equals("Flag"));}
};
